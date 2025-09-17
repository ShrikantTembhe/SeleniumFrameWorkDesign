package rahulshettyacademy.AbsctactComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// Common code of cartHeader for all the pages so used it here only.
	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartHeader;

	// Common code to check the order history of the booked items
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orderHeader;

	// place Order button.
	@FindBy(xpath = "//a[contains(@class,'btnn action__submit')]")
	WebElement placeOrder;

	public void waitForElementToAppear(By findBY) {

		// By.cssSelector(".mb-3") this can be handled by using only By class in
		// selenium(By FindBy)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
	}

	// Wait for error message to appear.
	public void waitForWebElementToAppear(WebElement element) {

		// By.cssSelector(".mb-3") this can be handled by using only By class in
		// selenium(By FindBy)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// driver.findElemnt(Used WebElement)
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1500);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	}

	public CartPage goToCartPage() {

		cartHeader.click();
		CartPage cartpage = new CartPage(driver);

		return cartpage;
	}

	public OrderPage goToOrdersPage() {

		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);

		return orderpage;
	}

	public void clickOnHiddenElements() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click()", placeOrder);

	}

}