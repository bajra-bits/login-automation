package test.testRunner;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.driver.SeleniumDriver;
import test.pages.LoginPage;
import test.utils.Utils;

import java.time.Duration;

public class LoginRunner {
    private LoginPage loginPage;
    private final String invalidCreds = "Epic sadface: Username and password do not match any user in this service";
    private final String passwordEmpty = "Epic sadface: Password is required";
    private final String emailEmpty = "Epic sadface: Username is required";
    private final String lockedUser = "Epic sadface: Sorry, this user has been locked out.";

    public LoginRunner() {
        WebDriver driver = SeleniumDriver.getInstance();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    public void doLoginWithValidCreds() {
        loginPage.loginWithValidCreds(Utils.getEmail(), Utils.getPassword());
        loginPage.logout();
        System.out.println("User login successful!");
    }

    public void doLoginWithInvalidCreds() {
        String validationMessage = loginPage.loginWithInvalidCreds(Utils.getInvalidEmail(), Utils.getInvalidPassword());
        System.out.println(validationMessage);
    }

    public void doLoginWithBlankEmail() {
        String validationMessage = loginPage.loginWithBlankEmail("", Utils.getPassword());
        System.out.println(validationMessage);
    }
    public void doLoginWithBlankPass() {
        String validationMessage = loginPage.loginWithBlankPass(Utils.getEmail(), "");
        System.out.println(validationMessage);
    }
    public void doLoginWithInvalidEmail() {
        String validationMessage = loginPage.loginWithInvalidEmail(Utils.getInvalidEmail(), Utils.getPassword());
        System.out.println(validationMessage);
    }
    public void doLoginWithInvalidPass() {
        String validationMessage = loginPage.loginWithInvalidPass(Utils.getEmail(), Utils.getInvalidPassword());
//        Assert.assertEquals(validationMessage, invalidCreds);
        System.out.println(validationMessage);
    }
    public void doLoginWithLockedUser() {
        String validationMessage = loginPage.loginWithInvalidPass(Utils.getLockedUser(), Utils.getPassword());
        System.out.println(validationMessage);
    }

    public void dLoginWithLockedUserInvalidPassword() {
        String validationMessage = loginPage.loginWithInvalidPass(Utils.getLockedUser(), Utils.getInvalidPassword());
        System.out.println(validationMessage);
    }

    public static void main(String[] args) throws Exception{
        LoginRunner runner = new LoginRunner();
        try {
//            runner.doLoginWithBlankPass();
//            runner.doLoginWithBlankEmail();
            runner.doLoginWithInvalidEmail();
            runner.doLoginWithInvalidPass();
            runner.doLoginWithInvalidCreds();
            runner.doLoginWithLockedUser();
            runner.dLoginWithLockedUserInvalidPassword();
            runner.doLoginWithValidCreds();

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }finally {
            SeleniumDriver.getInstance().close();

        }
    }
}
