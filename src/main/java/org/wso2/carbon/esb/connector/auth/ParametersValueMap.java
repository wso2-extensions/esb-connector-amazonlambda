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
 * ParametersValueMap class is used to store the map of parameters' name and its value.
 */
public class ParametersValueMap {

    private final MessageContext messageContext;
    private final Map<String, String> headersParametersValueMap;
    private final Map<String, String> queryParametersValueMap;
    private final Map<String, String> payloadParametersValueMap;

    public ParametersValueMap(MessageContext messageContext) {

        this.messageContext = messageContext;
        headersParametersValueMap = headerHashMap();
        queryParametersValueMap = queryHashMap();
        payloadParametersValueMap = payloadHashMap();
    }

    /**
     * headerHashMap method is used to store the map of header parameters' name and its value.
     *
     * @return header parameters' name and its value map.
     */
    private Map<String, String> headerHashMap() {

        String[] keys = KeysStore.getHeaderKeys();
        Map<String, String> parametersMap = new HashMap<>();
        for (String key : keys) {
            String paramValue =
                    (messageContext.getProperty(key) != null) ? (String) messageContext
                            .getProperty(key) : AmazonLambdaConstants.EMPTY_STR;
            parametersMap.put(key, paramValue);
        }
        return parametersMap;
    }

    /**
     * queryHashMap method is used to store the map of query parameters' name and its value.
     *
     * @return query parameters' name and its value map.
     */
    private Map<String, String> queryHashMap() {

        Map<String, String> parametersMap = new HashMap<>();
        for (String key : KeysStore.getQueryKeys()) {
            Object property = messageContext.getProperty(key);
            if (property == null) {
                parametersMap.put(key, "");
                continue;
            }
            String value = (String) property;
            parametersMap.put(key, value);
        }
        return parametersMap;
    }

    /**
     * payloadHashMap method is used to store the map of payload parameters' name and its value.
     *
     * @return payload parameters' name and its value map.
     */
    private Map<String, String> payloadHashMap() {

        String[] keys = KeysStore.getPayloadKeys();
        Map<String, String> parametersMap = new HashMap<>();
        for (String key : keys) {
            String paramValue =
                    (messageContext.getProperty(key) != null) ? (String) messageContext
                            .getProperty(key) : AmazonLambdaConstants.EMPTY_STR;
            parametersMap.put(key, paramValue);
        }
        return parametersMap;
    }

    /**
     * getQueryValueHashMap method returns queryParametersValueMap.
     *
     * @return queryParametersValueMap.
     */
    public Map<String, String> getQueryValueHashMap() {

        return queryParametersValueMap;
    }

    /**
     * getHeadersValueHashMap method returns headersParametersValueMap.
     *
     * @return headersParametersValueMap.
     */
    public Map<String, String> getHeadersValueHashMap() {

        return headersParametersValueMap;
    }

    /**
     * getPayloadsValueHashMap method returns payloadParametersValueMap.
     *
     * @return payloadParametersValueMap.
     */
    public Map<String, String> getPayloadsValueHashMap() {

        return payloadParametersValueMap;
    }
}
