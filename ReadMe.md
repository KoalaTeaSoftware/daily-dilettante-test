# The Koala Tea Software Sample Cucumber / Maven Test Suite

This test suite allows you to perform E2E test on a website. It comprises the following main elements

1. Framework - This is a fairly static lego-brick providing a lot of things that you are likely to want on any site.
2. Configuration - You can provide data about the SUT, how you want the framework to behave, and reporting. See the
   ReadMe.md in the configuration folder
3. Validations - This contains a few simple samples that provide some starting points from which you can build the full
   set of verifications needed for the site under test.

## Using this framework

This set of files can be copied / cloned and then used to build your test suite for the website that you want to test.

Hack the config files so that they point the framework at the appropriate URL, and then run the ~
/test/src/java/framework/FrameworkRunner. This should do some simple smoke tests of the framework itself, and generate
an HTML report. Ensure that this shows the expected framework smoke results.

As it is a Maven project, and it uses Boni Garcia's Web Driver, `mvn verify` can be used to run your verifications
wihtin the likes of a GitHub Action. The POM file's surefire plugin details will cause it to run the test runners(s)
within the validations section.