package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SwaglabsHomePage {

    private final SelenideElement userNameField = $(By.id("user-name"));
    private final SelenideElement password = $(By.id("password"));
    private final SelenideElement loginButton = $(By.id("login-button"));
    private final List<SelenideElement> getAllProducts = $$(".inventory_list");
    private final SelenideElement numberOfProductsInCart = $(By.xpath("//span[@class=\"fa-layers-counter shopping_cart_badge\"]"));

    public void setUserName(){
        userNameField.sendKeys("standard_user");
    }

    public void setPassword(){
        password.sendKeys("secret_sauce");
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public SelenideElement getProduct(String productName){
        return $(By.xpath("//div[contains(text(),'" + productName + "')]"));
    }

    public List<SelenideElement> getGetAllProducts(){
        return getAllProducts;
    }

    public String getNumberOfProductsInCart() {
        return numberOfProductsInCart.text();
    }
}
