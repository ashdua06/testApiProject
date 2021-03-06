package com.test.testSetup;

public class TestEnvironmentSetup {

    //Values for now are regression, sanity, smoke
    public static final String TESTTYPE = System.getProperty("TestingType", null);
    //Values can be methods, system, package, group, class
    public static final String SUITETYPE = System.getProperty("SuiteType", "package");
    public static final int THREADCOUNT = Integer.parseInt(System.getProperty("ThreadCount", "1"));

    public static void main(String[] args) throws Exception {
        //Test Comment Added
        CreateTestNGXml.createXml(TestEnvironmentSetup.SUITETYPE, TestEnvironmentSetup.TESTTYPE, TestEnvironmentSetup.THREADCOUNT, args[1], args[0]);
    }
}
