package com.test.helpers.report;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.UUID;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
public class FileHelper {

    private static Logger logger = LoggingManager.getLogger(FileHelper.class);


    public FileHelper() {
    }



    public static String createRequestJsonFile(String jsonMsg, String fileName) {
        String requestFilePath = null;

        try {
            String resultFolder = ReportHelper.getResultFolderpath();
            resultFolder = resultFolder + "/Requests";
            File resultFileFolder = new File(resultFolder);
            resultFileFolder.delete();
            resultFileFolder.mkdir();
            UUID randNo = UUID.randomUUID();
            requestFilePath = resultFolder + "/Requests_" + fileName + "_" + randNo + ".json";
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(requestFilePath, true));
            resultFile.append(jsonMsg);
            resultFile.close();
            requestFilePath = "." + requestFilePath.split(ReportHelper.getResultFolderpath())[1];
            Reporter.log("<br><a href='" + requestFilePath + "'>Request_" + fileName + "</a><br>");
        } catch (Exception var7) {
            logger.log(Level.ERROR, "Error while creating JSON file - " + var7);
        }

        return requestFilePath;
    }

    public static String createResponseJsonFile(String jsonMsg, String fileName) {
        String responseFilePath = null;

        try {
            String resultFolder = ReportHelper.getResultFolderpath();
            resultFolder = resultFolder + "/Responses";
            File resultFileFolder = new File(resultFolder);
            resultFileFolder.delete();
            resultFileFolder.mkdir();
            UUID randNo = UUID.randomUUID();
            responseFilePath = resultFolder + "/Responses_" + fileName + "_" + randNo + ".json";
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(responseFilePath, true));
            resultFile.append(jsonMsg);
            resultFile.close();
            responseFilePath = "." + responseFilePath.split(ReportHelper.getResultFolderpath())[1];
            Reporter.log("<br><a href='" + responseFilePath + "'>Response_" + fileName + "</a><br>");
        } catch (Exception var7) {
            logger.log(Level.ERROR, "Error while creating reponse JSON file - " + var7);
        }

        return responseFilePath;
    }



}
