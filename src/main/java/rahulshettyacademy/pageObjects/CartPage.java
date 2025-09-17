package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbsctactComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Write page factory annotation related work here.
	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	// CheckOut Button
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutEle;

	// Select Country
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//div//div//div[@class='cartSection']//h3")
	List<WebElement> cartProducts;

	By actualProduct = By.xpath("//div//div//div[@class='cartSection']//h3");

	// Click on country name
	@FindBy(xpath = "//section/button")
	List<WebElement> selectedCountry;

	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement orderText;

	public boolean verifyProductDisplay(String productName) {

		// Just verify product present or not.
		Boolean match = cartProducts.stream().anyMatch(
				cartProduct -> cartProduct.findElement(actualProduct).getText().equalsIgnoreCase(productName));
		return match;

	}

	public void checkoutProducts() {

		checkoutEle.click();
	}

	public void scrollDownAndSelectCountry(String countryName) {
		// Handle autosuggestive drop down and select india.
		country.sendKeys(countryName);

	}

	public void selectActualCountry(String countryName) {
		// Click on the selected country by java 8.
		selectedCountry.stream().filter(bh -> bh.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null)
				.click();

	}

	public void clickPlaceorderButton() {
		// Click on place order button.
		clickOnHiddenElements();
	}

	public String getTheFinalText() {

		System.out.print("Order_Confirmation:");

		String orderTexts = orderText.getText();
		System.out.println(orderText.getText());

		return orderTexts;
	}
}
