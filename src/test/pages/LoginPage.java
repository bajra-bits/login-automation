package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.driver.SeleniumDriver;
import test.utils.Utils;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private WebElement email;
    private WebElement password;
    private WebElement loginButton;
    private List<WebElement> errorList;
    private Wait<WebDriver> wait = new WebDriverWait(SeleniumDriver.getInstance(), Duration.ofSeconds(2));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        email = driver.findElement(By.id("user-name"));
        password = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.id("login-button"));
    }

    public void logout() {
       WebElement hamburgerIcon = driver.findElement(By.id("react-burger-menu-btn"));
       WebElement logout =  driver.findElement(By.xpath("//*[@id='logout_sidebar_link']"));
       hamburgerIcon.click();

        /* explicit wait for sidebar to appear*/
        wait.until(d -> logout.isDisplayed());
        logout.click();
    }

    public void loginWithValidCreds(String email, String password) {
        login(email, password);
    }

    public String loginWithInvalidCreds(String email, String password) {
        login(email, password);
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithBlankPass(String email, String password) {
        login(email, password);
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithBlankEmail(String email, String password) {
        login(email, password);
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithInvalidEmail(String email, String password) {
        login(email, password);
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithInvalidPass(String email, String password) {
        login(email, password);
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public void login(String email, String password) {
        this.email.clear();
        this.email.sendKeys(email);
        this.password.clear();
        this.password.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
        String err = "";
        if(errorList.size() > 0) {
        err = errorList.get(0).getText();
        }
        return err;
    }
}
