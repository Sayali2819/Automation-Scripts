package datadriven;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testing {

	public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Scanner sc = new Scanner(System.in);

        String url = "https://bbg.dibcs.in/home";
        String name = "Varsha";
        String mobile = "7878787878";
        String city = "Bhayandar";
        String bType = "/html/body/app-root/ion-app/ion-alert/div[2]/div[3]/button[4]/div/div[2]";
        String bCat = "/html/body/app-root/ion-app/ion-alert/div[2]/div[3]/button[9]/div/div[2]";
        String addPath = "/html/body/app-root/ion-app/ion-router-outlet/app-home/ion-content/ion-card/ion-card-content/ion-textarea/label/div[3]/textarea";

        String cmpName = "Art";
        String address = "Unit No. 504, 5th Floor, Tower 3, NESCO IT Park, NESCO Centre, Western Express Hwy, Goregaon, Mumbai";
        String expectedUrl = "https://bbg.dibcs.in/otp?mobile_no=" + mobile;

        int timeout = 20;
        String logXp = "/html/body/app-root/ion-app/ion-router-outlet/app-otp/ion-content/ion-card/ion-card-content/ion-input[1]/label/div[3]/input";
        String otpXp = "/html/body/app-root/ion-app/ion-router-outlet/app-otp/ion-content/ion-card/ion-card-content/ion-input[2]/label/div[3]/input";
        String registeredMob = "7777770007";
        String dbLink = "https://bbg.dibcs.in/dashboard";

        driver.get(url);

        System.out.println("Select 1. Register  2. Login:");
        int num = sc.nextInt();

        if (num == 1) 
        {
            registerUser(driver, wait, name, mobile, city, bType, bCat, addPath, cmpName, address, expectedUrl);
        } 
        else if (num == 2) 
        {
            loginUser(driver, wait, logXp, otpXp, registeredMob, timeout, dbLink);
        }
    }

    private static void registerUser(WebDriver driver, WebDriverWait wait, String name, String mobile, String city,
                                     String bType, String bCat, String addPath, String cmpName, String address, String expectedUrl) throws InterruptedException 
    {

        driver.findElement(By.id("ion-input-0")).sendKeys(name);
        driver.findElement(By.id("ion-input-1")).sendKeys(mobile);
        driver.findElement(By.id("ion-input-2")).sendKeys(city);

        // Business Type Drop Down
        WebElement typeDd = driver.findElement(By.className("ng-pristine"));
        typeDd.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(bType)));
        option.click();
        driver.findElement(By.xpath("//*[@id=\"ion-overlay-3\"]/div[2]/div[4]/button[2]")).click();

        // Business Category Drop Down
        Thread.sleep(4000);
        driver.findElement(By.name("bussines_category")).click();
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(bCat)));
        category.click();
        driver.findElement(By.xpath("/html/body/app-root/ion-app/ion-alert/div[2]/div[4]/button[2]/span")).click();

        // Company Name
        driver.findElement(By.id("ion-input-3")).sendKeys(cmpName);

        // Address
        Thread.sleep(5000);
        driver.findElement(By.xpath(addPath)).sendKeys(address);

        // Register Button
        driver.findElement(By.xpath("//ion-button[@shape='round']")).click();

        // Validating Url
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current Url: " + currentUrl);

        if (expectedUrl.equals(currentUrl)) 
        {
            System.out.println("Registered Successfully!");
        }
        else 
        {
            System.out.println("User is already registered!");
        }
    }

    private static void loginUser(WebDriver driver, WebDriverWait wait, String logXp, String otpXp, String registeredMob, int timeout, String dbLink) throws InterruptedException
    {

        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(), 'Click here to LOGIN')]")));
        loginButton.click();

   
       
        while (timeout > 0) 
        {
            driver.findElement(By.xpath(logXp)).sendKeys(registeredMob);
            WebElement sendOtp = driver.findElement(By.xpath("//*[@id=\"main\"]/app-otp/ion-content/ion-card/ion-card-content/div/ion-button"));
            if (sendOtp.isDisplayed()) 
            {
            	sendOtp.click();
                break;
            }

            timeout--;
            Thread.sleep(500);
        }
        
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Success')]")));
        if(msg.isDisplayed())
        {
        	System.out.println("User is already registered!");
        }
        else
        {
        	System.out.println("User is not registered!");
        }
        //WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/ion-app/ion-alert/div[2]/div[1]/h2[2]")));
//        if(error.isDisplayed())
//        {
//        	System.out.println("Number is not registered. Please register!");
//        }
 
//        else
       {
            Thread.sleep(3000); // Wait for the OTP input field to appear
//            driver.findElement(By.xpath(otpXp)).sendKeys("12345");
            WebElement otpInput = driver.findElement(By.xpath(otpXp));
            System.out.println("OTP is sent to User!");
           
           if (otpInput.isDisplayed()) 
           {              
            	System.out.println(otpInput.isDisplayed());
                otpInput.sendKeys("12345");
                System.out.println("Correct Otp");
            } 
            else 
            {
                System.out.println("Wrong Otp");
            }
            driver.findElement(By.xpath("/html/body/app-root/ion-app/ion-router-outlet/app-otp/ion-content/ion-card/ion-card-content/div/ion-button")).click();
            
//          Validating URL
            Thread.sleep(3000);
            String dbActLink = driver.getCurrentUrl();
            System.out.println(dbActLink);

            if (dbLink.equals(dbActLink))
            {
                System.out.println("User Logged in successfully!");
            } 
            else if(dbActLink.equals("https://bbg.dibcs.in/otp"))
            {
              System.out.println("User is not Logged-in registered!");
            }
       }      
    }
}
