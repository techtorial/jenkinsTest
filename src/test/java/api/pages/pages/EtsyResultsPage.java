package api.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EtsyResultsPage {

    private EtsyResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static EtsyResultsPage getEtsyResultsPage(WebDriver driver) {
        return new EtsyResultsPage(driver);
    }


    @FindBy(css = ".currency-value")
    public List<WebElement> allPrices;


}
