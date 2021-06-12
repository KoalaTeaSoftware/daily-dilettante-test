package framework.steps;

import framework.Context;
import framework.helpers.resourceLocator;
import framework.helpers.softAssert;
import framework.objects.HtmlPageObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Steps that all HTML pages can support
 */
public class HtmlPageSteps {
    private HtmlPageObject page;

    private HtmlPageObject getMyPage() {
        if (page == null)
            page = new HtmlPageObject(Context.defaultDriver);
        return page;
    }

    @Given("I navigate to the page {string}")
    public void iNavigateToThePage(String fullUrl) {
        String s = resourceLocator.interpretURL(fullUrl);
        Assert.assertNotNull("Unable to make the URL work", s);
        Context.defaultActor.getResource(s);
    }

    @Then("the page title is {string}")
    public void thePageTitleIs(String expected) {
        if (expected.isEmpty()) {
            try {
                expected = Context.sutConfiguration.getProperty("defaultTitle");
            } catch (NoSuchFieldException e) {
                Assert.fail("The expected title has not been defined in the UST configuration, and not defined in " +
                        "this test step");
            }
        }
        Assert.assertEquals("The page title is not as expected", expected, getMyPage().readPageTitle());
    }

    @And("the first heading is {string}")
    public void theFirstHeadingIs(String expected) {
        Assert.assertEquals("Unexpected H1", expected, getMyPage().readFirstHeader());
    }

    @And("the first heading does not contain {string}")
    public void theFirstHeadingDoesNotContain(String needle) {
        boolean haystackDoesContainNeedle = getMyPage().readFirstHeader().contains(needle);

        Assert.assertFalse(
                "The first header " + page.readFirstHeader() + ":should not contain :" + needle + ":",
                haystackDoesContainNeedle);
    }

    @And("the page scheme is {string}")
    public void thePageSchemeIs(String expectedScheme) {
        try {
            if (expectedScheme.isEmpty()) expectedScheme = Context.sutConfiguration.getProperty("defaultScheme");

            Assert.assertEquals("Unexpected scheme for the current page",
                    expectedScheme.replaceAll("[:/]", ""),
                    getMyPage().readCurrentLocation().getProtocol()
            );
        } catch (NoSuchFieldException e) {
            Assert.fail("Please define a default scheme, in the SUT configuration file");
        } catch (MalformedURLException e) {
            Assert.fail("Current location can't be determined:" + e.getMessage() + ": ");
        }
    }


    @Then("all images are well formed")
    public void allImagesAreWellFormed() {
        softAssert sa = new softAssert();
        List<WebElement> imgList = getMyPage().listImgTags();

        final int numImgs = imgList.size();

        for (int i = 0; i < numImgs; i++) {

            sa.assertTrue(
                    getMyPage().browserShowsImage(imgList.get(i)),
                    "Image number " + i + "does not appear to be well formed");
        }
        sa.assertAll();
    }
}
