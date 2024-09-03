package hrishabhPandey.Tests;


import hrishabhPandey.TestComponents.BaseTest;
import hrishabhPandey.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Test2 extends BaseTest {
    String prodName = "IPHONE 13 PRO";
    @Test(dataProvider = "getData",groups = "Purchase")
    public void SubmitOrder(HashMap<String,String> data) throws IOException, InterruptedException {


        //String prodName = "IPHONE 13 PRO";

        /*LandingPage landingpage = launchApplication();*/
        // we are not capturing here, instead we accessing parent class variable in next line


        ProductCatalogue productCatalogue = landingpage.loginApplication(data.get("email"), data.get("password"));


        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(data.get("prodName"));


        CartPage cartPage = productCatalogue.goToCartPage();


        Boolean match = cartPage.getCartProducts(data.get("prodName"));


        Assert.assertTrue(match);


        cartPage.goToCheckOut();
        CheckOutPage checkOutPage = cartPage.goToCheckOutPage();


        checkOutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkOutPage.PlaceOrder();


        String Confmessage = confirmationPage.getConfirmationMessage();

        Assert.assertTrue(Confmessage.equalsIgnoreCase("Thankyou for the order."));



    }

    @Test(dependsOnMethods = {"SubmitOrder"})
    public void orderHistoryTest(){
        ProductCatalogue productCatalogue = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
        OrderPage orderPage = productCatalogue.goToOrderHeadr();
        Assert.assertTrue(orderPage.verifyOrderDisplay(prodName));
    }

    /*@DataProvider //Providing data by passing array
    public Object[][] getData(){
        return new Object[][] {{"anshika@gmail.com","Iamking@000","IPHONE 13 PRO"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
    }*/

    /*@DataProvider//Providing data by hashmap
    public Object[][] getData(){
        HashMap<String,String> map = new HashMap<>();
        map.put("email","anshika@gmail.com");
        map.put("password","Iamking@000");
        map.put("prodName","IPHONE 13 PRO");

        HashMap<String,String> map1 = new HashMap<>();
        map1.put("email","shetty@gmail.com");
        map1.put("password","Iamking@000");
        map1.put("prodName","ADIDAS ORIGINAL");

        return new Object[][] {{map1},{map}};
    }*/

    @DataProvider//Providing data by List of hashmap by json file
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\hrishabhPandey\\Data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}


