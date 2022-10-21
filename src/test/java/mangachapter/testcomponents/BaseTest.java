package mangachapter.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import jc.mangapageobjects.Homepage;


public class BaseTest {
	public WebDriver driver;
	public Homepage homePage;
	
	public WebDriver setupDriver() throws IOException   {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//resources//GlobalData2.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); 
				
		//prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.setHeadless(true);
			}
			options.addExtensions(new File("X:\\5.1.2_0.crx"));
			
			driver = new ChromeDriver(options);

		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fireFoxOptions = new FirefoxOptions();
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.addExtension(new File("X:\\adblock_plus-3.14.2.xpi"));
			fireFoxOptions.setProfile(firefoxProfile);
			driver = new FirefoxDriver(fireFoxOptions);
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addExtensions(new File("X:\\5.1.1_0.crx"));
			driver = new EdgeDriver(options);
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
	}
	
	public List<HashMap<String, String>> dataReader(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath)
				,StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	}
	@BeforeMethod
	public  Homepage launchApplication() throws IOException {
		driver = setupDriver();
		homePage = new Homepage(driver);
		driver.get("https://onepiecechapters.com/");
		return homePage;
	}
	
	@AfterMethod
	public void stopApplication() {
		driver.quit();
	}
}
