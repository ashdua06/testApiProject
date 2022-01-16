package com.test.apiRequestBuilder;
import io.restassured.response.Response;
public interface APIInterface {

    RequestPojo getRequestPojo();

    ResponsePojo getResponsePojo();

    Response getApiResponse();
    void createRequestJsonAndExecute();

    interface ResponsePojo{
        boolean isSuccess();
        Object getErrors();
        Object getBody();
    }



    interface RequestPojo{
    }

}
