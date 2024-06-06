package datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		String url = "https://www.saucedemo.com/v1/";
		String userpath = "//input[@id='user-name']";
		String pwdpath = "//input[@id='password']";
		String button = "btn_action";
		String username = "standard_user";
		String pwd = "secret_sauce";
		
		driver.get(url);
		driver.findElement(By.xpath(userpath)).sendKeys(username);
		driver.findElement(By.xpath(pwdpath)).sendKeys(pwd);
		driver.findElement(By.className(button)).click();

	}

}
