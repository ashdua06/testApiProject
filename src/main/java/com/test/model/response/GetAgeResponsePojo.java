package com.test.model.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.test.apiRequestBuilder.APIInterface;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAgeResponsePojo implements APIInterface.ResponsePojo {
    private String name;
    private int age;
    private int count;

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public Object getErrors() {
        return null;
    }

    @Override
    public Object getBody() {
        return null;
    }
}
