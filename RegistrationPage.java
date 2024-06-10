import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrationPage 
{

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		WebDriver driver = null;
	    
		Properties prop = new Properties();
		File file = new File("data/registration.properties");
		FileInputStream input = new FileInputStream(file);
		prop.load(input);
		
		String browser = prop.getProperty("browser");
		String url = "https://eroomrent.in/ownerreg.php";
		String currentUrl = "https://eroomrent.in/ownerlogin.php";
		
		//locators
		String nameId = prop.getProperty("nameId");
		String mobileId = prop.getProperty("mobileId");
		String passwordId = prop.getProperty("passwordId");
		String confirmPwdId = prop.getProperty("confirmPwdId");
		String emailName = prop.getProperty("emailName");
		String submitName = prop.getProperty("submitName");

		//Data
		String name = prop.getProperty("name");
		String mobile = prop.getProperty("mobile");
		String password = prop.getProperty("password");
		String confirm = prop.getProperty("confirm");
		String email = prop.getProperty("email");
		
		
		if(browser.equalsIgnoreCase("FireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.get(url);
		driver.findElement(By.id(nameId)).sendKeys(name);
		driver.findElement(By.id(mobileId)).sendKeys(mobile);
		driver.findElement(By.id(passwordId)).sendKeys(password);
		driver.findElement(By.id(confirmPwdId)).sendKeys(confirm);
		driver.findElement(By.name(emailName)).sendKeys(email);
		driver.findElement(By.name(submitName)).click();
		
		
		driver.switchTo().alert().accept();
		String actualUrl = driver.getCurrentUrl();
		
		if(currentUrl.equals(actualUrl))
		{
			System.out.println("Registered Successfully!");
		}
		else
		{
			System.out.println("User is already registered!");
		}		
	}
}
