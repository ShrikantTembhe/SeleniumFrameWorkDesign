package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbsctactComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	// Write page factory annotation related work here.
	@FindBy(xpath = "//tr//td[2]")
	private List<WebElement> productTitles;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public boolean verifyOrdersDisplay(String productName) {
		Boolean flag = productTitles.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return flag;

	}

}