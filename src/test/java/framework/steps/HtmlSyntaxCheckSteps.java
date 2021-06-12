package framework.steps;


import framework.helpers.Reports;
import framework.helpers.resourceLocator;
import framework.objects.W3cHtmlChecker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import java.io.UnsupportedEncodingException;
import java.time.Duration;

import static framework.helpers.DateHelpers.humanReadableDuration;

public class HtmlSyntaxCheckSteps {
    final Duration tout = Duration.ofSeconds(40);
    private W3cHtmlChecker w3cHtmlValidator = null;
    private String url;

    @Given("the w3C HTML tester reviews the file {string}")
    public void theW3CHTMLTesterReviewsTheFile(String urlOfFile) {
        url = resourceLocator.interpretURL(urlOfFile);
        try {
            w3cHtmlValidator = new W3cHtmlChecker(url, tout);
        } catch (TimeoutException e) {
            Assert.fail("Failed to find results from:" + url + ": in " + humanReadableDuration(tout) + " " +
                    "seconds");
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Internal Error: Failed to interpret:" + url + ": " + e.getMessage() + ":");
        }
    }

    @Then("the w3c HTML tester reports compliance")
    public void theW3CHTMLTesterReportsCompliance() {
        if (!w3cHtmlValidator.fileValidates()) {
            Reports.writeToHtmlReport(w3cHtmlValidator.getErrorList());
            Assert.fail(url + ": (unescaped) should be syntactically correct");
        }
    }
}
