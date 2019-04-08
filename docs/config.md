# Configuring Amazon Lambda Operations
[[Signing Up for AWS]](#signing-up-for-aws ) [[Obtaining user credentials]](#obtaining-user-credentials) [[Initializing the Connector]](#initializing-the-Connector)

To use the AmazonLambda service, you must have an AWS account. If you don't already have an account, you are prompted to create one when you sign up. You're not charged for any AWS services that you sign up for unless you use them.
## Signing Up for AWS



* **To sign up for AWS:**

    1. Navigate to this link https://aws.amazon.com/, and then choose Create an AWS Account.

        > NOTE: If you previously signed in to the AWS Management Console using AWS account root user credentials, choose Sign in to a different account. If you previously signed in to the console using IAM credentials, choose Sign-in using root account credentials. Then choose Create a new AWS account.

     2. Follow the online instructions.

Part of the sign-up procedure involves receiving a phone call and entering a verification code using the phone keypad.AWS will notify you by email when your account is active and available for you to use.

## Obtaining user credentials

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

## Initializing the Connector

To use the Amazon Lambda connector, add the <amazonlambda.init> element in your configuration before carrying out any Amazon Lambda operations. This Amazon Lambda configuration authenticates with Amazon Lambda by specifying the AWS access key ID and secret access key ID, which are used for every operation. The signature is used with every request and thus differs based on the request the user makes.

**init**
```xml
<amazonlambda.init>
    <region>{$ctx:region}</region>
    <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
    <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
    <blocking>{$ctx:blocking}</blocking>
</amazonlambda.init>
```
**Properties** 
* region: The region of the application access. 
* secretAccessKey: The secret access key.
* accessKeyId: The accessKeyId of the user account to generate the signature.
* blocking: Boolean type, this property helps the connector perform blocking invocations to Amazon DynamoDB.

Now that you have connected to Amazon Lambda, use the information in the following topics to perform various operations with the connector.

* [Working with Accounts in Amazon Lambda](account.md)
* [Working with Alias in Amazon Lambda](aliases.md)
* [Working with Function in Amazon Lambda](functions.md)
* [Working with Layer in Amazon Lambda](layers.md)
