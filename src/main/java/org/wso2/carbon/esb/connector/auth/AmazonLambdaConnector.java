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


public class AmazonLambdaConnector extends AbstractConnector {

    private static Log log = LogFactory.getLog(AmazonLambdaConnector.class);

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        GetParametersValueMap getParametersValueMap = new GetParametersValueMap(messageContext);

        final StringBuilder canonicalRequest = new StringBuilder();
        final StringBuilder stringToSign = new StringBuilder();
        final StringBuilder authHeader = new StringBuilder();

        final Map<String, String> headersParamsMap = new HashMap<>();
        final Map<String, String> queryParamsMap = new HashMap<>();
        final Map<String, String> payloadParamsMap = new HashMap<>();

        Builder builder = new Builder(messageContext);
        final String code = builder.getFunctionCode();
        final String deadLetterConfig = builder.getDeadLetterConfig();
        final String environment = builder.getEnvironment();
        final String tracingConfig = builder.getTracingConfig();
        final String vpcConfig = builder.getVpcConfig();
        final String routingConfig = builder.getRoutingConfig();


        if (code != null && !code.isEmpty()) {
            payloadParamsMap.put("Code", code);

        }
        if (deadLetterConfig != null && !deadLetterConfig.isEmpty()) {
            payloadParamsMap.put("DeadLetterConfig", deadLetterConfig);

        }
        if (environment != null && !environment.isEmpty()) {
            payloadParamsMap.put("Environment", environment);

        }
        if (tracingConfig != null && !tracingConfig.isEmpty()) {
            payloadParamsMap.put("TracingConfig", tracingConfig);

        }
        if (vpcConfig != null && !vpcConfig.isEmpty()) {
            payloadParamsMap.put("VpcConfig", vpcConfig);

        }
        if (routingConfig != null && !routingConfig.isEmpty()) {
            payloadParamsMap.put("RoutingConfig", routingConfig);

        }

        final Date date = new Date();
        final TimeZone timeZone = TimeZone.getTimeZone("GMT");
        final DateFormat dateFormat = new SimpleDateFormat(AmazonLambdaConstants.ISO8601_BASIC_DATE_FORMAT);
        dateFormat.setTimeZone(timeZone);
        final String amzDate = dateFormat.format(date);

        messageContext.setProperty(AmazonLambdaConstants.X_AMZ_DATE, amzDate);

        String amz = messageContext.getProperty(AmazonLambdaConstants.X_AMZ_DATE).toString();

        System.out.println("SYSTEM PRINTING AMAZON DATE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(amz);

        final DateFormat FORMAT_FOR_DATE_ONLY = new SimpleDateFormat(AmazonLambdaConstants.FORMAT_FOR_DATE_ONLY);
        FORMAT_FOR_DATE_ONLY.setTimeZone(timeZone);
        final String dateOnly = FORMAT_FOR_DATE_ONLY.format(date);

        //final Map<String, String> parameterNamesMap = getParameterNamesMap();
        final Map<String, String> queryParametersValueMap = getParametersValueMap.getQueryValueHashMap();
        final Map<String, String> headerParametersValueMap = getParametersValueMap.getHeadersValueHashMap();
        final Map<String, String> payloadParametersValueMap = getParametersValueMap.getPayloadsValueHashMap();

        try {

            canonicalRequest.append(messageContext.getProperty(AmazonLambdaConstants.HTTP_METHOD))
                    .append(AmazonLambdaConstants.NEW_LINE);

            //APPENDING CANONICAL URI

            String urlRemainder = (String) messageContext.getProperty(AmazonLambdaConstants.URI_REMAINDER);
            if (urlRemainder != null && !urlRemainder.isEmpty()) {
                canonicalRequest.append(urlRemainder.replaceAll(AmazonLambdaConstants.TRIM_SPACE_REGEX, AmazonLambdaConstants.EMPTY_STR))
                        .append(AmazonLambdaConstants.NEW_LINE);
            } else {
                canonicalRequest.append(AmazonLambdaConstants.FORWARD_SLASH)
                        .append(AmazonLambdaConstants.NEW_LINE);
            }

            //APPENDING CANONICAL QUERY STRING

            final StringBuilder canonicalQueryString = new StringBuilder();
//
//            final Map<String, String> queryParametersMap = GetParameterNamesMap.query();
//            for (Map.Entry<String, String> entry : queryParametersMap.entrySet()) {
//
//                String tempParam = queryParametersValueMap.get(entry.getKey());
//
//                if (!tempParam.isEmpty()) {
//                    queryParamsMap.put(entry.getValue(),
//                            tempParam.replaceAll(AmazonLambdaConstants.TRIM_SPACE_REGEX, AmazonLambdaConstants.EMPTY_STR));
//                }
//            }

            System.out.println("hellow world outside loop");

            final SortedSet<String> queryKeys = new TreeSet<>(queryParamsMap.keySet());

            for (String key : queryKeys) {

                String queryValue = queryParamsMap.get(key);
                canonicalQueryString.append(URLEncoder.encode(key, AmazonLambdaConstants.UTF_8))
                        .append(AmazonLambdaConstants.EQUAL)
                        .append(URLEncoder.encode(queryValue, AmazonLambdaConstants.UTF_8))
                        .append(AmazonLambdaConstants.AMPERSAND);
            }
            String x = "";
            x = x.concat(canonicalQueryString.toString());
            System.out.println("CANONICAL QUERYSTRING>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(x);
            System.out.println();

            if (canonicalQueryString.length() > 0) {

                canonicalRequest.append(canonicalQueryString.substring(0, canonicalQueryString.length() - 1))
                        .append(AmazonLambdaConstants.NEW_LINE);
            } else {
                canonicalRequest.append(AmazonLambdaConstants.NEW_LINE);
            }

            //working with canonical header

            final Map<String, String> headerParametersMap = GetParameterNamesMap.headers();
            final StringBuilder canonicalHeaders = new StringBuilder();
            final StringBuilder signedHeader = new StringBuilder();

            headersParamsMap.put(AmazonLambdaConstants.X_AMZ_DATE, amz);
            for (Map.Entry<String, String> entry : headerParametersMap.entrySet()) {
                String key = entry.getKey();
                String tempParam = headerParametersValueMap.get(key);
                if (!tempParam.isEmpty()) {
                    headersParamsMap.put(headerParametersMap.get(key),
                            tempParam.replaceAll(AmazonLambdaConstants.TRIM_SPACE_REGEX, AmazonLambdaConstants.EMPTY_STR));

                }
            }
            final SortedSet<String> headerKeys = new TreeSet<>(headersParamsMap.keySet());
            for (String key : headerKeys) {
                String headerValues = headersParamsMap.get(key);
                canonicalHeaders.append(key.toLowerCase()).append(AmazonLambdaConstants.COLON)
                        .append(headerValues).append(AmazonLambdaConstants.NEW_LINE);
                signedHeader.append(key.toLowerCase());
                signedHeader.append(AmazonLambdaConstants.SEMI_COLON);
            }

            System.out.println("system printed value of canonical headers");
            System.out.println(canonicalHeaders.toString());
            canonicalRequest.append(canonicalHeaders.toString());
            canonicalRequest.append(AmazonLambdaConstants.NEW_LINE);

            // Remove unwanted semi-colon at the end of the signedHeader string
            // signedHeader.append(AmazonLambdaConstants.HOST);
            String signedHeaders = "";
            if (signedHeader.length() > 0) {
                signedHeaders = signedHeader.substring(0, signedHeader.length() - 1);
            }
            System.out.println("PRINTED VALUE OF SIGNEDHEADERS: ");
            System.out.println(signedHeaders);
            canonicalRequest.append(signedHeaders);
            canonicalRequest.append(AmazonLambdaConstants.NEW_LINE);

            //PAYLOAD BUILDING AND APPENDING

            String requestPayload = "";
            Object requestPayLoad = messageContext.getProperty(AmazonLambdaConstants.REQUEST_PAYLOAD);
            if (requestPayLoad != null && !((String) requestPayLoad).trim().isEmpty()) {
                requestPayload = (String) requestPayLoad;
            }
            else{
                final Map<String, String> payloadParametersMap = GetParameterNamesMap.payload();
                final StringBuilder payloadBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : payloadParametersMap.entrySet()) {
                    String key = entry.getKey();
                    String tempParam = payloadParametersValueMap.get(key);
                    if (!tempParam.isEmpty()) {
                        payloadParamsMap.put(payloadParametersMap.get(key),
                            tempParam.replaceAll(AmazonLambdaConstants.TRIM_SPACE_REGEX, AmazonLambdaConstants.EMPTY_STR));
                    }
                }
                final SortedSet<String> payloadKeys = new TreeSet<>(payloadParamsMap.keySet());

                for (String key : payloadKeys) {
                    String payloadValues = payloadParamsMap.get(key);
                    payloadBuilder.append('"');
                    payloadBuilder.append(key);
                    payloadBuilder.append('"');
                    payloadBuilder.append(':');

                    if (payloadValues.substring(0, 1).equals("{") || payloadValues.substring(0, 1).equals("[")) {
                        payloadBuilder.append(payloadValues)
                                .append(',');
                    } else if (key.equals(AmazonLambdaConstants.API_PUBLISH) ||
                            key.equals(AmazonLambdaConstants.API_TIMEOUT)) {
                        payloadBuilder.append(payloadValues)
                                .append(',');
                    } else {
                        payloadBuilder.append('"');
                        payloadBuilder.append(payloadValues);
                        payloadBuilder.append('"');

                        payloadBuilder.append(',');
                    }
                }
                if (payloadBuilder.length() > 0) {
                    requestPayload = "{" + payloadBuilder.substring(0, payloadBuilder.length() - 1) + "}";
                }
                messageContext.setProperty(AmazonLambdaConstants.REQUEST_PAYLOAD, requestPayload);
            }

            log.info("============================== DEBUG REQUEST PAYLOAD ==============================");
            log.info(requestPayload);
            log.info("===================================================================");

            //HASHING AND MAKING IT LOWER CASE HEXADECIMAL STRING FOR APPENDING TO CANONICAL REQUEST
            canonicalRequest.append(bytesToHex(hash(messageContext, requestPayload)).toLowerCase());

            //BUILDING STRING TO SIGN//sign karney se pahele  ka building

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

        } catch (Exception e) {
            throw new ConnectException(e);
        }
        System.out.println("canonical request>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(canonicalRequest.toString());
        System.out.println("string to sign..........>>>>>>>>>>>>>");
        System.out.println(stringToSign.toString());
        System.out.println("signature>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println();
        System.out.println("canonicalHeaders>>>>>>>>>>>>>>>>>>>>");

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
































