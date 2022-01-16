package com.test.helpers.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    static int RETRYLIMIT = 1;
    int counter = 0;

    public static void setRetryLimit(int retryLimit) {
        RetryAnalyzer.RETRYLIMIT = retryLimit;
    }

    @Override
    public boolean retry(ITestResult result) {
        if (counter < RetryAnalyzer.RETRYLIMIT) {
            counter++;
            return true;
        } else {
            return false;
        }

    }

}