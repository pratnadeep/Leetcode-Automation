package demo;

import java.io.File;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    private WebDriverWait wait;

    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(90).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();
        
        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        //options.setCapability("someCapability", "value");  
  
        // Example: Disabling browser extensions  
         // Disable browser extensions  
        
         options.addArguments("--headless");  
         options.addArguments("--disable-gpu");  
         options.addArguments("--window-size=1920,1080");  
         options.addArguments("--hide-scrollbars");  
         options.addArguments("--enable-logging");  
         options.addArguments("--log-level=0");  
         options.addArguments("--v=99");  
         options.addArguments("--single-process");  
         options.addArguments("--ignore-certificate-errors");  
         options.addArguments("--no-sandbox");  
         options.addArguments("--disable-dev-shm-usage");  
           
         // Set a user agent string to mimic a non-headless browser  
         options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36"); 
        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    

    }
    public static void takeScreenshot(WebDriver driver, String screenshotType, String description) {
		try {
			File theDir = new File("/screenshots");
			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			String timestamp = String.valueOf(java.time.LocalDateTime.now());
			String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, description);

			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			File DestFile = new File("screenshots/" + fileName);
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://leetcode.com/");
        String sitename = "leetcode";
        if(driver.getCurrentUrl().contains(sitename))
            System.out.println("The URL of the Leetcode homepage contains "+ "leetcode");
            takeScreenshot(driver, "contains", "screenshot");

        
    }

    public  void testCase02()throws InterruptedException{
        driver.get("https://leetcode.com/problemset/");
        Thread.sleep(6000);
      
   // Verify that you are on the problem set page, by checking the URL contains "problemset".

   String verify_link = driver.getCurrentUrl();

   if(verify_link.contains("problemset")){
    System.out.println("url contains problemset");
    takeScreenshot(driver, "contains1", "screenshot1");
   }
   else{
     System.out.println("url does not contain problemset");
   }


   Thread.sleep(2000);

   List<WebElement> questions=driver.findElements(By.xpath("//div[@class='inline-block min-w-full']//div[@role='rowgroup']//div[@class='mx-2 flex items-center py-[11px]']//a[@class='h-5 hover:text-blue-s dark:hover:text-dark-blue-s']"));
        for(WebElement problem:questions){
            int count=0;
            if(count<5){
                String text=problem.getText();
                if(text.contains("Two Sum")||text.contains("Add Two Numbers")||text.contains("Longest Substring")||text.contains("Median of Two Sorted Arrays")||text.contains("Longest Palindromic Substring")){
                System.out.println(text);
                
                }
                count++;
            }else{
                break;
            }
        }
    
   

   System.out.println("end Test case: testCase02");
    }


        public void testCase03() throws InterruptedException {  
            System.out.println("Start Test case: testCase03");  
            driver.get("https://leetcode.com/");  
            JavascriptExecutor js = (JavascriptExecutor) driver;  
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");  
            Thread.sleep(3000); // Wait for 3 seconds  
            takeScreenshot(driver, "contains1", "screenshot4");
            WebElement questions = driver.findElement(By.xpath("//p[contains(text(),'View Questions')]"));  
            questions.click();  
            Thread.sleep(3000); // Wait for 3 seconds  
            WebElement twosum = driver.findElement(By.xpath("//a[contains(@class, 'h-5 hover:text-blue-s dark:hover:text-dark-blue-s') and contains(@href, '/problems/two-sum')]"));  
            twosum.click();  
            Thread.sleep(3000); // Wait for 3 seconds  
            String currentURL = driver.getCurrentUrl();  
            String expectedTitle = "two-sum";  
            if (currentURL.contains(expectedTitle)) {  
                System.out.println("The URL contains the expected title" + " " + expectedTitle);  
            } else {  
                System.out.println("The URL does not contain the expected title" + " " + expectedTitle);  
            }  
            System.out.println("end Test case: testCase03");  
        }  
          
        public void testCase04() throws InterruptedException {  
            System.out.println("Start Test case: testCase04");  
            driver.get("https://leetcode.com/");  
            JavascriptExecutor js = (JavascriptExecutor) driver;  
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");  
            Thread.sleep(3000); // Wait for 3 seconds  
            WebElement questions = driver.findElement(By.xpath("//p[contains(text(),'View Questions')]"));  
            questions.click();  
            Thread.sleep(3000); // Wait for 3 seconds  
            WebElement twosum = driver.findElement(By.xpath("//a[contains(@class, 'h-5 hover:text-blue-s dark:hover:text-dark-blue-s') and contains(@href, '/problems/two-sum')]"));  
            twosum.click();  
            Thread.sleep(3000); // Wait for 3 seconds  
            //WebElement enable = driver.findElement(By.xpath("//button[text()='Enable Dynamic Layout']"));  
            //enable.click();  
            //Thread.sleep(3000); // Wait for 3 seconds  
            //WebElement skip = driver.findElement(By.xpath("//button[text()='Skip tour']"));  
            //skip.click();  
           // Thread.sleep(3000); // Wait for 3 seconds  
            WebElement submission = driver.findElement(By.xpath("//div[@class='normal absolute left-0 top-0 whitespace-nowrap font-normal'][contains(text(), 'Submissions')]"));  
            js.executeScript("arguments[0].scrollIntoView(true);", submission);  
            submission.click();  
            Thread.sleep(2000); // Wait for 2 seconds  
            WebElement register = driver.findElement(By.xpath("//a[contains(text(), 'Register or Sign In')]"));  
            String text = register.getText();  
            if (text.equals("Register or Sign In")) {  
                System.out.println("The message \"Register or Sign In\" is displayed");  
            } else {  
                System.out.println("The message \"Register or Sign In\" is not displayed");  
            }  
            System.out.println("end Test case: testCase04");  
        }  
}

