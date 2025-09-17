package pages;

import org.openqa.selenium.By;
import utils.*;


/**
 *
 * @author Omar Tarek
 */
public class Checkout_Page {
    
    private By checkoutButtonLocator = By.cssSelector("#do_action > div.container > div > div > a");
    private By signFromCheckoutLocator = By.cssSelector("#checkoutModal > div > div > div.modal-body > p:nth-child(2) > a");
    private By placeOrderButtonLocator = By.cssSelector("#cart_items > div > div:nth-child(7) > a");
    private By addCommentBeforeCheckoutLocator = By.cssSelector("#ordermsg > textarea");
    private By nameOnCardField   = By.name("name_on_card");
    private By cardNumberField   = By.name("card_number");
    private By cvcField          = By.name("cvc");
    private By expiryMonthField  = By.name("expiry_month");
    private By expiryYearField   = By.name("expiry_year");
    private By payAndConfirmBtn  = By.cssSelector("#submit");
private By SuccessPaymentMsgLocator = By.cssSelector("#form > div > div > div > p");
private By ContinueBtnLocator = By.cssSelector("#form > div > div > div > div > a");
private By downloadInvoiceButtonLocator = By.cssSelector("#form > div > div > div > a");

    public void clickCheckoutButton()
    {
        ElementUtils.click(checkoutButtonLocator);
    }
    
    public void clickCheckoutAndsign()
    {
        ElementUtils.click(signFromCheckoutLocator);
    }
    
    public void addCommentBeforeCheckout(String comment)
    {
        KeyboardUtils.sendKeys(addCommentBeforeCheckoutLocator, comment);
    }
    
    public void clickPlaceOrderButton ()
    {
        ElementUtils.click(placeOrderButtonLocator);
    }
    
    
    public void clickDownloadInvoiceButton ()
    {
        ElementUtils.click(downloadInvoiceButtonLocator);
    }

    public void enterPaymentDetails(String name, String cardNumber, String cvc, String expMonth, String expYear) {
        KeyboardUtils.sendKeys(nameOnCardField, name);
        KeyboardUtils.sendKeys(cardNumberField, cardNumber);
        KeyboardUtils.sendKeys(cvcField, cvc);
        KeyboardUtils.sendKeys(expiryMonthField, expMonth);
        KeyboardUtils.sendKeys(expiryYearField, expYear);
    }

    public void clickPayAndConfirmOrder() {
        ElementUtils.click(payAndConfirmBtn);
    }
   
    public String getSuccessPaymentMsg() {
        return ElementUtils.getText(SuccessPaymentMsgLocator);
    }

    public void clickContinueAfterSuccess() {
        ElementUtils.click(ContinueBtnLocator);
    }
    
    
    // === Locators ===
private By deliveryAddressBox = By.id("address_delivery");
private By billingAddressBox  = By.id("address_invoice");

// === Method to get Delivery Address ===
public String getDeliveryAddress() {
    String deliveryAddress = ElementUtils.getText(deliveryAddressBox).trim();
    System.out.println("Delivery Address: " + deliveryAddress);
    return deliveryAddress;
}

// === Method to get Billing Address ===
public String getBillingAddress() {
    String billingAddress = ElementUtils.getText(billingAddressBox).trim();
    System.out.println("Billing Address: " + billingAddress);
    return billingAddress;
}



}
