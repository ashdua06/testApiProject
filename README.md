                    **API Test Framework**

Framework for API automation testing

**Example Used in framework:**
Used public api to predict the age of a person based on their name.

Sample API URL:
https://api.agify.io?name=himanshu


**Components in src/main/java**
1) apiRequestBuilder: This contains request builder class for each API that need to be automated. This implements API Interface and extends BaseAPI class and define the structure of API
2) exception: Contains custom exceptions
3) global: Contains global classes like API Endpoints and urls
4) helper: It contains all helper classes and utilities like report , listeners ,api
5) model: It contains pojo classes for request and response
6) testSetup: It contains classes to create testNg xml to run automation suite
7) validators: It contains classes for response validation

**Components in src/test/java/systemTesting**
1) basePackage: It contains class to get test data from data source.
2) testPackages: It contains test classes

**What all framework supports**
1) Reporting: Html Report generates in Report folder at the end of suite run. Report contains all assertions , request body , response body and exceptions in case of failures
2) Logging: Log4J used to generate logs in logs folder
3) Profiling: Used profiles to run test suite on different environment like TEST , UAT ,DEV
4) testNg xml auto creation: testNg xml generates at the beginning during run time based on input parameters
5) JacksonJson library: Used jackson json library in pojo and object mapper for serialization and deserialization
6) Jenkins supported: Can be run through jenkins by sending below command and input parameter  mvn clean install -PProfile -DTestingType=testPackage
7) Singleton: Used singleton design pattern at many places in order to make classes thread safe
8) Retry Failed cases: Added listener that retries failed cases


**How to run automation suite**
1) Suite can be run using any of the below ways
    - Update testPackage in testNg xml file and directly run testNg xml
    - Run command  "mvn clean install -PProfile -DTestingType=testPackage" in terminal or pass through jenkins.
2) If user runs through command then first testNg xml will be created and then testNg file will run.
3) Html Report will be generated in Report folder and logs in logs folder




