package com.page.object;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingSummaryDialog {
	private WebDriver driver;
	@FindBy(css = "#summary #continue")
	private WebElement summaryClickContinue;

	@FindBy(css = "#summary #continue-checkout")
	private WebElement goToDirectPaymentBtn;

	public BookingSummaryDialog(WebDriver driver) {
		this.driver = driver;
	}

	
	
	public void clickContinueOnSummaryPage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#summary #continue")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#summary #continue")));
		summaryClickContinue.click();
	}

	public void clickGoToDirectPayment() throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#summary #continue-checkout")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#summary #continue-checkout")));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,500)");

		goToDirectPaymentBtn.click();
	}

	public String getSummaryTotalPrice() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#summary-total-price")));
		String summaryTotalPrice = driver.findElement(By.cssSelector("#summary-total-price")).getText();
		return summaryTotalPrice;

	}

	public String getCheckOutSummary() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#shopping-cart-totals-table tr > td > strong > span")));
		String summaryCheckOut = driver
				.findElement(By.cssSelector("#shopping-cart-totals-table tr > td > strong > span")).getText();
		return summaryCheckOut;

	}

	public void CompareCheckOutAndTotalPrice() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#summary-total-price")));
		String summaryTotalPrice = driver.findElement(By.cssSelector("#summary-total-price")).getText();
		System.out.println("TotalPrice = " + summaryTotalPrice);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#shopping-cart-totals-table tr > td > strong > span")));
		String summaryCheckOut = driver
				.findElement(By.cssSelector("#shopping-cart-totals-table tr > td > strong > span")).getText();
		System.out.println("Check Out Price = " + summaryCheckOut);
		assertThat(summaryTotalPrice, equalTo(summaryCheckOut));
		System.out.println("Check Out Price = TotalPrice");
	}

	public void CompareContractInformation(String expectedName, String expextedEmail, String expectedMobilePhone) {

		String[] expectedArray = { expectedName, expextedEmail, expectedMobilePhone };
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse-contact .contact_info > div")));

		List<WebElement> myList = driver.findElements(By.cssSelector("#collapse-contact .contact_info > div"));

		List<String> all_elements_text = new ArrayList<>();

		for (int i = 0; i < myList.size(); i++) {

			all_elements_text.add(myList.get(i).getText());

			System.out.println("Contract_Actual = " + myList.get(i).getText());
			System.out.println("Contract_Expected = " + expectedArray[i]);
			assertThat(myList.get(i).getText(), equalTo(expectedArray[i]));
		}
	}

}
