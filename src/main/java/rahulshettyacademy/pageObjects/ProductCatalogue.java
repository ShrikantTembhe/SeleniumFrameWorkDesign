package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbsctactComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List of products to ge display
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	// Without driver.findElement can be written as:
	By ProductsBy = By.cssSelector(".mb-3");

	// without driver.findElement can be written as:

	By addToCart = By.cssSelector(".card-body button:last-of-type");

	// Waiting for toast message
	By toastMessage = By.cssSelector("div[class*='toast-message']");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(ProductsBy);
		return products;
	}

	// Product information is obtaining from this method as below:
	public WebElement getProductByName(String productName) {
		// Find the exact matching name of element.
		WebElement prod = getProductsList().stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		if (prod == null) {
			throw new RuntimeException("Product not found on UI: " + productName);
		}

		prod.findElement(addToCart).click();

		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}

}
