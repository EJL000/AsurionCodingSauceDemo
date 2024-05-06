import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest { 
    // Declare webdriver
    WebDriver driver = new ChromeDriver();

    @Test
    public void StandardLogin() {
        // Accessing webpage
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        // Login Mechanism
        // Finding elements for login 
        WebElement username=driver.findElement(By.id("user-name"));
        WebElement password=driver.findElement(By.id("password"));
        WebElement login=driver.findElement(By.id("login-button"));
        // Standard User Login Credentials
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        login.click();
        // Login Verification
        String expectedLoggedInUrl="https://www.saucedemo.com/inventory.html";
        String actualLoggedInUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedLoggedInUrl, actualLoggedInUrl);
        // Logout Mechanism
        // Finding elements for logout
        WebElement menuButton=driver.findElement(By.id("react-burger-menu-btn"));
        WebElement logoutButton=driver.findElement(By.id("logout_sidebar_link"));
        // Click menu button
        menuButton.click();
        // Wait for right-side panel before interacting with logout button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        // Logout Verification
        String expectedLoggedOutUrl="https://www.saucedemo.com/";
        String actualLoggedOutUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedLoggedOutUrl, actualLoggedOutUrl);
    }
    @Test
    public void LockedOutLogin() {
        // Accessing webpage
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        // Login Mechanism
        // Finding elements for login 
        WebElement username=driver.findElement(By.id("user-name"));
        WebElement password=driver.findElement(By.id("password"));
        WebElement login=driver.findElement(By.id("login-button"));
        // Locked Out User Login Credentials
        username.sendKeys("locked_out_user");
        password.sendKeys("secret_sauce");
        login.click();
        // Unsuccessful Login Verification
        String expectedLoggedInUrl="https://www.saucedemo.com/";
        String actualLoggedInUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedLoggedInUrl, actualLoggedInUrl);
        // Verifying Error Message
        WebElement errorMessageContainer=driver.findElement(By.className("error-message-container"));
        String expectedErrorMessage="Epic sadface: Sorry, this user has been locked out.";
        String actualErrorMessage=errorMessageContainer.getText();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
