# Working with Functions in Amazon Lambda
[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview
The following operations allow you to work with functions in Amazon Lambda. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with functions, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [addPermission](#granting-permission-to-use-a-function-to-an-aws-service-or-another-account)    | Grants an AWS service or another account permission to use a function. |
| [createFunction](#creating-a-lambda-function)      | Creates a new function.  |
| [deleteFunction](#deleting-a-lambda-function)      | Deletes a Lambda function.  |
| [getFunction](#getting-details-about-the-function-or-function-version)     | Returns information about the function or function version.  |
| [getFunctionConfiguration](#getting-the-version-specific-settings-of-a-lambda-function-or-version)     | Returns the version-specific settings of a Lambda function or version.  |
| [invoke](#invoking-a-lambda-function)     | Invokes a Lambda function.  |
| [listFunctions](#returning-a-list-of-functions)     | Returns a list of Lambda functions, with the version-specific configuration of each.  |
| [removePermission](#revoking-permission-to-use-function-from-an-aws-service-or-another-account)     | Revokes function-use permission from an AWS service or another account.  |

## Operation details
This section provides details on each of the operations.

### Granting permission to use a function to an AWS service or another account
The addPermission implementation of the POST operation grants an AWS service or another account permission to use a function.You can apply the policy at the function level, or specify a qualifier to restrict access to a single version or alias. If you use a qualifier, the invoker must use the full Amazon Resource Name (ARN) of that version or alias to invoke the function. To grant permission to another account, specify the account ID as the Principal. For AWS services, the principal is a domain-style identifier defined by the service, like s3.amazonaws.com or sns.amazonaws.com. For AWS services, you can also specify the ARN or owning account of the associated resource as the SourceArn or SourceAccount. If you grant permission to a service principal without specifying the source, other accounts could potentially configure resources in their account to invoke your Lambda function. This action adds a statement to a resource-based permission policy for the function. For more information about function policies, see [Lambda Function Policies](https://docs.aws.amazon.com/lambda/latest/dg/access-control-resource-based.html).

**addPermission**
```xml
<amazonlambda.addPermission>
    <functionName>{$ctx:functionName}</functionName>
    <permissionAction>{$ctx:permissionAction}</permissionAction>
    <permissionStatementId>{$ctx:permissionStatementId}</permissionStatementId>
    <permissionPrincipal>{$ctx:permissionPrincipal}</permissionPrincipal>
    <permissionQualifier>{$ctx:permissionQualifier}</permissionQualifier>
    <apiVersionAddPermission>{$ctx:apiVersionAddPermission}</apiVersionAddPermission>

</amazonlambda.addPermission>
```

**Properties**
* apiVersionAddPermission : API version for AddPermission method.
* functionName : Name of the Lambda function, version, or alias.
* permissionAction : The action that the principal can use on the function.For example, lambda:InvokeFunction or lambda:GetFunction.
* permissionStatementId : A statement identifier that differentiates the statement from others in the same policy.
* permissionPrincipal : The AWS service or account that invokes the function. If you specify a service, use SourceArn or SourceAccount to limit who can invoke the function through that service.
* permissionQualifier : Specify a version or alias.

**Sample request**

Following is a sample REST request that can be handled by the addPermission operation.
```json
{
    "secretAccessKey":"0b+fcboKq87Nf7mH6M55z8I*****************",
    "accessKeyId":"AKIAJHJX************",
    "region":"us-east-2",
    "blocking":"false",
    "functionName":"testFunction",
    "permissionAction":"lambda:addPermission",
    "permissionPrincipal":"s3.amazonaws.com",
    "permissionStatementId":"Permisssion_Added182p",
    "apiVersionAddPermission":"2015-03-31"
}
```

**Sample response**

Given below is a sample response for the addPermission operation.

    Status: 201 Created
```json
{
    "Statement": "{\"Sid\":\"Permisssion_Added182p\",\"Effect\":\"Allow\",\"Principal\":{\"Service\":\"s3.amazonaws.com\"},\"Action\":\"lambda:addPermission\",\"Resource\":\"arn:aws:lambda:us-east-2:*******:function:testFunction\"}"
}
```
**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_AddPermission.html

## Creating a Lambda function
The createFunction operation creates a Lambda function. To create a function, you need a [ deployment package ](https://docs.aws.amazon.com/lambda/latest/dg/deployment-package-v2.html) and an [ execution role](https://docs.aws.amazon.com/lambda/latest/dg/lambda-permissions.html#lambda-intro-execution-role). The deployment package contains your function code. The execution role grants the function permission to use AWS services, such as Amazon CloudWatch Logs for log streaming and AWS X-Ray for request tracing. A function has an unpublished version, and can have published versions and aliases. The unpublished version changes when you update your function's code and configuration. A published version is a snapshot of your function code and configuration that can't be changed. An alias is a named resource that maps to a version, and can be changed to map to a different version. Use the Publish parameter to create version 1 of your function from its initial configuration. The other parameters let you configure version-specific and function-level settings. You can modify version-specific settings later with [ UpdateFunctionConfiguration ](https://docs.aws.amazon.com/lambda/latest/dg/API_UpdateFunctionConfiguration.html). Function-level settings apply to both the unpublished and published versions of the function, and include tags ([TagResource](https://docs.aws.amazon.com/lambda/latest/dg/API_TagResource.html)) and per-function concurrency limits ([PutFunctionConcurrency](https://docs.aws.amazon.com/lambda/latest/dg/API_PutFunctionConcurrency.html)). If another account or an AWS service invokes your function, use [AddPermission](https://docs.aws.amazon.com/lambda/latest/dg/API_AddPermission.html) to grant permission by creating a resource-based IAM policy. You can grant permissions at the function level, on a version, or on an alias. To invoke your function directly, use [Invoke](https://docs.aws.amazon.com/lambda/latest/dg/API_Invoke.html). To invoke your function in response to events in other AWS services, create an event source mapping ([CreateEventSourceMapping](https://docs.aws.amazon.com/lambda/latest/dg/API_CreateEventSourceMapping.html)), or configure a function trigger in the other service. For more information, see [Invoking Functions](https://docs.aws.amazon.com/lambda/latest/dg/invoking-lambda-functions.html).

**createFunction**
```xml
<amazonlambda.createFunction>
    <apiVersionCreateFunction>{$ctx:apiVersionCreateFunction}</apiVersionCreateFunction>
    <functionName>{$ctx:functionName}</functionName>
    <functionDescription>{$ctx:functionDescription}</functionDescription>
    <s3Bucket>{$ctx:s3Bucket}</s3Bucket>
    <s3Key>{$ctx:s3Key}</s3Key>
    <s3ObjectVersion>{$ctx:s3ObjectVersion}</s3ObjectVersion>
    <zipFile>{$ctx:zipFile}</zipFile>
    <targetArn>{$ctx:targetArn}</targetArn>
    <environmentVariables>{$ctx:environmentVariables}</environmentVariables>
    <handler>{$ctx:handler}</handler>
    <kmsKeyArn>{$ctx:kmsKeyArn}</kmsKeyArn>
    <layers>{$ctx:layers}</layers>
    <memorySize>{$ctx:memorySize}</memorySize>
    <publish>{$ctx:publish}</publish>
    <role>{$ctx:role}</role>
    <runtime>{$ctx:runtime}</runtime>
    <tags>{$ctx:tags}</tags>
    <timeout>{$ctx:timeout}</timeout>
    <mode>{$ctx:mode}</mode>
    <securityGroupIds>{$ctx:securityGroupIds}</securityGroupIds>
    <subnetIds>{$ctx:subnetIds}</subnetIds>
</amazonlambda.createFunction>
```

**Properties**
* apiVersionCreateFunction : The API version for the CreateFunction method.
* functionName : The name of the Lambda function.
* functionDescription : Contains description of the function.
* s3Bucket : An Amazon S3 bucket name in the same region as your function.
* s3Key : The Amazon S3 key of the deployment package.
* s3ObjectVersion : For versioned objects, the version of the deployment package object to use.
* zipFile : The base64-encoded contents of zip file containing your deployment package. AWS SDK and AWS CLI clients handle the encoding for you.
* targetArn : The Amazon Resource Name (ARN) of an Amazon SQS queue or Amazon SNS topic.
* environmentVariables : Environment variable key-value pairs.
* handler : The name of the method within your code that Lambda calls to execute your function.
* kmsKeyArn : The ARN of the KMS key used to encrypt your function's environment variables. If not provided, AWS Lambda will use a default service key. 
* layers : A list of function layers to add to the function's execution environment.
* memorySize : The amount of memory that your function has access to. Increasing the function's memory also increases it's CPU allocation. The default value is 128 MB. The value must be a multiple of 64 MB.
* publish : Set to true to publish the first version of the function during creation.
* role : The Amazon Resource Name (ARN) of the function’s execution role.
* runtime : The runtime version for the function.Valid Values: nodejs | nodejs4.3 | nodejs6.10 | nodejs8.10 | java8 | python2.7 | python3.6 | python3.7 | dotnetcore1.0 | dotnetcore2.0 | dotnetcore2.1 | nodejs4.3-edge | go1.x | ruby2.5 |.
* tags : The list of tags (key-value-pairs) assigned to the new function. For more information see Tagging Lambda Functions in the AWS Lambda Developer Guide.
* timeout : The amount of time that Lambda allows a function to run before terminating it. The default is 3 seconds. The maximum allowed value is 900 seconds.
* mode : Set Mode to Activate to sample and trace a subset of incoming requests with AWS X-Ray. The tracing mode to Activate to sample and trace a subset of incoming requests with AWS X-Ray.
* securityGroupIds : A list of VPC security groups IDs.
* subnetIds : A list of VPC subnet IDs.

**Sample request**

Following is a sample REST request that can be handled by the createFunction operation.
```json
{
    "secretAccessKey":"0b+fcboKq87Nf7mH6M55z8I*****************",
    "accessKeyId":"AKIAJHJX************",
    "region":"us-east-2",
    "blocking":"false",
    "s3Bucket":"ajbuck8",
    "s3Key":"fnc.zip",
    "s3ObjectVersion":"null",
    "functionName":"createdFunc",
    "handler":"mdhandler",
    "role":"arn:aws:iam::14*****:role/service-role/yfuj",
    "runtime":"python3.7",
    "apiVersionCreateFunction":"2015-03-31"
}
```

**Sample response**

Given below is a sample response for the createFunction operation.

```json
{
    "CodeSha256": "tp34ACQUVOU5YVe84VQUQHsHWdfixrnP/mkMdtt6gEc=",
    "CodeSize": 338,
    "DeadLetterConfig": null,
    "Description": "",
    "Environment": null,
    "FunctionArn": "arn:aws:lambda:us-east-2:*********:function:createdFunc",
    "FunctionName": "createdFunc",
    "Handler": "mdhandler",
    "KMSKeyArn": null,
    "LastModified": "2019-03-05T09:36:27.074+0000",
    "Layers": null,
    "MasterArn": null,
    "MemorySize": 128,
    "RevisionId": "acdf452b-5bf0-4203-9e22-728c200aa42a",
    "Role": "arn:aws:iam::**********:role/service-role/yfuj",
    "Runtime": "python3.7",
    "Timeout": 3,
    "TracingConfig": {
        "Mode": "PassThrough"
    },
    "Version": "$LATEST",
    "VpcConfig": null
}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_CreateFunction.html

## Deleting a Lambda function
Implementation of deleteFunction method deletes a Lambda function. To delete a specific function version, use the Qualifier parameter. Otherwise, all versions and aliases are deleted. To delete Lambda event source mappings that invoke a function, use [DeleteEventSourceMapping](https://docs.aws.amazon.com/lambda/latest/dg/API_DeleteEventSourceMapping.html). For AWS services and resources that invoke your function directly, delete the trigger in the service where you originally configured it.

**deleteFunction**
```xml
<amazonlambda.deleteFunction>
    <functionName>{$ctx:functionName}</functionName>
    <deleteFunctionQualifier>{$ctx:deleteFunctionQualifier}</deleteFunctionQualifier>
    <apiVersionDeleteFunction>{$ctx:apiVersionDeleteFunction}</apiVersionDeleteFunction>
</amazonlambda.deleteFunction>
```

**Properties**
* apiVersionDeleteFunction : API version for DeleteFunction method.
* functionName  : The name of the Lambda function.
* deleteFunctionQualifier: Specify a version to delete. You can't delete a version that's referenced by an alias.

**Sample request**

Following is a sample REST request that can be handled by the deleteFunction operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJX************",
  "region":"us-east-1",
  "blocking":"false",
  "functionName":"func",
  "apiVersionDeleteFunction":"2015-03-31"
}
```

**Sample response**

Given below is a sample response for the deleteFunction operation.

    Status: 204 No Content


**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_DeleteFunction.html

## Getting details about the function or function version
Implementation of getFunction operation return information about the function or function version, with a link to download the deployment package that's valid for 10 minutes. If you specify a function version, only details that are specific to that version are returned.

**getFunction**
```xml
<amazonlambda.getFunction>
    <functionName>{$ctx:functionName}</functionName>
    <qualifier>{$ctx:qualifier}</qualifier>
    <apiVersionGetFunction>{$ctx:apiVersionGetFunction}</apiVersionGetFunction>
</amazonlambda.getFunction>
```

**Properties**
* apiVersionGetFunction : API version for GetFunction method.
* functionName : The name of the Lambda function.
* qualifier : Specify a version or alias.

**Sample request**

Following is a sample REST request that can be handled by the getFunction operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-2",
  "blocking":"false",
  "functionName":"Fn",
  "qualifier":"$LATEST",
  "apiVersionGetFunction":"2015-03-31"
}
```

**Sample response**

Given below is a sample response for the getFunction operation.

```json
{
    "Code": {
        "Location": "https://awslambda-us-east-2-tasks.s3.us-east-2.amazonaws.com/snapshots/1*****6/test-9f25e193-f604-4d9e-83f1-1254f57e92bc?versionId=wGTdzzK2xtmCGZdt_kgFyy4dlBV8qr1N&X-Amz-Security-Token=FQoGZXIvYXdzEFoaDGu12sbFFNlw0JI6rCK3A6sbM%2FoxC7a2gKuwHXuKoacmpYJa0L%2FtR%2B52PUf9Pbxh2K4OOg5iffmAhfRV%2BpdhyLs32zWlkiYXRpZseDeZPAbofXMZSoLDWhtLVB0EmLTwz33gX8EQfrsvAJa2xWyM9bsebmNwHe9jTa56DvfaQzPEEa4QXpzWEKH8i5%2FSz9iNCrQhbRP%2B5dvclV%2FULql2gMPlxbwPIZNIYdF1xZuddIGcZInkrEHL3956%2B0kHag%2FL%2FoWzN81IGkySbjKNgRFeLxlDEn9ZpDiC%2FdrnNqJ%2FuBdgben7T1ZV3ck5ra0aT7XKaZhDtEN4jHv0sw3O9rORxvlne50TZ56aVePW%2FpUekHjTUiMgrwG%2B2J4uXl2ht2lTJQW3heAFFCoo1DawPlSG%2Fszht8Mt%2BhkHOrE7Re2GRTlnj0jEzEtqgp3JjuaYZU7dtbU4PhbvavF2LtxWFin9p0hWGkcMjKWuWDTaHLdj%2FzTSkS3qifkD9k34B6P%2BaQE1liduGSwK4CgNGNIP5PISt%2Fyoq2Gii1A3yIKyFgeL1W3cJ%2FuhVL9iC%2FsAN6AMkGMsNNjO%2BxvlclQ0YNK10sGhsc7A0z0Cvsgo0O344wU%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20190305T090047Z&X-Amz-SignedHeaders=host&X-Amz-Expires=599&X-Amz-Credential=ASIARQRML75E7CY33SUO%2F20190305%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Signature=e82c9ea475e1ba363b6e061c2eebeded0dfd8f275ad8313e16f42430a4f4819b",
        "RepositoryType": "S3"
    },
    "Concurrency": null,
    "Configuration": {
        "CodeSha256": "pETr5sslHxypYmc5mm3M8j3RFMB2G5f5y8lQM/7ZVIs=",
        "CodeSize": 262,
        "DeadLetterConfig": null,
        "Description": "",
        "Environment": null,
        "FunctionArn": "arn:aws:lambda:us-east-2:********:function:test:$LATEST",
        "FunctionName": "test",
        "Handler": "index.handler",
        "KMSKeyArn": null,
        "LastModified": "2019-03-05T08:43:52.123+0000",
        "Layers": [
            {
                "Arn": "arn:aws:lambda:us-east-2:*******:layer:ballerina-09903:1",
                "CodeSize": 177304793,
                "UncompressedCodeSize": 207173983
            }
        ],
        "MasterArn": null,
        "MemorySize": 128,
        "RevisionId": "1da07f2e-469d-4981-a350-38bb01f19167",
        "Role": "arn:aws:iam::**********:role/test-role",
        "Runtime": "nodejs8.10",
        "Timeout": 3,
        "TracingConfig": {
            "Mode": "PassThrough"
        },
        "Version": "$LATEST",
        "VpcConfig": {
            "SecurityGroupIds": [],
            "SubnetIds": [],
            "VpcId": "",
            "VpcSetupStatus": null,
            "VpcSetupStatusReason": null
        }
    },
    "Tags": null
}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_GetFunction.html

## Getting the version specific settings of a Lambda function or version
Implementation of getFunctionConfiguration operation returns the version-specific settings of a Lambda function or version. The output includes only option that can vary between versions of a function. To modify these settings, use [UpdateFunctionConfiguration](https://docs.aws.amazon.com/lambda/latest/dg/API_UpdateFunctionConfiguration.html). To get all of a function's details, including function-level settings, use [GetFunction](https://docs.aws.amazon.com/lambda/latest/dg/API_GetFunction.html).

**getFunctionConfiguration**
```xml
<amazonlambda.getFunctionConfiguration>
    <functionName>{$ctx:functionName}</functionName>
    <qualifier>{$ctx:qualifier}</qualifier>
    <apiVersionGetFunctionConfiguration>{$ctx:apiVersionGetFunctionConfiguration}</apiVersionGetFunctionConfiguration>
</amazonlambda.getFunctionConfiguration>
```

**Properties**
* apiVersionGetFunctionConfiguration : API version for GetFunctionConfiguration method.
* functionName : The name of the Lambda function.
* qualifier : Specify a version or alias.

**Sample request**

Following is a sample REST request that can be handled by the getFunctionConfiguration operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-2",
  "blocking":"false",
  "functionName":"test",
  "qualifier":"$LATEST",
  "apiVersionGetFunctionConfiguration":"2015-03-31"
}
```

**Sample response**

Given below is a sample response for the getFunctionConfiguration operation.

    Status: 200 OK
```json
{
    "CodeSha256": "pETr5sslHxypYmc5mm3M8j3RFMB2G5f5y8lQM/7ZVIs=",
    "CodeSize": 262,
    "DeadLetterConfig": null,
    "Description": "",
    "Environment": null,
    "FunctionArn": "arn:aws:lambda:us-east-2:*********:function:test:$LATEST",
    "FunctionName": "test",
    "Handler": "index.handler",
    "KMSKeyArn": null,
    "LastModified": "2019-03-05T08:43:52.123+0000",
    "Layers": [
        {
            "Arn": "arn:aws:lambda:us-east-2:***********:layer:ballerina-09903:1",
            "CodeSize": 177304793,
            "UncompressedCodeSize": 207173983
        }
    ],
    "MasterArn": null,
    "MemorySize": 128,
    "RevisionId": "1da07f2e-469d-4981-a350-38bb01f19167",
    "Role": "arn:aws:iam::*********:role/test-role",
    "Runtime": "nodejs8.10",
    "Timeout": 3,
    "TracingConfig": {
        "Mode": "PassThrough"
    },
    "Version": "$LATEST",
    "VpcConfig": {
        "SecurityGroupIds": [],
        "SubnetIds": [],
        "VpcId": "",
        "VpcSetupStatus": null,
        "VpcSetupStatusReason": null
    }
}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_GetFunctionConfiguration.html

## Invoking a Lambda function
Implementation of invoke operation invokes a Lambda function. You can invoke a function synchronously (and wait for the response), or asynchronously. To invoke a function asynchronously, set InvocationType to Event. For synchronous invocation, details about the function response, including errors, are included in the response body and headers. For either invocation type, you can find more information in the [execution log](https://docs.aws.amazon.com/lambda/latest/dg/monitoring-functions.html) and trace. To record function errors for asynchronous invocations, configure your function with a [dead letter queue](https://docs.aws.amazon.com/lambda/latest/dg/dlq.html). The status code in the API response doesn't reflect function errors. Error codes are reserved for errors that prevent your function from executing, such as permissions errors, [limit errors](https://docs.aws.amazon.com/lambda/latest/dg/limits.html), or issues with your function's code and configuration. For example, Lambda returns TooManyRequestsException if executing the function would cause you to exceed a concurrency limit at either the account level (ConcurrentInvocationLimitExceeded) or function level (ReservedFunctionConcurrentInvocationLimitExceeded). For functions with a long timeout, your client might be disconnected during synchronous invocation while it waits for a response. Configure your HTTP client, SDK, firewall, proxy, or operating system to allow for long connections with timeout or keep-alive settings. This operation requires permission for the lambda:InvokeFunction action.

**invoke**
```xml
<amazonlambda.invoke>
    <functionName>{$ctx:functionName}</functionName>
    <apiVersionInvoke>{$ctx:apiVersionInvoke}</apiVersionInvoke>
    <qualifier>{$ctx:qualifier}</qualifier>
    <x-amz-invocation-type>{$ctx:x-amz-invocation-type}</x-amz-invocation-type>
    <x-amz-log-type>{$ctx:x-amz-log-type}</x-amz-log-type>
    <x-amz-client-context>{$ctx:x-amz-client-context}</x-amz-client-context>
    <payload>{$ctx:payload}</payload>
</amazonlambda.invoke>
```

**Properties**
* apiVersionInvoke : API version for Invoke method.
* functionName : The name of the Lambda function.
* qualifier : Specify a version or alias to invoke.
* x-amz-invocation-type : It specifies the way you want to invoke the function.
        
    Choose from the following options.
        
        i)    RequestResponse (default) - Invoke the function synchronously. Keep the connection open until the function returns a response or times out. The API response includes the function response and additional data.
        
        ii)   Event - Invoke the function asynchronously. Send events that fail multiple times to the function's dead-letter queue (if it's configured). The API response only includes a status code.
        
        iii)  DryRun - Validate parameter values and verify that the user or role has permission to invoke the function.
        
* x-amz-log-type: It specifies whether to include the execution log in the response. Set to Tail to include it in the response. Valid values are: None and Tail
* x-amz-client-context: It's the base64-encoded data about the invoking client to pass to the function in the context object. It can be up to 3583 bytes. 
* payload: The JSON that you want to provide to your Lambda function as input.

**Sample request**

Following is a sample REST request that can be handled by the invoke operation.
```json
{
	"secretAccessKey":"0b+fcboKq87Nf7m****************",
	"accessKeyId":"AKIAJHJXWUY*********",
	"region":"us-east-1",
	"blocking":"false",
	"functionName":"LambdawithLayer",
	"apiVersionInvoke":"2015-03-31"
}
```

**Sample response**

Given below is a sample response for the invoke operation.

```json
{
    "body": "Hello from Lambda Layers!",
    "statusCode": 200
}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_Invoke.html

## Returning a list of functions
The listMethod implementation returns a list of Lambda functions, with the version-specific configuration of each. Set FunctionVersion to ALL to include all published versions of each function in addition to the unpublished version. To get more information about a function or version, use [GetFunction](https://docs.aws.amazon.com/lambda/latest/dg/API_GetFunction.html).

**listFunctions**
```xml
<amazonlambda.listFunctions>
    <functionVersion>{$ctx:functionVersion}</functionVersion>
    <apiVersionListFunctions>{$ctx:apiVersionListFunctions}</apiVersionListFunctions>
    <marker>{$ctx:marker}</marker>
    <masterRegion>{$ctx:masterRegion}</masterRegion>
    <maxItems>{$ctx:maxItems}</maxItems>
</amazonlambda.listFunctions>
```

**Properties**
* apiVersionListFunction : API version for ListFunctions method.
* functionVersion : Version name which specifies the version to include in entries for each function. Set to ALL to include entries for all published versions of each function.
* marker : It specifies the pagination token that is returned by a previous request to retrieve the next page of results.
* masterRegion : For Lambda@Edge functions, the AWS Region of the master function. For example, us-east-2 or ALL. If specified, you must set FunctionVersion to ALL..
* maxItems : It specifies the value, ranging from 1 to 10000, to limit the number of functions in the response. 

**Sample request**

Following is a sample REST request that can be handled by the listFunctions operation.
```json
{
    "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
    "accessKeyId":"AKIAJHJ*************",
    "region":"us-east-1",
    "blocking":"false",
    "functionVersion":"ALL",
    "marker":"1",
    "masterRegion":"us-east-1",
    "maxItems":"3",
    "apiVersionListFunctions":"2015-03-31"
}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_ListFunctions.html

## Revoking permission to use function from an AWS service or another account
The removePermission implementation of the DELETE method revokes function-use permission from an AWS service or another account. You can get the ID of the statement from the output of [GetPolicy](https://docs.aws.amazon.com/lambda/latest/dg/API_GetPolicy.html).

**removePermission**
```xml
<amazonlambda.removePermission>
    <functionName>{$ctx:functionName}</functionName>
    <apiVersionRemovePermission>{$ctx:apiVersionRemovePermission}</apiVersionRemovePermission>
    <permissionStatementId>{$ctx:permissionStatementId}</permissionStatementId>
    <permissionQualifier>{$ctx:permissionQualifier}</permissionQualifier>
    <permissionRevisionId>{$ctx:permissionRevisionId}</permissionRevisionId>
</amazonlambda.removePermission>
```

**Properties**
* apiVersionRemovePermission : API version for RemovePermission method.
* functionName : Name of the Lambda function.
* permissionStatementId : Statement ID of the permission to remove.
* permissionQualifier : It specifies a version or alias to remove permission from a published version of the function.
* permissionRevisionId : It's a Id which allow to update the policy only if the revision ID matches the ID that's specified. Use this option to avoid modifying a policy that has changed since you last read it.

**Sample request**

Following is a sample REST request that can be handled by the removePermission operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-1",
  "blocking":"false",
  "functionName":"Fn",
  "permissionStatementId":"Permisssion_Added1443p",
  "apiVersionRemovePermission":"2015-03-31"
}
```

**Sample response**

Given below is a sample response for the removePermission operation.

    Status: 204 No Content

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_RemovePermission.html

### Sample configuration

Following example illustrates how to connect to Amazon Lambda with the init operation and addPermission operation.

1. Create a sample proxy as below :
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
      name="amazonlambda_addPermission"
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
        <property expression="json-eval($.action)" name="action"/>
        <property expression="json-eval($.statementId)" name="statementId"/>
	    <property expression="json-eval($.principal)" name="principal"/>
	    <property expression="json-eval($.apiVersionAddPermission)" name="apiVersionAddPermission"/>
        <amazonlambda.init>
           <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
           <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
           <region>{$ctx:region}</region>
           <blocking>{$ctx:blocking}</blocking>
        </amazonlambda.init>
        <amazonlambda.addPermission>
            <functionName>{$ctx:functionName}</functionName>
            <permissionAction>{$ctx:permissionAction}</permissionAction>
            <permissionStatementId>{$ctx:permissionStatementId}</permissionStatementId>
            <permissionPrincipal>{$ctx:permissionPrincipal}</permissionPrincipal>
            <apiVersionAddPermission>{$ctx:apiVersionAddPermission}</apiVersionAddPermission>	
	    </amazonlambda.addPermission>           
        <respond/>
     </inSequence>
  </target>
  <description/>
</proxy>


```
2. Create a json file named addPermission.json and copy the configurations given below to it:
```json
{
    "secretAccessKey":"b+fcbf7mH6M*****************",
    "accessKeyId":"AKIAJHJ**********",
    "region":"us-east-2",
    "blocking":"false",
    "functionName":"createdFuncLast",
    "permissionAction":"lambda:addPermission",
    "permissionPrincipal":"s3.amazonaws.com",
    "permissionStatementId":"Permisssion_Added",
    "apiVersionAddPermission":"2015-03-31"
}
```

3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/amazonlambda_addPermission -H "Content-Type: application/json" -d @addPermission.json

```
5. Amazon Lambda returns a json response similar to the one shown below:
 
```json
{
    "Statement": "{\"Sid\":\"Permisssion_Added\",\"Effect\":\"Allow\",\"Principal\":{\"Service\":\"s3.amazonaws.com\"},\"Action\":\"lambda:addPermission\",\"Resource\":\"arn:aws:lambda:us-east-2:*********:function:createdFuncLast\"}"
}
```
