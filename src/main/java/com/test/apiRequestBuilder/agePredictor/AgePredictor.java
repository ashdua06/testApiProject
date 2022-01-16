package com.test.apiRequestBuilder.agePredictor;

import com.test.apiRequestBuilder.APIInterface;
import com.test.exceptions.AgePredictorException;
import com.test.global.APIEndpoints;
import com.test.global.LocalConfig;
import com.test.helpers.api.BaseApi;
import com.test.helpers.api.*;
import com.test.model.request.GetAgeRequestPojo;
import com.test.model.response.GetAgeResponsePojo;
import io.restassured.response.Response;

public class AgePredictor extends BaseApi implements APIInterface {
    private Response response;
    private GetAgeRequestPojo getAgeRequestPojo;
    private GetAgeResponsePojo getAgeResponsePojo;

    public AgePredictor(GetAgeRequestPojo getAgeRequestPojo){
        this.getAgeRequestPojo=getAgeRequestPojo;
        setMethod(MethodType.GET);
        setContentType(ContentType.JSON);
        setBaseUri(LocalConfig.AGE_PREDICTOR_URL);
        setBasePath(APIEndpoints.AGE_PREDICTOR.GET_AGE);
        addQueryParam("name",this.getAgeRequestPojo.getName());
        addHeader("uniqueTrackingId",this.getAgeRequestPojo.getUniqueTrackingId());
    }

    @Override
    public GetAgeRequestPojo getRequestPojo() {
        return getAgeRequestPojo;
    }

    @Override
    public GetAgeResponsePojo getResponsePojo() {
        return getAgeResponsePojo;
    }

    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        try{
            response=execute();
            getAgeResponsePojo=JacksonJsonImpl.getInstance().fromJson(response.asString(),GetAgeResponsePojo.class);
        }
        catch (Exception e){
            throw new AgePredictorException("Error in executing get age api response",e);
        }
    }
}
