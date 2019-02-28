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

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Sample integration test
 */
public class AmazonLambdaConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> eiRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();
    private static int SLEEP_TIME;

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        String connectorName = System.getProperty("connector_name") + "-connector-" +
                System.getProperty("connector_version") + ".zip";
        init(connectorName);
        getApiConfigProperties();


        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");
        SLEEP_TIME = Integer.parseInt(connectorProperties.getProperty("sleepTime"));
    }

//    @Test(groups = {"wso2.ei"})
//    public void testAddLayerVersionPermissionWithMandatoryParameters() throws Exception {
//        eiRequestHeadersMap.put("Action", "urn:addLayerVersionPermission");
//        RestResponse<JSONObject> eiRestResponse =
//                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "addLayerVersionPermission_mandatory.json");
//
//        log.info(eiRestResponse.getBody());
//        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
//    }

    /**
     * Positive test case for addPermission method with mandatory parameters.
     *
     */
    @Test(groups = {"wso2.ei"})
    public void testAddPermissionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:addPermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "addPermission_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }
    /*
    @Test(groups = {"wso2.ei"})
    public void testCreateAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "createAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }

    @Test(groups = {"wso2.ei"})
    public void testCreateFunctionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:createFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "createFunction_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 201);
    }
    @Test(groups = {"wso2.ei"})
    public void testGetAccountSettingsWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getAccountSettings");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "GET", eiRequestHeadersMap, "getAccountSettings_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(groups = {"wso2.ei"})
    public void testGetAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "GET", eiRequestHeadersMap, "getAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(groups = {"wso2.ei"})
    public void testGetFunctionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "GET", eiRequestHeadersMap, "getFunction_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(groups = {"wso2.ei"})
    public void testGetFunctionConfigurationWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:getFunctionConfiguration");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "GET", eiRequestHeadersMap, "getFunctionConfiguration_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

//    @Test(groups = {"wso2.ei"})
//    public void testInvokeWithMandatoryParameters() throws Exception {
//        eiRequestHeadersMap.put("Action", "urn:invoke");
//        RestResponse<JSONObject> eiRestResponse =
//                sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "invoke_mandatory.json");
//
//        log.info(eiRestResponse.getBody());
//        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
//    }

    @Test(groups = {"wso2.ei"})
    public void testListFunctionsWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:listFunctions");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "GET", eiRequestHeadersMap, "listFunctions_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

//    @Test(groups = {"wso2.ei"})
//    public void testRemoveLayerVersionPermissionWithMandatoryParameters() throws Exception {
//        eiRequestHeadersMap.put("Action", "urn:removeLayerVersionPermission");
//        RestResponse<JSONObject> eiRestResponse =
//                sendJsonRestRequest(proxyUrl, "DELETE", eiRequestHeadersMap, "removeLayerVersionPermission_mandatory.json");
//
//        log.info(eiRestResponse.getBody());
//        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
//    }

    @Test(groups = {"wso2.ei"})
    public void testRemovePermissionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:removePermission");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "DELETE", eiRequestHeadersMap, "removePermission_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    @Test(groups = {"wso2.ei"})
    public void testUpdateAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:updateAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "PUT", eiRequestHeadersMap, "updateAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(groups = {"wso2.ei"})
    public void testDeleteAliasWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteAlias");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "DELETE", eiRequestHeadersMap, "deleteAlias_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    @Test(groups = {"wso2.ei"})
    public void testDeleteFunctionWithMandatoryParameters() throws Exception {
        eiRequestHeadersMap.put("Action", "urn:deleteFunction");
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(proxyUrl, "DELETE", eiRequestHeadersMap, "deleteFunction_mandatory.json");

        log.info(eiRestResponse.getBody());
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 204);
    }

    */


}