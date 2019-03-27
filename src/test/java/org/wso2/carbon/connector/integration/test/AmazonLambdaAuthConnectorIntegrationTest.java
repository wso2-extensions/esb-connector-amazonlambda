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
     *
     * @throws Exception
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        String connectorName = System.getProperty("connector_name") + "-connector-" +
                System.getProperty("connector_version") + ".zip";
        init(connectorName);
        getApiConfigProperties();
        eiRequestHeadersMap.put("Content-Type", "application/json");
    }

    /**
     * Positive test case for createFunction method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {createFunction} integration test mandatory case.")
    public void testCreateFunctionWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:createFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "createFunction_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for createFunction method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {createFunction} integration test optional case.")
    public void testCreateFunctionWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:createFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createFunction_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for getFunction method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda (getFunction) integration test with mandatory parameters.")
    public void testGetFunctionWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunction_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for getFunction method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters"},
            description = "amazonlambda (getFunction) integration with optional parameters.")
    public void testGetFunctionWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunction_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for getFunctionConfiguration method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {getFunctionConfiguration} integration test Mandatory case.")
    public void testGetFunctionConfigurationWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getFunctionConfiguration");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunctionConfiguration_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for getFunctionConfiguration method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters"},
            description = "amazonlambda {getFunctionConfiguration} integration test optional case.")
    public void testGetFunctionConfigurationWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getFunctionConfiguration");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunctionConfiguration_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for invoke method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {invoke} integration test mandatory case.")
    public void testInvokeWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:invoke");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "invoke_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for invoke method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters"},
            description = "amazonlambda {invoke} integration test optional case.")
    public void testInvokeWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:invoke");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "invoke_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for listFunction method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters",
            "testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {listFunctions} integration test mandatory case.")
    public void testListFunctionsWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:listFunctions");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "listFunctions_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for listFunction method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithOptionalParameters",
            "testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {listFunctions} integration test optional case.")
    public void testListFunctionsWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:listFunctions");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "listFunctions_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for addPermission method with mandatory parameters. Depends on createFunction.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {addPermission} integration test mandatory case.")
    public void testAddPermissionWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:addPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addPermission_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for addPermission method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {addPermission} integration test optional case.")
    public void testAddPermissionWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:addPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addPermission_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for removePermission method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddPermissionWithMandatoryParameters"},
            description = "amazonlambda {removePewrmission} integration test mandatory case.")
    public void testRemovePermissionWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:removePermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removePermission_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for removePermission method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddPermissionWithOptionalParameters"},
            description = "amazonlambda {removePermission} integration test optional case.")
    public void testRemovePermissionWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:removePermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removePermission_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);

    }

    /**
     * Positive test case for createAlias method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {createAlias} integration test mandatory case.")
    public void testCreateAliasWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:createAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createAlias_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for createAlias method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateFunctionWithMandatoryParameters"},
            description = "amazonlambda {createAlias} integration test optional case.")
    public void testCreateAliasWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:createAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createAlias_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for getAlias method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithMandatoryParameters"},
            description = "amazonlambda {getAlias} integration test mandatory case.")
    public void testGetAliasWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getAlias_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for getAlias method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithOptionalParameters"},
            description = "amazonlambda {getAlias} integration test optional case.")
    public void testGetAliasWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getAlias_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for updateAlias method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithMandatoryParameters"},
            description = "amazonlambda {updateAlias} integration test mandatory case.")
    public void testUpdateAliasWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:updateAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "updateAlias_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for updateAlias method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithOptionalParameters"},
            description = "amazonlambda {updateAlias} integration test optional case.")
    public void testUpdateAliasWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:updateAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "updateAlias_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for deleteAlias method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithMandatoryParameters",
            "testGetAliasWithMandatoryParameters",
            "testUpdateAliasWithMandatoryParameters"},
            description = "amazonlambda {deleteAlias} integration test mandatory case.")
    public void testDeleteAliasWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:deleteAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "deleteAlias_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for deleteAlias method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testCreateAliasWithOptionalParameters",
            "testGetAliasWithOptionalParameters",
            "testUpdateAliasWithOptionalParameters"},
            description = "amazonlambda {deleteAlias} integration test optional case.")
    public void testDeleteAliasWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:deleteAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "deleteAlias_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for getAccountSettings method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {getAccountSettings} integration test mandatory case.")
    public void testGetAccountSettingsWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getAccountSettings");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getAccountSettings_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for addLayerVersionPermission method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {addLayerVersionPermission} integration test mandatory case.")
    public void testAddLayerVersionPermissionWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:addLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addLayerVersionPermission_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for addLayerVersionPermission method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {addLayerVersionPermission} integration test optional case.")
    public void testAddLayerVersionPermissionWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:addLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addLayerVersionPermission_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Positive test case for removeLayerVersionPermission method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddLayerVersionPermissionWithMandatoryParameters"},
            description = "amazonlambda {removeLayerVersionPermission} integration test mandatory case.")
    public void testRemoveLayerVersionPermissionWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:removeLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removeLayerVersionPermission_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for removeLayerVersionPermission method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"testAddLayerVersionPermissionWithOptionalParameters"},
            description = "amazonlambda {removeLayerVersionPermission} integration test optional case.")
    public void testRemoveLayerVersionPermissionWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:removeLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removeLayerVersionPermission_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for deleteFunction method with mandatory parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {deleteFunction} integration test Mandatory case.")
    public void testDeleteFunctionWithMandatoryParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:deleteFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "deleteFunction_mandatory.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /**
     * Positive test case for deleteFunction method with optional parameters.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {deleteFunction} integration test optional case.")
    public void testDeleteFunctionWithOptionalParameters() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:deleteFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "deleteFunction_optional.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    /*
    TEST CASES FOR NEGATIVE SCENARIO
     */

    /**
     * Negative test case for addLayerVersionPermission method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {addLayerVersionPermission} integration test negative case.")
    public void testAddLayerVersionPermissionWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:addLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addLayerVersionPermission_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for addPermission method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {addPermission} integration test negative case.")
    public void testAddPermissionWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:addPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "addPermission_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for createAlias method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {createAlias} integration test negative case.")
    public void testCreateAliasWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:createAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createAlias_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Negative test case for createFunction method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {createFunction} integration test negative case.")
    public void testCreateFunctionWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:createFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "createFunction_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Negative test case for getAccountSettings method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {getAccountSettings} integration test negative case.")
    public void testGetAccountSettingsWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getAccountSettings");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getAccountSettings_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for getAlias method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {getAlias} integration test negative case.")
    public void testGetAliasWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getAlias_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for getFunction method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {getFunction} integration test negative case.")
    public void testGetFunctionWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunction_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for getFunctionConfiguration method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {getFunctionConfiguration} integration test negative case.")
    public void testGetFunctionConfigurationWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:getFunctionConfiguration");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "getFunctionConfiguration_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for invoke method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {invoke} integration test negative case.")
    public void testInvokeWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:invoke");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "invoke_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for listFunctions method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {listFunctions} integration test negative case.")
    public void testListFunctionsWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:listFunctions");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "listFunctions_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Negative test case for removeLayerVersionPermission method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"},
            description = "amazonlambda {removeLayerVersionPermission} integration test negative case.")
    public void testRemoveLayerVersionPermissionWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:removeLayerVersionPermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removeLayerVersionPermission_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for removePermission method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {removePermission} integration test negative case.")
    public void testRemovePermissionWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:removePermission");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "removePermission_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for updateAlias method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {updateAlias} integration test negative case.")
    public void testUpdateAliasWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:updateAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "updateAlias_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for deleteAlias method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {deleteAlias} integration test negative case.")
    public void testDeleteAliasWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:deleteAlias");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "deleteAlias_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    /**
     * Negative test case for deleteFunction method.
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test(groups = {"wso2.ei"}, description = "amazonlambda {deleteFunction} integration test negative case.")
    public void testDeleteFunctionWithNegativeCase() throws IOException, JSONException {

        eiRequestHeadersMap.put("Action", "urn:deleteFunction");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                        "deleteFunction_negative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 403);
    }

    private String clearLog(Object middle) {

        return "\n======================================\n" + middle + "\n======================================\n";
    }

    private String prettyJson(JSONObject json) throws JSONException {

        return json.toString(2);
    }

}
