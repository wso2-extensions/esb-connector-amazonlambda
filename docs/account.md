# Working with Account in Amazon Lambda

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to work with account. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with account, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [getAccountSettings](#getting-account-settings)    | Retrieves details about your account's limits and usage in an AWS Region.  |

### Operation details

This section provides further details on the operation related to account.

#### Getting Account Settings

The getAccountSettings operation retrieves details about your account's limits and usage in an AWS Region.

**getAccountSettings**
```xml
<amazonlambda.getAccountSettings/>
```

**Sample request**

Following is a sample REST request that can be handled by the getAccountSettings operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-2",
  "blocking":"false"
}
```

**Sample response**

Given below is a sample response for the getAccountSettings on operation.

```json
{
    "AccountLimit": {
        "CodeSizeUnzipped": 262144000,
        "CodeSizeZipped": 52428800,
        "ConcurrentExecutions": 1000,
        "TotalCodeSize": 80530636800,
        "UnreservedConcurrentExecutions": 1000,
        "UnreservedConcurrentExecutionsMinimum": null
    },
    "AccountUsage": {
        "FunctionCount": 1,
        "TotalCodeSize": 176268666
    },
    "DeprecatedFeaturesAccess": null,
    "HasFunctionWithDeprecatedRuntime": false,
    "PreviewFeatures": null
}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_GetAccountSettings.html

### Sample configuration

Following example illustrates how to connect to Amazon Lambda with the init operation and getAccountSettings operation.

1. Create a sample proxy as below :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
      name="amazonlambda_getAccountSettings"
      startOnLoad="true"
      statistics="disable"
      trace="disable"
      transports="https, http">
  <target>
     <inSequence>
        <property expression="json-eval($.secretAccessKey)" name="secretAccessKey"/>
        <property expression="json-eval($.accessKeyId)" name="accessKeyId"/>
        <property expression="json-eval($.region)" name="region"/>
        <property expression="json-eval($.blocking)" name="blocking"/>       
        <awslambda.init>
           <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
           <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
           <region>{$ctx:region}</region>
           <blocking>{$ctx:blocking}</blocking>
        </awslambda.init>
        <awslambda.getAccountSettings/>           
        <respond/>
     </inSequence>
  </target>
  <description/>
</proxy>
```
2. Create a json file named getAccountSettings.json and copy the configurations given below to it:

```json
{
    "secretAccessKey":"b+fcbf7mH6M*****************",
    "accessKeyId":"AKIAJHJ**********",
    "region":"us-east-1",
    "blocking":"false"
}
```

3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/amazonlambda_getAccountSettings -H "Content-Type: application/json" -d @getAccountSettings.json
```
5. Amazon Lambda returns a json response similar to the one shown below:
 
```json
{
    "AccountLimit": {
        "CodeSizeUnzipped": 262144000,
        "CodeSizeZipped": 52428800,
        "ConcurrentExecutions": 1000,
        "TotalCodeSize": 80530636800,
        "UnreservedConcurrentExecutions": 1000,
        "UnreservedConcurrentExecutionsMinimum": null
    },
    "AccountUsage": {
        "FunctionCount": 4,
        "TotalCodeSize": 176270013
    },
    "DeprecatedFeaturesAccess": null,
    "HasFunctionWithDeprecatedRuntime": false,
    "PreviewFeatures": null
}
```
