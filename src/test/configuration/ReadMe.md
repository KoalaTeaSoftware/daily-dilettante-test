# testFramework.properties

* This will set information in framework.Context
* Its location is hard-coded into framework.Context()
* It also determines where other config files will be sought.

# systemUnderTest.properties

* Defines things like the subject of the testing

# reporting.properties

* See https://gitlab.com/monochromata-de/cucumber-reporting-plugin#configuring-optional
* The location of this file is written into a System property (for the duration of the test), and the HTML report
  generator grabs the info from there.
 