package api.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EtsyHomePage {

    private EtsyHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#global-enhancements-search-query")
    public WebElement searchField;


    public static EtsyHomePage getEtsyHomePage(WebDriver driver) {
        return new EtsyHomePage(driver);
    }





}
