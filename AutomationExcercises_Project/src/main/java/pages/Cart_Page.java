/**
 * Cart Page Object Class
 *
 * This class represents the Cart Page in the Automation Exercise application.
 * It encapsulates locators and actions related to cart functionality,
 * including product verification, subscription, and cart summary details.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */
package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.*;

public class Cart_Page {
    
    // -----------------------------
    // Page Dependencies
    // -----------------------------
    Home_Page homePage = new Home_Page();
    
    
    // -----------------------------
    // Locators
    // -----------------------------
    private By footerLocator = By.tagName("footer");                                     // Footer section
    private By subscriptionHeaderLocator = By.cssSelector("div.single-widget h2");       // "Subscription" header
    private By subscriptionEmailLocator = By.id("susbscribe_email");                     // Email input field
    private By subscriptionButtonLocator = By.cssSelector("button#subscribe");           // Subscribe button
    private By successMessageLocator = By.cssSelector("div.alert-success.alert");        // Success message after subscription
    
    // Dynamic product locator templates
    private String productNameLocatorTemplate     = "//tr[@id='product-%s']//td[@class='cart_description']//h4/a";
    private String productPriceLocatorTemplate    = "//tr[@id='product-%s']//td[@class='cart_price']/p";
    private String productQuantityLocatorTemplate = "//tr[@id='product-%s']//td[@class='cart_quantity']/button";
    private String productTotalLocatorTemplate    = "//tr[@id='product-%s']//td[@class='cart_total']/p";
    
    private By shoppingCartHeaderLocator = By.cssSelector("#cart_items > div > div.breadcrumbs > ol > li.active"); // "Shopping Cart" header
    private By cartProductNamesLocator   = By.cssSelector("td.cart_description h4 a");                             // Product names in cart
    
    
    // -----------------------------
    // Page Actions
    // -----------------------------
    
    /** Scroll to the footer section */
    public void scrollToFooter() {
        ActionsUtils.scrollToElement(footerLocator);
    }

    /** Get the subscription header text */
    public String subscriptionHeader() {
        return ElementUtils.getText(subscriptionHeaderLocator);
    }

    /** Send subscription email and click Subscribe button */
    public void sendSubscriptionEmail(String email) {
        KeyboardUtils.sendKeys(subscriptionEmailLocator, email);
        ElementUtils.click(subscriptionButtonLocator);
    }

    /** Get the success message after subscribing */
    public String getSuccessMessage() {
        return ElementUtils.getText(successMessageLocator);
    }
    
    
    // -----------------------------
    // Dynamic Product Methods
    // -----------------------------
    
    /** Get product name by product ID */
    public String getProductNameInCart(String productId) {
        By locator = By.xpath(String.format(productNameLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    /** Get product price by product ID */
    public String getProductPriceInCart(String productId) {
        By locator = By.xpath(String.format(productPriceLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    /** Get product quantity by product ID */
    public String getProductQuantityInCart(String productId) {
        By locator = By.xpath(String.format(productQuantityLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    /** Get total price for a product by product ID */
    public String getProductTotalInCart(String productId) {
        By locator = By.xpath(String.format(productTotalLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    /** Get the Shopping Cart header text */
    public String getshoppingCartHeader() {
        return ElementUtils.getText(shoppingCartHeaderLocator);
    }
    
    
    // -----------------------------
    // Verification
    // -----------------------------
    
    /**
     * Verify that all expected products exist in the cart.
     * 
     * @param ExpextedElements List of expected product names
     */
    public void verifyProductsInCart(List<String> ExpextedElements) {
        List<String> cartProducts = new ArrayList<>();
        List<WebElement> cartElements = ElementUtils.getElements(cartProductNamesLocator);

        for (WebElement element : cartElements) {
            cartProducts.add(element.getText().trim());
        }

        System.out.println("Expected products: " + ExpextedElements);
        System.out.println("Actual products in cart: " + cartProducts);

        // Validation: ensure all expected products are present
        for (String expected : ExpextedElements) {
            if (!cartProducts.contains(expected)) {
                throw new AssertionError("Product not found in cart: " + expected);
            }
        }

        System.out.println("All products verified in cart");
    }
}
