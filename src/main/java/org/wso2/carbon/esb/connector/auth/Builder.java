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

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.wso2.carbon.esb.connector.constants.AmazonLambdaConstants;

public class Builder {

    //private static final Log LOG = LogFactory.getLog(Builder.class);

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

    private String functionCode() {

        StringBuilder codeBuilder = new StringBuilder();

        if (messageContext.getProperty(AmazonLambdaConstants.S3_BUCKET) != null) {
            codeBuilder.append('"')
                    .append("S3Bucket")
                    .append('"')

                    .append(':')

                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.S3_BUCKET).toString())
                    .append('"')
                    .append(',');
        }

        if (messageContext.getProperty(AmazonLambdaConstants.S3_KEY) != null) {
            codeBuilder.append('"')
                    .append("S3Key")
                    .append('"')

                    .append(':')

                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.S3_KEY).toString())
                    .append('"')
                    .append(',');
        }

        if (messageContext.getProperty(AmazonLambdaConstants.S3_OBJECT_VERSION) != null) {
            codeBuilder.append('"')
                    .append("S3ObjectVersion")
                    .append('"')

                    .append(':')

                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.S3_OBJECT_VERSION).toString())
                    .append('"')
                    .append(',');
        }
        if (messageContext.getProperty(AmazonLambdaConstants.S3_ZIP_FILE) != null) {
            codeBuilder.append('"')
                    .append("ZipFile")
                    .append('"')

                    .append(':')

                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.S3_ZIP_FILE))
                    .append('"')
                    .append(',');
        }
        String builtCode = "";
        if (codeBuilder.length() > 0) {
            builtCode = "{" + codeBuilder.substring(0, codeBuilder.length() - 1) + "}";
        }
        return builtCode;
    }

    private String deadLetterConfig() {

        StringBuilder deadLetterConfigBuilder = new StringBuilder();
        if (messageContext.getProperty(AmazonLambdaConstants.TARGET_ARN) != null) {
            deadLetterConfigBuilder.append('"')
                    .append("TargetArn")
                    .append('"')

                    .append(':')

                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.TARGET_ARN).toString())
                    .append('"')
                    .append(',');

        }
        String builtDeadLetterConfig = "";
        if (deadLetterConfigBuilder.length() > 0) {
            builtDeadLetterConfig = "{" + deadLetterConfigBuilder.substring(0, deadLetterConfigBuilder.length() - 1) + "}";
        }
        return builtDeadLetterConfig;
    }

    private String environment() {//check for setting variables, use of " " is not needed
        StringBuilder environmentBuilder = new StringBuilder();

        if (messageContext.getProperty(AmazonLambdaConstants.ENVIRONMENT_VARIABLES) != null) {
            environmentBuilder.append('"')
                    .append("Variables")
                    .append('"')

                    .append(':')

                    //.append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.ENVIRONMENT_VARIABLES).toString())
                    //.append('"')
                    .append(',');

        }
        String builtEnvironment = "";
        if (environmentBuilder.length() > 0) {
            builtEnvironment = "{" + environmentBuilder.substring(0, environmentBuilder.length() - 1) + "}";
        }
        return builtEnvironment;
    }

    private String tracingConfig() {

        StringBuilder tracingConfigBuilder = new StringBuilder();
        if (messageContext.getProperty(AmazonLambdaConstants.MODE) != null) {
            tracingConfigBuilder.append('"')
                    .append("Mode")
                    .append('"')

                    .append(':')

                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.MODE).toString())
                    .append('"')
                    .append(',');

        }
        String builtTracingConfig = "";
        if (tracingConfigBuilder.length() > 0) {
            builtTracingConfig = "{" + tracingConfigBuilder.substring(0, tracingConfigBuilder.length() - 1) + "}";
        }
        return builtTracingConfig;
    }

    private String vpcConfig() {

        StringBuilder vpcConfigBuilder = new StringBuilder();
        if (messageContext.getProperty(AmazonLambdaConstants.SECURITY_GROUP_IDS) != null) {
            vpcConfigBuilder.append('"')
                    .append("SecurityGroupIds")
                    .append('"')

                    .append(':')

                    .append("[ ")
                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.SECURITY_GROUP_IDS).toString())
                    .append('"')
                    .append(" ]")
                    .append(',');

        }

        if (messageContext.getProperty(AmazonLambdaConstants.SUBNET_IDS) != null) {
            vpcConfigBuilder.append('"')
                    .append("SubnetIds")
                    .append('"')

                    .append(':')

                    .append("[ ")
                    .append('"')
                    .append(messageContext.getProperty(AmazonLambdaConstants.SUBNET_IDS).toString())
                    .append('"')
                    .append(" ]")
                    .append(',');

        }
        String builtVpcConfig = "";
        if (vpcConfigBuilder.length() > 0) {
            builtVpcConfig = "{" + vpcConfigBuilder.substring(0, vpcConfigBuilder.length() - 1) + "}";
        }
        return builtVpcConfig;
    }

//        private String routingConfig(){
//            StringBuilder routingConfigBuilder = new StringBuilder();
//            LOG.info("============================== DEBUG ==============================");
//            LOG.info(messageContext.getProperty(AmazonLambdaConstants.ADDITIONAL_VERSION_WEIGHTS));
//            LOG.info("===================================================================");
//            if(messageContext.getProperty(AmazonLambdaConstants.ADDITIONAL_VERSION_WEIGHTS) != null) {
//                (String) messageContext.getProperty(AmazonLambdaConstants.ADDITIONAL_VERSION_WEIGHTS);
//                routingConfigBuilder.append('"')
//                        .append("AdditionalVersionWeights")
//                        .append('"')
//
//                        .append(':')
//
//                        //.append('"')
//                        .append(messageContext.getProperty(AmazonLambdaConstants.ADDITIONAL_VERSION_WEIGHTS).toString())//string and number are there might need separete implementation
//                        //.append('"')
//                        .append(',');
//
//            }
//            String builtRoutingConfig="";
//            if(routingConfigBuilder.length()>0){
//                builtRoutingConfig= "{" + routingConfigBuilder.substring(0, routingConfigBuilder.length()-1) + "}";
//            }
//            return builtRoutingConfig;
//        }

    public String routingConfig() {

        Object o = messageContext.getProperty(AmazonLambdaConstants.ADDITIONAL_VERSION_WEIGHTS);

        if (o == null) {
            return "";
        }

        String versionWeight = (String) o;

        if (versionWeight.trim().isEmpty()) {
            return "";
        }

        return String.format("{\"AdditionalVersionWeights\": %s}", versionWeight);
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

