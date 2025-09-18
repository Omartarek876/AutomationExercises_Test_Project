/**
 * Login Page Object Class
 *
 * This class represents the Login Page in the Automation Exercise application.
 * It contains all locators and actions related to the login functionality.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */
package pages;

import org.openqa.selenium.By;
import utils.*;

/**
 * Page Object Model (POM) class for the Login Page.
 * Encapsulates all locators and actions related to user login functionality.
 */
public class Login_Page {
    
    // ---------------- Variables ----------------
    private String browser; // Placeholder for browser name if needed in the future
    
    // ---------------- Locators ----------------
    
    // Login Section
    private By loginHeaderLocator          = By.cssSelector("#form h2"); // "Login to your account" header
    private By emailInputLocator           = By.cssSelector("input[data-qa='login-email']"); // Email input field
    private By passwordInputLocator        = By.cssSelector("input[data-qa='login-password']"); // Password input field
    private By loginButtonLocator          = By.cssSelector("button[data-qa='login-button']"); // Login button
    private By InvalidLoginErrorMsgLocator = By.cssSelector("#form form p"); // Invalid login error message
    
    // Signup Section
    private By newUserSignupHeaderLocator  = By.xpath("//h2[contains(text(),'New User Signup!')]"); // "New User Signup!" header
    
    // Links
    private By forgotPasswordLinkLocator   = By.linkText("Forgot Password?"); // Forgot Password link
    private By cartLocator                 = By.linkText("Cart"); // Cart link
    
    
    // ---------------- Methods ----------------
    
    /** Enter email in login form */
    public void enterEmail(String email) {
        KeyboardUtils.sendKeys(emailInputLocator, email);
    }

    /** Enter password in login form */
    public void enterPassword(String password) {
        KeyboardUtils.sendKeys(passwordInputLocator, password);
    }

    /** Click on login button */
    public void clickLoginButton() {
        ElementUtils.click(loginButtonLocator);
    }

    /**
     * Perform login with given credentials
     * @param email user email
     * @param password user password
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    /** Get "New User Signup!" header text */
    public String NewUserSignupHeader() {
        return ElementUtils.getText(newUserSignupHeaderLocator);
    }

    /** Get "Login to your account" header text */
    public String LoginHeader() {
        return ElementUtils.getText(loginHeaderLocator);
    } 
    
    /** Get invalid login error message */
    public String InvalidLoginErrorMsg() {
        return ElementUtils.getText(InvalidLoginErrorMsgLocator);
    }
    
    /** Navigate to Cart page */
    public void clickCart() {
        ElementUtils.click(cartLocator);
    }

    /** Click on Forgot Password link */
    public void clickForgotPassword() {
        ElementUtils.click(forgotPasswordLinkLocator);
    }
}
