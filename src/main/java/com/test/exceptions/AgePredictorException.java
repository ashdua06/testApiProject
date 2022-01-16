package com.test.exceptions;

public class AgePredictorException extends RuntimeException{
    public AgePredictorException() {
    }

    private AgePredictorException(String var1) {
        super(var1);
    }

    public AgePredictorException(Object var1) {
        this(String.valueOf(var1));
        if (var1 instanceof Throwable) {
            this.initCause((Throwable) var1);
        }

    }

    public AgePredictorException(boolean var1) {
        this(String.valueOf(var1));
    }

    public AgePredictorException(char var1) {
        this(String.valueOf(var1));
    }

    public AgePredictorException(int var1) {
        this(String.valueOf(var1));
    }

    public AgePredictorException(long var1) {
        this(String.valueOf(var1));
    }

    public AgePredictorException(float var1) {
        this(String.valueOf(var1));
    }

    public AgePredictorException(double var1) {
        this(String.valueOf(var1));
    }

    public AgePredictorException(String var1, Throwable var2) {
        super(var1, var2);
    }

}
