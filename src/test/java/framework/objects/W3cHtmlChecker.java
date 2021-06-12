package framework.objects;

import framework.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class W3cHtmlChecker {
    //    Context.defaultDriver.findElement(By.xpath("/html/body/ol/li[@class='error']/ancestor::ol")).getAttribute("outerHTML")
    private final By errorList = By.xpath("//li[@class='error']/ancestor::ol");

    /**
     * It is best to aim this directly at the single files that you create.
     * For example, Bootstrap's css invokes error messages (false negatives?) from this tester.
     *
     * @param urlOfHtmlFile - make it a single file.Scheme is not necessary
     */
    public W3cHtmlChecker(String urlOfHtmlFile, Duration tout) throws UnsupportedEncodingException {

        String sut = "https://validator.w3.org/nu/?doc=";
        sut += URLEncoder.encode(urlOfHtmlFile, StandardCharsets.UTF_8.toString());

        Context.defaultActor.getResource(sut);

        new WebDriverWait(Context.defaultDriver, tout).
                until(ExpectedConditions.presenceOfElementLocated(By.className("details"))
                );
    }

    /**
     * A <p> with class of success only occurs if it passes the test
     * The implicit page wait comes into play when the page is faulty
     *
     * @return - whether it contains text that indicates success, or failure
     */
    public Boolean fileValidates() {
        return Context.defaultDriver.findElement(By.className("success")).isDisplayed();
    }

    public String getErrorList() {
        return Context.defaultDriver.findElement(errorList).getAttribute("outerHTML");
    }
}