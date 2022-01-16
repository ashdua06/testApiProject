package com.test.validators.assertions;
import com.test.helpers.report.ReportHelper;
import org.testng.Assert;
import java.util.List;
public class CustomAssert {
    private List<Throwable> m_errors;

    private CustomAssert() {
    }

    private boolean status;

    //static TestReportHelper ReportHelper= new TestReportHelper();
    public static void assertTrue(boolean condition, String message) {
        ReportHelper.reporterLogging(condition,message);
        Assert.assertTrue(condition, message);
    }

    public static void assertNotNull(Object condition, String message) {
        ReportHelper.reporterLogging(condition!=null?true:false,message);
        Assert.assertNotNull(condition, message);
    }

    public static void assertFalse(boolean condition, String message) {
        ReportHelper.reporterLogging(!condition,message);
        Assert.assertTrue(!condition, message);
    }

    public static void assertEquals(String actual, String expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual.equals(expected),str);
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertEquals(Boolean actual, Boolean expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual==expected?true:false,str);
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertEquals(double actual, double expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual==expected?true:false,str);
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertEquals(int actual, int expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual==expected?true:false,str);
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertNotEquals(String actual, String expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(!actual.equals(expected),str);
        Assert.assertNotEquals(actual, expected, message);
    }

    public static void assertNotEquals(Boolean actual, Boolean expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual==expected?false:true,str);
        Assert.assertNotEquals(actual, expected, message);
    }


    public static void assertNotEquals(double actual, double expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual==expected?false:true,str);
        Assert.assertNotEquals(actual, expected, message);
    }

    public static void assertNotEquals(int actual, int expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual==expected?false:true,str);
        Assert.assertNotEquals(actual, expected, message);
    }

    public static void assertContainsIgnoreCase(String completeString, String subString, String message) {
        String errorMessage = message + " -- \nComplete String : " + completeString + "\nSubstring : " + subString + "\n\n";
        ReportHelper.reporterLogging(completeString.toLowerCase().contains(subString.toLowerCase()),errorMessage);
        Assert.assertTrue(completeString.toLowerCase().contains(subString.toLowerCase()), errorMessage);
    }


    public static void assertEqualsIgnoreCase(String actual, String expected, String message) {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        ReportHelper.reporterLogging(actual.equalsIgnoreCase(expected),str);
        Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase(), message);
    }

    public static void assertContains(String completeString, String subString, String message) {
        String errorMessage = message + " -- \nComplete String : " + completeString + "\nSubstring : " + subString + "\n\n";
        ReportHelper.reporterLogging(completeString.contains(subString),errorMessage);
        Assert.assertTrue(completeString.contains(subString), errorMessage);
    }

    public static void assertNotContains(String completeString, String subString, String message) {
        String errorMessage = message + " -- \nComplete String : " + completeString + "\nSubstring : " + subString + "\n\n";
        ReportHelper.reporterLogging(!completeString.contains(subString),errorMessage);
        Assert.assertTrue(!completeString.contains(subString), errorMessage);
    }

    public static void assertFail(String message) {
        ReportHelper.reporterLogging(false,message);
        Assert.fail(message);
    }


}
