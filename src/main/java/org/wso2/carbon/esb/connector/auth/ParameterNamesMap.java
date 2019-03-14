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

import org.wso2.carbon.esb.connector.constants.AmazonLambdaConstants;

import java.util.HashMap;
import java.util.Map;

public class ParameterNamesMap {

    public static Map<String, String> getHeaderParameterNamesMap() {

        final Map<String, String> headersMap = new HashMap<>();

        headersMap.put(AmazonLambdaConstants.CONTENT_TYPE, AmazonLambdaConstants.API_CONTENT_TYPE);
        headersMap.put(AmazonLambdaConstants.HOST, AmazonLambdaConstants.API_HOST);
        headersMap.put(AmazonLambdaConstants.X_AMZ_DATE, AmazonLambdaConstants.API_X_AMZ_DATE);
        headersMap.put(AmazonLambdaConstants.X_AMZ_INVOCATION_TYPE, AmazonLambdaConstants.API_X_AMZ_INVOCATION_TYPE);
        headersMap.put(AmazonLambdaConstants.X_AMZ_LOG_TYPE, AmazonLambdaConstants.API_X_AMZ_LOG_TYPE);
        headersMap.put(AmazonLambdaConstants.X_AMZ_CLIENT_CONTEXT, AmazonLambdaConstants.API_X_AMZ_CLIENT_CONTEXT);

        return headersMap;
    }

    public static Map<String, String> getQueryParameterNamesMap() {

        final Map<String, String> queryParametersMap = new HashMap<>();
        queryParametersMap.put(AmazonLambdaConstants.URI_REVISION_ID, AmazonLambdaConstants.API_URI_REVISION_ID);
        queryParametersMap.put(AmazonLambdaConstants.URI_QUALIFIER, AmazonLambdaConstants.API_URI_QUALIFIER);
        queryParametersMap.put(AmazonLambdaConstants.URI_FUNCTION_VERSION, AmazonLambdaConstants.API_URI_FUNCTION_VERSION);
        queryParametersMap.put(AmazonLambdaConstants.URI_MARKER, AmazonLambdaConstants.API_URI_MARKER);
        queryParametersMap.put(AmazonLambdaConstants.URI_MASTER_REGION, AmazonLambdaConstants.API_URI_MASTER_REGION);
        queryParametersMap.put(AmazonLambdaConstants.URI_MAX_ITEM, AmazonLambdaConstants.API_URI_MAX_ITEM);


        return queryParametersMap;
    }

    public static Map<String, String> getPayloadParameterNamesMap() {

        final Map<String, String> payloadParametersMap = new HashMap<>();

        //payloadParametersMap.put(AmazonLambdaConstants.CODE, AmazonLambdaConstants.API_CODE);
        //payloadParametersMap.put(AmazonLambdaConstants.DEAD_LETTER_CONFIG, AmazonLambdaConstants.API_DEAD_LETTER_CONFIG);
        payloadParametersMap.put(AmazonLambdaConstants.DESCRIPTION, AmazonLambdaConstants.API_DESCRIPTION);
        //payloadParametersMap.put(AmazonLambdaConstants.ENVIRONMENT, AmazonLambdaConstants.API_ENVIRONMENT);
        payloadParametersMap.put(AmazonLambdaConstants.FUNCTION_NAME, AmazonLambdaConstants.API_FUNCTION_NAME);
        payloadParametersMap.put(AmazonLambdaConstants.ALIAS_NAME, AmazonLambdaConstants.API_ALIAS_NAME);
        payloadParametersMap.put(AmazonLambdaConstants.HANDLER, AmazonLambdaConstants.API_HANDLER);
        payloadParametersMap.put(AmazonLambdaConstants.KMS_KEY_ARN, AmazonLambdaConstants.API_KMS_KEY_ARN);
        payloadParametersMap.put(AmazonLambdaConstants.LAYERS, AmazonLambdaConstants.API_LAYERS);
        payloadParametersMap.put(AmazonLambdaConstants.MEMORY_SIZE, AmazonLambdaConstants.API_MEMORY_SIZE);
        payloadParametersMap.put(AmazonLambdaConstants.PUBLISH, AmazonLambdaConstants.API_PUBLISH);
        payloadParametersMap.put(AmazonLambdaConstants.ROLE, AmazonLambdaConstants.API_ROLE);
        payloadParametersMap.put(AmazonLambdaConstants.RUNTIME, AmazonLambdaConstants.API_RUNTIME);
        payloadParametersMap.put(AmazonLambdaConstants.TAGS, AmazonLambdaConstants.API_TAGS);
        payloadParametersMap.put(AmazonLambdaConstants.TIMEOUT, AmazonLambdaConstants.API_TIMEOUT);
        //payloadParametersMap.put(AmazonLambdaConstants.TRACING_CONFIG, AmazonLambdaConstants.API_TRACING_CONFIG);
        //payloadParametersMap.put(AmazonLambdaConstants.VPC_CONFIG, AmazonLambdaConstants.API_VPC_CONFIG);
        payloadParametersMap.put(AmazonLambdaConstants.ACTION, AmazonLambdaConstants.API_ACTION);
        payloadParametersMap.put(AmazonLambdaConstants.ORGANIZATION_ID, AmazonLambdaConstants.API_ORGANIZATION_ID);
        payloadParametersMap.put(AmazonLambdaConstants.PRINCIPAL, AmazonLambdaConstants.API_PRINCIPAL);
        payloadParametersMap.put(AmazonLambdaConstants.STATEMENT_ID, AmazonLambdaConstants.API_STATEMENT_ID);
        payloadParametersMap.put(AmazonLambdaConstants.EVENT_SOURCE_TOKEN, AmazonLambdaConstants.API_EVENT_SOURCE_TOKEN);
        payloadParametersMap.put(AmazonLambdaConstants.REVISION_ID, AmazonLambdaConstants.API_REVISION_ID);
        payloadParametersMap.put(AmazonLambdaConstants.SOURCE_ACCOUNT, AmazonLambdaConstants.API_SOURCE_ACCOUNT);
        payloadParametersMap.put(AmazonLambdaConstants.SOURCE_ARN, AmazonLambdaConstants.API_SOURCE_ARN);
        payloadParametersMap.put(AmazonLambdaConstants.FUNCTION_VERSION, AmazonLambdaConstants.API_FUNCTION_VERSION);
        payloadParametersMap.put(AmazonLambdaConstants.PAYLOAD, AmazonLambdaConstants.API_PAYLOAD);
        //payloadParametersMap.put(AmazonLambdaConstants.ROUTING_CONFIG, AmazonLambdaConstants.API_ROUTING_CONFIG);
        return payloadParametersMap;
    }
}
