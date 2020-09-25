import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;

import static org.testng.Assert.assertEquals;

import org.w3c.dom.Document;

import javax.xml.parsers.*;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class MyTest extends BasePage{
    private static WebDriver driver;
        // create ExtentReports and attach reporter
        private static ExtentReports extent ;
        // creates a toggle for the given test, adds all log events under it
        private static ExtentTest test ;


    @BeforeClass
    public static void beforeClass() throws Exception {//check the browser type
        String type = getData("browserType");
        if (type.equals("Chrome")) {
            driver = DriverSingleton.getDriverInstance();
            driver = new ChromeDriver();
        }
        else if (type.equals("FF")) {
            System.setProperty("webdriver.firefox.driver", "C:\\Users\\tzufi\\Downloads\\QA expert\\chromedriver_win32\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Users\\tzufi\\Desktop\\class12\\extent.html");
            // attach reporter- where it was saved
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            // name your test and add description
            test = extent.createTest("BuyMe", "Automation Test for BuyMe website");
            // add custom system info
            extent.setSystemInfo("Environment", "Production");
            extent.setSystemInfo("Tester", "Tzufit");
            // log results
            test.log(Status.PASS, "@Before class");
        }
    }
    @Test
    public void openURL() throws ParserConfigurationException {//check URL
        driver.navigate().to(getData("URL"));
    }
    @Test
    private static String getData (String keyName) throws Exception{//get data from XML file
        ClassLoader classLoader = MyTest.class.getClassLoader();
        String xmlFilePath = String.valueOf(new File(classLoader.getResource("DATA.xml").getFile()));
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();

    @Test
    public void ByMe() throws InterruptedException {//registration to BuyMe

        clickElement(By.className("seperator-link"));
        clickElement(By.className("text-btn"));
        Thread.sleep(5000);  // Let the user actually see something!
         sendKeysToElement(By.cssSelector("input[type=text]"),"Tzufit");
         sendKeysToElement(By.cssSelector("input[type=email]"),"Tzufit.hamama@gmail.com");
        List<WebElement> InsertPassword=driver.findElements(By.cssSelector("input[type=password]"));
        InsertPassword.get(0).sendKeys("Aa12345678");
        InsertPassword.get(1).sendKeys("Aa12345678");
        Thread.sleep(5000);  // Let the user actually see something!
        clickElement(By.cssSelector("button[type=submit]"));
    }
    private void Assertregistration() throws InterruptedException{//assert to the registration information
        String Name = driver.findElement(By.cssSelector("input[type=text]")).getText();
        String Mail = driver.findElement(By.cssSelector("input[type=email]")).getText();
        String password = driver.findElement(By.cssSelector("input[placeholder=\"סיסמה\"]")).getText();
        String passwordAgain = driver.findElement(By.cssSelector("input[placeholder=\"אימות סיסמה\"]")).getText();
        Thread.sleep(5000);  // Let the user actually see something!
        Assert.assertEquals(Name, "Tzufit");
        Assert.assertEquals(Mail, "Tzufit.hamama@gmail.com");
        Assert.assertEquals(password, "Aa12345678");
        Assert.assertEquals(passwordAgain, "Aa12345678");

    }
    @Test
    public void HomePage() throws InterruptedException {//choose categories of the gift
        clickElement(By.xpath("//span[.='סכום']"));
        driver.findElements(By.className("active-result")).get(2).click();
        clickElement(By.xpath("//span[.='אזור']")).;
        driver.findElements(By.className("active-result")).get(1).click();
        clickElement(By.xpath("//span[.='קטגוריה']"));
        driver.findElements(By.className("active-result")).get(15).click();
        Thread.sleep(5000);  // Let the user actually see something!
         clickElement(By.cssSelector("a[rel=nofollow]"));
    }
    @Test
    public void PickBusiness() throws InterruptedException {//choose the gift
        Thread.sleep(1000);
        clickElement(By.linkText("הכי נמכרים"));
        clickElement(By.linkText("סופר-פארם"));
        sendKeysToElement(By.cssSelector("input[placeholder=\"מה הסכום?\"]"),"123");
        clickElement(By.cssSelector("button[type=submit]"));
    }



    }
   @Test
   public void  SenderReceiverInformation() throws InterruptedException {//fill the all area for the gift
       Thread.sleep(5000);
       clickElement(By.xpath("//span[.='למישהו אחר']"));
       sendKeysToElement(By.xpath("//input[@data-parsley-required-message='מי הזוכה המאושר? יש להשלים את שם המקבל/ת']"),"Simba");
       sendKeysToElement(By.xpath("//input[@data-parsley-required-message='למי יגידו תודה? שכחת למלא את השם שלך']"),"Tzufit");

       clickElement(By.xpath("//span[.='לאיזה אירוע?']"));
       driver.findElements(By.className("active-result")).get(1).click();
       sendKeysToElement(By.xpath("//textarea[@rows='4']"),"מזל טוב! כייף לשמוח איתכם!");

       WebElement UploadImg = driver.findElement(By.name("fileUpload"));
       WebElement UploadImg2 =driver.findElement(By.cssSelector("input[type=file]"));
       Thread.sleep(5000);  // Let the user actually see something!

       UploadImg.sendKeys("C:\\Users\\tzufi\\Desktop\\class12\\Tulips.jpg");

       clickElement(By.className("send-now"));
       clickElement(By.xpath("//span[@class='icon icon-envelope']"));
       sendKeysToElement(By.cssSelector("input[type=email]"),"SimbaTheCat@gmail.com");
       clickElement(By.cssSelector("button[type=submit]"));

       String RecieverName =driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/div/div[2]/div/div/div[2]/div[2]/span[2]")).getText(); //יחזיר את המחרוזת "סימבה"
       String SenderName =driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/div/div[2]/div/div/div[2]/div[3]/span[2]")).getText();

       Assert.assertEquals(RecieverName, "Simba");
       Assert.assertEquals(SenderName, "Tzufit");
   }

///Write all test logs (all System.out.print…) into a .log file
@Test
        private void Print() throws FileNotFoundException {
            PrintStream Print=new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(Print);
        }

        @AfterClass// close the website
    public void afterAll() {
        driver.quit();
        test.log(Status.INFO,"Test is done"); 
        // build and flush report
         extent.flush();

    }
}
