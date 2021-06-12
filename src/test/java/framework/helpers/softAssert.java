package framework.helpers;

import static framework.helpers.Reports.writeToHtmlReport;

public class softAssert {
    private boolean crashed;
    private final String setName;

    public softAssert() {
        this.crashed = false;
        this.setName = "Undefined";
    }

    public softAssert(String setName) {
        this.crashed = false;
        this.setName = setName;
    }

    public void assertAll() {
        assertTrue(crashed, "One of the assertion for this set (" + setName + ") failed");
    }

    public void assertTrue(boolean flag, String s) {
        if (!flag) {
            this.crashed = true;
            writeToHtmlReport(s);
        }
    }
}
