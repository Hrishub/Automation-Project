package hrishabhPandey.pageObjects;

import hrishabhPandey.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(css = "a[class*=_submit]")
    WebElement placeOrderbtn;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement getSelectCountry;

    By selectionBox = By.cssSelector(".ta-results");

    public void selectCountry(String countryName) {
        selectCountry.sendKeys(countryName);
        webElementToappear(selectionBox);

        String ClickOnOption = Keys.chord(Keys.ARROW_DOWN, Keys.ENTER);
        getSelectCountry.sendKeys(ClickOnOption);

    }

    public ConfirmationPage PlaceOrder() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = placeOrderbtn;
        Actions actions = new Actions(driver);

        actions.moveToElement(element).click().perform();

        return new ConfirmationPage(driver);
    }


}
