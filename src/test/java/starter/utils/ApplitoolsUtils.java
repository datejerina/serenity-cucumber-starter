package starter.utils;

import com.applitools.eyes.*;
import com.applitools.eyes.config.Configuration;
import io.cucumber.java.Scenario;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import starter.globalvariables.ApplitoolsGlobalVariables;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ApplitoolsUtils {

    public static void setupEyes(Scenario scenario) {
        /*
         * Setup a common batch for all tests
         */
        BatchInfo batchInfo = new BatchInfo("Comparing purchase receipt");

        //for the differences pdf
        //batchInfo.setId("04bbb9a2-6ed8-4aef-a76f-a4c6bd90d85b");

        //this one is for the PDFs
        batchInfo.setId("23a46df7-14fd-42f5-973a-cd982644ae90");
        Configuration config = new Configuration();
        config.setBatch(batchInfo);

        //how to connect to applitools: Server and apikey
        config.setApiKey(SerenityUtils.getProperty("applitools.api.key"));
        config.setServerUrl(SerenityUtils.getProperty("applitools.server.url"));

        //AppName, TestName, Browser, ViewporSize, OS
        config.setAppName("Demo");
        config.setTestName(scenario.getName());
        config.setEnvironmentName("Demo Env");
        config.setHostOS("MacOS");

        ApplitoolsGlobalVariables.config = config;
        ApplitoolsGlobalVariables.eyes.setConfiguration(config);
    }

    public static void openEyes(RectangleSize size) {
        var eyes = ApplitoolsGlobalVariables.eyes;
        if (!eyes.getIsOpen()) {
            var configuration = ApplitoolsGlobalVariables.config;
            var testName = configuration.getTestName();
            var appName = configuration.getAppName();
            eyes.open(appName, testName, size);
        }
    }

    @SneakyThrows
    public static TestResults validatePDFAndGetResults(File pdfFilePath) {
        var eyes = ApplitoolsGlobalVariables.eyes;
        if (eyes == null)
            return null;
        PDDocument doc = PDDocument.load(pdfFilePath);
        PDFRenderer pdfRenderer = new PDFRenderer(doc);
            try {
                BufferedImage image = pdfRenderer.renderImageWithDPI(0, 500);
                openEyes(new RectangleSize(image.getWidth(), image.getHeight()));
                eyes.check(pdfFilePath.getName() , com.applitools.eyes.images.Target.image(image));
                image.getGraphics().dispose();
                image.flush();
            } catch (IOException e) {
                eyes.abortIfNotClosed();
                throw new RuntimeException(e);
            }

        doc.close();
        return closeEyes();
    }

    @SneakyThrows
    public static TestResults validatePDFDifferencesAndGetResults(File pdfFilePath) throws IOException {
        var eyes = ApplitoolsGlobalVariables.eyes;
        if (eyes == null)
            return null;
        PDDocument doc = PDDocument.load(pdfFilePath);
        PDFRenderer pdfRenderer = new PDFRenderer(doc);
        try {
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 800);
            openEyes(new RectangleSize(image.getWidth(), image.getHeight()));
            eyes.check(pdfFilePath.getName() , com.applitools.eyes.images.Target.image(image));
            image.getGraphics().dispose();
            image.flush();
        } catch (IOException e) {
            eyes.abortIfNotClosed();
            throw new RuntimeException(e);
        }

        doc.close();
        return closeEyes();
    }


    @SneakyThrows
    public static TestResults closeEyes(){
        TestResults testResult;
        try {
            testResult = ApplitoolsGlobalVariables.eyes.close();

        }catch (EyesException e){
            System.out.println(e.toString());
            testResult = new TestResults();
            testResult.setStatus(TestResultsStatus.Failed);

        }
            return testResult;
    }
}
