package test.testRunner;


import org.openqa.selenium.WebDriver;
import test.driver.SeleniumDriver;
import test.dto.LoginDTO;
import test.pages.DashboardPage;
import test.pages.LoginPage;
import test.utils.Utils;

import java.time.Duration;

public class LoginRunner {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private final String invalidCreds = "Epic sadface: Username and password do not match any user in this service";
    private final String passwordEmpty = "Epic sadface: Password is required";
    private final String emailEmpty = "Epic sadface: Username is required";
    private final String lockedUser = "Epic sadface: Sorry, this user has been locked out.";

    public LoginRunner() {
        WebDriver driver = SeleniumDriver.getInstance();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    public void getProductList() {
        LoginDTO response = loginPage.login(Utils.getEmail(), Utils.getPassword());
        if (response.status) {
            dashboardPage.listProducts();
        } else {
            System.out.println("Invalid credintials. Login Failed!");
        }
    }

    public void login(String email, String password, String message) {
        StringBuilder errMessage = new StringBuilder("");
        LoginDTO response = loginPage.login(email, password);
        if (!response.status) {
            errMessage.append("**Error " + response.element.getText() + " ");
            errMessage.append(message.compareTo(response.element.getText()) == 0 ? "Pass" : "Failed");
            errMessage.append(" **");
        }
        System.out.println(errMessage);
    }

    public void login(String email, String password) {
        LoginDTO response = loginPage.login(email, password);
        if (response.status) {
            System.out.println("Login successful");
        }
    }

    public static void main(String[] args) throws Exception {
        LoginRunner runner = new LoginRunner();
        try {
            /* invalid login*/
//            runner.login(Utils.getEmail(), Utils.getInvalidPassword(), runner.invalidCreds);
//            runner.login(Utils.getInvalidEmail(), Utils.getPassword(), runner.invalidCreds);
//            runner.login(Utils.getInvalidEmail(), Utils.getInvalidPassword(), runner.invalidCreds);
//            runner.login("", "", runner.emailEmpty);
//            runner.login(Utils.getEmail(), "", runner.passwordEmpty);
//            runner.login("", Utils.getPassword(), runner.emailEmpty);

            /* login locked user*/
            runner.login(Utils.getLockedUser(), Utils.getPassword(), runner.lockedUser);
            runner.login(Utils.getLockedUser(), Utils.getInvalidPassword(), runner.invalidCreds);

            /* valid login */
//            runner.login(Utils.getEmail(), Utils.getPassword());
//            runner.loginPage.logout();

            /* Print product lines */
//            runner.getProductList();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
//            SeleniumDriver.getInstance().close();
        }
    }
}


/*
 * verify login with valid credentials
 * verify login with password blank
 * verify login with username blank
 * verify login with valid username invalid password
 * verify login with invalid uesrname valid password
 * verify login with invalid credentails
 * verify login with locked user
 * verify login with locked user invalid password
 * */