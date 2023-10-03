package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {

		// In Java, their is an inbuilt class known as PROPERIES who reads the global
		// properties.
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\nikhi\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		// We need to give location of the .properties file that we given above.
		prop.load(fis); // This load() method takes file as an inputStream. We have to send
						// GlobalData.properties file as input stream. That we did above
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser"): prop.getProperty("browser");
		// String browserName = prop.getProperty("chrome");

		if (browserName.contains("chrome")) {
		ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup(); // By this line ChromeDriver will automatically download in out
														// system based on Chrome browser version.
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //optional step
		}
		// Now we are using ChromeDriver. But if we have requirement for firefox or
		// edgeDriver, then it should be execute.
		// For that we need to setup global properties.
		//}
		else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// This will read the JSON file and convert its data to STRING variable.
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert String to HASHMAP by "Jackson Databind". We need to get dependency
		// from mvnrepository.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	// taking SS.
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true) // before all the methods in this class and SubmitOrderTest.java class
									// launchApplication() will execute very 1st.
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
