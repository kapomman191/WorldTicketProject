package com.page.object;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingDialog {
	private WebDriver driver;
	@FindBy(css = "input#traveltype-return")
	private WebElement returnRadioButton;

	@FindBy(css = "input#traveltype-one-way")
	private WebElement oneWayTripRadioButton;

	@FindBy(id = "origin")
	private WebElement selectOriginalAirport;

	@FindBy(id = "destination")
	private WebElement selectDestinationAirport;

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

	public BookingDialog(WebDriver driver) {
		this.driver = driver;
	}

	public void selectReturnTrip() {
		if (!returnRadioButton.isSelected()) {
			returnRadioButton.isSelected();
		}
	}

	public void selectOneWayTrip() {
		if (!returnRadioButton.isSelected()) {
			oneWayTripRadioButton.isSelected();
		}
	}

	public void inputOriginalAirport(String originalAirport) {
		selectOriginalAirport.clear();
		selectOriginalAirport.sendKeys(originalAirport);
	}

	public void inputDestinationAirport(String destinationAirport) {
		selectDestinationAirport.clear();
		selectDestinationAirport.sendKeys(destinationAirport);
		selectDestinationAirport.sendKeys(Keys.ENTER);
	}

	public void selectingDateOfJourney() {

		Random rand = new Random();
		int randomDepartureDate = rand.nextInt(30) + 1;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//li[@class='dates-select-depart travel-date']//a[text()='" + randomDepartureDate + "']")));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//li[@class='dates-select-depart travel-date']//a[text()='" + randomDepartureDate + "']")));
		driver.findElement(
				By.xpath("//li[@class='dates-select-depart travel-date']//a[text()='" + randomDepartureDate + "']"))
				.click();

	}

	public void selectingDateOfReturn() {

		Random rand = new Random();
		int randomReturnDate = rand.nextInt(30) + 1;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//li[@class='flights-select-return travel-date']//a[text()='" + randomReturnDate + "']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//li[@class='flights-select-return travel-date']//a[text()='" + randomReturnDate + "']")));
		driver.findElement(
				By.xpath("//li[@class='flights-select-return travel-date']//a[text()='" + randomReturnDate + "']"))
				.click();
	
	}

	public void clickTravelWithChid() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#select-child-note")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#select-child-note")));
		WebElement travelWithChild = driver.findElement(By.cssSelector("#select-child-note"));
		travelWithChild.click();
	
	}

	public void addAdultValue(String text) {
		Select selectAdult = new Select(driver.findElement(By.cssSelector("#select-adult")));
		selectAdult.selectByVisibleText(text);

	}

	public void addChildValue(String text) {
		Select selectChild = new Select(driver.findElement(By.cssSelector("#select-child")));
		selectChild.selectByVisibleText(text);

	}

	public void addInfantValue(String text) {
		Select selectInfant = new Select(driver.findElement(By.cssSelector("#select-infant")));
		selectInfant.selectByVisibleText(text);

	}

	public void clickSearchBtnOnFindTrip() {
		btnSearch.click();

	}
}