package pardotwebtests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractWebTests {

	static String baseWebUrlString = "https://pi.pardot.com";
	static protected WebDriver webDriver;
	private static final String userName = "pardot.applicant@pardot.com";
	private static final String password = "Applicant2012";

	@BeforeClass
	public static void setupClass() {

		webDriver = new FirefoxDriver();
		webDriver.get(baseWebUrlString);
		webDriver.findElement(By.id("email_address")).sendKeys(userName);
		webDriver.findElement(By.id("password")).sendKeys(password);
		// webDriver.findElement(By.className("button")).submit();
		webDriver.findElement(By.name("commit")).submit();
		assertEquals("Dashboard - Pardot", webDriver.getTitle());
	}

	@AfterClass
	public static void cleanupClass() {
		// webDriver.quit();
	}

	public String randomStringGenerator(String prefix) {
		Random random = new Random();
		int i = random.nextInt(10000);
		Date date = new Date();
		if (prefix == null) {
			prefix = "pardot";
		}

		String timeStamp = String.valueOf(date.getDay()) + String.valueOf(date.getMonth())
				+ String.valueOf(date.getYear()) + String.valueOf(date.getHours()) + String.valueOf(date.getMinutes())
				+ String.valueOf(date.getSeconds());
		String randomString = prefix + "_" + String.valueOf(i) + "_"+timeStamp;
		//String randomString = prefix + "_" + String.valueOf(i);
		return randomString;
	}
	
	public static void waitForClick()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
