package ui.pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {
    By btn_Home=By.xpath("//a[@aria-label=\"Home\"]");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageLoaded()
    {
        return WaitForElementToBeVisible(btn_Home);
    }
}
