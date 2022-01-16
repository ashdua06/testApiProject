package com.test.model.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.test.apiRequestBuilder.APIInterface;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GetAgeRequestPojo implements APIInterface.RequestPojo {
    private String name;
    private String uniqueTrackingId;
    private TestMeta testMeta;

    @Data
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class TestMeta{
        private Map<String,String> tcDescription;
        private int expectedAge;
        private int expectedStatusCode;
    }
}
