

# Working with Layers in Amazon Lambda
[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview
The following operations allow you to work with layers in Amazon Lambda. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with functions, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [addLayerVersionPermission](#adding-permission-to-the-resource-based-policy-of-lambda-layer)    | Adds permission to the resource-based policy of a version of an AWS Lambda layer. |
| [removeLayerVersionPermission](#revoking-permission-to-the-resource-based-policy-of-lambda-layer)     | Revokes permission to the resource-based policy of a version of an AWS Lambda layer.  |

## Operation details
This section provides details on each of the operations.

## Adding permission to the resource based policy of Lambda layer
Adds permissions to the resource-based policy of a version of an [AWS Lambda layer](https://docs.aws.amazon.com/lambda/latest/dg/configuration-layers.html). Use this action to grant layer usage permission to other accounts. You can grant permission to a single account, all AWS accounts, or all accounts in an organization. To revoke permission, call [RemoveLayerVersionPermission](https://docs.aws.amazon.com/lambda/latest/dg/API_RemoveLayerVersionPermission.html) with the statement ID that you specified when you added it.

**addLayerVersionPermission**
```xml
<amazonlambda.addLayerVersionPermission>
    <layerName>{$ctx:layerName}</layerName>
    <layerVersionNumber>{$ctx:layerVersionNumber}</layerVersionNumber>
    <layerRevisionId>{$ctx:layerRevisionId}</layerRevisionId>
    <layerAction>{$ctx:layerAction}</layerAction>
    <layerOrganizationId>{$ctx:layerOrganizationId}</layerOrganizationId>
    <layerPrincipal>{$ctx:layerPrincipal}</layerPrincipal>
    <layerStatementId>{$ctx:layerStatementId}</layerStatementId>
</amazonlambda.addLayerVersionPermission>
```

**Properties**
* layerName: The name or Amazon Resource Name (ARN) of the layer.
* layerVersionNumber: The version number.
* layerRevisionId: Only update the policy if the revision ID matches the ID specified. Use this option to avoid modifying a policy that has changed since you last read it.
* layerAction: The API action that grants access to the layer. For example, lambda:GetLayerVersion.
* layerOrganizationId: With the principal set to *, grant permission to all accounts in the specified organization.
* layerPrincipal: An account ID, or * to grant permission to all AWS accounts.
* layerStatementId: An identifier that distinguishes the policy from others on the same layer version.

**Sample request**

Following is a sample REST request that can be handled by the addLayerVersionPermission operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-2",
  "blocking":"false",
  "layerVersionNumber":"1",
  "layerPrincipal":"*",
  "layerStatementId":"Permisssion_Added",
  "layerAction":"lambda:GetLayerVersion",
  "layerName":"CustomFunction"
}
```

**Sample response**

Given below is a sample response for the addLayerVersionPermission operation.

```json
{
    "RevisionId": "632d9fdb-a063-4309-99f5-023762923216",
    "Statement": "{\"Sid\":\"Layer_Version_Permisssion_Added\",\"Effect\":\"Allow\",\"Principal\":\"*\",\"Action\":\"lambda:GetLayerVersion\",\"Resource\":\"arn:aws:lambda:us-east-2:**********:layer:CustomFunction:1\"}"
}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_AddLayerVersionPermission.html

## Revoking permission to the resource based policy of Lambda layer
Removes a statement from the permissions policy for a version of an AWS Lambda layer. For more information, see [AddLayerVersionPermission](https://docs.aws.amazon.com/lambda/latest/dg/API_AddLayerVersionPermission.html).

**removeLayerVersionPermission**
```xml
<amazonlambda.removeLayerVersionPermission>
    <layerName>{$ctx:layerName}</layerName>
    <layerVersionNumber>{$ctx:layerVersionNumber}</layerVersionNumber>
    <layerStatementId>{$ctx:layerStatementId}</layerStatementId>
    <layerRevisionId>{$ctx:layerRevisionId}</layerRevisionId>
</amazonlambda.removeLayerVersionPermission>
```

**Properties**
* layerName: The name or Amazon Resource Name (ARN) of the layer.
* layerVersionNumber: The version number of layer.
* layerRevisionId: Only update the policy if the revision ID matches the ID specified. Use this option to avoid modifying a policy that has changed since you last read it.
* layerStatementId: An identifier that distinguishes the policy from others on the same layer version.

**Sample request**

Following is a sample REST request that can be handled by the removeLayerVersionPermission operation.
```json
{
	"secretAccessKey":"ZvLiOJbh/Gm5o/wE9l7+kAVtjDRg414a/Ev8sF0M",
	"accessKeyId":"AKIAIZCDHDKX7DBMEKSA",
	"region":"us-east-2",
	"blocking":"false",
	"layerVersionNumber":"1",
	"layerStatementId":"Layer_Version_Permisssion_Added",
	"layerName":"CustomFunction"
}
```

**Sample response**

Given below is a sample response for the removeLayerVersionPermission operation.


    Status: 204 No Content


**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_RemoveLayerVersionPermission.html

### Sample configuration

Following example illustrates how to connect to Amazon Lambda with the init operation and addLayerVersionPermission operation.

1. Create a sample proxy as below :
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
      name="amazonlambda_addLayerVersionPermission"
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
        <property expression="json-eval($.layerName)" name="layerName"/>
        <property expression="json-eval($.layerVersionNumber)" name="layerVersionNumber"/>
        <property expression="json-eval($.layerRevisionId)" name="layerRevisionId"/>
        <property expression="json-eval($.layerAction)" name="layerAction"/>
        <property expression="json-eval($.layerOrganizationId)" name="layerOrganizationId"/>
        <property expression="json-eval($.layerPrincipal)" name="layerPrincipal"/>        
        <property expression="json-eval($.layerStatementId)" name="layerStatementId"/>
        <amazonlambda.init>
           <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
           <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
           <region>{$ctx:region}</region>
           <blocking>{$ctx:blocking}</blocking>
        </amazonlambda.init>
        <amazonlambda.addLayerVersionPermission>
            <layerName>{$ctx:layerName}</layerName>
            <layerVersionNumber>{$ctx:layerVersionNumber}</layerVersionNumber>
            <layerRevisionId>{$ctx:layerRevisionId}</layerRevisionId>
            <layerAction>{$ctx:layerAction}</layerAction>
            <layerOrganizationId>{$ctx:layerOrganizationId}</layerOrganizationId>
            <layerPrincipal>{$ctx:layerPrincipal}</layerPrincipal>	
            <layerStatementId>{$ctx:layerStatementId}</layerStatementId>
	    </amazonlambda.addLayerVersionPermission>           
        <respond/>
     </inSequence>
  </target>
  <description/>
</proxy>


```
2. Create a json file named addLayerVersionPermission.json and copy the configurations given below to it:
```json
{
    "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
    "accessKeyId":"AKIAJHJ*************",
    "region":"us-east-1",
    "blocking":"false",
    "layerVersionNumber":"1",
    "layerPrincipal":"*",
    "layerStatementId":"Layer_Version_Permisssion_Added",
    "layerAction":"lambda:GetLayerVersion",
    "layerName":"CustomFunction"
}
```

3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/amazonlambda_addLayerVersionPermission -H "Content-Type: application/json" -d @addLayerVersionPermission.json
```
5. Amazon Lambda returns a json response similar to the one shown below:
 
```json
{
    "RevisionId": "632d9fdb-a063-4309-99f5-023762923216",
    "Statement": "{\"Sid\":\"Layer_Version_Permisssion_Added\",\"Effect\":\"Allow\",\"Principal\":\"*\",\"Action\":\"lambda:GetLayerVersion\",\"Resource\":\"arn:aws:lambda:us-east-2:********:layer:CustomFunction:1\"}"
}
```
