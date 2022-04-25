package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DeleteEmpPages {
WebDriver driver;
	
	public DeleteEmpPages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

} 
	}
