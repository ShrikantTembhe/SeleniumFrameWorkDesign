package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.TestComponents.RetryAnalyzer;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

//Contains all negative test scenarios.
public class ErrorValidation extends BaseTests {

	// Passing the input to the class that having failed test case only.
	@Test(groups = { "ErrorHandling" }, retryAnalyzer = RetryAnalyzer.class)
	public void loginErrorValidations() throws IOException {

		// Product catalog class called to perform operation on products.
		landingpage.loginApplication("shrikanttembhe@mail.com", "Shrikant@123456");

		// Validation can be handled by assertions.
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test
	public void productErrorValidations() throws InterruptedException {

		String productName = "ZARA COAT 3";
		@SuppressWarnings("unused")
		String productName1 = "ADIDAS ORIGINAL";

		// Product catalog class called to perform operation on products.
		ProductCatalogue productcatalogue = landingpage.loginApplication("shrikanttembhe@mail.com", "Shrikant@123");
		// Product are added in the list form.
		@SuppressWarnings("unused")
		List<WebElement> product = productcatalogue.getProductsList();
		productcatalogue.addProductToCart(productName);

		// Click on cart button on header.
		// On cart page verify the item is selected in list or not.
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean flag = cartpage.verifyProductDisplay("ZARA COAT 33");

		Assert.assertFalse(flag);

	}

}