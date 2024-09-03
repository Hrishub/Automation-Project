package hrishabhPandey.pageObjects;

import hrishabhPandey.AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);



    }
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> OrderedItems;


    public Boolean verifyOrderDisplay(String prodName) {
        Boolean match = OrderedItems.stream().anyMatch(OrderedProduct -> OrderedProduct.getText().equals(prodName));
        return match;
    }


}
