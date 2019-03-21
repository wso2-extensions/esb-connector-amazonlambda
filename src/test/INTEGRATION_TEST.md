## Integration tests for WSO2 EI Amazon Lambda connector
    
### Pre-requisites:

    - Maven 3.x
    - Java 1.8
    -Amazon Credentials

### Tested Platforms: 

    - Ubuntu 16.04
    - WSO2 EI 6.4.0
    
To use the AmazonLambda service, you must have an AWS account. If you don't already have an account, you are prompted to create one when you sign up. You're not charged for any AWS services that you sign up for unless you use them.
### Signing Up for AWS



* **To sign up for AWS:**

    1. Open https://aws.amazon.com/, and then choose Create an AWS Account.

        > NOTE: If you previously signed in to the AWS Management Console using AWS account root user credentials, choose Sign in to a different account. If you previously signed in to the console using IAM credentials, choose Sign-in using root account credentials. Then choose Create a new AWS account.

     2. Follow the online instructions.

Part of the sign-up procedure involves receiving a phone call and entering a verification code using the phone keypad.AWS will notify you by email when your account is active and available for you to use.

### Obtaining user credentials

You can access the Amazon Lambda service using the root user credentials but these credentials allow full access to all resources in the account as you can't restrict permission for root user credentials.If you want to restrict certain resources and allow controlled access to AWS services then you can create IAM(Identity and Access Management) users in your AWS account in that case

* **Follow the steps below to get an AWS Access Key for your AWS root account:**

  1. Go to the AWS Management Console.
  2. Hover over your company name in the right top menu and click "My Security Credentials".
  3. Scroll to the "Access Keys" section.
  4. Click on "Create New Access Key".
  5. Copy both the Access Key ID (YOUR_AMAZON_LAMBDA_KEY) and Secret Access Key (YOUR_AMAZON_LAMBDA_SECRET).

* **Follow the steps below to get an AWS Access Key for an IAM user account:**

  1. Sign in to the AWS Management Console and open the IAM console.
  2. In the navigation pane, choose Users.
  3. Add a checkmark next to the name of the desired user, and then choose User Actions from the top.
  4. Click on Manage Access Keys.
  5. Click on Create Access Key.
  6. Click on Show User Security Credentials. Copy and paste the Access Key ID and Secret Access Key values, or click on Download Credentials to download the credentials in a CSV (file).


### Steps for performing integration test.

 1. Download EI 6.4.0 from official website.

 2. This EI should be configured as below; (If you are using the EI version other than 6.4.0)
    	Please make sure that the below mentioned Axis configurations are enabled (repository/conf/axis2/axis2.xml).

    	    <messageFormatter contentType="application/x-amz-json-1.0"  class="org.apache.synapse.commons.json.JsonStreamFormatter"/>
    	    <messageBuilder contentType="application/x-amz-json-1.0" 	class="org.apache.synapse.commons.json.JsonStreamBuilder"/>

 3. Compress modified EI and copy that zip file in to location "{AMAZONLAMBDA_CONNECTOR_HOME}/repository/".

 4. Update the AmazonLambda properties file at location "{AMAZONLAMBDA_CONNECTOR_HOME}/src/test/resources/artifacts/ESB/connector/config" and "{AMAZONLAMBDA_CONNECTOR_HOME}/repository/esb-connector-amazonlambda.properties" with your value.

        i)        accessKeyId                               -   The accessKeyId of the user account to generate the signature.
        ii)       secretAccessKey                           -   The secret access key for the application generated from Amazon.
        iii)      region                                    -   The region of the application access.
        iv)       blocking                                  -   Boolean value (true/false).
        v)        layerAction                               -   The API action that grants access to the layer. For example, lambda:GetLayerVersion.
        vi)       layerStatementId                          -   An identifier that distinguishes the policy from others on the same layer version.
        vii)      layerPrincipal                            -   An account ID, or * to grant permission to all AWS accounts.
        vii)      layerName                                 -   The name of the layer.
        viii)     layerVersionNumber                        -   The version number of the layer.
        ix)       permissionAction                          -   The action that the principal can use on the function. For example, lambda:InvokeFunction
        x)        permissionStatementId                     -   A statement identifier that differentiates the statement from others in the same policy.
        xi)       permissionPrincipal                       -   The AWS service or account that invokes the function.If you specify a service, use SourceArn or SourceAccount to limit who can invoke the function through that service.
        xii)      permissionQualifier                       -   Specify a version or alias to add/remove permission to/from function.
        xiii)     createAliasDescription                    -   Description about the alias that you are going to create.
        xiv)      aliasName                                 -   Name of the alias.
        xv)       updatedAliasDescription                   -   Description about the to-be-updated alias.
        xvi)      functionNameOptional                      -   Name of function you want to create(using optional test case).
        xvii)     functionName                              -   Name of the function.
        xix)      functionVersion                           -   Version of the function.
        xx)       qualifier                                 -   Specify alias or version.
        xxi)      functionVersionToBeListed                 -   Version name which specifies the version to include in entries for each function. Set to ALL to include entries for all published versions of each function.
        xxii)     masterRegion                              -   For Lambda@Edge functions, the AWS Region of the master function. For example, us-east-2 or ALL. If specified, you must set FunctionVersion to ALL.
        xxiii)    maxItems                                  -   It specifies the value, ranging from 1 to 10000, to limit the number of functions in the response.
        xxiv)     marker                                    -   It specifies the pagination token that is returned by a previous request to retrieve the next page of results.
        xxv)      s3Bucket                                  -   An Amazon S3 bucket name in the same region as your function.
        xxvi)     s3Key                                     -   The Amazon S3 key of the deployment package.
        xxvii)    s3ObjectVersion                           -   For versioned objects, the version of the deployment package object to use.
        xxviii)   zipFile                                   -   The base64-encoded contents of zip file containing your deployment package. AWS SDK and AWS CLI clients handle the encoding for you.
        xxix)     targetArn                                 -   The Amazon Resource Name (ARN) of an Amazon SQS queue or Amazon SNS topic.
        xxx)      functionDescription                       -   Description of the function that you are about to create.
        xxxi)     handler                                   -   The name of the method within your code that Lambda calls to execute your function.
        xxxii)    role                                      -   The Amazon Resource Name (ARN) of the function's execution role.
        xxxiii)   runtime                                   -   The runtime version for the function.Valid Values: nodejs | nodejs4.3 | nodejs6.10 | nodejs8.10 | java8 | python2.7 | python3.6 | python3.7 | dotnetcore1.0 | dotnetcore2.0 | dotnetcore2.1 | nodejs4.3-edge | go1.x | ruby2.5 |.
        xxxiv)    logType                                   -   Specifies whether to include log in the response. Set to Tail to include the execution log in the response. Valid Values: None | Tail 
        xxxv)     apiVersionAddLayerVersionPermission       -   API version for AddLayerVersionPermission method.
        xxxvi)    apiVersionAddPermission                   -   API version for AddPermission method.
        xxxvii)   apiVersionCreateAlias                     -   API version for CreateAlias method.
        xxxviii)  apiVersionCreateFunction                  -   API version for CreateFunction method.
        xxxix)    apiVersionDeleteAlias                     -   API version for DeleteAlias method.
        xl)       apiVersionDeleteFunction                  -   API version for DeleteFunction method.
        xli)      apiVersionGetAccountSettings              -   API version for GetAccountSettings method.
        xlii)     apiVersionGetAlias                        -   API version for GetAlias method.
        xliii)    apiVersionGetFunction                     -   API version for GetFunction method.
        xliv)     apiVersionGetFunctionConfiguration        -   API version for GetFunctionConfiguration method.
        xlv)      apiVersionInvoke                          -   API version for Invoke method.
        xlvi)     apiVersionListFunctions                   -   API version for ListFunctions method.
        xlvii)    apiVersionRemoveLayerVersionPermission    -   API version for RemoveLayerVersionPermission method.
        xlviii)   apiVersionRemovePermission                -   API version for RemovePermission method.
        xlix)     apiVersionUpdateAlias                     -   API version for UpdateAlias method.
        l)        payload                                   -   The JSON that you want to provide to your Lambda function as input for Invoke method.


 5. Navigate to "<AMAZONLAMBDA_CONNECTOR_HOME>" and run the following command. <br/>
       ```$ mvn clean install -Dskip-tests=false```
