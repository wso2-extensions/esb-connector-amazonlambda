## Integration tests for WSO2 EI Amazon Lambda connector
    
### Pre-requisites:

    - Maven 3.x
    - Java 1.8

### Tested Platforms: 

    - Ubuntu 16.04
    - WSO2 EI 6.4.0

STEPS

 1. Download EI 6.4.0 from official website.

 2. This EI should be configured as below; (If you are using the EI version other than 6.4.0)
    	Please make sure that the below mentioned Axis configurations are enabled (repository/conf/axis2/axis2.xml).

    	    <messageFormatter contentType="application/x-amz-json-1.0"  class="org.apache.synapse.commons.json.JsonStreamFormatter"/>
    	    <messageBuilder contentType="application/x-amz-json-1.0" 	class="org.apache.synapse.commons.json.JsonStreamBuilder"/>

 3. Compress modified EI and copy that zip file in to location "{AMAZONLAMBDA_CONNECTOR_HOME}/repository/".

 4. Prerequisites for AmazonLambda Connector Integration Testing

    Follow these steps before start testing.    
    
        i)  Create a fresh account in Amazon AWS and log on to http://aws.amazon.com/lambda/ with the web browser.
        ii)  Save the AWSAccessKeyId and AWSSecretKey while continuing the registration process.

 5. Update the AmazonLambda properties file at location "{AMAZONLAMBDA_CONNECTOR_HOME}/src/test/resources/artifacts/ESB/connector/config" and "{AMAZONLAMBDA_CONNECTOR_HOME}/repository/esb-connector-amazonlambda.properties" as below.

        i)      accessKeyId                     -   The accessKeyId of the user account to generate the signature.
        ii)     secretAccessKey                 -   The secret access key for the application generated from Amazon.
        iii)    region                          -   The region of the application access.
        iv)     sleepTime                       -   An integer value in milliseconds, to wait between API calls to avoid conflicts at API end.
        

 6. Navigate to "<AMAZONLAMBDA_CONNECTOR_HOME>" and run the following command. <br/>
       ```$ mvn clean install -Dskip-tests=false```
