package hrishabhPandey.pageObjects;

import hrishabhPandey.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }


    @FindBy(css = ".mb-3")
    List<WebElement> products;


    By prodBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");

    By toastMessage = By.cssSelector("#toast-container");

    @FindBy(css = ".ng-animating")
    WebElement spinner;


    public List<WebElement> getProductList() {
        webElementToappear(prodBy);
        return products;
    }

    public WebElement getProductByName(String prodName) {
        WebElement prod = getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst()
                .orElse(null);

        return prod;
    }

    public void addProductToCart(String prodName) throws InterruptedException {
        WebElement prod = getProductByName(prodName);
        prod.findElement(addToCart).click();
        webElementToappear(toastMessage);
        webElementToDisappear(spinner);


    }


}
