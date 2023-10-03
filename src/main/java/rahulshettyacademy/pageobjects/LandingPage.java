package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver); //Providing driver to the parent i.e. "AbstractComponent" class
		
		this.driver = driver;
		PageFactory.initElements(driver, this); // initializes the page element so that you can work directly on the element without getting the NullPointerException
	}
	
	// Webelement userEmails = driver.findElement(By.id("userEmail"))
	
	@FindBy(id="userEmail") // this will do the whole work of "driver.findElement(By.id("userEmail"))"
	WebElement userEmails; // And above will be store in this WebElement.
	
	//Webelement password = driver.findElement(By.id("userPassword"))
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	//Webelement submit = driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submit;
	
	//div[aria-label='Incorrect email or password.']
	////div[@aria-label='Incorrect email or password.']
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmails.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		// Ithe object banavto ahe ProductCatalogue cha karan aplyala mahit ahe ki varch "Loggin" button vr click kelyavr apn 
		//ProductCatalogue page vrch janar ahe. mhnun apn tya page cha object ithe banavla Ani return kela tyatlya methods access krayla.

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
