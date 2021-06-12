package framework.actors;


import framework.Context;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.util.concurrent.TimeUnit;

public class FirefoxActor extends Actor {
    @Override
    protected void startService() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Override
    public void createDriver() {
        System.out.println("[info] Creating a Web Driver for Firefox");

        boolean maximise = false;
        boolean headless = false;

        try {
            if (Context.testConfiguration.getProperty("headless").equalsIgnoreCase("true"))
                headless = true;
        } catch (NoSuchFieldException e) {
            // leave it false
        }
        try {
            if (Context.testConfiguration.getProperty("windowMaximize").equalsIgnoreCase("true"))
                maximise = true;
        } catch (NoSuchFieldException e) {
            // leave it false
        }

        // https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/firefox/FirefoxOptions.html
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(headless);

        driver = new FirefoxDriver(options);
        //        driver = new FirefoxDriver(service, options);

        driver.manage().timeouts().implicitlyWait(Context.implicitWait, TimeUnit.SECONDS);

        if (maximise)
            driver.manage().window().maximize();

    }

    private GeckoDriverService service;

}
