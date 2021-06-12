package validations.steps;

import framework.Context;
import framework.helpers.softAssert;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import validations.objects.pages.OnRelease;

public class chapterOnRelease {
    private OnRelease onRelease;

    /**
     * Lazy instantiation of the handle on the page
     * This will (generally speaking) allow the instantiation to occur once for each scenario
     *
     * @return - the instance of the page being looked at
     */
    private OnRelease getMe() {
        if (onRelease == null) {
            onRelease = new OnRelease(Context.defaultDriver);
        }
        return onRelease;
    }


    @And("there is more than {int} listed film(s)")
    public void thereIsMoreThanListedFilm(int minNumListings) {
        int actualCount = getMe().getFilmListItems().size();

        Assert.assertTrue(
                String.format("There are %d listings there should be at least %d", actualCount, minNumListings),
                actualCount > minNumListings);
    }

    @And("all thumbnails are shown")
    public void allThumbnailsAreShown() {
        softAssert sa = new softAssert();
        int idx = 0;

        for (WebElement el : getMe().getFilmThumbnails()) {
            sa.assertTrue(
                    el.getAttribute("alt").length() > 1,
                    String.format("The alt attribute for film number %d is suspiciously short. Value :%s:",
                            idx, el.getAttribute("alt")
                    )
            );
            // the link checker (part of the framework) will also verify that the src attribute points to an actual file
            sa.assertTrue(
                    getMe().browserShowsImage(el),
                    String.format("The src attribute (%s) appears to be bad", el.getAttribute("src")
                    )
            );
            idx++;
        }
        sa.assertAll();
    }

    @And("all titles are populated")
    public void allTitlesArePopulated() {
        softAssert sa = new softAssert();
        int idx = 0;

        for (WebElement el : getMe().getFilmTitles()) {
            sa.assertTrue(
                    el.getText().length() > 1,
                    String.format("The title attribute for film number %d is suspiciously short. Value :%s:",
                            idx, el.getText()
                    )
            );
            idx++;
        }
        sa.assertAll();
    }

    @And("all puff paragraphs are populated")
    public void allPuffParagraphsArePopulated() {
        softAssert sa = new softAssert();
        int idx = 0;

        for (WebElement el : getMe().getFilmPuffs()) {
            sa.assertTrue(
                    el.getText().length() > 20,
                    String.format("The puff paragraph for film number %d is suspiciously short. Value :%s:",
                            idx, el.getText()
                    )
            );
            idx++;
        }
        sa.assertAll();
    }

}
