package validations.steps;

import framework.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import validations.objects.pages.CommonPage;

/**
 * Steps that can be performed on all of the pages for this site
 */
public class CommonSteps {
    @Given("the page is fully drawn")
    public void thePageIsFullyDrawn() {
        // the constructor for this sort of object will wait until it thinks that the page is complete
        new CommonPage(Context.defaultDriver);
    }

    @When("I click on the nav link with text {string}")
    public void iClickOnTheNavLinkWithText(String linkText) {
        CommonPage commonPage = new CommonPage(Context.defaultDriver);
        WebElement linkElement = commonPage.getNavItem(linkText);

        Assert.assertTrue("The '" + linkText + "' link should be visible and enabled",
                linkElement.isDisplayed() && linkElement.isEnabled()
        );
        linkElement.click();
    }
}
