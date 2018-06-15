package com.page.object;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectOutBoundInBoundDialog {
	private WebDriver driver;

	public SelectOutBoundInBoundDialog(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='tabs-panels flight-list tableview-inbound']//tbody/tr[4]/td['+randomInBoundFare+']")
	private WebElement selectInBoundFareInFirstRow;

	public void randomSelectOutBoundFare() throws InterruptedException {
		// selecting OutBound
		Random rand = new Random();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='tabs-panels flight-list tableview-outbound']//tbody/tr[4]//div/input[@class='class-input-field']//..//label")));

		List<WebElement> possibleSeatsOutBound = driver.findElements(By.xpath(
				"//div[@class='tabs-panels flight-list tableview-outbound']//tbody/tr[4]//div/input[@class='class-input-field']//..//label"));

		int RowCount = rand.nextInt(possibleSeatsOutBound.size());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		possibleSeatsOutBound.get(RowCount).click();

	}

	public void randomSelectInBoundFare() throws InterruptedException {
		// selecting InBound		
		Random rand = new Random();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='tabs-panels flight-list tableview-inbound']//tbody/tr[4]//div/input[@class='class-input-field']//..//label")));
		List<WebElement> possibleSeatsInBound = driver.findElements(By.xpath(
				"//div[@class='tabs-panels flight-list tableview-inbound']//tbody/tr[4]//div/input[@class='class-input-field']//..//label"));
		int RowCount = rand.nextInt(possibleSeatsInBound.size());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		possibleSeatsInBound.get(RowCount).click();
		
	}

	public void checkOutBoundCity(String expectedOutBound) {

		WebElement departureCity = driver
				.findElement(By.cssSelector("div[class='panel table-tab'] div[class='planeplane-from'] + span"));

		assertThat(departureCity.getText(), equalTo(expectedOutBound));
	}

	public void checkInBoundCity(String expectedInBound) {

		WebElement departureCity = driver
				.findElement(By.cssSelector("div[class='panel table-tab'] div[class='planeplane-to'] + span"));

		assertThat(departureCity.getText(), equalTo(expectedInBound));
	}

}
