# AmazonLambda EI Connector


The AmazonLambda Connector allows you to access the REST API of [Amazon Web Service Lambda (AWS Lambda)](https://docs.aws.amazon.com/lambda/latest/dg/welcome.html) ,
which lets you run code without provisioning or managing servers. With Lambda, you can run code for virtually any type of application or backend service - all with zero administration. Just upload your code  in one of the languages that AWS Lambda supports (currently Node.js, Java, C#, Go and Python) and Lambda takes care of everything required to run and scale your code with high availability. You can set up your code to automatically trigger from other AWS services or call it directly from any web or mobile app. You pay only for the compute time you consume-there is no charge when your code is not running.

## Compatibility

| Connector Version | Supported WSO2 EI version |
| ------------- |-------------|
| 1.0.0    | EI 6.4.0 |
 
>Notes:
  The API Version for each method seems to vary. So please use respective Version for each method to make request to API. 

## Getting started

#### Download and install the connector

1. Download the connector from the [WSO2 Store](https://store.wso2.com/publisher/assets/esbconnector/details/fb0aec6a-3525-4bc4-952e-0b60489a82bc) by clicking the **Download Connector** button.
2. Then you can follow this [Documentation](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+the+Management+Console) to add and enable the connector via the Management Console in your EI instance.
3. For more information on using connectors and their operations in your EI configurations, see [Using a Connector](https://docs.wso2.com/display/EI640/Using+a+Connector).
4. If you want to work with connectors via EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+Tooling).

#### Configuring the connector operations

To get started with Amazon Lambda connector and their operation, see [Configuring Amazon Lambda Operations](docs/config.md).

## Building From the Source

Follow the steps given below to build the Amazon Lambda connector from the source code:

1. Get a clone or download the source from [Github](https://github.com/wso2-extensions/esb-connector-amazonlambda).
2. Run the following Maven command from the `esb-connector-amazonlambda` directory: `mvn clean install`.
3. The AmazonLambda connector zip file is created in the `esb-connector-amazonlambda/target` directory

## How You Can Contribute

As an open source project, WSO2 extensions welcome contributions from the community.
Check the [issue tracker](https://github.com/wso2-extensions/esb-connector-amazonlambda/issues) for open issues that interest you. We look forward to receiving your contributions.
