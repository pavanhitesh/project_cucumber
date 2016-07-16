package com.pavan.definitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Example {
	
	private Scenario scenario;
	WebDriver driver;
	
	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("executed before method");
		this.scenario=scenario;
		driver = new FirefoxDriver();
	}
	
	@After
	public void embedScreenshot() {
		try {
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			driver.close();
			driver.quit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Given("^I Start Step$")
	public void i_start_step() {
		System.out.println("Started the Step");
		driver.get("https://www.google.co.in/?gfe_rd=cr&ei=gcxvV_m9AsuL8QfU3LjQAg&gws_rd=ssl");
	}
	
	@Then("^I Start Step1$")
	public void i_start_step1() {
		System.out.println("Started the Step1");
	}
	
	

}
