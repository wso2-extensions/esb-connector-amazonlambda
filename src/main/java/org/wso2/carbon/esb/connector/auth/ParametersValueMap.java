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
import org.wso2.carbon.esb.connector.constants.AmazonLambdaConstants;

import java.util.HashMap;
import java.util.Map;

public class ParametersValueMap {


    private static Log log = LogFactory.getLog(ParametersValueMap.class);

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
