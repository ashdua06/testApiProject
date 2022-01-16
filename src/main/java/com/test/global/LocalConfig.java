package com.test.global;
import com.test.helpers.PropertyUtils;
public class LocalConfig {

    public static final String AGE_PREDICTOR_URL;

    static {
        try {
            PropertyUtils.getInstance().load("config.properties");
            AGE_PREDICTOR_URL = System.getProperty("AGE_PREDICTOR_URL",PropertyUtils.getInstance().getValue("AGE_PREDICTOR_URL"));
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Something wrong !!! Check configurations.", t);
        }
    }
}
