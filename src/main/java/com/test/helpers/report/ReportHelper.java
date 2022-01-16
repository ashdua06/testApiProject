package com.test.helpers.report;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.Utils;
public class ReportHelper implements ReportConstants{
    static int count = 0;
    static int passCount = 0;
    static int failCount = 0;
    String newDate = null;
    Date startTime = null;
    private static String resultFolderpath;
    private static Logger logger = LoggingManager.getLogger(ReportHelper.class);
    private static ThreadLocal<String> threadLocalResultFileStringPath = new InheritableThreadLocal();
    private static String suiteFileStringPath;

    public ReportHelper() {
    }

    public static String getResultFolderpath() {
        return resultFolderpath;
    }

    public void setResultFolderpath(String resultFolderpath) {
        ReportHelper.resultFolderpath = resultFolderpath;
    }

    public static String getResultFileStringPath() {
        return (String)threadLocalResultFileStringPath.get();
    }

    public void setResultFileStringPath(String resultFileStringPath) {
        threadLocalResultFileStringPath.set(resultFileStringPath);
    }

    @BeforeSuite(
            alwaysRun = true
    )
    public void beforeSuite() {
        String resultsFolderPath = "./Results";

        try {
            File resultFolder = new File(resultsFolderPath);
            FileUtils.deleteDirectory(resultFolder);
            resultFolder.mkdir();
            this.startTime = new Date();
            SimpleDateFormat oDateFormat = new SimpleDateFormat("dd_MM_YY");
            this.newDate = oDateFormat.format(this.startTime);
            resultFolderpath = resultsFolderPath + "/Result_" + this.newDate;
            File resultFileFolder = new File(resultFolderpath);
            resultFileFolder.mkdir();
            suiteFileStringPath = resultsFolderPath + "/TestResult_" + this.newDate + ".html";
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(suiteFileStringPath, false));
            resultFile.append("<html><HEAD><TITLE>Automation Report</TITLE></HEAD><body><h4 align=\"center\"><FONT COLOR=\"660066\" FACE=\"Arial\"SIZE=5><b>API Automation Test Report</b>");
            resultFile.append("<h4 align='left'> <FONT COLOR=\"660000\" FACE=\"Arial\" SIZE=4.5> Detailed Report</h4><table  border=1 cellspacing=1  cellpadding=1 ><tr>");
            resultFile.append("<td width=150  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Test Scenario</b></td>");
            resultFile.append("<td width=150  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Test Scenario File</b></td>");
            resultFile.append("<td width=150  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Status</b></td>");
            resultFile.append("<td width=150  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Exception</b></td>");
            resultFile.close();
            this.setResultFolderpath(resultFolderpath);
        } catch (IOException var6) {
            logger.error("Result file creation failed with exception - " + var6);
        }

    }

    @AfterSuite(
            alwaysRun = true
    )
    public void afterSuite(ITestContext context) {
        try {
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(suiteFileStringPath, true));
            Date testEndTime = new Date();
            long testDuration = testEndTime.getTime() - this.startTime.getTime();
            resultFile.append("</table>");
            resultFile.append("<h4 align='left'> <FONT COLOR=\"66000\" FACE=\"Arial\" SIZE=4.5> Summary</h4><table cellspacing=1 cellpadding=1 border=1>");
            resultFile.append("<tr><td width=300  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Run Start Time</b></td>");
            resultFile.append("<td width=150 align=\"center\"><FONT COLOR=\"#385daa\" FACE=\"Arial\" SIZE=2.75><b>" + this.startTime + "</b></td></tr>");
            resultFile.append("<tr><td width=300  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Run End Time</b></td>");
            resultFile.append("<td width=150 align=\"center\"><FONT COLOR=\"#385daa\" FACE=\"Arial\" SIZE=2.75><b>" + testEndTime + "</b></td></tr>");
            resultFile.append("<tr><td width=300  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Test Run Duration(in ms)</b></td>");
            resultFile.append("<td width=150 align=\"center\"><FONT COLOR=\"#385daa\" FACE=\"Arial\" SIZE=2.75><b>" + testDuration + "</b></td></tr>");
            resultFile.append("<tr><td width=300  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Total Tests executed</b></td>");
            resultFile.append("<td width=150 align=\"center\"><FONT COLOR=\"#385daa\" FACE=\"Arial\" SIZE=2.75><b>" + String.valueOf(passCount + failCount) + "</b></td></tr>");
            resultFile.append("<tr><td width=300  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Total Pass Test count</b></td>");
            resultFile.append("<td width=150 align=\"center\"><FONT COLOR=\"#385daa\" FACE=\"Arial\" SIZE=2.75><b>" + String.valueOf(passCount) + "</b></td></tr>");
            resultFile.append("<tr><td width=300  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>Total Fail Test count</b></td>");
            resultFile.append("<td width=150 align=\"center\"><FONT COLOR=\"#385daa\" FACE=\"Arial\" SIZE=2.75><b>" + String.valueOf(failCount) + "</b></td></tr>");
            resultFile.append("</table></body></html>");
            resultFile.close();
        } catch (Exception var9) {
            logger.error("Unable to close result file - " + var9);
        }

    }

    @BeforeMethod(
            alwaysRun = true
    )
    public void createinitialHTMLReport(Method method) {
        try {
            UUID randNo = UUID.randomUUID();
            this.setResultFileStringPath(resultFolderpath + "/" + method.getName().toString() + "_" + randNo + ".html");
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(getResultFileStringPath(), true));
            resultFile.append("<html></head><body><title>API Test Report</title><table style=table-layout:fixed; width:40px border = '1'><tr><th>API URL</th><th>Headers</th><th>Request</th><th>Response</th><th>Message</th><th>Status</th><th>Calling Method</th></tr>");
            resultFile.close();
        } catch (Exception var4) {
            this.setResultFileStringPath((String)null);
            logger.error("Initial error report not created - " + var4);
        }

    }

    @AfterMethod(
            alwaysRun = true
    )
    public void appendFinalHTMLReport(ITestResult result) {
        ITestNGMethod testNGMethod = result.getMethod();

        try {
            int number = testNGMethod.getCurrentInvocationCount();
            String status = "Pass";
            String scenario = "";
            if (result.getParameters().length != 0) {
                Object[] var6 = result.getParameters();
                int var7 = var6.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    Object parameter = var6[var8];
                    if (parameter instanceof Map && (((Map)((Map)parameter)).containsKey("TC_DESCRIPTION") || ((Map)((Map)parameter)).containsKey("TCDESCRIPTION"))) {
                        if (((Map)((Map)parameter)).containsKey("TC_DESCRIPTION")) {
                            scenario = ((Map)((Map)parameter)).get("TC_DESCRIPTION").toString();
                        } else {
                            scenario = ((Map)((Map)parameter)).get("TCDESCRIPTION").toString();
                        }
                        break;
                    }
                }
            }

            if (scenario.equals("") && result.getMethod().getDescription() != null) {
                scenario = result.getMethod().getDescription();
            }

            if (System.getProperty("Test Status") != null && "Warning".equalsIgnoreCase(System.getProperty("Test Status"))) {
                status = "Warning";
                ++passCount;
            } else if (result.getStatus() == 2) {
                status = "Fail";
                ++failCount;
            } else if (result.getStatus() == 1) {
                ++passCount;
            }

            ++count;
            String resultFilePath = getResultFileStringPath().split("/Results")[1];
            resultFilePath = "." + resultFilePath;
            if (result.getStatus() != 3) {
                this.appendMethodInformationInSuite(scenario, result.getMethod().getMethodName() + "_" + number, resultFilePath, status, result.getThrowable());
                BufferedWriter resultFile = new BufferedWriter(new FileWriter(getResultFileStringPath(), true));
                resultFile.append("</table></body></html>");
                resultFile.close();
            }

            System.clearProperty("Test Status");
        } catch (IOException var10) {
            logger.error("Report not completed - " + var10);
        }

    }

    public String logException(String methodName, Throwable exception) {
        String exceptionFilePath = "";

        try {
            UUID randNo = UUID.randomUUID();
            exceptionFilePath = resultFolderpath + "/" + methodName + "_Exception_" + randNo + ".html";
            String stackTraceLine = "";
            String str = Utils.stackTrace(exception, true)[0];
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(exceptionFilePath, true));
            resultFile.append("<html></head><body><title>Exception Stack Trace</title>");
            Scanner scanner = new Scanner(str);

            while(scanner.hasNext()) {
                stackTraceLine = scanner.nextLine();
                resultFile.append(stackTraceLine + "<br>");
            }

            resultFile.close();
        } catch (Exception var9) {
            logger.error("Exception not reported - " + var9);
        }

        return exceptionFilePath;
    }

    public void appendMethodInformationInSuite(String scenarioName, String methodName, String methodFilePath, String status, Throwable exception) {
        boolean hasThrowable = exception != null;
        String exceptionFilePath = "";
        if (hasThrowable) {
            exceptionFilePath = this.logException(methodName, exception);
            exceptionFilePath = "." + exceptionFilePath.split("/Results")[1];
        }

        try {
            String statusTag = null;
            if (status.equalsIgnoreCase("Pass")) {
                statusTag = "<td bgcolor='#00FF7F'>";
            } else if (status.equalsIgnoreCase("Fail")) {
                statusTag = "<td bgcolor='#FF4500'>";
            } else if (status.equalsIgnoreCase("Warning")) {
                statusTag = "<td bgcolor='#FFEA62'>";
            } else {
                statusTag = "<td>";
            }

            BufferedWriter resultFile = new BufferedWriter(new FileWriter(suiteFileStringPath, true));
            resultFile.append("<tr>");
            resultFile.append("<td>" + scenarioName + "</td>");
            resultFile.append("<td><a href='" + methodFilePath + "' />" + methodName + "</td>");
            resultFile.append(statusTag + status + "</td>");
            if (!exceptionFilePath.equals("")) {
                resultFile.append("<td><a href='" + exceptionFilePath + "'/> Exception </td>");
            } else {
                resultFile.append("<td></td>");
            }

            resultFile.append("</tr>");
            resultFile.close();
        } catch (IOException var10) {
            logger.error("Error in appending information to suite - " + var10);
        }

    }

    public static void appendresultHTMLReport(String resultFileStringPath, String apiURL, String requestFile, String responseFile, String message, String status, String comment, String headers) {
        try {
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(resultFileStringPath, true));
            String statusTag = null;
            if (!status.equalsIgnoreCase("True") && !status.equalsIgnoreCase("Pass")) {
                if (!status.equalsIgnoreCase("false") && !status.equalsIgnoreCase("Fail")) {
                    if (status.equalsIgnoreCase("Warning")) {
                        statusTag = "<td bgcolor='#FFEA62'>";
                    } else {
                        statusTag = "<td>";
                    }
                } else {
                    statusTag = "<td bgcolor='#FF4500'>";
                }
            } else {
                statusTag = "<td bgcolor='#00FF7F'>";
            }

            resultFile.append("<tr>");
            resultFile.append("<td width=30% style=WORD-break:BREAK-all>" + apiURL + "</td>");
            resultFile.append("<td width=10% style=WORD-break:BREAK-all>" + headers + "</td>");
            resultFile.append("<td width=10% style=WORD-break:BREAK-all>" + requestFile + "</td>");
            resultFile.append("<td width=10% style=WORD-break:BREAK-all>" + responseFile + "</td>");
            resultFile.append("<td width=30% style=WORD-break:BREAK-all>" + message + "</td>");
            resultFile.append(statusTag + status + "</td>");
            resultFile.append("<td style=word-wrap: break-word>" + comment + "</td>");
            resultFile.append("</tr>");
            resultFile.close();
        } catch (Exception var10) {
            logger.error("Append to HTML failed - " + var10.getStackTrace());
        }

    }

    public static boolean reporterLogging(Boolean status, String sMessage) {
        logger.info(sMessage+" ---- "+status);
        return reporterLogging(String.valueOf(status), sMessage);
    }

    public static boolean reporterLogging(String status, String sMessage) {

        try {
            appendresultHTMLReport(getResultFileStringPath(), "", "", "", sMessage, String.valueOf(status), Thread.currentThread().getStackTrace()[2].getMethodName(), "");
            Reporter.log("<br>");
            if (status.equalsIgnoreCase("True")) {
                Reporter.log("<Font Color=#008000> PASS </Font>" + sMessage);
            } else if (status.equalsIgnoreCase("Warning")) {

                Reporter.log("<Font Color=#FFEA62> WARN </Font>" + sMessage);
            } else if (status.equalsIgnoreCase("HtmlReporting")) {

                Reporter.log("<Font Color=red> FAIL </Font> " + sMessage);
            } else {
                Reporter.log("<Font Color=red> FAIL </Font> " + sMessage);
            }
        } catch (Exception var4) {
            logger.error("Not able to log in report - " + var4);
        }

        return Boolean.getBoolean(status);
    }


}
