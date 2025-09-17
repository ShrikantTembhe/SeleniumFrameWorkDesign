package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

//Must extend the base class.
public class StepDefinationsImplementations extends BaseTests {

	public LandingPage landingPage;
	ProductCatalogue productcatalogue;
	CartPage cartpage;

	// Define the method as per .feature file first.
	// Bring landing page method here.
	@Given(" I landed on E commerce page")
	public void I_landed_on_E_commerce_page() throws IOException {

		landingPage = launchApplication();
	}

	@Given("^User logged in with username (.+) and password (.+)$")
	public void User_logged_in_with_username_and_password(String userName, String password) {

		productcatalogue = landingpage.loginApplication(userName, password);
	}

	@When("^I add product (.+) to the cart$")
	public void I_add_product_to_the_cart(String productName) throws InterruptedException {

		@SuppressWarnings("unused")
		List<WebElement> product = productcatalogue.getProductsList();
		productcatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and select country (.+) submit the order$")
	public void Checkout_and_submit_the_order(String productName, String country) {

		cartpage = productcatalogue.goToCartPage();
		Boolean flag = cartpage.verifyProductDisplay(productName);
		// Validations can not be written to the page object classes.
		Assert.assertTrue(flag);
		cartpage.checkoutProducts();
		cartpage.scrollDownAndSelectCountry(country);
		cartpage.selectActualCountry(country);
		cartpage.clickPlaceorderButton();

	}

	@Then("{string} message is displayed on the confirmation page")
	public void message_is_displayed_on_the_confirmation_page(String string) {

		Assert.assertEquals(string.toUpperCase(), cartpage.getTheFinalText());

		driver.close();
	}
}
