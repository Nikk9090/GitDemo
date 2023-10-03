package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		//LandingPage lp = launchApplication(); // We don't need this line because in BaseTest.java which is parent of this class
		//having method launchApplication() which launches the application. We are annoted that method with @BEFOREMETHOD. That means
		//before all the methods in this class and that class launchApplication() will execute 1st.
		ProductCatalogue productCatalogue = lp.loginApplication(input.get("email"), input.get("password"));
		// We know that after loging in we will goto product catalog page. Mag ata
		// prattek page sathi apn object tayar krt baslo tr
		// te nit nai disat na. Mhnun ek smart way ahe. Aplylala mhit ahe ki "Login"
		// button vr click kelyavr apn dusrya page vr janar
		// ahe. Tr mg apnha ProductCatalogue ch object ithe create n karta
		// "LandingPage.java" madhech karu te click() action nantr. Check it in
		// loginApplication() method.

		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productCatalogue.goToCartPage(); // Child class object accessing parent class method here.
		// ProductCatalogue sathi jasa kela tsa CartPage sathi kru ata.
		// CartPage cartpage = new CartPage(driver);
		// Boolean match = cartpage.VerifyProductDisplay(input.get("product");
		// Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutPage.submitOrder();
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

		// driver.findElement(By.id("userEmail")).sendKeys("patilnikhil9162@gmail.com");
		// driver.findElement(By.id("userPassword")).sendKeys("Nikhil@1234");
		// driver.findElement(By.id("login")).click();
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));

		// WebElement prod =
		// products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// Wait until the loading after clicking add to cart button
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// Now We need to check whether the correct product entered in the CART or not.
		// List<WebElement> cartProducts =
		// driver.findElements(By.cssSelector(".cartSections h3"));
		// Difference between filter() and anyMatch():
		// If we want complete web element then we will use filter() and if we want to
		// just check condition whether the element is present in the CART or not like
		// follows the we can use anyMatch()
		// Boolean match =
		// cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("ZARA
		// COAT 3"));
		// Assert.assertTrue(match);
		// driver.findElement(By.cssSelector(".totalRow button")).click();

		// Actions a = new Actions(driver);
		// a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")),"india").build().perform();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/section/button[2]")).click();
		// driver.findElement(By.cssSelector(".action__submit")).click();

//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	//We are putting order completely till above method, now we need to check whether our placed order is present in the 
	//ORDERS section or not, for that we are creating below method.
	//We are creating separate method for this because if the test fails then we will clearly know that where test is failing like 
	//during putting or submitting order or after submitting the order.
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = lp.loginApplication("patilnikhil9162@gmail.com", "Nikhil@1234");
		//Now we need to click on that ORDER button so that we can see all the orders. But that ORDER button is present
		//in header which is common for all the pages. So that we can write for ORDER button in the AbstractComponent().
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "patilnikhil9162@gmail.com");
//		map.put("password", "Nikhil@1234");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PuchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
}}

