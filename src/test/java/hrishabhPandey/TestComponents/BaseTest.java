package hrishabhPandey.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hrishabhPandey.pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    public WebDriver driver;
    public LandingPage landingpage;


    public WebDriver initializeDriver() throws IOException {
        // properties class

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "\\src\\main\\java\\hrishabhPandey\\Resources\\Global.properties");
        prop.load(fis);

       /* String browserName = System.getProperty("browser");
        prop.getProperty("browser");*/


        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        /*String browserName = System.getProperty("browser");
        if (browserName == null || browserName.isEmpty()) {
            browserName = prop.getProperty("browser"); // Use value from properties file
        }*/

        /*ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();*/
        if (browserName.equalsIgnoreCase("chrome")) System.out.println("hello");
        else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "C:\\Users\\hrishabh.d.pandey\\Documents\\geckodriver.exe");
            driver = new FirefoxDriver();
            // Firefox
        } else if (browserName.equalsIgnoreCase("edge")) {
            // Edge
            System.setProperty("webdriver.edge.driver", "C:\\Users\\hrishabh.d.pandey\\Documents\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }

    /*public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
    {
        //read json to string
    String jsonContent = 	FileUtils.readFileToString(new File(filePath),
            StandardCharsets.UTF_8);

    //String to HashMap- Jackson Databind

    ObjectMapper mapper = new ObjectMapper();
      List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
      return data;

    //{map, map}

    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";


    }

    @BeforeMethod(alwaysRun=true)*/
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver = initializeDriver();
        landingpage = new LandingPage(driver);
        landingpage.goTo();
        return landingpage;


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    /* @AfterMethod(alwaysRun = true)
     public void tearDown(){
         if (driver != null) {
             driver.close();
         }
     }*/
    //Taking Screenshot method
    public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File Source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png");
        FileUtils.copyFile(Source, file);
        return System.getProperty("user.dir") + "\\src\\test\\java\\hrishabhPandey\\Data";
    }


    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //String to HashMap- Jackson Datbind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

        //{map, map} : returns List of HashMap<String, String>
    }
}






























	
	/*@AfterMethod(alwaysRun=true)
	
	public void tearDown()
	{
		driver.close();
	}
}
*/