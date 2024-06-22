package datadriven;

//Data Driven Testing
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//Taking input from excel sheet
		File file = new File("C:\\Sayali\\Book1.xlsx");
		FileInputStream input = new FileInputStream(file);
		XSSFWorkbook excelWBook = new XSSFWorkbook(input);
		XSSFSheet excelSheet = excelWBook.getSheet("TestData");
		
		int totalRows = excelSheet.getLastRowNum()+1;
		
		String username;
		String pwd;
		
		
		for(int r=1; r<totalRows; r++)
		{
			username = excelSheet.getRow(r).getCell(0).toString();
			pwd = excelSheet.getRow(r).getCell(1).toString();
			login(driver, wait, username, pwd);
			Thread.sleep(5000);
		}
	}
	public static void login(WebDriver driver, WebDriverWait wait, String username, String pwd) throws InterruptedException
	{
		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().window().maximize();
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("login-button")).click();
		
		String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
		Thread.sleep(3000);
		String actUrl = driver.getCurrentUrl();
		if(expectedUrl.equals(actUrl))
		{
			System.out.println("Logged-in successfully!");
		}
		else
		{
			System.out.println("Login Failed!");
		}
		
	}

}
