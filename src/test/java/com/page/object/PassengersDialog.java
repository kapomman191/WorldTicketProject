package com.page.object;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PassengersDialog {
	private WebDriver driver;

	public PassengersDialog(WebDriver driver) {
		this.driver = driver;
	}

	public void fillPassengersInformation(int numPassengers, String title, String firstName, String lastName,
			String day, String month, String year) throws InterruptedException {

		Select passengersTitle = new Select(driver.findElement(By.id("passenger_title_" + (numPassengers - 1))));
		passengersTitle.selectByVisibleText(title);

		WebElement passengersFirstName = driver.findElement(By.id("passenger_firstname_" + (numPassengers - 1)));
		passengersFirstName.clear();
		passengersFirstName.sendKeys(firstName);
		passengersFirstName.sendKeys(Keys.ENTER);

		WebElement passengersLastName = driver.findElement(By.id("passenger_lastname_" + (numPassengers - 1)));
		passengersLastName.clear();
		passengersLastName.sendKeys(lastName);
		passengersLastName.sendKeys(Keys.ENTER);

		Select passengersDay = new Select(driver.findElement(By.id("passenger_day_" + (numPassengers - 1))));
		passengersDay.selectByVisibleText(day);

		Select passengersMonth = new Select(driver.findElement(By.id("passenger_month_" + (numPassengers - 1))));
		passengersMonth.selectByVisibleText(month);

		Select passengersYear = new Select(driver.findElement(By.id("passenger_year_" + (numPassengers - 1))));
		passengersYear.selectByVisibleText(year);

		List<WebElement> myList = driver
				.findElements(By.cssSelector(".departure-flight-item ul[class='price-overview per-passenger'] b"));

		List<String> all_elements_text = new ArrayList<>();

		String expectedPassenger = firstName + " " + lastName;

		all_elements_text.add(myList.get(numPassengers - 1).getText());

		System.out.println("departure_Actual = " + myList.get(numPassengers - 1).getText());
		System.out.println("departure_Expected = " + expectedPassenger);
		assertTrue(myList.get(numPassengers - 1).getText().contains(expectedPassenger));

		Boolean isPresent = driver
				.findElements(By.cssSelector(".return-flight-item ul[class='price-overview per-passenger']"))
				.size() > 0;
		if (isPresent) {
			List<WebElement> myListReturn = driver
					.findElements(By.cssSelector(".return-flight-item ul[class='price-overview per-passenger'] b"));
			List<String> all_elements_text_return = new ArrayList<>();
			all_elements_text_return.add(myListReturn.get(numPassengers - 1).getText());

			System.out.println("return_Actual = " + myListReturn.get(numPassengers - 1).getText());
			System.out.println("return_Expected = " + expectedPassenger);
			assertTrue(myListReturn.get(numPassengers - 1).getText().contains(expectedPassenger));

		}

	}

	public void fillContactInformation(String title, String firstName, String lastName, String email,
			String mobilePhone) {
		Select contactTitle = new Select(driver.findElement(By.id("contact_title")));
		contactTitle.selectByVisibleText(title);

		WebElement contactFirstName = driver.findElement(By.id("contact_firstname"));
		contactFirstName.clear();
		contactFirstName.sendKeys(firstName);

		WebElement contactLastName = driver.findElement(By.id("contact_lastname"));
		contactLastName.clear();
		contactLastName.sendKeys(lastName);

		WebElement contactEmail = driver.findElement(By.id("contact_email"));
		contactEmail.clear();
		contactEmail.sendKeys(email);

		WebElement contactMobilePhone = driver.findElement(By.id("contact_mobile"));
		contactMobilePhone.clear();
		contactMobilePhone.sendKeys(mobilePhone);

	}
}
