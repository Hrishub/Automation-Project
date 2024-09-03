package hrishabhPandey.Tests;

import hrishabhPandey.TestComponents.BaseTest;
import hrishabhPandey.TestComponents.Retry;
import hrishabhPandey.pageObjects.CartPage;
import hrishabhPandey.pageObjects.OrderPage;
import hrishabhPandey.pageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {
    @Test(retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {

        // .ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
        landingpage.loginApplication("anshika@gmail.com", "Lee");
        landingpage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidationTest() throws InterruptedException {
        String prodName = "IPHONE 13 PRO";


        ProductCatalogue productCatalogue = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");


        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(prodName);


        CartPage cartPage = productCatalogue.goToCartPage();


        Boolean match = cartPage.getCartProducts("Iphone14");


        Assert.assertFalse(match);


    }

}
