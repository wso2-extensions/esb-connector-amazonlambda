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
    <action>{$ctx:action}</action>
    <statementId>{$ctx:statementId}</statementId>
    <principal>{$ctx:principal}</principal>
</amazonlambda.addPermission>
```

**Properties**
* functionName: Name of the Lambda function, version, or alias.
* action: The action that the principal can use on the function.For example, lambda:InvokeFunction or lambda:GetFunction.
* statementId: A statement identifier that differentiates the statement from others in the same policy.
* principal: The AWS service or account that invokes the function. If you specify a service, use SourceArn or SourceAccount to limit who can invoke the function through that service.

**Sample request**

Following is a sample REST request that can be handled by the addPermission operation.
```json
{
    "secretAccessKey":"0b+fcboKq87Nf7mH6M55z8I*****************",
    "accessKeyId":"AKIAJHJX************",
    "region":"us-east-1",
    "blocking":"false",
    "functionName":"Fn",
    "action":"lambda:addPermission",
    "principal":"s3.amazonaws.com",
    "statementId":"Permisssion_Added1443p"
}
```

**Sample response**

Given below is a sample response for the addPermission operation.

```json
{

}
```
**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_AddPermission.html

## Creating a Lambda function
The createFunction operation creates a Lambda function. To create a function, you need a [ deployment package ](https://docs.aws.amazon.com/lambda/latest/dg/deployment-package-v2.html) and an [ execution role](https://docs.aws.amazon.com/lambda/latest/dg/lambda-permissions.html#lambda-intro-execution-role). The deployment package contains your function code. The execution role grants the function permission to use AWS services, such as Amazon CloudWatch Logs for log streaming and AWS X-Ray for request tracing. A function has an unpublished version, and can have published versions and aliases. The unpublished version changes when you update your function's code and configuration. A published version is a snapshot of your function code and configuration that can't be changed. An alias is a named resource that maps to a version, and can be changed to map to a different version. Use the Publish parameter to create version 1 of your function from its initial configuration. The other parameters let you configure version-specific and function-level settings. You can modify version-specific settings later with [ UpdateFunctionConfiguration ](https://docs.aws.amazon.com/lambda/latest/dg/API_UpdateFunctionConfiguration.html). Function-level settings apply to both the unpublished and published versions of the function, and include tags ([TagResource](https://docs.aws.amazon.com/lambda/latest/dg/API_TagResource.html)) and per-function concurrency limits ([PutFunctionConcurrency](https://docs.aws.amazon.com/lambda/latest/dg/API_PutFunctionConcurrency.html)). If another account or an AWS service invokes your function, use [AddPermission](https://docs.aws.amazon.com/lambda/latest/dg/API_AddPermission.html) to grant permission by creating a resource-based IAM policy. You can grant permissions at the function level, on a version, or on an alias. To invoke your function directly, use [Invoke](https://docs.aws.amazon.com/lambda/latest/dg/API_Invoke.html). To invoke your function in response to events in other AWS services, create an event source mapping ([CreateEventSourceMapping](https://docs.aws.amazon.com/lambda/latest/dg/API_CreateEventSourceMapping.html)), or configure a function trigger in the other service. For more information, see [Invoking Functions](https://docs.aws.amazon.com/lambda/latest/dg/invoking-lambda-functions.html).

**createFunction**
```xml
<amazonlambda.createFunction>
    <functionName>{$ctx:functionName}</functionName>
    <description>{$ctx:description}</description>
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
* functionName: The name of the Lambda function.
* description: Contains description of the function.
* s3Bucket: An Amazon S3 bucket name in the same region as your function.
* s3Key: The Amazon S3 key of the deployment package.
* s3ObjectVersion: For versioned objects, the version of the deployment package object to use.
* zipFile: The base64-encoded contents of zip file containing your deployment package. AWS SDK and AWS CLI clients handle the encoding for you.
* targetArn: The Amazon Resource Name (ARN) of an Amazon SQS queue or Amazon SNS topic.
* environmentVariables: Environment variable key-value pairs.
* handler: The name of the method within your code that Lambda calls to execute your function.
* kmsKeyArn: The ARN of the KMS key used to encrypt your function's environment variables. If not provided, AWS Lambda will use a default service key. 
* layers: A list of function layers to add to the function's execution environment.
* memorySize: The amount of memory that your function has access to. Increasing the function's memory also increases it's CPU allocation. The default value is 128 MB. The value must be a multiple of 64 MB.
* publish: Set to true to publish the first version of the function during creation.
* role: The Amazon Resource Name (ARN) of the function’s execution role.
* runtime: The runtime version for the function.Valid Values: nodejs | nodejs4.3 | nodejs6.10 | nodejs8.10 | java8 | python2.7 | python3.6 | python3.7 | dotnetcore1.0 | dotnetcore2.0 | dotnetcore2.1 | nodejs4.3-edge | go1.x | ruby2.5 |.
* tags: The list of tags (key-value-pairs) assigned to the new function. For more information see Tagging Lambda Functions in the AWS Lambda Developer Guide.
* timeout: The amount of time that Lambda allows a function to run before terminating it. The default is 3 seconds. The maximum allowed value is 900 seconds.
* mode: Set Mode to Activate to sample and trace a subset of incoming requests with AWS X-Ray. The tracing mode to Activate to sample and trace a subset of incoming requests with AWS X-Ray.
* securityGroupIds: A list of VPC security groups IDs.
* subnetIds: A list of VPC subnet IDs.

**Sample request**

Following is a sample REST request that can be handled by the createFunction operation.
```json
{
    "secretAccessKey":"0b+fcboKq87Nf7mH6M55z8I*****************",
    "accessKeyId":"AKIAJHJX************",
    "region":"us-east-1",
    "blocking":"false",
    "s3Bucket":"bucketName",
    "s3Key":"fnc.zip",
    "s3ObjectVersion":"22ic0JWCGdaE6uWr5o******",
    "functionName":"createdFunc",
    "handler":"mdhandler",
    "role":"arn:aws:iam::6********798:role/aj@role8421",
    "runtime":"python3.7"
}
```

**Sample response**

Given below is a sample response for the createFunction operation.

```json
{

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
    <qualifier>{$ctx:qualifier}</qualifier>
</amazonlambda.deleteFunction>
```

**Properties**
* functionName: The name of the Lambda function.
* qualifier: Specify a version to delete. You can't delete a version that's referenced by an alias.

**Sample request**

Following is a sample REST request that can be handled by the deleteFunction operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJX************",
  "region":"us-east-1",
  "blocking":"false",
  "functionName":"func"
}
```

**Sample response**

Given below is a sample response for the deleteFunction operation.

```json
{

}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_DeleteFunction.html

## Getting details about the function or function version
Implementation of getFunction operation return information about the function or function version, with a link to download the deployment package that's valid for 10 minutes. If you specify a function version, only details that are specific to that version are returned.

**getFunction**
```xml
<amazonlambda.getFunction>
    <functionName>{$ctx:functionName}</functionName>
    <qualifier>{$ctx:qualifier}</qualifier>
</amazonlambda.getFunction>
```

**Properties**
* functionName: The name of the Lambda function.
* qualifier: Specify a version to delete. You can't delete a version that's referenced by an alias.

**Sample request**

Following is a sample REST request that can be handled by the getFunction operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-1",
  "blocking":"false",
  "functionName":"Fn",
  "qualifier":"$LATEST"
}
```

**Sample response**

Given below is a sample response for the getFunction operation.

```json
{

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
</amazonlambda.getFunctionConfiguration>
```

**Properties**
* functionName: The name of the Lambda function.
* qualifier: Specify a version to delete. You can't delete a version that's referenced by an alias.

**Sample request**

Following is a sample REST request that can be handled by the getFunctionConfiguration operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-1",
  "blocking":"false",
  "functionName":"Fn",
  "qualifier":"$LATEST"
}
```

**Sample response**

Given below is a sample response for the getFunctionConfiguration operation.

```json
{

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
    <qualifier>{$ctx:qualifier}</qualifier>
    <x-amz-invocation-type>{$ctx:x-amz-invocation-type}</x-amz-invocation-type>
    <x-amz-log-type>{$ctx:x-amz-log-type}</x-amz-log-type>
    <x-amz-client-context>{$ctx:x-amz-client-context}</x-amz-client-context>
    <requestPayload>{$ctx:requestPayload}</requestPayload>
</amazonlambda.invoke>
```

**Properties**
* functionName: The name of the Lambda function.
* qualifier: Specify a version to delete. You can't delete a version that's referenced by an alias.
* x-amz-invocation-type: It specifies the way you want to invoke the function.
        
    Choose from the following options.
        
        i)    RequestResponse (default) - Invoke the function synchronously. Keep the connection open until the function returns a response or times out. The API response includes the function response and additional data.
        
        ii)   Event - Invoke the function asynchronously. Send events that fail multiple times to the function's dead-letter queue (if it's configured). The API response only includes a status code.
        
        iii)  DryRun - Validate parameter values and verify that the user or role has permission to invoke the function.
        
* x-amz-log-type: It specifies whether to include the execution log in the response. Set to Tail to include it in the response. Valid values are: None and Tail
* x-amz-client-context: It's the base64-encoded data about the invoking client to pass to the function in the context object. It can be up to 3583 bytes. 
* requestPayload: The JSON that you want to provide to your Lambda function as input.

**Sample request**

Following is a sample REST request that can be handled by the invoke operation.
```json
{
	"secretAccessKey":"0b+fcboKq87Nf7m****************",
	"accessKeyId":"AKIAJHJXWUY*********",
	"region":"us-east-1",
	"blocking":"false",
	"functionName":"arn:aws:lambda:us-east-1:61*********:function:Fn",
	"qualifier":"$LATEST",
	"x-amz-invocation-type":"Event",
	"x-amz-log-type":"Tail"
}
```

**Sample response**

Given below is a sample response for the invoke operation.

```json
{

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
    <marker>{$ctx:marker}</marker>
    <masterRegion>{$ctx:masterRegion}</masterRegion>
    <maxItems>{$ctx:maxItems}</maxItems>
</amazonlambda.listFunctions>
```

**Properties**
* functionVersion: Version name which specifies the version to include in entries for each function. Set to ALL to include entries for all published versions of each function.
* marker: It specifies the pagination token that is returned by a previous request to retrieve the next page of results.
* masterRegion: For Lambda@Edge functions, the AWS Region of the master function. For example, us-east-2 or ALL. If specified, you must set FunctionVersion to ALL..
* maxItems: It specifies the value, ranging from 1 to 10000, to limit the number of functions in the response. 

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
    "maxItems":"3"
}
```

**Sample response**

Given below is a sample response for the listFunctions on operation.

```json
{

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
    <statementId>{$ctx:statementId}</statementId>
    <qualifier>{$ctx:qualifier}</qualifier>
    <revisionId>{$ctx:revisionId}</revisionId>
</amazonlambda.removePermission>
```

**Properties**
* functionName: Name of the Lambda function.
* statementId: Statement ID of the permission to remove.
* qualifier: It specifies a version or alias to remove permission from a published version of the function.
* revisionId: It's a Id which allow to update the policy only if the revision ID matches the ID that's specified. Use this option to avoid modifying a policy that has changed since you last read it.

**Sample request**

Following is a sample REST request that can be handled by the removePermission operation.
```json
{
  "secretAccessKey":"0b+fcboKq87Nf7mH6M**********************",
  "accessKeyId":"AKIAJHJ*************",
  "region":"us-east-1",
  "blocking":"false",
  "functionName":"Fn",
  "statementId":"Permisssion_Added1443p"
}
```

**Sample response**

Given below is a sample response for the removePermission on operation.

```json
{

}
```

**Related Amazon Lambda documentation**
https://docs.aws.amazon.com/lambda/latest/dg/API_RemovePermission.html

### Sample configuration

Following example illustrates how to connect to Amazon Lambda with the init operation and METHODNAME operation.

1. Create a sample proxy as below :
```xml
<proxy/>
```
2. Create a json file named METHODNAME.json and copy the configurations given below to it:
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
CURL COMMAND HERE
```
5. Amazon Lambda returns a json response similar to the one shown below:
 
```json
{
"RESPONSE":"BODY HERE"
}
```


