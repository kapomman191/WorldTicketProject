package com.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalDialog {
	private WebDriver driver;
	@FindBy(css = "div.modal-dialog #as_guest")	
	private WebElement processWithGuestBtn;
	
	public ModalDialog(WebDriver driver) {
		this.driver = driver;
	}
	
	public void bookingwithGuest() {
		//selecting date of journey
			WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.modal-dialog #as_guest")));
			processWithGuestBtn.click();
		}
}
