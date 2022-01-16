package com.test.validators.api;

import com.test.apiRequestBuilder.agePredictor.AgePredictor;
import com.test.exceptions.AgePredictorException;
import com.test.validators.assertions.CustomAssert;

public class AgeValidator {
    private static volatile AgeValidator instance;

    private AgeValidator() {
    }

    public static AgeValidator getInstance() {
        if (instance == null) {
            synchronized (AgeValidator.class) {
                if (instance == null) {
                    instance = new AgeValidator();
                }
            }
        }
        return instance;
    }

    public void validateGetAgeResponse(AgePredictor obj){
        try{
            CustomAssert.assertEquals(obj.getApiResponse().getStatusCode(),obj.getRequestPojo().getTestMeta().getExpectedStatusCode()," HTTP Status code validation");
            CustomAssert.assertEquals(obj.getResponsePojo().getName(),obj.getRequestPojo().getName()," Name Validation in response");
            CustomAssert.assertEquals(obj.getResponsePojo().getAge(),obj.getRequestPojo().getTestMeta().getExpectedAge()," Age Validation in response");
        }
        catch (Exception e){
            throw new AgePredictorException("Error in validating age api response ",e);
        }

    }
}
