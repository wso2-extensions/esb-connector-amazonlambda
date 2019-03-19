/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.esb.connector.auth;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.constants.AmazonLambdaConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Class AmazonLambdaAuthConnector which helps to generate authentication header for Amazon Lambda WSO2 ESB Connector.
 */
public class AmazonLambdaAuthConnector extends AbstractConnector {

    private static Log log = LogFactory.getLog(AmazonLambdaAuthConnector.class);

    /**
     * Connect method which is generating authentication of the connector for each request.
     *
     * @param messageContext
     * @throws ConnectException
     */
    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        ParametersValueMap parametersValueMap = new ParametersValueMap(messageContext);

        final StringBuilder canonicalRequest = new StringBuilder();
        final StringBuilder stringToSign = new StringBuilder();
        final StringBuilder authHeader = new StringBuilder();

        final Map<String, String> headersParamsMap = new HashMap<>();
        final Map<String, String> queryParamsMap = new HashMap<>();
        final Map<String, String> payloadParamsMap = new HashMap<>();

        InnerPayloadParameterBuilder builder = new InnerPayloadParameterBuilder(messageContext);
        final String code = builder.getFunctionCode();
        final String deadLetterConfig = builder.getDeadLetterConfig();
        final String environment = builder.getEnvironment();
        final String tracingConfig = builder.getTracingConfig();
        final String vpcConfig = builder.getVpcConfig();
        final String routingConfig = builder.getRoutingConfig();

        /*
        Checks whether any of the inner payload parameter has value and if it has value,
         */
        if (StringUtils.isNotEmpty(code)) {
            payloadParamsMap.put("Code", code);
        }
        if (StringUtils.isNotEmpty(deadLetterConfig)) {
            payloadParamsMap.put("DeadLetterConfig", deadLetterConfig);
        }
        if (StringUtils.isNotEmpty(environment)) {
            payloadParamsMap.put("Environment", environment);
        }
        if (StringUtils.isNotEmpty(tracingConfig)) {
            payloadParamsMap.put("TracingConfig", tracingConfig);
        }
        if (StringUtils.isNotEmpty(vpcConfig)) {
            payloadParamsMap.put("VpcConfig", vpcConfig);
        }
        if (StringUtils.isNotEmpty(routingConfig)) {
            payloadParamsMap.put("RoutingConfig", routingConfig);
        }

        // Generates time-stamp which will be sent to API and to use in Signature generation.
        final Date date = new Date();
        final TimeZone timeZone = TimeZone.getTimeZone("GMT");
        final DateFormat dateFormat = new SimpleDateFormat(AmazonLambdaConstants.ISO8601_BASIC_DATE_FORMAT);
        dateFormat.setTimeZone(timeZone);
        final String amzDate = dateFormat.format(date);

        /**Setting amzDate to message context which can be later used by Connector to send it
         *as header to every API request.
         */
        messageContext.setProperty(AmazonLambdaConstants.X_AMZ_DATE, amzDate);

        //Generates date  in the yyyyMMdd.
        final DateFormat FORMAT_FOR_DATE_ONLY = new SimpleDateFormat(AmazonLambdaConstants.FORMAT_FOR_DATE_ONLY);
        FORMAT_FOR_DATE_ONLY.setTimeZone(timeZone);
        final String dateOnly = FORMAT_FOR_DATE_ONLY.format(date);

        final Map<String, String> queryParametersValueMap = parametersValueMap.getQueryValueHashMap();
        final Map<String, String> headerParametersValueMap = parametersValueMap.getHeadersValueHashMap();
        final Map<String, String> payloadParametersValueMap = parametersValueMap.getPayloadsValueHashMap();

        try {
            //Appending HTTP method.
            canonicalRequest.append(messageContext.getProperty(AmazonLambdaConstants.HTTP_METHOD))
                    .append(AmazonLambdaConstants.NEW_LINE);
            //Setting canonicalUri.
            String canonicalUri = (String) messageContext.getProperty(AmazonLambdaConstants.URI_REMAINDER);
            if (StringUtils.isNotEmpty(canonicalUri)) {
                canonicalRequest.append(canonicalUri.replaceAll
                        (AmazonLambdaConstants.TRIM_SPACE_REGEX, AmazonLambdaConstants.EMPTY_STR))
                        .append(AmazonLambdaConstants.NEW_LINE);
            } else {
                canonicalRequest.append(AmazonLambdaConstants.FORWARD_SLASH)
                        .append(AmazonLambdaConstants.NEW_LINE);
            }
            //Setting canonicalQueryString
            final StringBuilder canonicalQueryString = new StringBuilder();
            final Map<String, String> queryParametersMap = ParameterNamesMap.getQueryParameterNamesMap();
            for (Map.Entry<String, String> entry : queryParametersMap.entrySet()) {
                String key = entry.getKey();
                String tempParam = queryParametersValueMap.get(key);
                if (StringUtils.isNotEmpty((tempParam))) {
                    queryParamsMap.put(queryParametersMap.get(key), tempParam);
                }
            }
            final SortedSet<String> queryKeys = new TreeSet<>(queryParamsMap.keySet());
            for (String key : queryKeys) {
                String queryValue = queryParamsMap.get(key);
                canonicalQueryString.append(URLEncoder.encode(key, AmazonLambdaConstants.UTF_8))
                        .append(AmazonLambdaConstants.EQUAL)
                        .append(URLEncoder.encode(queryValue, AmazonLambdaConstants.UTF_8))
                        .append(AmazonLambdaConstants.AMPERSAND);
            }
            //Removes additional ampersand added at the end from canonicalQueryString and append to canonicalRequest.
            if (canonicalQueryString.length() > 0) {
                canonicalRequest.append(canonicalQueryString.substring(0, canonicalQueryString.length() - 1))
                        .append(AmazonLambdaConstants.NEW_LINE);
            } else {
                canonicalRequest.append(AmazonLambdaConstants.NEW_LINE);
            }
            //Setting canonicalHeader and signedHeader
            final Map<String, String> headerParametersMap = ParameterNamesMap.getHeaderParameterNamesMap();
            final StringBuilder canonicalHeader = new StringBuilder();
            final StringBuilder signedHeader = new StringBuilder();
            headersParamsMap.put(AmazonLambdaConstants.API_X_AMZ_DATE, amzDate);
            for (Map.Entry<String, String> entry : headerParametersMap.entrySet()) {
                String key = entry.getKey();
                String tempParam = headerParametersValueMap.get(key);
                if (StringUtils.isNotEmpty(tempParam)) {
                    headersParamsMap.put(headerParametersMap.get(key),
                            tempParam.replaceAll
                                    (AmazonLambdaConstants.TRIM_SPACE_REGEX, AmazonLambdaConstants.EMPTY_STR));
                }
            }
            final SortedSet<String> headerKeys = new TreeSet<>(headersParamsMap.keySet());
            for (String key : headerKeys) {
                String headerValues = headersParamsMap.get(key);
                canonicalHeader.append(key.toLowerCase()).append(AmazonLambdaConstants.COLON)
                        .append(headerValues).append(AmazonLambdaConstants.NEW_LINE);
                signedHeader.append(key.toLowerCase());
                signedHeader.append(AmazonLambdaConstants.SEMI_COLON);
            }
            //Appending canonicalHeader to canonicalRequest.
            canonicalRequest.append(canonicalHeader.toString());
            canonicalRequest.append(AmazonLambdaConstants.NEW_LINE);
            // Removes unwanted semi-colon at the end of the signedHeader string
            String signedHeaders = "";
            if (signedHeader.length() > 0) {
                signedHeaders = signedHeader.substring(0, signedHeader.length() - 1);
            }
            //Appending signedHeaders to canonicalRequest.
            canonicalRequest.append(signedHeaders);
            canonicalRequest.append(AmazonLambdaConstants.NEW_LINE);
            //Payload Building from the payload parameter and value given by user.
            String requestPayload = "";
            final Map<String, String> payloadParametersMap = ParameterNamesMap.getPayloadParameterNamesMap();
            final StringBuilder payloadBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : payloadParametersMap.entrySet()) {
                String key = entry.getKey();
                String tempParam = payloadParametersValueMap.get(key);
                if (StringUtils.isNotEmpty(tempParam)) {
                    payloadParamsMap.put(payloadParametersMap.get(key),
                            tempParam.replaceAll
                                    (AmazonLambdaConstants.TRIM_SPACE_REGEX, AmazonLambdaConstants.EMPTY_STR));
                }
            }
            final SortedSet<String> payloadKeys = new TreeSet<>(payloadParamsMap.keySet());
            if (payloadKeys.equals(AmazonLambdaConstants.PAYLOAD)) {
                payloadBuilder.append(payloadParamsMap.get(AmazonLambdaConstants.PAYLOAD));
            } else {
                for (String key : payloadKeys) {
                    String payloadValues = payloadParamsMap.get(key);
                    payloadBuilder.append('"');
                    payloadBuilder.append(key);
                    payloadBuilder.append('"');
                    payloadBuilder.append(':');
                    /**
                     * Checks for "{" and "[", to omit putting payload value inside quotation mark, which
                     * represents either the value has nested-payload-like parameter or is an array. In both
                     * case the value need not to be inside the quotation mark.
                     */
                    if (payloadValues.substring(0, 1).equals("{") || payloadValues.substring(0, 1).equals("[")) {
                        payloadBuilder.append(payloadValues)
                                .append(',');
                    }
                    /**
                     * Checks for the parameter which contains integer value. If it is such parameters then
                     * it directly put the value without putting inside quotation mark while building the payload.
                     */
                    else if (key.equals(AmazonLambdaConstants.API_PUBLISH) ||
                            key.equals(AmazonLambdaConstants.API_TIMEOUT)) {
                        payloadBuilder.append(payloadValues)
                                .append(',');
                    }
                    /**
                     *If both conditions mentioned above fails then the values should be inside the quotation mark.
                     * This condition does so.
                     */
                    else {
                        payloadBuilder.append('"');
                        payloadBuilder.append(payloadValues);
                        payloadBuilder.append('"');

                        payloadBuilder.append(',');
                    }
                }
            }
            /**
             * Checks the length of payload if it is greater than zero, meaning payload is not empty, it keeps all the
             * appended payload's parameters and values within the "{}".
             */
            if (payloadBuilder.length() > 0) {
                requestPayload = "{" + payloadBuilder.substring(0, payloadBuilder.length() - 1) + "}";
            }
            /**
             * Setting requestPayload to the message context which will be used by methods to send the payload while
             * making the API request.
             */
            messageContext.setProperty(AmazonLambdaConstants.REQUEST_PAYLOAD, requestPayload);

            //Hashing and making it lowercase hexadecimal string for appending to canonical request.
            canonicalRequest.append(bytesToHex(hash(messageContext, requestPayload)).toLowerCase());

            //Creates stringToSign
            stringToSign.append(AmazonLambdaConstants.AWS4_HMAC_SHA_256);
            stringToSign.append(AmazonLambdaConstants.NEW_LINE);
            stringToSign.append(amzDate);
            stringToSign.append(AmazonLambdaConstants.NEW_LINE);
            stringToSign.append(dateOnly);
            stringToSign.append(AmazonLambdaConstants.FORWARD_SLASH);
            stringToSign.append(messageContext.getProperty(AmazonLambdaConstants.REGION));
            stringToSign.append(AmazonLambdaConstants.FORWARD_SLASH);
            stringToSign.append(messageContext.getProperty(AmazonLambdaConstants.SERVICE));
            stringToSign.append(AmazonLambdaConstants.FORWARD_SLASH);
            stringToSign.append(messageContext.getProperty(AmazonLambdaConstants.TERMINATION_STRING));
            stringToSign.append(AmazonLambdaConstants.NEW_LINE);
            stringToSign.append(bytesToHex(hash(messageContext, canonicalRequest.toString())).toLowerCase());

            //Creates signingKey
            final byte[] signingKey =
                    getSignatureKey(messageContext,
                            messageContext.getProperty(AmazonLambdaConstants.SECRET_ACCESS_KEY).toString(),
                            dateOnly, messageContext.getProperty(AmazonLambdaConstants.REGION).toString(),
                            messageContext.getProperty(AmazonLambdaConstants.SERVICE).toString());

            // Construction of authorization header value to be included in API request
            authHeader.append(AmazonLambdaConstants.AWS4_HMAC_SHA_256);
            authHeader.append(" ");
            authHeader.append(AmazonLambdaConstants.CREDENTIAL);
            authHeader.append(AmazonLambdaConstants.EQUAL);
            authHeader.append(messageContext.getProperty(AmazonLambdaConstants.ACCESS_KEY_ID));
            authHeader.append(AmazonLambdaConstants.FORWARD_SLASH);
            authHeader.append(dateOnly);
            authHeader.append(AmazonLambdaConstants.FORWARD_SLASH);
            authHeader.append(messageContext.getProperty(AmazonLambdaConstants.REGION));
            authHeader.append(AmazonLambdaConstants.FORWARD_SLASH);
            authHeader.append(messageContext.getProperty(AmazonLambdaConstants.SERVICE));
            authHeader.append(AmazonLambdaConstants.FORWARD_SLASH);
            authHeader.append(messageContext.getProperty(AmazonLambdaConstants.TERMINATION_STRING));
            authHeader.append(AmazonLambdaConstants.COMMA);
            authHeader.append(AmazonLambdaConstants.SIGNED_HEADERS);
            authHeader.append(AmazonLambdaConstants.EQUAL);
            authHeader.append(signedHeaders);
            authHeader.append(AmazonLambdaConstants.COMMA);
            authHeader.append(AmazonLambdaConstants.API_SIGNATURE);
            authHeader.append(AmazonLambdaConstants.EQUAL);
            authHeader.append(bytesToHex(hmacSHA256(signingKey, stringToSign.toString())).toLowerCase());

            // Adds authorization header to message context
            messageContext.setProperty(AmazonLambdaConstants.AUTHORIZATION_HEADER, authHeader.toString());

        } catch (InvalidKeyException exc) {
            storeErrorResponseStatus(messageContext, exc, AmazonLambdaConstants.INVALID_KEY_ERROR_CODE);
            handleException(AmazonLambdaConstants.INVALID_KEY_ERROR, exc, messageContext);
        } catch (NoSuchAlgorithmException exc) {
            storeErrorResponseStatus(messageContext, exc, AmazonLambdaConstants.NO_SUCH_ALGORITHM_ERROR_CODE);
            handleException(AmazonLambdaConstants.NO_SUCH_ALGORITHM_ERROR, exc, messageContext);
        } catch (IllegalStateException exc) {
            storeErrorResponseStatus(messageContext, exc, AmazonLambdaConstants.ILLEGAL_STATE_ERROR_CODE);
            handleException(AmazonLambdaConstants.CONNECTOR_ERROR, exc, messageContext);
        } catch (UnsupportedEncodingException exc) {
            storeErrorResponseStatus(messageContext, exc, AmazonLambdaConstants.UNSUPPORTED_ENCODING_ERROR_CODE);
            handleException(AmazonLambdaConstants.CONNECTOR_ERROR, exc, messageContext);
        }
    }

    private void storeErrorResponseStatus(final MessageContext ctxt, final Throwable throwable, final int errorCode) {

        ctxt.setProperty(SynapseConstants.ERROR_CODE, errorCode);
        ctxt.setProperty(SynapseConstants.ERROR_MESSAGE, throwable.getMessage());
        ctxt.setFaultResponse(true);
    }

    private void storeErrorResponseStatus(final MessageContext ctxt, final String message, final int errorCode) {

        ctxt.setProperty(SynapseConstants.ERROR_CODE, errorCode);
        ctxt.setProperty(SynapseConstants.ERROR_MESSAGE, message);
        ctxt.setFaultResponse(true);
    }

    private byte[] hash(final MessageContext messageContext, final String text) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(AmazonLambdaConstants.SHA_256);
            messageDigest.update(text.getBytes(AmazonLambdaConstants.UTF_8));
        } catch (Exception exc) {
            storeErrorResponseStatus(messageContext, exc, AmazonLambdaConstants.ERROR_CODE_EXCEPTION);
            handleException(AmazonLambdaConstants.CONNECTOR_ERROR, exc, messageContext);
        }
        if (messageDigest == null) {
            log.error(AmazonLambdaConstants.CONNECTOR_ERROR);
            storeErrorResponseStatus(messageContext, AmazonLambdaConstants.CONNECTOR_ERROR,
                    AmazonLambdaConstants.ERROR_CODE_EXCEPTION);
            handleException(AmazonLambdaConstants.CONNECTOR_ERROR, messageContext);
        }
        if (messageDigest != null) {
            return messageDigest.digest();
        }
        return null;
    }

    /**
     * bytesToHex method HexEncoded the received byte array.
     *
     * @param bytes bytes to be hex encoded
     * @return hex encoded String of the given byte array
     */
    private static String bytesToHex(final byte[] bytes) {

        final char[] hexArray = AmazonLambdaConstants.HEX_ARRAY_STRING.toCharArray();
        char[] hexChars = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {
            final int byteVal = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[byteVal >>> 4];
            hexChars[j * 2 + 1] = hexArray[byteVal & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Provides the HMAC SHA 256 encoded value(using the provided key) of the given data.
     *
     * @param key  to use for encoding
     * @param data to be encoded
     * @return HMAC SHA 256 encoded byte array
     * @throws NoSuchAlgorithmException     No such algorithm Exception
     * @throws InvalidKeyException          Invalid key Exception
     * @throws UnsupportedEncodingException Unsupported Encoding Exception
     * @throws IllegalStateException        Illegal State Exception
     */
    private static byte[] hmacSHA256(final byte[] key, final String data)
            throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {

        final String algorithm = AmazonLambdaConstants.HAMC_SHA_256;
        final Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data.getBytes(AmazonLambdaConstants.UTF8));
    }

    /**
     * Returns the encoded signature key to be used for further encodings as per API doc.
     *
     * @param ctx         message context of the connector
     * @param key         key to be used for signing
     * @param dateStamp   current date stamp
     * @param regionName  region name given to the connector
     * @param serviceName Name of the service being addressed
     * @return Signature key
     * @throws UnsupportedEncodingException Unsupported Encoding Exception
     * @throws IllegalStateException        Illegal Argument Exception
     * @throws NoSuchAlgorithmException     No Such Algorithm Exception
     * @throws InvalidKeyException          Invalid Key Exception
     */
    private static byte[] getSignatureKey(final MessageContext ctx, final String key, final String dateStamp,
                                          final String regionName, final String serviceName)
            throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException {

        final byte[] kSecret = (AmazonLambdaConstants.AWS4 + key).getBytes(AmazonLambdaConstants.UTF8);
        final byte[] kDate = hmacSHA256(kSecret, dateStamp);
        final byte[] kRegion = hmacSHA256(kDate, regionName);
        final byte[] kService = hmacSHA256(kRegion, serviceName);
        return hmacSHA256(kService, ctx.getProperty(AmazonLambdaConstants.TERMINATION_STRING).toString());
    }
}
