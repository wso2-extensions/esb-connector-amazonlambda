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

public class GetParametersValueMap {

    private final MessageContext messageContext;
    private final Map<String, String> headersParametersValueMap;
    private final Map<String, String> queryParametersValueMap;
    private final Map<String, String> payloadParametersValueMap;

    public GetParametersValueMap(MessageContext messageContext) {

        this.messageContext = messageContext;
        headersParametersValueMap = headerHashMap();
        queryParametersValueMap = queryHashMap();
        payloadParametersValueMap = payloadHashMap();

    }

    private Map<String, String> headerHashMap() {

        String[] keys = GetKeys.headers();
        Map<String, String> parametersMap = new HashMap<>();

        for (String key : keys) {
            String paramValue =
                    (messageContext.getProperty(key) != null) ? (String) messageContext
                            .getProperty(key) : AmazonLambdaConstants.EMPTY_STR;
            parametersMap.put(key, paramValue);
        }
        return parametersMap;

    }

    private Map<String, String> queryHashMap() {

        String[] keys = GetKeys.query();
        Map<String, String> parametersMap = new HashMap<>();
        for (String key : keys) {
            String paramValue =
                    (messageContext.getProperty(key) != null) ? (String) messageContext
                            .getProperty(key) : AmazonLambdaConstants.EMPTY_STR;
            parametersMap.put(key, paramValue);
        }
        return parametersMap;

    }

    private Map<String, String> payloadHashMap() {

        String[] keys = GetKeys.payload();
        Map<String, String> parametersMap = new HashMap<>();
        for (String key : keys) {
            String paramValue =
                    (messageContext.getProperty(key) != null) ? (String) messageContext
                            .getProperty(key) : AmazonLambdaConstants.EMPTY_STR;
            parametersMap.put(key, paramValue);
        }
        return parametersMap;

    }

    public Map<String, String> getQueryValueHashMap() {

        return queryParametersValueMap;
    }

    public Map<String, String> getHeadersValueHashMap() {

        return headersParametersValueMap;
    }

    public Map<String, String> getPayloadsValueHashMap() {

        return payloadParametersValueMap;
    }
}
