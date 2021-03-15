package stepdefs;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.SwaglabsHomePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.open;


public class SwaglabsHomePageSteps {

    private final static String LOGIN_PAGE = "https://www.saucedemo.com/";
    private final static String INVENTORY_PAGE_URL = LOGIN_PAGE + "inventory.html";
    private final SwaglabsHomePage swaglabsHomePage = new SwaglabsHomePage();

    @Given("^I am on the swaglabs home page$")
    public void iAmOnTheSwaglabsHomePage() throws InterruptedException {
        open(LOGIN_PAGE);
    }

    @When("^I enter correct username and password$")
    public void iEnterCorrectUsernameAndPassword() {
        swaglabsHomePage.setUserName();
        swaglabsHomePage.setPassword();
    }

    @And("^I click on Login button$")
    public void iClickOnLoginButton() {
        swaglabsHomePage.clickLoginButton();
    }

    @Then("^the inventory page is loaded$")
    public void theInventoryPageIsLoaded() {
        Assert.assertTrue(Selenide.title().equalsIgnoreCase("SWAG LABS"));
    }

    @Given("^I am on the inventory page$")
    public void iAmOnTheInventoryPage() {
        open(LOGIN_PAGE);
        swaglabsHomePage.setUserName();
        swaglabsHomePage.setPassword();
        swaglabsHomePage.clickLoginButton();
        Assert.assertTrue(Selenide.title().equalsIgnoreCase("SWAG LABS"));
    }

    @When("^I choose correct product name ([^\"]*)")
    public void iChooseCorrectProductName(String productName) {
        findAndGetTheProduct(productName);

    }

    @And("^I click Add To Cart button for ([^\"]*)")
    public void iClickAddToCartButton(String productName) {
        WebElement inventoryButton = findAndGetTheProduct(productName).findElementByTagName("button");
        Assert.assertNotNull(inventoryButton);
        inventoryButton.click();
    }

    @Then("^product is added to the cart$")
    public void productIsAddedToTheCart() {
        Assert.assertEquals(swaglabsHomePage.getNumberOfProductsInCart(), "1");
    }

    public SelenideElement findAndGetTheProduct(String productName) {
        List<SelenideElement> elements = swaglabsHomePage.getGetAllProducts();
        Optional<SelenideElement> product = elements.stream()
                .filter(
                        item -> {
                            if (item.text().contains(productName)) {
                                return true;
                            }
                            return false;
                        }
                )
                .findFirst();
        //every time you add a product, it checks if your parent list has that item or not
        Assert.assertTrue(product.isPresent());
        return product.get();
    }

    @When("^I choose few products$")
    public void iChooseFewProducts() {
        for (String product : getListOfProductsToAddToCart()) {
            findAndGetTheProduct(product);
        }
    }

    @And("^I add all the products$")
    public void iAddAllTheProducts() {
        for (String product : getListOfProductsToAddToCart()) {
            iClickAddToCartButton(product);
        }
    }

    public List<String> getListOfProductsToAddToCart() {
        List<String> productList = new ArrayList<>();
        productList.add("Sauce Labs Bike Light");
        productList.add("Sauce Labs Bolt T-Shirt");
        productList.add("Sauce Labs Fleece Jacket");
        return productList;
    }

    @Then("^(\\d+) products are added to the cart$")
    public void productsAreAddedToTheCart(int productsInCart) {
        Assert.assertEquals(swaglabsHomePage.getNumberOfProductsInCart(), String.valueOf(productsInCart));

    }
}
