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

    public void loginWithValidCreds() {
        email.clear();
        email.sendKeys(Utils.getEmail());
        password.clear();
        password.sendKeys(Utils.getPassword());
        loginButton.click();
    }

    public String loginWithInvalidCreds() {
        email.clear();
        email.sendKeys(Utils.getInvalidEmail());
        password.clear();
        password.sendKeys(Utils.getInvalidPassword());
        loginButton.click();
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithBlankPass() {
        email.clear();
        email.sendKeys(Utils.getInvalidEmail());
        password.sendKeys("");
        loginButton.click();
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithBlankEmail() {
        email.sendKeys("");
        password.clear();
        password.sendKeys(Utils.getInvalidPassword());
        loginButton.click();
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithInvalidEmail() {
        email.clear();
        email.sendKeys(Utils.getInvalidEmail());
        password.clear();
        password.sendKeys(Utils.getPassword());
        loginButton.click();
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String loginWithInvalidPass() {
        email.clear();
        email.sendKeys(Utils.getEmail());
        password.clear();
        password.sendKeys(Utils.getInvalidPassword());
        loginButton.click();
        errorList = driver.findElements(By.xpath("//*[@data-test='error']"));
        return getErrorMessage();
    }

    public String getErrorMessage() {
        String err = "";
        if(errorList.size() > 0) {
        err = errorList.get(0).getText();
        }
        return err;
    }
}
