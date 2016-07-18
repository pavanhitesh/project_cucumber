package com.pavan.definitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.pavan.cvsreader.GuiObjectsMap;
import com.pavan.utill.WebPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Example {
	
	private Scenario scenario;
	WebDriver driver;
	private static final String BasePath = System.getProperty("user.dir")+"//src//main//java//com//pavan//definitions";
	
	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		System.out.println("executed before method");
		GuiObjectsMap.loadDataFiles(BasePath);
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
	public void i_start_step() throws Exception {
		System.out.println("Started the Step");
		WebPage page = new WebPage(driver);
		System.out.println("The data is "+page.getUiDataMap("username"));
		driver.get("https://www.google.co.in/?gfe_rd=cr&ei=gcxvV_m9AsuL8QfU3LjQAg&gws_rd=ssl");
	}
	
	@Then("^I Start Step1$")
	public void i_start_step1() {
		System.out.println("Started the Step1");
	}
	
	

}
