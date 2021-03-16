package StepDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class LoginStepDefination {
	//Open Browser
	ChromeDriver driver = new ChromeDriver();
	
	@Given("^user is already on the login page$")
	public void user_is_alreday_on_the_login_page() throws Throwable {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath +"/chromedriver");	
			
		
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");	 
		
	}

	@When("^user provides correct username and password$")
	public void user_provides_correct_username_and_password() throws Throwable {
		
		
		//Find Username element and Enter the standard username 
		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		//Find Password Element and enter the password
		
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
	    
	}

	@And("^user clicks on login button$")
	public void userclicks_on_login_button() throws Throwable {
		driver.findElement(By.id("login-button")).click();
	    
	}

	@Then("^Product page is displayed$")
	public void product_page_is_displayed() throws Throwable {
	    String aUrl=driver.getCurrentUrl();
	    
	    Assert.assertEquals("https://www.saucedemo.com/inventory.html",aUrl);
	    driver.close();
	}
	
	@When("^user provides locked username and password$")
	public void user_provides_locked_username_and_password() throws Throwable {
		//Find Username element and Enter the locked username 
				driver.findElement(By.id("user-name")).sendKeys("locked_out_user");

				//Find Password Element and enter the password
				
				driver.findElement(By.id("password")).sendKeys("secret_sauce");
	    
	}

	@Then("^error message is displayed$")
	public void error_message_is_displayed() throws Throwable {
	    boolean errorMessage= driver.findElement(By.className("error-button")).isDisplayed();
	    Assert.assertEquals(true, errorMessage);
	    driver.close();
	 
	}
	
	
	@Given("^user is already on the Product page$")
	public void user_is_already_on_the_Product_page() throws Throwable {
	    
		user_is_alreday_on_the_login_page();
		user_provides_correct_username_and_password();
		userclicks_on_login_button();
		driver.findElement(By.id("react-burger-menu-btn")).click();
		
	}

	@When("^user clicks on logout button$")
	public void user_clicks_on_logout_button() throws Throwable {
		driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
	}

	@Then("^user is loged out$")
	public void user_is_loged_out() throws Throwable {
     String actualUrl=driver.getCurrentUrl();
	    
	    Assert.assertEquals("https://www.saucedemo.com/s",actualUrl);
	    driver.close();
	}

	
}
