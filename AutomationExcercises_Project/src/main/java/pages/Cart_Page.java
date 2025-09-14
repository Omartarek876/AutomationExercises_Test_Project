package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import utils.*;

public class Cart_Page {
    
    // Footer
    private By footerLocator = By.tagName("footer"); 
    private By subscriptionHeaderLocator = By.cssSelector("div.single-widget h2"); // header inside widget
    private By subscriptionEmailLocator = By.id("susbscribe_email");
    private By subscriptionButtonLocator = By.cssSelector("button#subscribe");
    private By successMessageLocator = By.cssSelector("div.alert-success.alert");

    // Cart products (generic dynamic locators instead of hardcoded #product-1/#product-2)
    private String productNameLocatorTemplate = "//tr[@id='product-%s']//td[@class='cart_description']//h4/a";
    private String productPriceLocatorTemplate = "//tr[@id='product-%s']//td[@class='cart_price']/p";
    private String productQuantityLocatorTemplate = "//tr[@id='product-%s']//td[@class='cart_quantity']/button";
    private String productTotalLocatorTemplate = "//tr[@id='product-%s']//td[@class='cart_total']/p";
    private By shoppingCartHeaderLocator = By.cssSelector("#cart_items > div > div.breadcrumbs > ol > li.active");

        
    // -------- Methods --------

    public void scrollToFooter() {
        ActionsUtils.scrollToElement(footerLocator);
    }

    public String subscriptionHeader() {
        return ElementUtils.getText(subscriptionHeaderLocator);
    }

    public void sendSubscriptionEmail(String email) {
        KeyboardUtils.sendKeys(subscriptionEmailLocator, email);
        ElementUtils.click(subscriptionButtonLocator);
    }

    public String getSuccessMessage() {
        return ElementUtils.getText(successMessageLocator);
    }

    // ---------- Dynamic product methods ----------
    public String getProductNameInCart(String productId) {
        By locator = By.xpath(String.format(productNameLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    public String getProductPriceInCart(String productId) {
        By locator = By.xpath(String.format(productPriceLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    public String getProductQuantityInCart(String productId) {
        By locator = By.xpath(String.format(productQuantityLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    public String getProductTotalInCart(String productId) {
        By locator = By.xpath(String.format(productTotalLocatorTemplate, productId));
        return ElementUtils.getText(locator);
    }

    public String getshoppingCartHeader()
    {
        return ElementUtils.getText(shoppingCartHeaderLocator);
    }


}
