package com.pavan.pageactions;

import org.openqa.selenium.WebDriver;

import com.pavan.utill.BaseWebDriver;

public class LoginPageActions {
	
	private static WebDriver d;
	
	public LoginPageActions(WebDriver d) {
		this.d=d;
	}
	
	public void login() throws Exception {
		
		BaseWebDriver base = new BaseWebDriver(d);
		
		System.out.println("The data is "+base.getUiObjectLocator("txtUserName"));
		
	}
	

}
