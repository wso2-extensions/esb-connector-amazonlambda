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

public class ParameterStore {

    private static Map<String, String> headers;

    static {
        headers = new HashMap<>();
        headers.put(AmazonLambdaConstants.CONTENT_TYPE, AmazonLambdaConstants.API_CONTENT_TYPE);
        headers.put(AmazonLambdaConstants.HOST, AmazonLambdaConstants.API_HOST);
        headers.put(AmazonLambdaConstants.X_AMZ_DATE, AmazonLambdaConstants.API_X_AMZ_DATE);
        headers.put(AmazonLambdaConstants.X_AMZ_INVOCATION_TYPE, AmazonLambdaConstants.API_X_AMZ_INVOCATION_TYPE);
        headers.put(AmazonLambdaConstants.X_AMZ_LOG_TYPE, AmazonLambdaConstants.API_X_AMZ_LOG_TYPE);
        headers.put(AmazonLambdaConstants.X_AMZ_CLIENT_CONTEXT, AmazonLambdaConstants.API_X_AMZ_CLIENT_CONTEXT);
    }

    private final MessageContext context;

    public ParameterStore(MessageContext context) {

        this.context = context;
    }

    public String[] getHeaderKeys() {

        return headers.keySet().toArray(new String[0]);
    }
}

