import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvalidLogin {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);

		// Launching The Browser and Navigate to website
		driver.get("http://www.wsecu.com");
		driver.manage().window().maximize();

		// Entering incorrect username
		driver.findElement(By.id("digital-banking-username")).sendKeys("thisuserwillnotwork");
		// clicking Sign in button
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Thread.sleep(5000); // This wait for fix the URL to get (https://digital.wsecu.org/banking/signin)

		// Verifying URL is matching
		String actualURLtext = driver.getCurrentUrl();
		String expectedURLtext = "https://digital.wsecu.org/banking/signin";
		if (actualURLtext.equals(expectedURLtext)) {
			System.out.println("Verifying URL is matching");
		} else {
			System.out.println("URL does NOT match");
		}

		// enter an incorect password
		driver.findElement(By.id("widget-wsecu-multichannel-login-ng-3922485-username-password-form-password"))
				.sendKeys("thispasswillfailforsure");
		// clicking sign in button
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sign-in ng-binding']")).click();

		Thread.sleep(5000);

		// Getting the text from website
		String expectedMessage = "Sorry, incorrect username.";
		String actualMessage = driver.findElement(By.xpath("//div[@class='ng-binding ng-scope']")).getText();

		// Verifying "Sorry, incorrect username." message
		if (expectedMessage.equals(actualMessage)) {
			System.out.println("Verify error message recieved  -->" + expectedMessage);
		} else {
			System.out.println("Error Message does NOT recieved");
		}

		driver.close();

	}

}
