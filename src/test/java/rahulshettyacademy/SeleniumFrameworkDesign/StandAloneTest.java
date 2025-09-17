package rahulshettyacademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromiumdriver().setup();

		WebDriver driver = new ChromeDriver();

		String productName = "ZARA COAT 3";

		String ind = "India";

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys("shrikanttembhe@mail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Shrikant@123");

		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement actualProduct = products.stream().filter(
				product -> product.findElement(By.xpath("//b[text()='ZARA COAT 3']")).getText().equals(productName))
				.findFirst().orElse(null);

		// Limiting the scope of drive so that to use {actualProduct} so that only
		// specific item will be obtained
		// instead of all webpage elements.

		actualProduct.findElement(By.xpath("//button[text()=' Add To Cart']")).click();

		// Wait until the product added popup at bottom get displayed.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='toast-message']")));

		// Wait unitl the animation that appears on clicking {Add To Cart} button should
		// get invisible.
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// Clicking on cart button to view items present in the cart.
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		// Check whether the item added to cart is present in cart section or not used
		// boolean flag.
		// USed java 8
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean flag = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(flag);

		// Click checkout buttons.
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,300)");

		// Handle autosuggestive drop down and select india.
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(ind);

		List<WebElement> countries = driver.findElements(By.xpath("//section/button"));

		// Click on the selected country by java 8.
		countries.stream().filter(bh -> bh.getText().equalsIgnoreCase(ind)).findFirst().orElse(null).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Place Order ']")));

		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

		System.out.print("Order_ID:");
		System.out.println(driver.findElement(By.cssSelector(".em-spacer-1 .ng-star-inserted")).getText());

	}

}
