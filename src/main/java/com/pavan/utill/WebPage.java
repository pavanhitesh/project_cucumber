package com.pavan.utill;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pavan.cvsreader.CallerContext;
import com.pavan.cvsreader.GuiObjectsMap;











public class WebPage
{
	private static WebDriver d = null;
	private static WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(WebPage.class);
	private CallerContext context = new CallerContext();

	public WebPage(WebDriver d) {
		this.d = d;
		wait = new WebDriverWait(this.d, 40);
	}

	/**
	 * To get the webDriver Instance
	 * Where ever needed in the other class
	 * 
	 *  */
	public  WebDriver getWD() {
		return d;
	}
	/**
	 * To create the required browser instance.
	 * 
	 * @param Browser
	 * @return the instance of created web-driver
	 * @throws MalformedURLException
	 */

	public  WebDriver createWebDriverInstance(String Browser) throws MalformedURLException
	{
		if(d==null && Browser.equals("Firefox"))
		{

			d = new FirefoxDriver();
			logger.info("--FireFox Browser has opened ");
		}

		else if(d==null &&  Browser.equals("Chrome"))
		{
			String path ="binary/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path);
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			d = new ChromeDriver(caps);
			logger.info("--Chrome Browser has opened ");
		}

		else if (d==null && Browser.equals("IE"))
		{
			String path ="binary/IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", path);
			logger.info("--IEDriver has setup");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			caps.setCapability("requireWindowFocus", true);
			caps.setCapability("enablePersistentHover", true);
			caps.setCapability("native events", true);
			d = new InternetExplorerDriver(caps);
			logger.info("--IE Browser has opened ");
		}

		else if (d==null && Browser.equals("Safari"))
		{
			File safariExt = new File("binary/SafariDriver2.32.0.safariextz");
			SafariOptions opt = new SafariOptions();

			//opt.addExtensions(safariExt);
			DesiredCapabilities capabilities = DesiredCapabilities.safari();
			capabilities.setCapability(SafariOptions.CAPABILITY, opt);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("enablePersistentHover", true);
			capabilities.setCapability("native events", true);
			d = new SafariDriver(capabilities);
			logger.info("--Safari Browser has opened ");
		}
		else if (d==null && Browser.equals("IE32bit"))
		{
			String path ="binary/IEDriverServer_32bit.exe";
			System.setProperty("webdriver.ie.driver", path);
			logger.info("--IEDriver has setup");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(
					InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					true);
			caps.setCapability("requireWindowFocus", true);
			caps.setCapability("enablePersistentHover", false);
			caps.setCapability("native events", true);
			d = new InternetExplorerDriver(caps);
			logger.info("--IE Browser has opened ");
		}
		return d;


	}

	/**
	 * Fetch the locator from the gui map .If the key is not found ,pass the
	 * locator as the literal and try to perform the action.
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public  String getUiObjectLocator(String locator) throws Exception {
		 
		String callingClass = context.getClassExecutionStack()[3].getCanonicalName();
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[0].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[1].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[2].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[3].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[4].getCanonicalName());
		String c1 = GuiObjectsMap.getGuiMap().get(callingClass + "." + locator);
		if (c1 == null) {
			callingClass = context.getClassExecutionStack()[4].getCanonicalName();
			c1 = GuiObjectsMap.getGuiMap().get(callingClass + "." + locator);
			if (c1 == null) {
				/*
				 * AppLogger.getLogger().info( "could not find key for " +
				 * locator + ", using literal text instead");
				 */
				return locator;
			}
			return c1;
		}

		else
			return c1;

	}
	
	/**
	 * Fetch the locator from the gui map .If the key is not found ,pass the
	 * locator as the literal and try to perform the action.
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public  String getUiDataMap(String label) throws Exception {
		 
		String callingClass = context.getClassExecutionStack()[2].getCanonicalName();
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[0].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[1].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[2].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[3].getCanonicalName());
		System.out.println("Thecalling Class is "+context.getClassExecutionStack()[4].getCanonicalName());
		String c1 = GuiObjectsMap.getDataMap().get(callingClass + "." + label);
		if (c1 == null) {
			callingClass = context.getClassExecutionStack()[4].getCanonicalName();
			c1 = GuiObjectsMap.getDataMap().get(callingClass + "." + label);
			if (c1 == null) {
				/*
				 * AppLogger.getLogger().info( "could not find key for " +
				 * locator + ", using literal text instead");
				 */
				return label;
			}
			return c1;
		}

		else
			return c1;

	}


	/**
	 * To open the defined URL.
	 * @param URL
	 * @return and return the given URL if needed
	 * @throws Exception
	 */

	public  String openURL(String URL) throws Exception {
		getWD().get(URL);
		getWD().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return URL;
	}




	/**
	 * To Wait for required amount of time for each action performed in the called method
	 * @param Seconds
	 */

	public  void wait(int Seconds) {
		getWD().manage().timeouts().implicitlyWait(Seconds, TimeUnit.SECONDS);
	}

	/**
	 * To Wait the Execution for required amount of seconds
	 * @param Seconds
	 * @throws InterruptedException
	 */


	public  void delay(int Seconds) throws InterruptedException {
		Thread.sleep(Seconds * 1000);

	}

	/**
	 * Waits for page to load
	 * @throws Exception
	 */

	public  void waitForPageToLoad() throws Exception {
		WebDriverWait wait = new WebDriverWait(getWD(),40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[not (.='')]")));
	}

	/**
	 * To Get WebElement Based on the locator type 
	 * @param elementlocator
	 * @return
	 */

	public WebElement getId(String elementlocator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementlocator)));
		return getWD().findElement(By.id(elementlocator));
	}

	public WebElement getName(String elementlocator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementlocator)));
		return getWD().findElement(By.name(elementlocator));
	}

	public WebElement getXpath(String elementlocator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementlocator)));
		return getWD().findElement(By.xpath(elementlocator));
	}

	public WebElement getCSS(String elementlocator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementlocator)));
		return getWD().findElement(By.cssSelector(elementlocator));
	}

	public WebElement getClassName(String elementlocator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementlocator)));
		return getWD().findElement(By.className(elementlocator));
	}

	public WebElement getLinkText(String elementlocator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elementlocator)));
		return getWD().findElement(By.linkText(elementlocator));
	}

	/**
	 * Does not wait for the page to load .Just clicks and proceeds for the next
	 * statement.
	 * 
	 * @param elementlocator
	 * @param idtype
	 * @throws Exception
	 */

	public  void click(String elementlocator,String locatortype) throws Exception {

		if (locatortype.equalsIgnoreCase("id")) {
			getId(elementlocator).click();
		} else if (locatortype.equalsIgnoreCase("name")) {
			getName(locatortype).click();
		} else if (locatortype.equalsIgnoreCase("xpath")) {
			getXpath(elementlocator).click();
		} else if (locatortype.equalsIgnoreCase("css")) {
			getCSS(elementlocator).click();
		} else if (locatortype.equalsIgnoreCase("class")) {
			getClassName(elementlocator).click();
		} else if (locatortype.equalsIgnoreCase("link")) {
			getLinkText(elementlocator).click();
		}
	}



	/**
	 * Click on the required element and waits page to load
	 * 
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @throws Exception
	 */

	public  void clickAndWait(String elementlocator , String locatortype) throws Exception {

		click(elementlocator, locatortype);
		waitForPageToLoad();
	}

	/**
	 * To Find the WebElement in the web page.
	 * @param element
	 * @param idtype
	 * @return
	 */

	public  WebElement getWebElement(String elementlocator, String locatortype)
	{
		WebElement element=null;
		if (locatortype.equalsIgnoreCase("id")) {
			element = getId(elementlocator);
		} else if (locatortype.equalsIgnoreCase("name")) {
			element = getName(locatortype);
		} else if (locatortype.equalsIgnoreCase("xpath")) {
			element = getXpath(elementlocator);
		} else if (locatortype.equalsIgnoreCase("css")) {
			element = getCSS(elementlocator);
		} else if (locatortype.equalsIgnoreCase("class")) {
			element = getClassName(elementlocator);
		} else if (locatortype.equalsIgnoreCase("link")) {
			element = getLinkText(elementlocator);
		}
		return element;

	}


	/**
	 * To Maximize the window.
	 */

	public  void maximize()
	{
		getWD().manage().window().maximize();
	}


	/**
	 * To Close the Instance of the Created WebDriver and assign the NULL.
	 */

	public  void close()
	{
		getWD().close();
		getWD().quit();
		d = null;
	}


	/**
	 * Returns the visible text of the element.
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @return
	 * @throws Exception
	 */

	public  String getText(String elementlocator , String locatortype) throws Exception {
		String Text =null;
		Text = getWebElement(elementlocator, locatortype).getText();
		return Text;
	}

	/**
	 * Returns the visible text of the element.
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @return
	 * @throws Exception
	 */

	public  String getValue(String elementlocator , String locatortype) throws Exception {
		String Text =null;
		Text = getWebElement(elementlocator, locatortype).getAttribute("value");
		return Text;
	}

	/**
	 * Check a toggle-button (checkbox/radio)
	 * 
	 * @param elementlocator
	 * @param locatortype
	 */

	public  void check(String elementlocator , String locatortype) {

		WebElement element = getWebElement(elementlocator, locatortype);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Uncheck a toggle-button (checkbox/radio)
	 * if element is selected
	 * 
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @throws Exception
	 */

	public  void uncheck(String elementlocator , String locatortype) throws Exception { 

		WebElement element = getWebElement(elementlocator, locatortype);
		if (element.isSelected()) {
			element.click();
		}	
	}

	/**
	 * To Select the visible text option from the Dropdown
	 * present in the webpage.
	 * 
	 * 
	 * @param selectlocator
	 * @param locatortype
	 * @param optionText
	 * @throws Exception
	 */


	public  void selectByVisibleText(String selectlocator , String locatortype , String optionText) throws Exception {

		WebElement element= getWebElement(selectlocator, locatortype);
		new Select(element).selectByVisibleText(optionText);

	}


	/**
	 * to get the selected value from the drop down list.
	 * 
	 * @param selectlocator
	 * @param locatortype
	 * @return
	 */

	public  String selectedValue(String selectlocator , String locatortype) {

		WebElement element=getWebElement(selectlocator, locatortype);
		return new Select(element).getFirstSelectedOption().getText();
	}


	/**
	 * Simulates a user hovering a mouse over the specified element.
	 * 
	 * 
	 * @param elementlocator
	 * @param locatortype
	 */

	public  void mouseOver(String elementlocator,String locatortype) {

		WebElement element = getWebElement(elementlocator, locatortype);
		Actions builder = new Actions(getWD());
		builder.moveToElement(element).build().perform();
	}

	/**
	 * Simulates a user hovering a mouse over the specified element.
	 * and click on the particular element.
	 * 
	 * @param elementlocator
	 * @param locatortype
	 */

	public  void mouseOverClick(String elementlocator,String locatortype) {

		WebElement element = getWebElement(elementlocator, locatortype);
		Actions builder = new Actions(getWD());
		builder.moveToElement(element).click().build().perform();	
	}

	/**
	 * Gets whether a toggle-button (checkbox/radio) is checked.
	 * 
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @return true if the checkbox is checked, otherwise false
	 * @throws Exception
	 */

	public  boolean isChecked(String elementlocator , String locatortype) throws Exception {

		WebElement element = getWebElement(elementlocator, locatortype);
		return element.isSelected();
	}

	/**
	 * Gets whether a webelement is displayed or not.
	 * 
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @return true if the specified element is visible,otherwise false
	 * @throws Exception
	 */

	public  boolean isVisible(String elementlocator , String locatortype) throws Exception {

		try {

			WebElement element=getWebElement(elementlocator, locatortype);
			return element.isDisplayed();

		} catch(Exception e) {
			return false;
		}
	}


	/**
	 * to get non display fields in the webpage
	 * given input as map;
	 * @param Map1
	 * @return
	 * @throws Exception
	 */

	public  ArrayList<String> isElementVisible(Map<String,Pojo> Map1) throws Exception {

		ArrayList<String> nondisplay = new ArrayList<String>();
		for (Map.Entry<String, Pojo> entry : Map1.entrySet()) {
			Pojo obj = Map1.get(entry.getKey());
			if(isVisible(obj.getProperty(),obj.getPropertyType())) {
				System.out.println(entry.getKey()+" is available in the screen");

			} else {
				logger.info(entry.getKey()+" is not available in the screen");
				System.out.println(entry.getKey()+" is not available in the screen");
				nondisplay.add(entry.getKey());
			}
		}
		logger.info("The Undisplayed Fields are " +nondisplay);
		return nondisplay;
	}
	/**
	 * to get the uncleared text boxes
	 * @param Map1
	 * @return
	 * @throws Exception
	 */

	public  ArrayList<String> isAlltxtFieldsCleared(Map<String,Pojo> Map1) throws Exception {

		ArrayList<String> nondisplay = new ArrayList<String>();
		for (Map.Entry<String, Pojo> entry : Map1.entrySet()) {
			Pojo obj = Map1.get(entry.getKey());
			if(getValue(obj.getProperty(), obj.getPropertyType()).contentEquals("")) {

				logger.info(entry.getKey()+"is Cleared");
			} else {
				logger.info(entry.getKey()+"is not Cleared");
				nondisplay.add(entry.getKey());
			}
		}
		logger.info("The Uncleared Fields are " +nondisplay);
		return nondisplay;
	}


	/**
	 * To sendkeys in to any text box present on webelement
	 * 
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @param valuetotype
	 */

	public  void sendKeys(String elementlocator , String locatortype , String valuetotype) {

		getWebElement(elementlocator, locatortype).sendKeys(valuetotype);
	}

	/**
	 * Returns the title of the current active window
	 * 
	 * @return title of the open web page
	 * @throws Exception
	 */

	public  String getTitle() throws Exception
	{
		return getWD().getTitle();
	}


	/**
	 * To Get the List of WebElement
	 * 
	 * 
	 * @param elementlocator
	 * @param locatortype
	 * @return
	 */

	public  List<WebElement> getWebElementList(String elementlocator , String locatortype) {
		List<WebElement> elementlist=null;
		if(locatortype.equalsIgnoreCase("id")) {
			elementlist =getWD().findElements(By.id(elementlocator));
		} else if(locatortype.equalsIgnoreCase("xpath")) {
			elementlist =getWD().findElements(By.xpath(elementlocator));
		} else if(locatortype.equalsIgnoreCase("css")) {
			elementlist =getWD().findElements(By.cssSelector(elementlocator));
		} else if (locatortype.equalsIgnoreCase("class")) {
			elementlist =getWD().findElements(By.className(elementlocator));
		} else if(locatortype.equalsIgnoreCase("link")) {
			elementlist =getWD().findElements(By.linkText(elementlocator));
		} else if(locatortype.equalsIgnoreCase("name")) {
			elementlist =getWD().findElements(By.name(elementlocator));
		}
		return elementlist;
	}


	/**
	 * To get the Xpath count of
	 * any element present in the web page
	 * 
	 * @param elementlocator
	 * @return
	 * @throws Exception
	 */

	public  int getXPathCount(String elementlocator) throws Exception {

		return getWD().findElements(By.xpath(elementlocator)).size();
	}


	/**
	 * To switch the cursor the defined iframe located in the web page
	 * 
	 * @param framelocator
	 * @param locatortype
	 */

	public  void switchFrame(String framelocator , String locatortype) {

		WebElement element;
		element = getWebElement(framelocator, locatortype);
		getWD().switchTo().frame(element);
	}

	/**
	 * To switch the cursor the default web page.
	 */

	public  void switchToDefaultConatiner() {
		getWD().switchTo().defaultContent();
	}


	/**
	 * To verify the alert present in the web page
	 * 
	 * 
	 * @return true if alert present , other wise false.
	 * @throws Exception
	 */

	public  boolean isAlertPresent() throws Exception {

		try {
			WebDriverWait wait = new WebDriverWait(getWD(), 20);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		}
		catch(Exception e) {
			return false;
		}

	}


	/**
	 * to get the values in the drop down
	 * @param Property
	 * @param PropertyType
	 * @return
	 */
	public  ArrayList<String> getDropDownValue(String Property , String PropertyType) {

		ArrayList<String> ddlvalues = new ArrayList<String>();
		WebElement SelectDDL = getWebElement(Property, PropertyType);
		Select select = new Select(SelectDDL);
		List<WebElement> values = select.getOptions();
		for(WebElement value :values) {
			ddlvalues.add(value.getText());
		}
		return ddlvalues;

	}

	/**
	 * To get the text present on the Alert
	 * or confirmation box.
	 * 
	 * @return
	 * @throws Exception
	 */

	public  String getAlertText() throws Exception {

		String alerttext = null;
		alerttext =getWD().switchTo().alert().getText();
		return alerttext;
	}

	/**
	 * To click OK on the Confirmation box
	 * or accept the alert
	 * 
	 * @throws Exception
	 */

	public  void chooseOkOnNextConfirmation() throws Exception {

		getWD().switchTo().alert().accept();
	}

	/**
	 * To Click No on the confirmation box.
	 * or to dismiss alert
	 * 
	 * @throws Exception
	 */

	public  void chooseNoOnNextConfirmation() throws Exception {

		getWD().switchTo().alert().dismiss();
	}


	/**
	 * to handle windows
	 * @return
	 */
	/**
	 * To read the Text on the confirmation box.
	 * or to dismiss alert
	 * 
	 * @throws Exception
	 */


	public  String windowHandle() {

		String parentWindow = getWD().getWindowHandle();
		for(String childWindow: getWD().getWindowHandles()) {
			getWD().switchTo().window(childWindow);
		}
		return parentWindow;
	}

	/**
	 * to Switch between windows
	 * @param Window
	 */
	public  void switchWindow(String Window) {
		
		getWD().switchTo().window(Window);
	}

	/**
	 * to execute java scripts
	 * @param Code
	 */

	public  void javaScrpitExcecutor(String Code) {
		JavascriptExecutor javascript = (JavascriptExecutor)getWD();
		javascript.executeScript(""+Code+"");
	}


	/**
	 * to generate Alert with
	 * user defined message
	 * @param Message
	 * @param secondstoAccept
	 * @throws Exception
	 */
	public  void generateAlertandAccept(String Message, int secondstoAccept) throws Exception {
		
		javaScrpitExcecutor("alert('"+Message+"');");
		delay(secondstoAccept);
		chooseOkOnNextConfirmation();
	}

	
	public void waitforPageLoadJS() {
		
		JavascriptExecutor javascript = (JavascriptExecutor)getWD();
		javascript.executeScript("return document.readyState").equals("complete");
		
	}


	public  void mouseclick(WebElement a,WebElement b) {
		try {
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) getWD()).executeScript(mouseOverScript,a);
			Thread.sleep(1000);
			((JavascriptExecutor) getWD()).executeScript(mouseOverScript,b);
			Thread.sleep(1000);
			((JavascriptExecutor) getWD()).executeScript("arguments[0].click();",b);


		} catch (Exception e) {

		}
	}
	

	/**
	 * To clear the Data
	 * @param elementlocator
	 * @param locatortype
	 * @throws Exception
	 */
	public  void Clear(String elementlocator,String locatortype) throws Exception {
		
		getWebElement(elementlocator, locatortype).clear();
	}

}






