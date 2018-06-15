package com.page.object;

import java.util.Objects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.WebDriverWait;

public class EcomHomePage {
	private WebDriver driver;
	private static final String ECOM_URL = "https://ecom-test-dat.worldticket.net/";

	@FindBy(xpath = "//*[@id=\"lang-switcher-wrapper-regular\"]//a[text()='French']")
	private WebElement changeLanguageToFR;

	@FindBy(xpath = "//*[@id=\"lang-switcher-wrapper-regular\"]//a[text()='English']")
	private WebElement changeLanguageToEN;

	@FindBy(xpath = "//*[@id=\"lang-switcher-wrapper-regular\"]//a[text()='Danish']")
	private WebElement changeLanguageToDA;

	@FindBy(xpath = "//*[@id=\"lang-switcher-wrapper-regular\"]")
	private WebElement clickToViewLang;

	@FindBy(xpath = "//*[@id=\"lang-switcher-wrapper-regular\"]//span[contains(@class,'value')]")
	private WebElement currentlang;

	@FindBy(css = "input#traveltype-return")
	private WebElement returnRadioButton;

	@FindBy(css = "input#traveltype-one-way")
	private WebElement oneWayTripRadioButton;

	@FindBy(id = "origin")
	private WebElement selectOriginalAirport;

	@FindBy(id = "destination")
	private WebElement selectDestinationAirport;

	@FindBy(css = "#select-child-note")
	private WebElement travelWithChild;
	
	@FindBy(css = "#select-adult")
	private WebElement selectAdult;
	
	@FindBy(css = "#select-child")
	private WebElement selectChild;
	
	@FindBy(css = "#select-infant")
	private WebElement selectInfant;
	
	@FindBy(css = "#promo_code")
	private WebElement addPromoCode;
	
	@FindBy(css = "#btnSearch")
	private WebElement btnSearch;
	
	
	public EcomHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void openPage() {
		driver.get(ECOM_URL);
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().contains("WorldTicket New Airtickets Booking");
			}
		});
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void closePage() {
		driver.quit();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void changeEnglishLanguage() {
		String curLang = currentlang.getText();
		System.out.println(curLang);
		if (!Objects.equals(curLang, new String("English"))) {
			clickToViewLang.click();
			changeLanguageToEN.click();
		}
	}

	
}
