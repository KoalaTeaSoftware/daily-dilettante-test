package framework;

import framework.helpers.Reports;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before
    public void beforeScenario(Scenario givenScenario) {
        Context.scenario = givenScenario;
        Context.defaultDriver = Context.defaultActor.getDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        // this may seem a bit involved, but a direct use of getStatus().equals does not yield the hoped-for result
        if (scenario.isFailed()) {
            Reports.writeScreenShotToHtmlReport("Screenshot taken because this scenario is marked as " + scenario.getStatus().toString());
            Reports.writePageSourceToHtmlReport();
        }
        Context.defaultActor.closeDriver();
    }

}
