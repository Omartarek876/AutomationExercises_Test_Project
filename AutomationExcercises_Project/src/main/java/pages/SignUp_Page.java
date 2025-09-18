/**
 * SignUp Page Object Class
 *
 * This class represents the Sign Up Page in the Automation Exercise application.
 * It contains all locators and actions related to the user registration functionality.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */
package pages;

import org.openqa.selenium.By;
import utils.ElementUtils;
import utils.KeyboardUtils;

/**
 * Page Object Model (POM) class for the SignUp Page.
 * Encapsulates locators and actions for new user registration,
 * account information entry, and address details.
 */
public class SignUp_Page {
    
    // ---------------- Variables ----------------
    private String browser; // Placeholder for browser name if needed later

    // ---------------- Locators ----------------
    
    // Initial Signup Section
    private By signupHeaderLocator              = By.xpath("//h2[contains(text(),'New User Signup!')]"); 
    private By nameInputLocator                 = By.cssSelector("input[data-qa='signup-name']"); 
    private By emailInputLocator                = By.cssSelector("input[data-qa='signup-email']"); 
    private By signupButtonLocator              = By.cssSelector("button[data-qa='signup-button']"); 

    // Account Information Section
    private By enterAccountInfoHeaderLocator    = By.cssSelector("#form h2 b"); 
    private By titleMrRadioLocator              = By.id("id_gender1");
    private By titleMrsRadioLocator             = By.id("id_gender2");
    private By nameInputFormLocator             = By.id("name");
    private By emailInputFormLocator            = By.id("email");
    private By passwordInputLocator             = By.id("password");
    private By dayDropdownLocator               = By.id("days");
    private By monthDropdownLocator             = By.id("months");
    private By yearDropdownLocator              = By.id("years");
    private By newsletterCheckboxLocator        = By.id("newsletter");
    private By offersCheckboxLocator            = By.id("optin");

    // Address Section
    private By firstNameInputLocator            = By.id("first_name");
    private By lastNameInputLocator             = By.id("last_name");
    private By companyInputLocator              = By.id("company");
    private By address1InputLocator             = By.id("address1");
    private By address2InputLocator             = By.id("address2");
    private By countryDropdownLocator           = By.id("country");
    private By stateInputLocator                = By.id("state");
    private By cityInputLocator                 = By.id("city");
    private By zipcodeInputLocator              = By.id("zipcode");
    private By mobileNumberInputLocator         = By.id("mobile_number");
    private By createAccountButtonLocator       = By.xpath("//button[contains(text(),'Create Account')]");
    private By SignUpWithExistingEmailMsgLocator= By.cssSelector("#form div:nth-child(3) form p");


    // ---------------- Methods ----------------
    
    // ---------- Initial Signup ----------
    /** Enter name in SignUp form */
    public void SignUP_enterName(String name) { KeyboardUtils.sendKeys(nameInputLocator, name); }

    /** Enter email in SignUp form */
    public void SignUP_enterEmail(String email) { KeyboardUtils.sendKeys(emailInputLocator, email); }

    /** Click on SignUp button */
    public void clickSignupButton() { ElementUtils.click(signupButtonLocator); }

    /** Perform signup with name and email */
    public void signup(String name, String email) { 
        SignUP_enterName(name); 
        SignUP_enterEmail(email); 
        clickSignupButton(); 
    }

    // ---------- Headers ----------
    /** Get initial "New User Signup!" header */
    public String SignupHeader1() { return ElementUtils.getText(signupHeaderLocator); }

    /** Get "Enter Account Information" header */
    public String SignupHeader2() { return ElementUtils.getText(enterAccountInfoHeaderLocator); }

    // ---------- Title ----------
    /** Select title (Mr/Mrs) */
    public void selectTitle(String gender) {
        if (gender.equalsIgnoreCase("male")) 
            ElementUtils.selectRadioButton(titleMrRadioLocator);
        else if (gender.equalsIgnoreCase("female")) 
            ElementUtils.selectRadioButton(titleMrsRadioLocator);
    }

    // ---------- Inputs ----------
    /** Enter name */
    public void enterName(String name) { KeyboardUtils.sendKeys(nameInputFormLocator, name); }

    /** Enter password */
    public void enterPassword(String password) { KeyboardUtils.sendKeys(passwordInputLocator, password); }

    // ---------- DOB ----------
    /** Select Date of Birth (day, month, year) */
    public void selectDOB(String day, String month, String year) {
        ElementUtils.selectDropdownByValue(dayDropdownLocator, day);
        ElementUtils.selectDropdownByValue(monthDropdownLocator, month);
        ElementUtils.selectDropdownByValue(yearDropdownLocator, year);
    }

    // ---------- Newsletter & Offers ----------
    /** Check newsletter subscription */
    public void checkNewsletter() { ElementUtils.checkCheckbox(newsletterCheckboxLocator); }

    /** Check special offers subscription */
    public void checkSpecialOffers() { ElementUtils.checkCheckbox(offersCheckboxLocator); }

    // ---------- Address ----------
    public void enterFirstName(String firstName) { KeyboardUtils.sendKeys(firstNameInputLocator, firstName); }
    public void enterLastName(String lastName) { KeyboardUtils.sendKeys(lastNameInputLocator, lastName); }
    public void enterCompany(String company) { KeyboardUtils.sendKeys(companyInputLocator, company); }
    public void enterAddress1(String address) { KeyboardUtils.sendKeys(address1InputLocator, address); }
    public void enterAddress2(String address) { KeyboardUtils.sendKeys(address2InputLocator, address); }
    public void selectCountry(String country) { ElementUtils.selectDropdownByVisibleText(countryDropdownLocator, country); } 
    public void enterState(String state) { KeyboardUtils.sendKeys(stateInputLocator, state); }
    public void enterCity(String city) { KeyboardUtils.sendKeys(cityInputLocator, city); }
    public void enterZipCode(String zipcode) { KeyboardUtils.sendKeys(zipcodeInputLocator, zipcode); }
    public void enterMobileNumber(String mobile) { KeyboardUtils.sendKeys(mobileNumberInputLocator, mobile); }
    public void clickCreateAccount() { ElementUtils.click(createAccountButtonLocator); } 

    // ---------- Full Registration ----------
    /**
     * Fill full registration form and submit
     */
    public void FillAllRegisterationForm(String gender, String name, String password, String day,
        String month, String year, String firstName, String lastName, String company, String address1,
        String address2, String country, String state, String city, String zipcode, String mobileNumber) {
        
        selectTitle(gender);
        enterPassword(password);
        selectDOB(day, month, year);
        checkNewsletter();
        checkSpecialOffers();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterCompany(company);
        enterAddress1(address1);
        enterAddress2(address2);
        selectCountry(country);
        enterState(state);
        enterCity(city);
        enterZipCode(zipcode);
        enterMobileNumber(mobileNumber);
        clickCreateAccount();
    }

    // ---------- Existing Email ----------
    /** Get error message when signing up with an existing email */
    public String SignUpWithExistingEmail() { return ElementUtils.getText(SignUpWithExistingEmailMsgLocator); }
}
