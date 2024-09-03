package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/Cucumber",glue = "hrishabhPandey.stepDefinImpl",
        monochrome = true,tags = "@ErrorValidation", plugin = {"html:target/cucumber.html"})
public class TestNGtestRunner extends AbstractTestNGCucumberTests {

}
