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
import org.apache.synapse.MessageContext;
import org.wso2.carbon.esb.connector.constants.AmazonLambdaConstants;

import java.util.HashMap;
import java.util.Map;

public class ParameterStore {

    private static Map<String, String> headers;
    private static Map<String, String> payloads;
    private static Map<String, String> query;

    static {
        headers = new HashMap<>();
        payloads = new HashMap<>();
        query = new HashMap<>();

        headers.put(AmazonLambdaConstants.CONTENT_TYPE, AmazonLambdaConstants.API_CONTENT_TYPE);
        headers.put(AmazonLambdaConstants.HOST, AmazonLambdaConstants.API_HOST);
        headers.put(AmazonLambdaConstants.X_AMZ_DATE, AmazonLambdaConstants.API_X_AMZ_DATE);
        headers.put(AmazonLambdaConstants.X_AMZ_INVOCATION_TYPE, AmazonLambdaConstants.API_X_AMZ_INVOCATION_TYPE);
        headers.put(AmazonLambdaConstants.X_AMZ_LOG_TYPE, AmazonLambdaConstants.API_X_AMZ_LOG_TYPE);
        headers.put(AmazonLambdaConstants.X_AMZ_CLIENT_CONTEXT, AmazonLambdaConstants.API_X_AMZ_CLIENT_CONTEXT);

        query.put(AmazonLambdaConstants.URI_REVISION_ID, AmazonLambdaConstants.API_URI_REVISION_ID);
        query.put(AmazonLambdaConstants.URI_QUALIFIER, AmazonLambdaConstants.API_URI_QUALIFIER);
        query.put(AmazonLambdaConstants.URI_FUNCTION_VERSION, AmazonLambdaConstants.API_URI_FUNCTION_VERSION);
        query.put(AmazonLambdaConstants.URI_MARKER, AmazonLambdaConstants.API_URI_MARKER);
        query.put(AmazonLambdaConstants.URI_MASTER_REGION, AmazonLambdaConstants.API_URI_MASTER_REGION);
        query.put(AmazonLambdaConstants.URI_MAX_ITEM, AmazonLambdaConstants.API_URI_MAX_ITEM);


    }


    private final MessageContext context;

    public ParameterStore(MessageContext context) {

        this.context = context;
    }

    public String[] getHeaderKeys() {

        return headers.keySet().toArray(new String[0]);
    }

    public Map<String, String> getHeadersNameMaps(){
        return headers;
    }

    public String[] getQueryKey(){
        return query.keySet().toArray(new String[0]);
    }

    public String[] getPayloadKey(){
        return payloads.keySet().toArray(new String[0]);
    }
}

