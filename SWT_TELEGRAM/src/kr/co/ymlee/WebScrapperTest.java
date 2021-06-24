package kr.co.ymlee;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class WebScrapperTest {

	
	
	
//	public WebDriver driver = new FirefoxDriver();
//
//	/**
//	 * Open the test website.
//	 */
//	public void openTestSite() {
//		driver.navigate().to("http://testing-ground.scraping.pro/login");
//	}
//
//	/**
//	 * 
//	 * @param username
//	 * @param Password
//	 * 
//	 *            Logins into the website, by entering provided username and
//	 *            password
//	 */
//	public void login(String username, String Password) {
//
//		WebElement userName_editbox = driver.findElement(By.id("usr"));
//		WebElement password_editbox = driver.findElement(By.id("pwd"));
//		WebElement submit_button = driver.findElement(By.xpath("//input[@value='Login']"));
//
//		userName_editbox.sendKeys(username);
//		password_editbox.sendKeys(Password);
//		submit_button.click();
//
//	}
//
//	/**
//	 * grabs the status text and saves that into status.txt file
//	 * 
//	 * @throws IOException
//	 */
//	public void getText() throws IOException {
//		String text = driver.findElement(By.xpath("//div[@id='case_login']/h3")).getText();
//		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("status.txt"), "utf-8"));
//		writer.write(text);
//		writer.close();
//
//	}
//
//	/**
//	 * Saves the screenshot
//	 * 
//	 * @throws IOException
//	 */
//	public void saveScreenshot() throws IOException {
////		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
////		FileUtils.copyFile(scrFile, new File("screenshot.png"));
//	}
//
//	public void closeBrowser() {
//		driver.close();
//	}

	public static void main(String[] args) throws IOException {
		
        WebScrapperTest selTest = new WebScrapperTest();
        selTest.crawl();
        
		
//		WebScrapper webSrcapper = new WebScrapper();
//		webSrcapper.openTestSite();
//		webSrcapper.login("admin", "12345");  
//		webSrcapper.getText();    
//		webSrcapper.saveScreenshot();
//		webSrcapper.closeBrowser();
	}
	
    //WebDriver
    private WebDriver driver;
    
    //Properties
    public static final String WEB_DRIVER_PATH = "C:/android/workspace/DoduckSan/lib/chromedriver.exe";
    
    //크롤링 할 URL
    private String base_url;
    
    public WebScrapperTest() {
        super();
 
        //System Property SetUp
        System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        
        //Driver SetUp
        driver = new ChromeDriver();
        base_url = "https://www.naver.com";
    }
 
    public void crawl() {
 
        try {
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);
            System.out.println(driver.getPageSource());
            
            driver.navigate().to("http://testing-ground.scraping.pro/login");
//            login("admin", "12345");
        	WebElement userName_editbox = driver.findElement(By.id("usr"));
    		WebElement password_editbox = driver.findElement(By.id("pwd"));
    		WebElement submit_button = driver.findElement(By.xpath("//input[@value='Login']"));

    		userName_editbox.sendKeys("admin111");
    		password_editbox.sendKeys("12345");
    		
//    		submit_button.click();
    		
        } catch (Exception e) {
            
            e.printStackTrace();
        
        } finally {
 
            //driver.close();
        }
 
    }
//	public void login(String username, String Password) {
//
	
//
//	}
 
}