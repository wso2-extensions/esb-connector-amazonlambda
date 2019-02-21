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
import org.wso2.carbon.esb.connector.constants.AmazonLambdaConstants;

import java.util.HashMap;
import java.util.Map;

public class Builder {

    private static final Log LOG = LogFactory.getLog(Builder.class);

    private final MessageContext messageContext;
    private final String functionCode;
    private final String deadLetterConfig;
    private final String environment;
    private final String tracingConfig;
    private final String vpcConfig;
    private final String routingConfig;

    public Builder(MessageContext messageContext) {

        this.messageContext = messageContext;
        functionCode = functionCode();
        deadLetterConfig = deadLetterConfig();
        environment = environment();
        tracingConfig = tracingConfig();
        vpcConfig = vpcConfig();
        routingConfig = routingConfig();

    }

//    private boolean isFunctionCodeNotEmpty(Object code) {
//
//        return code != null && !((String) code).trim().isEmpty();
//    }

    private String functionCode() {

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

    private String deadLetterConfig() {

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

    private String environment() {//check for setting variables, use of " " is not needed
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

    private String tracingConfig() {

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

    private String vpcConfig() {

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

    public String routingConfig() {

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

    public String getFunctionCode() {

        return functionCode;
    }

    public String getDeadLetterConfig() {

        return deadLetterConfig;
    }

    public String getEnvironment() {

        return environment;
    }

    public String getTracingConfig() {

        return tracingConfig;
    }

    public String getVpcConfig() {

        return vpcConfig;
    }

    public String getRoutingConfig() {

        return routingConfig;
    }
}


