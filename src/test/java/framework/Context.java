package framework;

import framework.actors.Actor;
import framework.actors.ActorType;
import framework.helpers.ConfigReader;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Context {

    public static Scenario scenario;
    public static ActorType defaultActorType;
    public static Actor defaultActor;
    public static WebDriver defaultDriver;

    // these fields are static so that the test steps (which are static) can gain access to them
    public static ConfigReader testConfiguration;
    public static ConfigReader sutConfiguration;
    public static Integer implicitWait;
    public static Integer pageLoadWait;

    private Context() {
        try {
            testConfiguration = new ConfigReader("src/test/configuration/testFramework.properties");
            sutConfiguration = new ConfigReader(testConfiguration.getProperty("sutConfigPath"));
        } catch (IOException | NoSuchFieldException e) {
            e.printStackTrace();
            Assert.fail("Problem loading configuration for the test");
        }

        try {
            System.setProperty("cucumber.reporting.config.file", testConfiguration.getProperty("reportConfigFile"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Assert.fail("The report configuration file location must be defined the the test configuration");
        }

        try {
            defaultActorType = ActorType.valueOf(testConfiguration.getProperty("defaultBrowser").toUpperCase());
        } catch (NoSuchFieldException e) {
            defaultActorType = null;
        }

        try {
            implicitWait = Integer.parseInt(Context.testConfiguration.getProperty("implicitWaitTime"));
        } catch (NoSuchFieldException e) {
            implicitWait = 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Assert.fail("Unable to understand the implicitWaitTime test configuration value");
        }
        try {
            pageLoadWait = Integer.valueOf(Context.testConfiguration.getProperty("waitForPageLoad"));
        } catch (NoSuchFieldException e) {
            System.out.println("[info] Defaulting Page load wait to zero");
            pageLoadWait = 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Assert.fail("Unable to understand the waitForPageLoad test configuration value");
        }

        defaultActor = framework.actors.Factory.make(defaultActorType);
    }

    // There is one context, and it contains interpretations of the config files, and other shared info
    // Maybe this should be implemented in another way, because IntelliJ think that this is a utility class
    @SuppressWarnings({"InstantiationOfUtilityClass", "unused"})
    private static final Context me = new Context();
}
