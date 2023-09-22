package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage {
    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    public void listProducts() {
            List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
            for(WebElement prod :  products) {
                System.out.println(prod.getText());
        }
    }
}
