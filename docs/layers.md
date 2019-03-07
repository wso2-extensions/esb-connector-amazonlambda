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
    <versionNumber>{$ctx:versionNumber}</versionNumber>
    <revisionId>{$ctx:revisionId}</revisionId>
    <action>{$ctx:action}</action>
    <organizationId>{$ctx:organizationId}</organizationId>
    <principal>{$ctx:principal}</principal>
    <statementId>{$ctx:statementId}</statementId>
</amazonlambda.addLayerVersionPermission>
```

**Properties**
* layerName: The name or Amazon Resource Name (ARN) of the layer.
* versionNumber: The version number.
* revisionId: Only update the policy if the revision ID matches the ID specified. Use this option to avoid modifying a policy that has changed since you last read it.
* action: The API action that grants access to the layer. For example, lambda:GetLayerVersion.
* organizationId: With the principal set to *, grant permission to all accounts in the specified organization.
* principal: An account ID, or * to grant permission to all AWS accounts.
* statementId: An identifier that distinguishes the policy from others on the same layer version.

**Sample request**

Following is a sample REST request that can be handled by the addLayerVersionPermission operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-1",
  "blocking":"false",
  "versionNumber":"arn:aws:lambda:us-east-1:66******:layer:AWSLambda-Python37-SciPy1x:2",
  "principal":"*",
  "statementId":"Permisssion_Added171p",
  "action":"lambda:GetLayerVersion",
  "layerName":"arn:aws:lambda:us-east-1:66******:layer:AWSLambda-Python37-SciPy1x"
}
```

**Sample response**

Given below is a sample response for the addLayerVersionPermission operation.

```json
{

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
    <versionNumber>{$ctx:versionNumber}</versionNumber>
    <statementId>{$ctx:statementId}</statementId>
    <revisionId>{$ctx:revisionId}</revisionId>
</amazonlambda.removeLayerVersionPermission>
```

**Properties**
* layerName: The name or Amazon Resource Name (ARN) of the layer.
* versionNumber: The version number.
* revisionId: Only update the policy if the revision ID matches the ID specified. Use this option to avoid modifying a policy that has changed since you last read it.
* statementId: An identifier that distinguishes the policy from others on the same layer version.

**Sample request**

Following is a sample REST request that can be handled by the removeLayerVersionPermission operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-1",
  "blocking":"false"
//  incomplete
}
```

**Sample response**

Given below is a sample response for the removeLayerVersionPermission operation.

```json
{

}
```

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
        <property expression="json-eval($.versionNumber)" name="versionNumber"/>
        <property expression="json-eval($.revisionId)" name="revisionId"/>
        <property expression="json-eval($.action)" name="action"/>
        <property expression="json-eval($.organizationId)" name="organizationId"/>
        <property expression="json-eval($.principal)" name="principal"/>        
        <property expression="json-eval($.statementId)" name="statementId"/>
        <amazonlambda.init>
           <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
           <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
           <region>{$ctx:region}</region>
           <blocking>{$ctx:blocking}</blocking>
        </amazonlambda.init>
        <amazonlambda.addLayerVersionPermission>
            <layerName>{$ctx:layerName}</layerName>
            <versionNumber>{$ctx:versionNumber}</versionNumber>
            <revisionId>{$ctx:revisionId}</revisionId>
            <action>{$ctx:action}</action>
            <organizationId>{$ctx:organizationId}</organizationId>
            <principal>{$ctx:principal}</principal>	
            <statementId>{$ctx:statementId}</statementId>
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
    "versionNumber":"arn:aws:lambda:us-east-1:66******:layer:AWSLambda-Python37-SciPy1x:2",
    "principal":"*",
    "statementId":"Permisssion_Added171p",
    "action":"lambda:GetLayerVersion",
    "layerName":"arn:aws:lambda:us-east-1:66******:layer:AWSLambda-Python37-SciPy1x"
}
```

3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
CURL COMMAND HERE
```
5. Amazon Lambda returns a json response similar to the one shown below:
 
```json
{
"RESPONSE":"BODY HERE"
}
```