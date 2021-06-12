package validations.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OnRelease extends CommonPage {
    final private By locateFilmListItems = By.className("filmListItem");
    final private By locateFilmThumbnails = By.xpath("//*[contains(@class,'filmThumbnail')]");
    final private By locateFilmTitles = By.xpath(("//*[contains(@class,'textLinkToFilmDetails')]"));
    final private By locateFilmPuffs = By.className("filmPuff");

    public OnRelease(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getFilmListItems() {
        return myDriver.findElements(locateFilmListItems);
    }

    public List<WebElement> getFilmThumbnails() {
        return myDriver.findElements(locateFilmThumbnails);
    }

    public List<WebElement> getFilmTitles() {
        return myDriver.findElements(locateFilmTitles);
    }

    public List<WebElement> getFilmPuffs() {
        return myDriver.findElements(locateFilmPuffs);
    }
}
