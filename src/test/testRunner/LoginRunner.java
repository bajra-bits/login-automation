package test.testRunner;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.driver.SeleniumDriver;
import test.pages.LoginPage;

import java.time.Duration;

public class LoginRunner {
    private LoginPage loginPage;
    private String invalidCreds = "Epic sadface: Username and password do not match any user in this service";
    private String passwordEmpty = "Epic sadface: Password is required";
    private String emailEmpty = "Epic sadface: Username is required";

    public LoginRunner() {
        WebDriver driver = SeleniumDriver.getInstance();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    public void doLoginWithValidCreds() {
        loginPage.loginWithValidCreds();
        loginPage.logout();
        System.out.println("User login successful!");
    }

    public void doLoginWithInvalidCreds() {
        String validationMessage = loginPage.loginWithInvalidCreds();
        System.out.println(validationMessage);
    }

    public void doLoginWithBlankEmail() {
        String validationMessage = loginPage.loginWithBlankEmail();
        System.out.println(validationMessage);
    }
    public void doLoginWithBlankPass() {
        String validationMessage = loginPage.loginWithBlankPass();
        System.out.println(validationMessage);
    }
    public void doLoginWithInvalidEmail() {
        String validationMessage = loginPage.loginWithInvalidEmail();
        System.out.println(validationMessage);
    }
    public void doLoginWithInvalidPass() {
        String validationMessage = loginPage.loginWithInvalidPass();
        System.out.println(validationMessage);
    }

    public static void main(String[] args) throws Exception{
        LoginRunner runner = new LoginRunner();
        runner.doLoginWithBlankEmail();
        try {
            runner.doLoginWithBlankPass();
            runner.doLoginWithInvalidEmail();
            runner.doLoginWithInvalidPass();
            runner.doLoginWithInvalidCreds();
            runner.doLoginWithValidCreds();
            SeleniumDriver.getInstance().close();


        }catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
}
