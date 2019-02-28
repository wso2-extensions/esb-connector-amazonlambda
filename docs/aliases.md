# Working with Aliases in Amazon Lambda
[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview
The following operations allow you to work with Aliases in Amazon Lambda. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with Aliases, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [createAlias](#creating-an-alias)    | Creates an alias for a Lambda function version. |
| [deleteAlias](#deleting-an-alias)    | Deletes a Lambda function alias. |
| [getAlias](#getting-details-about-lambda-function-alias)      | Returns details about a Lambda function alias.  |
| [updateAlias](#updating-an-alias-configuration)     | Updates the configuration of a Lambda function alias.  |

## Operation details
This section provides details on each of the operations.

## Creating an alias
The createAlias implementation of the POST operation creates an alias for a Lambda function version. Use aliases to provide clients with a function identifier that you can update to invoke a different version. You can also map an alias to split invocation requests between two versions. Use the RoutingConfig parameter to specify a second version and the percentage of invocation requests that it receives.

**createAlias**
```xml
<amazonlambda.createAlias>
    <functionName>{$ctx:functionName}</functionName>
    <description>{$ctx:description}</description>
    <functionVersion>{$ctx:functionVersion}</functionVersion>
    <aliasName>{$ctx:aliasName}</aliasName>
    <additionalVersionWeights>{$ctx:additionalVersionWeights}</additionalVersionWeights>
</amazonlambda.createAlias>
```

**Properties**
* functionName : The name of the Lambda function that the alias invokes.
* description: The description of the alias.
* functionVersion : The function version that the alias invokes.
* aliasName: The name of the alias.
* additionalVersionWeights : The name of second alias, and the percentage of traffic that's routed to it.

**Sample request**

Following is a sample REST request that can be handled by the createAlias operation.
```xml
<createAlias>
    <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
    <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
    <region>{$ctx:region}</region>
    <blocking>{$ctx:blocking}</blocking>
    <functionName>{$ctx:functionName}</functionName>
    <description>{$ctx:description}</description>
    <functionVersion>{$ctx:functionVersion}</functionVersion>
    <aliasName>{$ctx:aliasName}</aliasName>
    <additionalVersionWeights>{$ctx:additionalVersionWeights}</additionalVersionWeights>
</createAlias>
```

**Related Amazon Lambda documentation**
[https://docs.aws.amazon.com/lambda/latest/dg/API_CreateAlias.html](https://docs.aws.amazon.com/lambda/latest/dg/API_CreateAlias.html)

## Deleting an alias
The deleteAlias implementation deletes a Lambda function alias.

**deleteAlias**
```xml
<amazonlambda.deleteAlias>
    <functionName>{$ctx:functionName}</functionName>
    <aliasName>{$ctx:aliasName}</aliasName>
</amazonlambda.deleteAlias>
```

**Properties**
* functionName : The name of the Lambda function that the alias invokes.
* aliasName: The name of the alias.

**Sample request**

Following is a sample REST request that can be handled by the deleteAlias operation.
```xml
<deleteAlias>
    <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
    <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
    <region>{$ctx:region}</region>
    <blocking>{$ctx:blocking}</blocking>
    <functionName>{$ctx:functionName}</functionName>
    <aliasName>{$ctx:aliasName}</aliasName>
</deleteAlias>
```

**Related Amazon Lambda documentation**
[https://docs.aws.amazon.com/lambda/latest/dg/API_DeleteAlias.html](https://docs.aws.amazon.com/lambda/latest/dg/API_DeleteAlias.html)

## Getting details about Lambda function alias
The getAlias implementation of the GET operation returns details about a Lambda function alias.

**getAlias**
```xml
<amazonlambda.getAlias>
    <functionName>{$ctx:functionName}</functionName>
    <aliasName>{$ctx:aliasName}</aliasName>
</amazonlambda.getAlias>
```

**Properties**
* functionName : The name of the Lambda function that the alias invokes.
* aliasName: The name of the alias.

**Sample request**

Following is a sample REST request that can be handled by the getAlias operation.
```xml
<getAlias>
    <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
    <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
    <region>{$ctx:region}</region>
    <blocking>{$ctx:blocking}</blocking>
    <functionName>{$ctx:functionName}</functionName>
    <aliasName>{$ctx:aliasName}</aliasName>
</getAlias>
```

**Related Amazon Lambda documentation**
[https://docs.aws.amazon.com/lambda/latest/dg/API_GetAlias.html](https://docs.aws.amazon.com/lambda/latest/dg/API_GetAlias.html)

## Updating an alias configuration
The updateAlias method implementation updates the configuration of a Lambda function alias.

**updateAlias**
```xml
<amazonlambda.updateAlias>
    <functionName>{$ctx:functionName}</functionName>
    <description>{$ctx:description}</description>
    <functionVersion>{$ctx:functionVersion}</functionVersion>
    <aliasName>{$ctx:aliasName}</aliasName>
    <additionalVersionWeights>{$ctx:additionalVersionWeights}</additionalVersionWeights>
</amazonlambda.updateAlias>
```

**Properties**
* functionName : The name of the Lambda function that the alias invokes.
* description: The description of the alias.
* functionVersion : The function version that the alias invokes.
* aliasName: The name of the alias.
* additionalVersionWeights : The name of second alias, and the percentage of traffic that's routed to it.

**Sample request**

Following is a sample REST request that can be handled by the updateAlias operation.
```xml
<updateAlias>
    <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
    <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
    <region>{$ctx:region}</region>
    <blocking>{$ctx:blocking}</blocking>
    <functionName>{$ctx:functionName}</functionName>
    <description>{$ctx:description}</description>
    <functionVersion>{$ctx:functionVersion}</functionVersion>
    <aliasName>{$ctx:aliasName}</aliasName>
    <additionalVersionWeights>{$ctx:additionalVersionWeights}</additionalVersionWeights>
</updateAlias>
```

**Related Amazon Lambda documentation**
[https://docs.aws.amazon.com/lambda/latest/dg/API_UpdateAlias.html](https://docs.aws.amazon.com/lambda/latest/dg/API_UpdateAlias.html)

## Sample configuration
Following is a sample proxy service that illustrates how to connect to Amazon Lambda with init operation and use the createAlias operation. The sample request for this proxy can be found in createAlias sample request. You can use this sample as a template for using other operations in this category.
    
**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="amazonlambda_createAlias"       
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
         <property expression="json-eval($.functionName)" name="functionName"/>
         <property expression="json-eval($.description)" name="description"/>
         <property expression="json-eval($.functionVersion)" name="functionVersion"/>
         <property expression="json-eval($.aliasName)" name="aliasName"/>
         <property expression="json-eval($.additionalVersionWeights)" name="additionalVersionWeights"/>
         <amazonlambda.init>
            <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
            <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
            <region>{$ctx:region}</region>
            <blocking>{$ctx:blocking}</blocking>
         </amazonlambda.init>
         <amazonlambda.createAlias>
            <functionName>{$ctx:functionName}</functionName>	   
            <description>{$ctx:description}</description>
            <functionVersion>{$ctx:functionVersion}</functionVersion>
            <aliasName>{$ctx:aliasName}</aliasName>
            <additionalVersionWeights>{$ctx:additionalVersionWeights}</additionalVersionWeights>
         </amazonlambda.createAlias>           
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>
```
