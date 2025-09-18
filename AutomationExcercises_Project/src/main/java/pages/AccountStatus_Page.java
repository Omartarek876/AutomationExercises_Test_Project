/**
 * Account Status Page Object Class
 *
 * Represents the Account Created and Account Deleted confirmation pages 
 * in the Automation Exercise application.
 * Provides methods to read confirmation messages and proceed using the Continue button.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */
package pages;

import org.openqa.selenium.By;
import utils.ElementUtils;

public class AccountStatus_Page {
    
    // -----------------------------
    // Locators
    // -----------------------------
    private By accountCreatedMsgLocator = By.cssSelector("#form > div > div > div > p:nth-child(2)");
    private By accountDeletedMsgLocator = By.cssSelector("#form > div > div > div > p:nth-child(2)");
    private By continueButtonLocator = By.xpath("//a[contains(text(),'Continue')]");
    

    // -----------------------------
    // Actions
    // -----------------------------
    /**
     * Get the confirmation message when an account is created.
     * @return String text of the message.
     */
    public String getAccountCreatedMessage() {
        return ElementUtils.getText(accountCreatedMsgLocator);
    }

    /**
     * Get the confirmation message when an account is deleted.
     * @return String text of the message.
     */
    public String getAccountDeletedMessage() {
        return ElementUtils.getText(accountDeletedMsgLocator);
    }

    /**
     * Click the Continue button to proceed after account status confirmation.
     */
    public void clickContinue() {
        ElementUtils.click(continueButtonLocator);
    }
}
