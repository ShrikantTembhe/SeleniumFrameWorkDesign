package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

//contains all positive test scenarions.
public class SubmitOrderTest extends BaseTests {

	String productName = "ZARA COAT 3";

	String orderText = "Thankyou for the order.";

	String ind = "India";

	// Large inout variables to the method submitOrder can not be provided so used
	// concept of map here.
	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// Product catalog class called to perform operation on products.
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		// Product are added in the list form.
		@SuppressWarnings("unused")
		List<WebElement> product = productcatalogue.getProductsList();
		productcatalogue.addProductToCart(input.get("product"));

		// Click on cart button on header.
		// On cart page verify the item is selected in list or not.
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean flag = cartpage.verifyProductDisplay(input.get("product"));
		// Validations can not be written to the page object classes.
		Assert.assertTrue(flag);
		cartpage.checkoutProducts();
		cartpage.scrollDownAndSelectCountry(ind);
		cartpage.selectActualCountry(ind);
		cartpage.clickPlaceorderButton();
		Assert.assertEquals(orderText.toUpperCase(), cartpage.getTheFinalText());

	}

	// This will check the order history so that it must execute after submit order
	// test.
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {

		ProductCatalogue productcatalogue = landingpage.loginApplication("udemy@mail.com", "Shrikant@123");

		OrderPage orderpage = productcatalogue.goToOrdersPage();

		orderpage.verifyOrdersDisplay(productName);

		Assert.assertTrue(orderpage.verifyOrdersDisplay(productName));

	}

	// Use of @DataProvider annotation to send multiple data to the methods.

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	/*
	 * HashMap<String, String> map = new HashMap<String, String>();
	 * 
	 * map.put("email", "shrikanttembhe@mail.com"); map.put("password",
	 * "Shrikant@123"); map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * 
	 * map1.put("email", "udemy@mail.com"); map1.put("password", "Shrikant@123");
	 * map1.put("productName", "IPHONE 13 PRO");
	 */

}
