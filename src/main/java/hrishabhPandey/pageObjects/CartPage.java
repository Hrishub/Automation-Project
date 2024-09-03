package hrishabhPandey.pageObjects;

import hrishabhPandey.AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);



    }




    @FindBy(css = ".cartSection h3")
    List<WebElement> CartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkout;



    public Boolean getCartProducts(String prodName){
        Boolean match = CartProducts.stream().anyMatch(cartproduct -> cartproduct.getText().equals(prodName));
        return match;
    }

    public void goToCheckOut(){
            checkout.click();
    }

    public CheckOutPage goToCheckOutPage(){
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }


}
