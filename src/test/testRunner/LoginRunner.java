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
        LoginDTO response  = loginPage.login(Utils.getEmail(), Utils.getPassword());
        if(response.status){
            dashboardPage.listProducts();
        } else {
            System.out.println("Invalid credintials. Login Failed!");
        }
    }

    public static void main(String[] args) throws Exception{
        LoginRunner runner = new LoginRunner();
        try {
            /* Print product lines */
            runner.getProductList();


        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }finally {
            SeleniumDriver.getInstance().close();
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