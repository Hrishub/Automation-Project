package hrishabhPandey.pageObjects;

import hrishabhPandey.AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }


    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(css = ".btn")
    WebElement login;
    // .ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error - Error message pop during login
    @FindBy(css = "[class*='-flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String Password) {
        userEmail.sendKeys(email);
        password.sendKeys(Password);
        login.click();

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;


    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getErrorMessage() {
        WaitforWebElementToappear(errorMessage);
        return errorMessage.getText();

    }


}
