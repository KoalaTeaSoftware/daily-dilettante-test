package framework.steps;


import framework.helpers.resourceLocator;
import framework.objects.W3cLinkChecker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

import static framework.helpers.DateHelpers.humanReadableDuration;

public class PageLinkCheck {
    final Duration tout = Duration.ofSeconds(120); // the link checker can be quite slow

    private W3cLinkChecker w3cLinkChecker;
    private String url;

    @Given("the w3c link checker reviews the file {string}")
    public void theW3CLinkCheckerReviewsTheFile(String urlOfFile) {
        url = resourceLocator.interpretURL(urlOfFile);
        try {
            w3cLinkChecker = new W3cLinkChecker(url, tout);
        } catch (TimeoutException e) {
            Assert.fail("Failed to find results from:" + url + ": in " + humanReadableDuration(tout) + " seconds");
        }
    }

    @Then("the w3c link checker reports compliance")
    public void theW3CLinkCheckerReportsCompliance() {
        Assert.assertTrue("Links on page :" + url + ": appear broken", w3cLinkChecker.fileValidates());
    }
}
