# Working with Account in Amazon Lambda

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to work with account. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with account, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [getAccountSettings](#getting-account-settings)    | Retrieves details about your account's limits and usage in an AWS Region.  |

### Operation details

This section provides further details on the operations related to tables.

#### Getting Account Settings

The getAccountSettings retrieves details about your account's limits and usage in an AWS Region.

**createTable**
```xml
<amazonlambda.getAccountSettings/>
```

**Sample request**

Following is a sample request that can be handled by the getAccountSettings operation.
```xml
<updateAlias>
    <secretAccessKey>{$ctx:secretAccessKey}</secretAccessKey>
    <accessKeyId>{$ctx:accessKeyId}</accessKeyId>
    <region>{$ctx:region}</region>
    <blocking>{$ctx:blocking}</blocking>
</updateAlias>
```

**Sample response**

Given below is a sample response for the createTable operation.

```json
{

}
```
**Related Amazon DynamoDB documentation**
https://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_CreateTable.html
