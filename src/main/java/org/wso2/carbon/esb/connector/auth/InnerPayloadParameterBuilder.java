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

import org.apache.synapse.MessageContext;
import org.wso2.carbon.esb.connector.constants.AmazonLambdaConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * InnerPayloadParameterBuilder class is used to build the inner payload. It gets the inner payload
 * parameters' values and build it as in the form of payload with their parameter for the respective outer payload parameter's value.
 */
public class InnerPayloadParameterBuilder {

    private final MessageContext messageContext;
    private final String functionCode;
    private final String deadLetterConfig;
    private final String environment;
    private final String tracingConfig;
    private final String vpcConfig;
    private final String routingConfig;

    public InnerPayloadParameterBuilder(MessageContext messageContext) {

        this.messageContext = messageContext;
        functionCode = buildFunctionCode();
        deadLetterConfig = buildDeadLetterConfig();
        environment = buildEnvironment();
        tracingConfig = buildTracingConfig();
        vpcConfig = buildVpcConfig();
        routingConfig = buildRoutingConfig();

    }

    /**
     * buildFunctionCode method builds the inner payload needed for the
     * API payload parameter "Code" in createFunction method.
     *
     * @return String String that is built as payload from the inner payload parameter/s of Code parameter.
     */
    private String buildFunctionCode() {

        Map<String, Object> codes = new HashMap<>();

        codes.put("S3Bucket", messageContext.getProperty(AmazonLambdaConstants.S3_BUCKET));
        codes.put("S3Key", messageContext.getProperty(AmazonLambdaConstants.S3_KEY));
        codes.put("S3ObjectVersion", messageContext.getProperty(AmazonLambdaConstants.S3_OBJECT_VERSION));

        StringBuilder codeBuilder = new StringBuilder();

        Object zipFile = messageContext.getProperty(AmazonLambdaConstants.S3_ZIP_FILE);
        if (zipFile != null && !((String) zipFile).trim().isEmpty()) {
            codeBuilder.append(String.format("\"%s\":%s", "ZipFile", zipFile));
            codeBuilder.append(",");
        }

        for (Map.Entry<String, Object> entry : codes.entrySet()) {
            if (entry.getValue() == null || ((String) entry.getValue()).trim().isEmpty()) {
                continue;
            }
            codeBuilder.append(String.format("\"%s\":\"%s\"", entry.getKey(), entry.getValue()));
            codeBuilder.append(",");
        }
        if (codeBuilder.length() == 0) {
            return "";
        }

        codeBuilder.setLength(codeBuilder.length() - 1);

        return String.format("{%s}", codeBuilder.toString());
    }

    /**
     * buildDeadLetterConfig  method build the inner payload needed for the
     * API payload parameter "DeadLetterConfig" in createFunction method.
     *
     * @return String String that is built as payload from the inner payload parameter of DeadLetterConfig parameter.
     */
    private String buildDeadLetterConfig() {

        Object targetArnObj = messageContext.getProperty(AmazonLambdaConstants.TARGET_ARN);

        if (targetArnObj == null) {
            return "";
        }

        String targetArnStr = (String) targetArnObj;

        if (targetArnStr.trim().isEmpty()) {
            return "";
        }

        return String.format("{\"TargetArn\":\"%s\"}", targetArnStr);
    }

    /**
     * buildEnvironment method builds the inner payload needed for the
     * API payload parameter "Environment" in createFunction method.
     *
     * @return String String that is built as in a form of payload from the inner payload parameter of Environment parameter.
     */
    private String buildEnvironment() {

        Object envVarObj = messageContext.getProperty(AmazonLambdaConstants.ENVIRONMENT_VARIABLES);

        if (envVarObj == null) {
            return "";
        }

        String environmentVariableStr = (String) envVarObj;

        if (environmentVariableStr.trim().isEmpty()) {
            return "";
        }

        return String.format("{\"Variables\":%s}", environmentVariableStr);
    }

    /**
     * buildTracingConfig method builds the inner payload needed for the API payload parameter "TracingConfig".
     *
     * @return String String that is built as in a form of payload from the inner payload parameter of TracingConfig parameter.
     */
    private String buildTracingConfig() {

        Object modeObj = messageContext.getProperty(AmazonLambdaConstants.MODE);

        if (modeObj == null) {
            return "";
        }

        String modeStr = (String) modeObj;

        if (modeStr.trim().isEmpty()) {
            return "";
        }

        return String.format("{\"Mode\":\"%s\"}", modeStr);
    }

    /**
     * buildVpcConfig method builds the inner payload for the API payload parameter "VpcConfig".
     *
     * @return String String that is built as in a form of payload structure from the inner payload parameter/s of VpcConfig.
     */
    private String buildVpcConfig() {

        StringBuilder vpcConfigBuilder = new StringBuilder();

        Object securityGroupIdsObj = messageContext.getProperty(AmazonLambdaConstants.SECURITY_GROUP_IDS);
        Object subnetIdsObj = messageContext.getProperty(AmazonLambdaConstants.SUBNET_IDS);

        String securityGroupIdsStr = "";
        String subnetIdsStr = "";

        if (securityGroupIdsObj != null && !((String) securityGroupIdsObj).trim().isEmpty()) {
            securityGroupIdsStr = ((String) securityGroupIdsObj).trim();
        }
        if (subnetIdsObj != null && !((String) subnetIdsObj).trim().isEmpty()) {
            subnetIdsStr = ((String) subnetIdsObj).trim();
        }

        if (securityGroupIdsStr != "" || subnetIdsStr != "") {
            vpcConfigBuilder.append('{');

            if (securityGroupIdsStr != "") {
                vpcConfigBuilder = vpcConfigBuilder.append(String.format("{\"SecurityGroupIds\":[\"%s\"]}", securityGroupIdsStr))
                        .append(',');
            }

            if (subnetIdsStr != "") {
                vpcConfigBuilder = vpcConfigBuilder.append(String.format("{\"SubnetIds\":[\"%s\"]}", subnetIdsStr));

            }
            vpcConfigBuilder.append('}');

            return vpcConfigBuilder.toString();
        }
        return "";
    }

    /**
     * buildRoutingConfig method builds the inner payload for the API payload parameter "RoutingConfig".
     *
     * @return string that is built as in the form of payload structure from the inner payload parameter of the
     * RoutingConfig.
     */
    public String buildRoutingConfig() {

        Object additionalVersionWeightsObj = messageContext.getProperty(AmazonLambdaConstants.ADDITIONAL_VERSION_WEIGHTS);

        if (additionalVersionWeightsObj == null) {
            return "";
        }

        String additonalVersionWeightStr = (String) additionalVersionWeightsObj;

        if (additonalVersionWeightStr.trim().isEmpty()) {
            return "";
        }

        return String.format("{\"AdditionalVersionWeights\": %s}", additonalVersionWeightStr);
    }

    /**
     * getFunctionCode method returns the value of functionCode.
     *
     * @return functionCode.
     */
    public String getFunctionCode() {

        return functionCode;
    }

    /**
     * getDeadLetterConfig method returns the value of deadLetterConfig.
     *
     * @return deadLetterConfig.
     */
    public String getDeadLetterConfig() {

        return deadLetterConfig;
    }

    /**
     * getEnvironment method returns environment
     *
     * @return environment
     */
    public String getEnvironment() {

        return environment;
    }

    /**
     * getTracingConfig method returns tracingConfig.
     *
     * @return tracingConfig.
     */
    public String getTracingConfig() {

        return tracingConfig;
    }

    /**
     * getVpcConfig method returns vpcConfig.
     *
     * @return vpcConfig.
     */
    public String getVpcConfig() {

        return vpcConfig;
    }

    /**
     * getRoutingConfig method returns routingConfig.
     *
     * @return routingConfig.
     */
    public String getRoutingConfig() {

        return routingConfig;
    }
}
