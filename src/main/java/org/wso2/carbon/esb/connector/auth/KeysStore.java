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

public class KeysStore {

    public static String[] getHeaderKeys() {

        return new String[]{
                AmazonLambdaConstants.CONTENT_TYPE, AmazonLambdaConstants.X_AMZ_INVOCATION_TYPE,
                AmazonLambdaConstants.X_AMZ_LOG_TYPE, AmazonLambdaConstants.X_AMZ_CLIENT_CONTEXT,
                AmazonLambdaConstants.X_AMZ_DATE, AmazonLambdaConstants.HOST
        };
    }

    public static String[] getQueryKeys() {

        return new String[]{
                AmazonLambdaConstants.URI_REVISION_ID, AmazonLambdaConstants.URI_QUALIFIER,
                AmazonLambdaConstants.URI_MAX_ITEM, AmazonLambdaConstants.URI_MASTER_REGION,
                AmazonLambdaConstants.URI_MARKER, AmazonLambdaConstants.URI_FUNCTION_VERSION,
        };
    }

    public static String[] getPayloadKeys() {

        return new String[]{

                AmazonLambdaConstants.DESCRIPTION,
                AmazonLambdaConstants.FUNCTION_NAME,
                AmazonLambdaConstants.HANDLER,
                AmazonLambdaConstants.KMS_KEY_ARN,
                AmazonLambdaConstants.LAYERS,
                AmazonLambdaConstants.MEMORY_SIZE,
                AmazonLambdaConstants.PUBLISH,
                AmazonLambdaConstants.ROLE,
                AmazonLambdaConstants.RUNTIME,
                AmazonLambdaConstants.TAGS,
                AmazonLambdaConstants.TIMEOUT,
                AmazonLambdaConstants.ACTION,
                AmazonLambdaConstants.ORGANIZATION_ID,
                AmazonLambdaConstants.PRINCIPAL,
                AmazonLambdaConstants.STATEMENT_ID,
                AmazonLambdaConstants.EVENT_SOURCE_TOKEN,
                AmazonLambdaConstants.REVISION_ID,
                AmazonLambdaConstants.SOURCE_ACCOUNT,
                AmazonLambdaConstants.SOURCE_ARN,
                AmazonLambdaConstants.FUNCTION_VERSION,
                AmazonLambdaConstants.ALIAS_NAME,

        };
    }
}
