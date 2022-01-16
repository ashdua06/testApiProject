package com.test.helpers.report;

public interface ReportConstants {
    String REPORT_START_TEMPLATE = "<html><HEAD><TITLE>Automation Report</TITLE></HEAD><body><h4 align=\"center\"><FONT COLOR=\"660066\" FACE=\"Arial\"SIZE=5><b>API Automation Test Report</b>";
    String DETAILED_REPORT_TITLE = "<h4 align='left'> <FONT COLOR=\"660000\" FACE=\"Arial\" SIZE=4.5> Detailed Report</h4><table  border=1 cellspacing=1  cellpadding=1 ><tr>";
    String DETAILED_REPORT_BODY_START = "<td width=150  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>";
    String REPORT_COLUMN_END = "</b></td>";
    String SUMMARY_REPORT_TEMPLATE = "<h4 align='left'> <FONT COLOR=\"66000\" FACE=\"Arial\" SIZE=4.5> Summary</h4><table cellspacing=1 cellpadding=1 border=1>";
    String SUMMARY_REPORT_ROW_START = "<tr><td width=300  align=\"center\" bgcolor=\"#e63d00\"><FONT COLOR=\"#E0E0E0\" FACE=\"Arial\" SIZE=2><b>";
    String SUMMARY_REPORT_VALUE_TEMPLATE = "<td width=150 align=\"center\"><FONT COLOR=\"#385daa\" FACE=\"Arial\" SIZE=2.75><b>";
    String REQ_RESP_LOG_TEMPLATE = "<html></head><body><title>API Test Report</title><table style=table-layout:fixed; width:40px border = '1'><tr><th>API URL</th><th>Headers</th><th>Request</th><th>Response</th><th>Message</th><th>Status</th><th>Calling Method</th></tr>";
    String TEST_PASS_TAG = "<td bgcolor='#00FF7F'>";
    String TEST_FAIL_TAG = "<td bgcolor='#FF4500'>";
    String TEST_WARN_TAG = "<td bgcolor='#FFEA62'>";
    String COMPARATOR_REPORT_TITLE = "<html></head><body><title>Compare Engine Response</title><table border = '1'><tr><th>Operation Type</th><th>Field Name</th><th>Expected value</th><th>Test build value</th></tr>";
    String TESTRESULT_WARNING = "Warning";
    String TESTRESULT_HTMLREPORT = "HtmlReporting";
}
