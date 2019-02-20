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

package org.wso2.carbon.esb.connector.constants;

public class AmazonLambdaConstants {

    public static final String API_URL = "uri.var.apiUrl";

    public static final String SECRET_ACCESS_KEY = "secretAccessKey";

    public static final String ACCESS_KEY_ID = "accessKeyId";

    public static final String HTTP_METHOD = "httpMethod";

    public static final String CANONICAL_URI = "canonicalUri"; // "/"

    public static final String CANONICAL_QUERY_STRING = "canonicalQueryString";

    public static final String CANONICAL_HEADERS = "canonicalHeaders";

    public static final String REQUEST_PAYLOAD = "requestPayload";

    public static final String REGION = "region";

    public static final String SERVICE = "service";

    public static final String CREDENTIAL_SCOPE = "credentialScope"; //date only + region + service + terminationString build garney aafailey

    public static final String TERMINATION_STRING = "terminationString";

    public static final String HASHED_CANONICAL_REQUEST = "hashedCanonicalRequest";

    public static final String SEMI_COLON = ";";

    public static final String NEW_LINE = "\n";

    public static final String API_SIGNATURE = "Signature";

    /**
     * Constant for credential.
     */
    public static final String CREDENTIAL = "Credential";

    /**
     * Constant for Signed Headers.
     */
    public static final String SIGNED_HEADERS = "SignedHeaders";

    /**
     * Constant for Authorization Header.
     */
    public static final String AUTHORIZATION_HEADER = "authorizationHeader";

    /**
     * Constant for Comma.
     */
    public static final String COMMA = ",";

    /**
     * Constant for Colon.
     */
    public static final String COLON = ":";

    /**
     * Constant for Forward Slash.
     */
    public static final String FORWARD_SLASH = "/";

    /**
     * Constant for Equal.
     */
    public static final String EQUAL = "=";

    /**
     * Constant for Ampersand.
     */
    public static final String AMPERSAND = "&";

    /**
     * Error code constant for Invalid parameters.
     */
    public static final String INVALID_PARAMETERS = "Invalid parameters";

    /**
     * Constant fot Invalid Key Error.
     */
    public static final String INVALID_KEY_ERROR = "Invalid key";

    /**
     * Constant for Invalid Error.
     */
    public static final String NO_SUCH_ALGORITHM_ERROR = "Invalid Algorithm";

    /**
     * Constant for Illegal State Error.
     */
    public static final String ILLEGAL_STATE_ERROR = "Illegal State";

    /**
     * Constant for Unsupported Encoding Error.
     */
    public static final String UNSUPPORTED_ENCORDING_ERROR = "Unsupported Encoding";

    /**
     * Constant errorCode for InvalidKeyException.
     */
    public static final int INVALID_KEY_ERROR_CODE = 700007;

    /**
     * Constant errorCode for NoSuchAlgorithmException.
     */
    public static final int NO_SUCH_ALGORITHM_ERROR_CODE = 700008;

    /**
     * Constant errorCode for IllegalArgumentException.
     */
    public static final int ILLEGAL_ARGUMENT_ERROR_CODE = 700013;

    /**
     * Constant for Illegal State Error Code.
     */
    public static final int ILLEGAL_STATE_ERROR_CODE = 700015;

    /**
     * Constant errorCode for UnsupportedEncodingException.
     */
    public static final int UNSUPPORTED_ENCORDING_ERROR_CODE = 700009;

    /**
     * Error code constant for generic exception.
     */
    public static final int ERROR_CODE_EXCEPTION = 900001;

    /**
     * Constant for two.
     */
    public static final int TWO = 2;

    /**
     * Constant for API variable for Expires.
     */
    public static final String API_EXPIRES = "Expires";

    /**
     * Constant for sha 256.
     */
    public static final String SHA_256 = "SHA-256";

    /**
     * Constant for aws4-hmac-sha256. Hashing algorithm used for creating string-to-sign
     */
    public static final String AWS4_HMAC_SHA_256 = "AWS4-HMAC-SHA256";

    /**
     * Constant for Date format.
     */
    public static final String ISO8601_BASIC_DATE_FORMAT = "yyyyMMdd'T'HHmmss'Z'";

    /**
     * Constant for Short Date Format.
     */
    public static final String FORMAT_FOR_DATE_ONLY = "yyyyMMdd";

    /**
     * Constant for UTF-8.
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * Constant for UTF8.
     */
    public static final String UTF8 = "UTF8";

    /**
     * Constant for HmacSha256. Used in signature method.
     */
    public static final String HAMC_SHA_256 = "HmacSHA256";

    public static final String SIGNATURE_METHOD = "HmacSHA256";

    // following two string variables may not be needed

    public static final String API_SIGNATURE_METHOD = "SignatureMethod";

    /**
     * Constant for AWS4.
     */
    public static final String AWS4 = "AWS4";

    /**
     * Constant for Hex Array String.
     */
    public static final String HEX_ARRAY_STRING = "0123456789ABCDEF";

    /**
     * Constant for Trim Spaces Regex.
     */
    public static final String TRIM_SPACE_REGEX = "  ++(?=(?:[^\"]*+\"[^\"]*+\")*+[^\"]*+$)";

    /**
     * Constant for Space.
     */
    public static final String SPACE = " ";

    /**
     * Constant for Emptyspace.
     */
    public static final String EMPTY_STR = "";

    /**
     * Constant for Connector Error.
     */
    public static final String CONNECTOR_ERROR = "Error occurred in connector";

    /**
     * Constant for Connector Error.
     */

    public static final String URI_REMAINDER = "uri.var.uriRemainder";

    //CONSTANTS FOR CANONICAL HEADER PARAMETER AND IT'S VALUE

    public static final String CONTENT_TYPE = "contentType";

    public static final String API_CONTENT_TYPE = "Content-Type";

    public static final String X_AMZ_INVOCATION_TYPE = "invocationType";

    public static final String API_X_AMZ_INVOCATION_TYPE = "X-Amz-Invocation-Type";

    public static final String X_AMZ_LOG_TYPE = "logType";

    public static final String API_X_AMZ_LOG_TYPE = "X-Amz-Log-Typ";

    public static final String X_AMZ_CLIENT_CONTEXT = "clientContext";

    public static final String API_X_AMZ_CLIENT_CONTEXT = "X-Amz-Client-Context";

    public static final String API_HOST = "Host";

    public static final String HOST = "hostName";

    public static final String API_X_AMZ_DATE = "X-Amz-Date";

    public static final String X_AMZ_DATE = "x-amz-date";

    //CONSTANTS FOR HEADERS PARAMETER(PAYLOAD PARAM)

    public static final String CODE = "payloadCode";

    public static final String DEAD_LETTER_CONFIG = "payloadDeadLetterConfig";

    public static final String DESCRIPTION = "payloadDescription";

    public static final String ENVIRONMENT = "payloadEnvironment";

    public static final String FUNCTION_NAME = "payloadFunctionName";//added the prefix payload because without the prefix user might use it values in proxy and the input will be inserted as a payload parameter

    public static final String HANDLER = "payloadHandler";

    public static final String KMS_KEY_ARN = "payloadKmsKeyArn";

    public static final String LAYERS = "payloadLayers";

    public static final String MEMORY_SIZE = "payloadMemorySize";

    public static final String PUBLISH = "payloadPublish";

    public static final String ROLE = "payloadRole";

    public static final String RUNTIME = "payloadRuntime";

    public static final String TAGS = "payloadTags";

    public static final String TIMEOUT = "payloadTimeout";

    public static final String TRACING_CONFIG = "payloadTracingConfig";

    public static final String VPC_CONFIG = "payloadVpcConfig";

    public static final String ACTION = "payloadAction";

    public static final String ORGANIZATION_ID = "payloadOrganizationId";

    public static final String PRINCIPAL = "payloadPrincipal";

    public static final String STATEMENT_ID = "payloadStatementId";

    public static final String EVENT_SOURCE_TOKEN = "payloadEventSourceToken";

    public static final String REVISION_ID = "payloadRevisionId";

    public static final String SOURCE_ACCOUNT = "payloadSourceAccount";

    public static final String SOURCE_ARN = "payloadSourceArn";

    public static final String FUNCTION_VERSION = "payloadFunctionVersion";

    public static final String ALIAS_NAME = "payloadAliasName";

    public static final String ROUTING_CONFIG = "payloadRoutingConfig";

    public static final String API_CODE = "Code";

    public static final String API_DEAD_LETTER_CONFIG = "DeadLetterConfig";

    public static final String API_DESCRIPTION = "Description";

    public static final String API_ENVIRONMENT = "Environment";

    public static final String API_FUNCTION_NAME = "FunctionName";

    public static final String API_HANDLER = "Handler";

    public static final String API_KMS_KEY_ARN = "KMSKeyArn";

    public static final String API_LAYERS = "Layers";

    public static final String API_MEMORY_SIZE = "MemorySize";

    public static final String API_PUBLISH = "Publish";

    public static final String API_ROLE = "Role";

    public static final String API_RUNTIME = "Runtime";

    public static final String API_TAGS = "Tags";

    public static final String API_TIMEOUT = "Timeout";

    public static final String API_TRACING_CONFIG = "TracingConfig";

    public static final String API_VPC_CONFIG = "VpcConfig";

    public static final String API_ACTION = "Action";

    public static final String API_ORGANIZATION_ID = "OrganizationId";

    public static final String API_PRINCIPAL = "Principal";

    public static final String API_STATEMENT_ID = "StatementId";

    public static final String API_EVENT_SOURCE_TOKEN = "EventSourceToken";

    public static final String API_REVISION_ID = "RevisionId";

    public static final String API_SOURCE_ACCOUNT = "SourceAccount";

    public static final String API_SOURCE_ARN = "SourceArn";

    public static final String API_FUNCTION_VERSION = "FunctionVersion";

    public static final String API_ALIAS_NAME = "Name";

    public static final String API_ROUTING_CONFIG = "RoutingConfig";

    //constants for endpoints

    public static final String URI_FUNCTION_NAME = "uri.var.functionName";

    public static final String URI_LAYER_NAME = "uri.var.layerName";

    public static final String URI_ALIAS_NAME = "uri.var.aliasName";

    public static final String URI_STATEMENT_ID = "uri.var.statementId";

    public static final String URI_VERSION_NUMBER = "uri.var.versionNumber";



    //constants for query parameter

    public static final String URI_REVISION_ID = "uri.var.revisionId";

    public static final String API_URI_REVISION_ID = "RevisionId";

    public static final String URI_QUALIFIER = "uri.var.qualifier";

    public static final String API_URI_QUALIFIER = "Qualifier";

    public static final String URI_MARKER = "uri.var.marker";

    public static final String API_URI_MARKER = "Marker";

    public static final String URI_FUNCTION_VERSION = "uri.var.functionVersion";

    public static final String API_URI_FUNCTION_VERSION = "FunctionVersion";

    public static final String URI_MASTER_REGION = "uri.var.masterRegion";

    public static final String API_URI_MASTER_REGION = "MasterRegion";

    public static final String URI_MAX_ITEM = "uri.var.maxItems";

    public static final String API_URI_MAX_ITEM = "MaxItems";




    //constants for innerPayload header

    public static final String S3_BUCKET = "payloadS3Bucket";

    public static final String S3_KEY = "payloadS3Key";

    public static final String S3_OBJECT_VERSION = "payloadS3ObjectVersion";

    public static final String TARGET_ARN = "payloadTargetArn";

    public static final String ENVIRONMENT_VARIABLES = "payloadEnvironmentVariables";

    public static final String MODE = "payloadModes";

    public static final String SECURITY_GROUP_IDS = "payloadSecurityGroupIds";

    public static final String SUBNET_IDS = "payloadSubnetIds";

    public static final String ADDITIONAL_VERSION_WEIGHTS = "payloadAdditionalVersionWeights";

    public static final String S3_ZIP_FILE = "payloadS3ZipFile";

}
