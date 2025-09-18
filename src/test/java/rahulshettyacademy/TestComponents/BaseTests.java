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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTests {

	public WebDriver driver;
	// Makes public so that it should be visible.
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {

		// Writing the global code for the every browser to execute the tests.
		// Chrome Firefox,Opera,Edge etc
		// Use properties class in java to get the data and parse it to stream to
		// access.

		Properties prop = new Properties();
		// Get file inputstream to pass the file to properties object.

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\Resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browserName = prop.getProperty("browser");

		// Write an condition to execute the test on specific browser only.
		// Writing the ternary operator code for the browser input from the command
		// prompt.

		if (browserName.equalsIgnoreCase("chrome")) {

			// This is basically used to run the browser in headless mode and which give
			// high performance.(headless mode)

			WebDriverManager.chromiumdriver().setup();

			driver = new ChromeDriver();
			// Setting up the system resoulation to stop faliure.

		}

		else if (browserName.equalsIgnoreCase("FireFox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("Edge")) {

			// Write edge browser code here.

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	// Data Reader Class Methods.
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert string content to hashmap by (Jackson databind) dependency.

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	// For giving life to the driver pass driver element there.
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

		FileUtils.copyFile(source, destination);

		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	// due to grouping the method got ignored by testNG so used(alwaysRun = true)
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		System.out.println("Launching the application in base class.");

		driver = initializeDriver();
		// Use landing page code object here to call the url.
		landingpage = new LandingPage(driver);

		landingpage.goTo(); // hit the url.

		// This is returning the landing page need to catch in the class objects.
		return landingpage;
	}

	// due to grouping the method got ignored by testNG so used(alwaysRun = true)
	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		 System.out.println("Closed The Browser.");
		// Closing the browser.
		driver.quit();
	}

}
