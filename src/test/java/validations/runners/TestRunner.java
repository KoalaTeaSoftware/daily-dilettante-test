package validations.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*
By default, Cucumber features/scenarios are run in the order:

1. Alphabetically by feature file directory
2. Alphabetically by feature file name
3. Order of scenarios within the feature file

 */

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/java/validations/features", // all of the features in the test suite
        glue = { // a list of package names, not directly related to file paths
                "framework", // the @Before and @After will not be run unless the package containing them is listed here
                "validations"
        },
        plugin = {
                "pretty",
                "html:target/stdReports",
                "json:target/cucumber.json",
                // see https://gitlab.com/monochromata-de/cucumber-reporting-plugin
                "de.monochromata.cucumber.report.PrettyReports:target/"
        }
        //                , tags = "@smoke"
        //        , tags = "@standards"
        , tags = "@ci and (not (@not-ci or @wip))"
        //                        ,dryRun = true
)

public class TestRunner {
}