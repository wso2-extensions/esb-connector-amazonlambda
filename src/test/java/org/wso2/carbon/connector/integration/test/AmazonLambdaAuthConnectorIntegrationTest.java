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
package org.wso2.carbon.connector.integration.test;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AmazonLambdaAuthConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> eiRequestHeadersMap = new HashMap<String, String>();

    /**
     * Set up the environment.
     * @throws Exception
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        String connectorName = System.getProperty("connector_name") + "-connector-" +
                System.getProperty("connector_version") + ".zip";
        init(connectorName);
        getApiConfigProperties();


        //eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");
    }

    /**
     * Positive test case for createFunction method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {createFunction} integration test mandatory case.")
    public void testCreateFunctionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createFunction_mandatory.json");
//        String revisionId = eiRestResponse.getBody().getString("RevisionId");
//        connectorProperties.setProperty("revisionId", revisionId);

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }


    /**
     * Positive test case for createFunction method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {createFunction} integration test optional case.")
    public void testCreateFunctionWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createFunction_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }


    /**
     * Positive test case for getFunction method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda (getFunction) integration test with mandatory parameters.")
    public void testGetFunctionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunction");

        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunction_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for getFunction method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters"},
            description = "amazonlambda (getFunction) integration with optional parameters.")
    public void testGetFunctionWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunction_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }


    /**
     * Positive test case for getFunctionConfiguration method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {getFunctionConfiguration} integration test Mandatory case.")
    public void testGetFunctionConfigurationWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunctionConfiguration");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunctionConfiguration_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for getFunctionConfiguration method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters"},
            description = "amazonlambda {getFunctionConfiguration} integration test optional case.")
    public void testGetFunctionConfigurationWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunctionConfiguration");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunctionConfiguration_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for invoke method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {invoke} integration test mandatory case.")
    public void testInvokeWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:invoke");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "invoke_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for invoke method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters"},
            description = "amazonlambda {invoke} integration test optional case.")
    public void testInvokeWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:invoke");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "invoke_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }



    /**
     * Positive test case for listFunction method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters",
            "testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {listFunctions} integration test mandatory case.")
    public void testListFunctionsWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:listFunctions");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "listFunctions_mandatory.json");

        log.info(clearLog(prettyJson(eiRestResponse.getBody())));
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for listFunction method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters",
            "testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {listFunctions} integration test optional case.")
    public void testListFunctionsWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:listFunctions");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "listFunctions_optional.json");

        log.info(clearLog(prettyJson(eiRestResponse.getBody())));
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for addPermission method with mandatory parameters. Depends on createFunction
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {addPermission} integration test mandatory case.")
    public void testAddPermissionWithMandatoryParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:addPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addPermission_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }


    /**
     * Positive test case for addPermission method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {addPermission} integration test optional case.")
    public void testAddPermissionWithOptionalParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:addPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addPermission_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }


    /**
     * Positive test case for removePermission method with mandatory parameters. Depends on testPermission
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddPermissionWithMandatoryParameters"},
            description = "amazonlambda {removePewrmission} integration test mandatory case.")
    public void testRemovePermissionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:removePermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removePermission_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for removePermission method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddPermissionWithOptionalParameters"},
            description = "amazonlambda {removePermission} integration test optional case.")
    public void testRemovePermissionWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:removePermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removePermission_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);

    }

    /**
     * Positive test case for createAlias method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {createAlias} integration test mandatory case.")
    public void testCreateAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for createAlias method with optional parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {createAlias} integration test optional case.")
    public void testCreateAliasWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createAlias_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }


//Depends on createAlias

    /**
     * Positive test case for getAlias method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithMandatoryParameters"},
            description = "amazonlambda {getAlias} integration test mandatory case.")
    public void testGetAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for getAlias method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithOptionalParameters"},
            description = "amazonlambda {getAlias} integration test optional case.")
    public void testGetAliasWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getAlias_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for updateAlias method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithMandatoryParameters"},
            description = "amazonlambda {updateAlias} integration test mandatory case.")
    public void testUpdateAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:updateAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "updateAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for updateAlias method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithOptionalParameters"},
            description = "amazonlambda {updateAlias} integration test optional case.")
    public void testUpdateAliasWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:updateAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "updateAlias_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for deleteAlias method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithMandatoryParameters",
            "testGetAliasWithMandatoryParameters",
            "testUpdateAliasWithMandatoryParameters"},
            description = "amazonlambda {deleteAlias} integration test mandatory case.")
    public void testDeleteAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "deleteAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for deleteAlias method with optional parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithOptionalParameters",
            "testGetAliasWithOptionalParameters",
            "testUpdateAliasWithOptionalParameters"},
            description = "amazonlambda {deleteAlias} integration test optional case.")
    public void testDeleteAliasWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "deleteAlias_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }


    /**
     * Positive test case for getAccountSettings method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {getAccountSettings} integration test mandatory case.")
    public void testGetAccountSettingsWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getAccountSettings");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getAccountSettings_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }




    /**
     * Positive test case for addLayerVersionPermission method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {addLayerVersionPermission} integration test mandatory case.")
    public void testAddLayerVersionPermissionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:addLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "addLayerVersionPermission_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for addLayerVersionPermission method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {addLayerVersionPermission} integration test optional case.")
    public void testAddLayerVersionPermissionWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:addLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addLayerVersionPermission_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }


    /**
     * Positive test case for removeLayerVersionPermission method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddLayerVersionPermissionWithMandatoryParameters"},
            description = "amazonlambda {removeLayerVersionPermission} integration test mandatory case.")
    public void testRemoveLayerVersionPermissionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:removeLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removeLayerVersionPermission_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for removeLayerVersionPermission method with optional parameters.
     *
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddLayerVersionPermissionWithOptionalParameters"},
            description = "amazonlambda {removeLayerVersionPermission} integration test optional case.")
    public void testRemoveLayerVersionPermissionWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:removeLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "removeLayerVersionPermission_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }


    /**
     * Positive test case for deleteFunction method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {deleteFunction} integration test Mandatory case.")
    public void testDeleteFunctionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "deleteFunction_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for deleteFunction method with optional parameters.
     *  dependsOnMethods = {"testCreateFunctionWithOptionalParameters"},
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {deleteFunction} integration test optional case.")
    public void testDeleteFunctionWithOptionalParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "deleteFunction_optional.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

///////////////////////////////////////////////////////////////
    /*
    FOR NEGATIVE CASE
     */

    /**
     * Negative test case for addLayerVersionPermission method.
     */
    @Test(groups = {"wso2.ei"})
    public void testAddLayerVersionPermissionWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:addLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "addLayerVersionPermission_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for addPermission method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testAddPermissionWithNegativeCase() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:addPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "addPermission_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for createAlias method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testCreateAliasWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "createAlias_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Negative test case for createFunction method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testCreateFunctionWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "createFunction_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Negative test case for getAccountSettings method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testGetAccountSettingsWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getAccountSettings");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getAccountSettings_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for getAlias method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testGetAliasWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getAlias_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for getFunction method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testGetFunctionWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getFunction_negative.json");

        log.info(eiRestResponse.getBody());
        //no matter what mandatory parameter gets empty value it's giving status: 200 ok
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for getFunctionConfiguration method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testGetFunctionConfigurationWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunctionConfiguration");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getFunctionConfiguration_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);//check here this 403 is because of signature invalidation
    }

    /**
     * Negative test case for invoke method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testInvokeWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:invoke");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "invoke_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for listFunction method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testListFunctionsWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:listFunctions");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "listFunctions_negative.json");

        log.info(clearLog(prettyJson(eiRestResponse.getBody())));
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
        /*
        {
            "Type": "User",
            "message": "The combination MasterArn=us-east-2 and FunctionVersion=NULL is not supported. Please see our documentation for valid combinations of ListFunctions options."
        }
         */
    }

    /**
     * Negative test case for removeLayerVersionPermission method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testRemoveLayerVersionPermissionWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:removeLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "removeLayerVersionPermission_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for removePermission method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testRemovePermissionWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:removePermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "removePermission_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for updateAlias method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testUpdateAliasWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:updateAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "updateAlias_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for deleteAlias method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testDeleteAliasWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "deleteAlias_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for deleteFunction method.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testDeleteFunctionWithNegativeCase() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "deleteFunction_negative.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    private String clearLog(Object middle) {
        return "\n=========================================\n" + middle + "\n=========================================\n";
    }

    private String prettyJson(JSONObject json) throws JSONException {
        return json.toString(2);
    }


}