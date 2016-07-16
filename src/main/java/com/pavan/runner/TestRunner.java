package com.pavan.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features ="src/main/java/com/pavan/features/",
				glue ="com/pavan/definitions/",
				tags={"@Cucu"},
				plugin={"pretty", "html:report/cucumber-html-report","json:report/cucumber-report.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
