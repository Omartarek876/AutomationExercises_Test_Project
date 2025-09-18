/**
 * Contact Us Page Object Class
 *
 * This class represents the Contact Us Page in the Automation Exercise application.
 * It encapsulates locators and actions related to the Contact Us functionality,
 * including form submission, file upload, and navigation.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */
package pages;

import org.openqa.selenium.By;
import utils.ActionsUtils;
import utils.AlertUtils;
import utils.ElementUtils;
import utils.KeyboardUtils;

public class ContactUs_page {
    
    // -----------------------------
    // Locators
    // -----------------------------
    private final By formNameLocator       = By.cssSelector("input[name='name']");              // Name input field
    private final By formEmailLocator      = By.cssSelector("input[name='email']");             // Email input field
    private final By formSubjectLocator    = By.cssSelector("input[name='subject']");           // Subject input field
    private final By formMsgLocator        = By.cssSelector("textarea[name='message']");        // Message textarea
    private final By submitButtonLocator   = By.cssSelector("#contact-us-form input[type='submit']"); // Submit button
    private final By uploadFileLocator     = By.cssSelector("#contact-us-form input[type='file']");   // File upload input
    private final By submittedFormLocator  = By.cssSelector("#contact-page .status.alert-success");   // Success message
    private final By goHomeButtonLocator   = By.cssSelector("a.btn.btn-success");               // Home button
    private final By headerLocator         = By.xpath("//h2[normalize-space()='Get In Touch']");// Page header
    
    
    // -----------------------------
    // Page Actions
    // -----------------------------
    /** Enter text into the Name field */
    public void EnterName(String name) { KeyboardUtils.sendKeys(formNameLocator, name); }

    /** Enter text into the Email field */
    public void EnterEmail(String email) { KeyboardUtils.sendKeys(formEmailLocator, email); }

    /** Enter text into the Subject field */
    public void EnterSubject(String subject) { KeyboardUtils.sendKeys(formSubjectLocator, subject); }

    /** Enter text into the Message field */
    public void EnterMsg(String msg) { KeyboardUtils.sendKeys(formMsgLocator, msg); }

    /** Click the Submit button */
    public void ClickSubmitButton() { ElementUtils.click(submitButtonLocator); }

    /** Upload a file into the form */
    public void UploadFile(String path) { ActionsUtils.uploadFile(uploadFileLocator, path); }

    
    // -----------------------------
    // Business Flows
    // -----------------------------
    /**
     * Fill and submit the Contact Us form, then handle the confirmation alert.
     * 
     * @param name    User name
     * @param email   User email
     * @param subject Subject of the message
     * @param msg     Body of the message
     * @param path    File path for upload
     */
    public void FillContactUsForm(String name, String email, String subject, String msg, String path) {
        EnterName(name);
        EnterEmail(email);
        EnterSubject(subject);
        EnterMsg(msg);
        UploadFile(path);
        ClickSubmitButton();
        AlertUtils.acceptAlert();
    }
    
    
    // -----------------------------
    // Getters
    // -----------------------------
    /** Get the Contact Us page header text */
    public String ContactUsHeader() { return ElementUtils.getText(headerLocator); }

    /** Get the submitted form success message */
    public String SubmittedFormMsg() { return ElementUtils.getText(submittedFormLocator); }
    
    
    // -----------------------------
    // Navigation
    // -----------------------------
    /** Click the Home button to navigate back to homepage */
    public void ClickHomeButton() { ElementUtils.click(goHomeButtonLocator); }
}
