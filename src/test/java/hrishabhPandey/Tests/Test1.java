package hrishabhPandey.Tests;

import hrishabhPandey.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        String prodName = "IPHONE 13 PRO";
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");

        driver.findElement(By.cssSelector(".btn")).click();
        LandingPage landingpage = new LandingPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); // this returns list of all div block
        // elemnts

        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst()
                .orElse(null);
        // this returns particular div block for iphone ,now we can apply futher filter
        // in this div block element

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equals(prodName));

        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        //Actions a = new Actions(driver);
        //a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build()
        // .perform();

        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        //WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ta-item:nth-of-type(2)")));
        //el.click();
        String ClickOnOption = Keys.chord(Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).sendKeys(ClickOnOption);
        //(//button[contains(@class,'ta-item')])[2]

        WebElement element = driver.findElement(By.cssSelector("a[class*=_submit]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();



        //driver.findElement(By.cssSelector("a[class*=_submit]")).click();

        String Confmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(Confmessage.equalsIgnoreCase("Thankyou for the order."));


    }
}
