package framework.steps;


import framework.helpers.resourceLocator;
import framework.objects.W3cCssChecker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

import static framework.helpers.DateHelpers.humanReadableDuration;

public class CssSyntaxCheckSteps {
    final Duration tout = Duration.ofSeconds(25);
    private W3cCssChecker w3cCssValidator = null;
    private String url;

    @Given("the w3C CSS tester reviews the file {string}")
    public void theWCCSSTesterReviewsTheFile(String urlOfFile) {
        this.url = resourceLocator.interpretURL(urlOfFile);
        try {
            w3cCssValidator = new W3cCssChecker(url, tout);
        } catch (TimeoutException e) {
            Assert.fail("Failed to find results from:" + url + ": in " + humanReadableDuration(tout) + " seconds");
        }

    }

    @Then("the w3c CSS tester reports compliance")
    public void theWCCSSTesterReportsCompliance() {
        Assert.assertTrue("File :" + url + ": should be syntactically acceptable", w3cCssValidator.fileValidates());
    }
}
