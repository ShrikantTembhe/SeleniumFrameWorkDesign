package rahulshettyacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbsctactComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Bring here landing page elements..
	// Enter email
	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// Modifying the above 20th line of code by using pageObjectFactory.(For
	// driver.findElement use(@FindBy))

	@FindBy(id = "userEmail")
	WebElement userEmail;

	// WebElement password = driver.findElement(By.id("userPassword"));

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	// WebElement submit = driver.findElement(By.id("login"));

	@FindBy(id = "login")
	WebElement submit;

	// To display error message with incorrect password and email.
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	// To perform login action define the method.
	// Hardcoded value are not allowed in this pageObjectFactory then pass it from
	// SubmitOrderTest class
	public ProductCatalogue loginApplication(String email, String password) {

		// Send from original class.
		userEmail.sendKeys(email);

		passwordEle.sendKeys(password);

		submit.click();

		ProductCatalogue productcatalogue = new ProductCatalogue(driver);

		return productcatalogue;
	}

	// Get the error message text
	public String getErrorMessage() {

		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	// For hitting url to land on page use method below
	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client/");
	}

}