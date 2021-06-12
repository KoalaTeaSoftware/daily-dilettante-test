package framework.objects;

import framework.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class W3cCssChecker {
    /**
     * It is best to aim this directly at the single files that you create.
     * For example, Bootstrap's css invokes error messages (false negatives?) from this tester.
     *
     * @param urlOfCssFile - make it a single file.Scheme is not necessary
     */
    public W3cCssChecker(String urlOfCssFile, Duration tout) {
        String fullUrl = "http://jigsaw.w3.org/css-validator/validator?uri=";
        fullUrl += urlOfCssFile;
        //noinspection SpellCheckingInspection
        fullUrl += "&profile=css3svg&usermedium=all&warning=1&vextwarning=";

        Context.defaultActor.getResource(fullUrl);

        new WebDriverWait(Context.defaultDriver, tout)
                // use the 'presence', i.e. is the element actually in the DOM - it may not be visible
                .until(ExpectedConditions.titleContains("W3C CSS Validator results for "));

    }

    /**
     * On both the success and failure pages, the first h3 tells you the result
     *
     * @return - whether it contains text that indicates success, or failure
     */
    public Boolean fileValidates() {
        String resultString = Context.defaultDriver.findElement(By.tagName("H3")).getText();

        return resultString.contains("No Error Found");
        // the alternative text is "We found the following errors"
    }
}