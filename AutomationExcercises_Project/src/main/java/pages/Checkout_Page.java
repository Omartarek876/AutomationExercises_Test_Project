/**
 * Checkout Page Object Class
 *
 * This class represents the Checkout Page in the Automation Exercise application.
 * It contains all locators and actions related to the checkout process, 
 * including adding comments, placing orders, entering payment details, 
 * downloading invoices, and verifying addresses.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */
package pages;

import org.openqa.selenium.By;
import utils.*;

public class Checkout_Page {

    // ================= Locators =================
    private By checkoutButtonLocator          = By.cssSelector("#do_action > div.container > div > div > a");
    private By signFromCheckoutLocator        = By.cssSelector("#checkoutModal > div > div > div.modal-body > p:nth-child(2) > a");
    private By placeOrderButtonLocator        = By.cssSelector("#cart_items > div > div:nth-child(7) > a");
    private By addCommentBeforeCheckoutLocator= By.cssSelector("#ordermsg > textarea");
    
    // Payment form locators
    private By nameOnCardField   = By.name("name_on_card");
    private By cardNumberField   = By.name("card_number");
    private By cvcField          = By.name("cvc");
    private By expiryMonthField  = By.name("expiry_month");
    private By expiryYearField   = By.name("expiry_year");
    private By payAndConfirmBtn  = By.cssSelector("#submit");

    // Confirmation & success locators
    private By SuccessPaymentMsgLocator = By.cssSelector("#form > div > div > div > p");
    private By ContinueBtnLocator       = By.cssSelector("#form > div > div > div > div > a");
    private By downloadInvoiceButtonLocator = By.cssSelector("#form > div > div > div > a");

    // Address locators
    private By deliveryAddressBox = By.id("address_delivery");
    private By billingAddressBox  = By.id("address_invoice");


    // ================= Actions =================

    /**
     * Clicks on the Checkout button.
     */
    public void clickCheckoutButton() {
        ElementUtils.click(checkoutButtonLocator);
    }

    /**
     * Clicks on the Sign In button from the checkout modal.
     */
    public void clickCheckoutAndsign() {
        ElementUtils.click(signFromCheckoutLocator);
    }

    /**
     * Adds a comment before placing the order.
     *
     * @param comment Comment text to add
     */
    public void addCommentBeforeCheckout(String comment) {
        KeyboardUtils.sendKeys(addCommentBeforeCheckoutLocator, comment);
    }

    /**
     * Clicks the Place Order button.
     */
    public void clickPlaceOrderButton() {
        ElementUtils.click(placeOrderButtonLocator);
    }

    /**
     * Clicks the Download Invoice button.
     */
    public void clickDownloadInvoiceButton() {
        ElementUtils.click(downloadInvoiceButtonLocator);
    }

    /**
     * Enters payment details into the payment form.
     *
     * @param name      Name on card
     * @param cardNumber Card number
     * @param cvc        Card CVC code
     * @param expMonth   Expiry month
     * @param expYear    Expiry year
     */
    public void enterPaymentDetails(String name, String cardNumber, String cvc, String expMonth, String expYear) {
        KeyboardUtils.sendKeys(nameOnCardField, name);
        KeyboardUtils.sendKeys(cardNumberField, cardNumber);
        KeyboardUtils.sendKeys(cvcField, cvc);
        KeyboardUtils.sendKeys(expiryMonthField, expMonth);
        KeyboardUtils.sendKeys(expiryYearField, expYear);
    }

    /**
     * Clicks on the Pay and Confirm Order button.
     */
    public void clickPayAndConfirmOrder() {
        ElementUtils.click(payAndConfirmBtn);
    }

    /**
     * Retrieves the success message after payment.
     *
     * @return Success message text
     */
    public String getSuccessPaymentMsg() {
        return ElementUtils.getText(SuccessPaymentMsgLocator);
    }

    /**
     * Clicks Continue after successful order placement.
     */
    public void clickContinueAfterSuccess() {
        ElementUtils.click(ContinueBtnLocator);
    }

    /**
     * Gets the Delivery Address from checkout page.
     *
     * @return Delivery address text
     */
    public String getDeliveryAddress() {
        String deliveryAddress = ElementUtils.getText(deliveryAddressBox).trim();
        System.out.println("Delivery Address: " + deliveryAddress);
        return deliveryAddress;
    }

    /**
     * Gets the Billing Address from checkout page.
     *
     * @return Billing address text
     */
    public String getBillingAddress() {
        String billingAddress = ElementUtils.getText(billingAddressBox).trim();
        System.out.println("Billing Address: " + billingAddress);
        return billingAddress;
    }
}
