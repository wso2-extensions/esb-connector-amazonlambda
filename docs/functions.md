# Working with Functions in Amazon Lambda
[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview
The following operations allow you to work with functions in Amazon Lambda. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with functions, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [addPermission](#granting-permission-to-use-a-function-to-an-aws-service-or-another-account)    | Grants an AWS service or another account permission to use a function. |
| [createFunction](#creating-a-lambda-function)      | Creates a new function.  |
| [deleteFunction](#deleting-a-function)      | Deletes a Lambda function.  |
| [getFunction](#getting-details-about-the-function-or-function-version)     | Returns information about the function or function version.  |
| [getFunctionConfiguration](#getting-the-version-specific-settings-of-a-lambda-function-or-version)     | Returns the version-specific settings of a Lambda function or version.  |
| [invoke](#invoking-a-lambda-function)     | Invokes a Lambda function.  |
| [listFunctions](#returning-a-list-of-functions)     | Returns a list of Lambda functions, with the version-specific configuration of each.  |
| [removePermission](#revoking-function-use-permission-from-an-aws-service-or-another-account.)     | Revokes function-use permission from an AWS service or another account.  |

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
* functionName: The name of the Lambda function
* description: Contains description of the function
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
* runtime: The runtime version for the function.Valid Values: nodejs | nodejs4.3 | nodejs6.10 | nodejs8.10 | java8 | python2.7 | python3.6 | python3.7 | dotnetcore1.0 | dotnetcore2.0 | dotnetcore2.1 | nodejs4.3-edge | go1.x | ruby2.5 | 
* tags: The list of tags (key-value-pairs) assigned to the new function. For more information see Tagging Lambda Functions in the AWS Lambda Developer Guide.
* timeout: The amount of time that Lambda allows a function to run before terminating it. The default is 3 seconds. The maximum allowed value is 900 seconds.
* mode: Set Mode to Activate to sample and trace a subset of incoming requests with AWS X-Ray. The tracing mode to Activate to sample and trace a subset of incoming requests with AWS X-Ray.
* securityGroupIds: A list of VPC security groups IDs.
* subnetIds: A list of VPC subnet IDs.

**Sample request**

Following is a sample REST request that can be handled by the createFunction operation.
```json
{

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