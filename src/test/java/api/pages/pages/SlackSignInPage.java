package api.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SlackSignInPage {


    public SlackSignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[.='sign in manually instead']")
    public WebElement signinManuallyLink;

    @FindBy(xpath = "//button[.='Continue']")
    public WebElement continueButton;

    @FindBy(id = "domain")
    public WebElement workspaceField;

    @FindBy(id = "email")
    public WebElement emailField;


    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "signin_btn")
    public WebElement signInButton;

    @FindBy(xpath = "//*[.='use Slack in your browser']")
    public WebElement continueToBrowserLink;

    @FindBy(xpath = "//span[@data-qa='channel_sidebar_name_api']")
    public WebElement apiChannel;

    @FindBy(xpath = "//div[@class='ql-placeholder']")
    public WebElement messageField;

    @FindBy(xpath = "//button[@aria-label='Send message']")
    public WebElement sendMessageButton;

    @FindBy(xpath = "//div[@class='c-message_kit__blocks c-message_kit__blocks--rich_text']")
    public List<WebElement> apiChannelMessageList;

    @FindBy(xpath = "//span[@class='c-message_kit__text']")
    public List<WebElement> apiChannelApiMessageList;



}
