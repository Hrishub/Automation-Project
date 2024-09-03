package hrishabhPandey.stepDefinImpl;

import hrishabhPandey.TestComponents.BaseTest;
import hrishabhPandey.pageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefintnImplmt extends BaseTest {

    public LandingPage landingpage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {

        landingpage = launchApplication();
    }

    @Given("^Logged in with Username(.+) and Password (.+)$")
    public void Logged_in_with_Username_and_Password(String username, String password) {
        productCatalogue = landingpage.loginApplication(username, password);

    }

    @When("^I add (.+) to the cart$")
    public void I_add_product_to_Cart(String prodName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(prodName);
    }

    @And("^Checkout (.+) and Submit the Order$")
    public void Checkout_product_And_Submit_Order(String productName) throws InterruptedException {
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.getCartProducts(productName);
        Assert.assertTrue(match);
        cartPage.goToCheckOut();
        CheckOutPage checkOutPage = cartPage.goToCheckOutPage();

        checkOutPage.selectCountry("india");
        confirmationPage = checkOutPage.PlaceOrder();


    }

    @Then("{string} message is displayed as Confirmation Page")
    public void Confirmtion_Message_isDisplayed(String string) {

        String Confmessage = confirmationPage.getConfirmationMessage();

        Assert.assertTrue(Confmessage.equalsIgnoreCase(string));

    }

    /*@Then("^\"Incorrect email or password.\" message is displayed on Login Page$")
    public void incorrect_message_is_displayed_on_login_page() {
        String expectedMessage = "Incorrect email or password.";
        Assert.assertEquals(expectedMessage, landingpage.getErrorMessage());
        driver.close();
    }*/


    @Then("{string} message is displayed on Login Page.")
    public void messageIsDisplayedOnLoginPage(String string) {

        Assert.assertEquals(string, landingpage.getErrorMessage());
        driver.close();
    }
}
