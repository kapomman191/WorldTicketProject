package example;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.page.object.BookingDialog;
import com.page.object.BookingSummaryDialog;
import com.page.object.EcomHomePage;
import com.page.object.SelectOutBoundInBoundDialog;
import com.page.object.ModalDialog;
import com.page.object.PassengersDialog;

public class EcomBookingScript {

	private EcomHomePage EcomPage;
	private BookingDialog BKDialog;
	private SelectOutBoundInBoundDialog SelectOutBoundInBoundDialog;
	private BookingSummaryDialog BookingSummaryDialog;
	private ModalDialog ModalDialog;
	private PassengersDialog PassengersDialog;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		EcomPage = PageFactory.initElements(driver, EcomHomePage.class);
		BKDialog = PageFactory.initElements(driver, BookingDialog.class);
		SelectOutBoundInBoundDialog = PageFactory.initElements(driver, SelectOutBoundInBoundDialog.class);
		BookingSummaryDialog = PageFactory.initElements(driver, BookingSummaryDialog.class);
		ModalDialog = PageFactory.initElements(driver, ModalDialog.class);
		PassengersDialog = PageFactory.initElements(driver, PassengersDialog.class);
		EcomPage.openPage();

	}

	 @AfterTest
	 public void afterTest() {
	 EcomPage.closePage();
	 }

	@Test
	public void runBooking() throws InterruptedException {
		System.out.println("Start");

		assertThat(EcomPage.getTitle(), equalTo("WorldTicket New Airtickets Booking"));
		assertThat(EcomPage.getPageSource(), containsString("Find your trip"));

		EcomPage.changeEnglishLanguage();

		BKDialog.selectReturnTrip();
		
		BKDialog.inputOriginalAirport("Midtjyllands Lufthavn (KRP)");

		BKDialog.inputDestinationAirport("Copenhagen Kastrup (CPH)");

		BKDialog.selectingDateOfJourney();

		BKDialog.selectingDateOfReturn();

		BKDialog.clickTravelWithChid();

		BKDialog.addAdultValue("1 Adult");

		BKDialog.addChildValue("1 Child");

		BKDialog.addInfantValue("1 Infant");

		BKDialog.clickSearchBtnOnFindTrip();

		SelectOutBoundInBoundDialog.checkOutBoundCity("MIDTJYLLANDS LUFTHAVN (KRP) - COPENHAGEN KASTRUP (CPH)");
		SelectOutBoundInBoundDialog.checkInBoundCity("COPENHAGEN KASTRUP (CPH) - MIDTJYLLANDS LUFTHAVN (KRP)");

		SelectOutBoundInBoundDialog.randomSelectOutBoundFare();

		SelectOutBoundInBoundDialog.randomSelectInBoundFare();

		BookingSummaryDialog.clickContinueOnSummaryPage();

		ModalDialog.bookingwithGuest();

		PassengersDialog.fillPassengersInformation(1, "Mr.", "First1", "Last1", "01", "January", "1986");
		PassengersDialog.fillPassengersInformation(2, "Mr.", "First2", "Last2", "02", "January", "2015");
		PassengersDialog.fillPassengersInformation(3, "Mr.", "First3", "Last3", "03", "January", "2017");

		PassengersDialog.fillContactInformation("Mr.", "First3", "Last3", "test@test.com", "+12345678");

		BookingSummaryDialog.clickGoToDirectPayment();

		BookingSummaryDialog.CompareCheckOutAndTotalPrice();

		BookingSummaryDialog.CompareContractInformation("Mr. First3 Last3", "Email: test@test.com",
				"Mobile phone: +12345678");
		System.out.println("Done");
	}

}
