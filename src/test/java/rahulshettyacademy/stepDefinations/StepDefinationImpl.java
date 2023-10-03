package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinationImpl extends BaseTest {

	public LandingPage lp;
	public ProductCatalogue productCatalogue;
	public CartPage cartpage;
	public ConfirmationPage confirmationpage;

	@Given("I landed in Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		lp = launchApplication();
	}

	// When we handling the regular expression, we will start that with ^ and will
	// end with $ as follows
	@Given("^Logged in with username (.+) and password (.+)$") // (.+) --> showing regular expression
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = lp.loginApplication(username, password);
		// As in TestNG we are getting values from the DataProvider, here we will get
		// values from SubmtOrder.feature file
	}

	@When("^I add product (.+) in Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	// And is a conjunction for previous step, here in this case WHEN is their, so
	// we can use both @When or @And also.
	// @And Checkout <productName> and submit the order
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productname) {
		cartpage = productCatalogue.goToCartPage();
		//Boolean match = cartpage.VerifyProductDisplay(productname);
		//Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationpage = checkoutPage.submitOrder();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
}
// When the values are coming from EXAMPLE then we will use regular expression i.e. (.+) and when the value is coming
//from other that EXAMPLE as we can see in THEN, then we will use {variable}. Automatically that value will listen by this.
















