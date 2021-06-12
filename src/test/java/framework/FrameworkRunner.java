package framework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/java/framework/features", // a file path to the root of all features relevant to this
        // runner
        glue = {
                // a list of package names, not directly related to file paths
                // the @Before and @After will not be run unless the package containing them is listed here
                "framework"
        },
        plugin = {
                "pretty",
                "html:target/stdReports",
                "json:target/cucumber.json",
                // see https://gitlab.com/monochromata-de/cucumber-reporting-plugin
                "de.monochromata.cucumber.report.PrettyReports:target/"
        }
        , tags = "@smoke"
        //                , dryRun = true
)

public class FrameworkRunner {
}