package starter.stepdefinitions;

import com.applitools.eyes.TestResults;
import com.applitools.eyes.TestResultsSummary;
import io.cucumber.java.en.When;
import org.junit.Assert;
import starter.globalvariables.ApplitoolsGlobalVariables;
import starter.utils.ApplitoolsUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ApplitoolsStepDefinitions {
    @When("{} compares the receipt on folder {string} with Applitools baseline")
    public void searchesForApplitoolsDifferencesPDF(String customer, String directoryToPDF) throws Exception {

        String path ="/Users/josemanuelflorezcastro/Documents/endava/Testing week 2023/serenity-cucumber-starter/src/test/resources/receipt/" + directoryToPDF + ".pdf";
        File file = new File(path);
        System.out.println(String.format("%s: ", customer) + " started applitools check");
        TestResults testResults = ApplitoolsUtils.validatePDFAndGetResults(file);

        System.out.println(String.format("%s: ", customer) + " completed applitools check");

        boolean result = testResults.isPassed();
        System.out.println(result);
        Assert.assertTrue("There were some mismatches on the comparison", result);


    }


    @When("Compare the differences of the following pdf file {string} with Applitools baseline")
    public void searchesForTotalDifferences( String directoryToPDF) throws Exception {

        String path ="/Users/josemanuelflorezcastro/Documents/endava/Testing week 2023/serenity-cucumber-starter/src/test/resources/receipt/" + directoryToPDF + ".pdf";
        File file = new File(path);
        System.out.println("started applitools check for total differences");
        TestResults testResults = ApplitoolsUtils.validatePDFDifferencesAndGetResults(file);

        System.out.println("completed applitools check for total differences");

        boolean result = testResults.isPassed();
        System.out.println(result);
        Assert.assertTrue("There are some differences found!", result);


    }
}
