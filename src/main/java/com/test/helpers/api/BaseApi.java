package com.test.helpers.api;
import com.test.helpers.api.ContentType;
import com.test.helpers.api.MethodType;
import com.test.helpers.report.FileHelper;
import com.test.helpers.report.LoggingManager;
import com.test.helpers.report.ReportHelper;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
public class BaseApi {

    private static ReportHelper reporter = new ReportHelper();
    private static FileHelper fileHelper = new FileHelper();
    private static Logger logger = LoggingManager.getLogger(BaseApi.class);

    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private MethodType method;
    private Object body;
    private ContentType contentType;
    private String baseUri;
    private Map<String, Object> pathParams = new HashMap<>();
    private Map<String, Object> queryParams = new HashMap<>();
    private Map<String, Object> formURLEncoded = new HashMap<>();
    private Map<String, Object> params = new HashMap<>();
    private String basePath;
    private String cookie;
    private Map<String, Object> headers = new HashMap<>();
    private Response response;
    private JsonPath jsonPathevaluator;
    private Boolean captureAPIDetails = true;
    private Boolean redirectFlag = true;
    private String jsonResponseSchema = "";


    public String getJsonResponseSchema() {
        return jsonResponseSchema;
    }

    public void setJsonResponseSchema(String jsonResponseSchema) {
        this.jsonResponseSchema = jsonResponseSchema;
    }

    public Boolean getRedirectFlag() {
        return redirectFlag;
    }

    public void setRedirectFlag(Boolean redirectFlag) {
        this.redirectFlag = redirectFlag;

    }

    public Boolean getCaptureAPIDetails() {
        return captureAPIDetails;
    }

    public void setCaptureAPIDetails(Boolean captureAPIDetails) {
        this.captureAPIDetails = captureAPIDetails;
    }

    public MethodType getMethod() {
        return method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public void setBody(Object obj) {
        this.body = obj;
        requestSpecBuilder.setBody(obj);
    }

    public void setBody(byte[] obj) {
        this.body = obj;
        requestSpecBuilder.setBody(obj);
    }

    public void setBasicAuth(String userName, String password) {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(userName);
        authScheme.setPassword(password);
        requestSpecBuilder.setAuth(authScheme);
    }

    public Object getBody() {
        return this.body;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
        requestSpecBuilder.setContentType(contentType.getContentType());

    }

    public ContentType getContentType() {
        return this.contentType;
    }


    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        requestSpecBuilder.setBaseUri(baseUri);
    }

    public String getBaseUri() {
        return this.baseUri;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
        requestSpecBuilder.addCookie(cookie);
    }

    public String getCookie() {
        return this.cookie;
    }


    public void setBasePath(String basePath) {
        this.basePath = basePath;
        requestSpecBuilder.setBasePath(basePath);
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        requestSpecBuilder.addHeaders(headers);
    }

    public void addHeader(String headerKey, String headerValue) {
        this.headers.put(headerKey, headerValue);
        requestSpecBuilder.addHeader(headerKey, headerValue);
    }

    public Map<String, Object> getHeaders() {
        return this.headers;
    }


    public void addQueryParam(String paramKey, Object paramValue) {
        this.queryParams.put(paramKey, paramValue);
        requestSpecBuilder.addQueryParam(paramKey, paramValue);
    }

    public void addQueryParams(Map<String, String> queryParams) {
        this.queryParams.putAll(queryParams);
        requestSpecBuilder.addQueryParams(queryParams);
    }

    public Map<String, Object> getQueryParams() {
        return this.queryParams;
    }


    public void addPathParam(String paramKey, Object paramValue) {
        this.pathParams.put(paramKey, paramValue);
        requestSpecBuilder.addPathParam(paramKey, paramValue);
    }

    public String getKeyValue(String keyvalue) {
        jsonPathevaluator = response.getBody().jsonPath();
        return (jsonPathevaluator.getString(keyvalue));
    }

    public void addPathParams(Map<String, String> pathParams) {
        this.pathParams.putAll(pathParams);
        requestSpecBuilder.addPathParams(pathParams);
    }

    public Map<String, Object> getPathParams() {
        return this.pathParams;
    }


    public void addFormURLEncoded(String paramKey, Object paramValue) {
        this.formURLEncoded.put(paramKey, paramValue);
        requestSpecBuilder.addFormParam(paramKey, paramValue);
    }


    public void addFormURLEncoded(Map<String, Object> formURLEncoded) {
        this.formURLEncoded.putAll(formURLEncoded);
        requestSpecBuilder.addFormParams(formURLEncoded);
    }

    public Map<String, Object> getFormURLEncoded() {
        return this.formURLEncoded;
    }

    public void addParam(String paramKey, Object paramValue) {
        this.params.put(paramKey, paramValue);
        requestSpecBuilder.addParam(paramKey, paramValue);
    }

    public void addParams(Map<String, String> queryParams) {
        this.params.putAll(queryParams);
        requestSpecBuilder.addParams(queryParams);
    }

    public void addMultiPart(String controlName, File file, String mimeType) {
        requestSpecBuilder.addMultiPart(controlName, file, mimeType);
    }

    public void addMultiPart(String controlName, File file) {
        requestSpecBuilder.addMultiPart(controlName, file);
    }

    public void addMultiPart(String controlName, String contentBody) {
        requestSpecBuilder.addMultiPart(controlName, contentBody);
    }

    public void addMultiPart(String controlName, String contentBody, String mimeType) {
        requestSpecBuilder.addMultiPart(controlName, contentBody, mimeType);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return requestSpecBuilder;
    }


    public Response execute() {
        RequestSpecification requestSpecification = requestSpecBuilder.addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
        Response response;
        RestAssured.defaultParser = Parser.JSON;

        switch (method) {
            case GET:
                response = given().spec(requestSpecification).when().redirects().follow(redirectFlag).get();
                break;
            case POST:
                response = given().spec(requestSpecification).when().redirects().follow(redirectFlag).post();
                break;
            case PUT:
                response = given().spec(requestSpecification).when().redirects().follow(redirectFlag).put();
                break;
            case DELETE:
                response = given().spec(requestSpecification).when().redirects().follow(redirectFlag).delete();
                break;
            case PATCH:
                response = given().spec(requestSpecification).when().redirects().follow(redirectFlag).patch();
                break;
            default:
                throw new RuntimeException("API method not specified");

        }
        this.response = response;
        if (captureAPIDetails) {
            captureAPIDetails();
        }

        return response;
    }

    public void captureAPIDetails() {
        String requestFilePath = "";
        String request=null;
        logger.info(getMethod()+" API \nHeaders: "+getHeaders()+"\nQuery Params: "+getQueryParams());
        logger.info("API Request :"+request);
        logger.info("API Response :"+this.response.asString());
        try {
            if (this.getBody() != null)
                request = "Request body: " + this.getBody();


            long responseTime = this.response.getTime();
            String url = this.baseUri + this.basePath;
            String response = null;
            if (this.response != null)
                response = this.response.asString();
            String headers=null;
            if(getHeaders()!=null)
                headers = JacksonJsonImpl.getInstance().toJSon(getHeaders());

            if (request != null) {
                requestFilePath = fileHelper.createRequestJsonFile(request, "");
            }
            String responseFilePath = fileHelper.createResponseJsonFile(response, "");

            String reportFolder = reporter.getResultFileStringPath();
            reporter.appendresultHTMLReport(reportFolder, url, "<a href='" + requestFilePath + "'>Request Data</a>", "<a href='" + responseFilePath + "'>Response Data</a>", "Response Time(in msec) :- " + String.valueOf(responseTime), "", "", headers);

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("error in capturing api details");
        }
    }

}
