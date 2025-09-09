/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import org.openqa.selenium.By;
import utils.ElementUtils;
import utils.KeyboardUtils;

/**
 *
 * @author Omar Tarek
 */
public class SignUp_Page {
    
    private String browser;
    
    private By signupHeaderLocator = By.xpath("//h2[contains(text(),'New User Signup!')]"); // Header
    private By nameInputLocator = By.xpath("//input[@data-qa='signup-name']"); // Name input
    private By emailInputLocator = By.xpath("//input[@data-qa='signup-email']"); // Email input
    private By signupButtonLocator = By.xpath("//button[@data-qa='signup-button']"); // Signup button

    // Signup Page Locators
    private By enterAccountInfoHeaderLocator = By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/h2/b");
    private By titleMrRadioLocator = By.id("id_gender1");
    private By titleMrsRadioLocator = By.id("id_gender2");
    private By nameInputFormLocator = By.id("name");
    private By emailInputFormLocator = By.id("email");
    private By passwordInputLocator = By.id("password");
    private By dayDropdownLocator = By.id("days");
    private By monthDropdownLocator = By.id("months");
    private By yearDropdownLocator = By.id("years");
    private By newsletterCheckboxLocator = By.id("newsletter");
    private By offersCheckboxLocator = By.id("optin");
    private By firstNameInputLocator = By.id("first_name");
    private By lastNameInputLocator = By.id("last_name");
    private By companyInputLocator = By.id("company");
    private By address1InputLocator = By.id("address1");
    private By address2InputLocator = By.id("address2");
    private By countryDropdownLocator = By.id("country");
    private By stateInputLocator = By.id("state");
    private By cityInputLocator = By.id("city");
    private By zipcodeInputLocator = By.id("zipcode");
    private By mobileNumberInputLocator = By.id("mobile_number");
    private By createAccountButtonLocator = By.xpath("//button[contains(text(),'Create Account')]");

    
    public void SignUP_enterName(String name) {
        KeyboardUtils.sendKeys(nameInputLocator, name);
    }

    public void SignUP_enterEmail(String email) {
        KeyboardUtils.sendKeys(emailInputLocator, email);
    }

    public void clickSignupButton() {
        ElementUtils.click(signupButtonLocator);
    }

    public void signup(String name, String email) {
        SignUP_enterName(name);
        SignUP_enterEmail(email);
        clickSignupButton();
    }

    public String SignupHeader1() {
        return ElementUtils.getText(signupHeaderLocator);
    }  
    
    
    // Check if "Enter Account Information" header is visible
    public String SignupHeader2() {
        return ElementUtils.getText(enterAccountInfoHeaderLocator); 
    }

    // Select Title (Mr / Mrs)
    public void selectTitle(String gender) {
       if (gender == "male") {
                 ElementUtils.selectRadioButton(titleMrRadioLocator);
       } else if (gender == "female"){
                 ElementUtils.selectRadioButton(titleMrsRadioLocator);
      }
   }
    

    // Enter Name
    public void enterName(String name) {
        KeyboardUtils.sendKeys(nameInputFormLocator, name);
    }

    // Enter Password
    public void enterPassword(String password) {
       KeyboardUtils.sendKeys(passwordInputLocator, password);
    }

    // Select DOB
    public void selectDOB(String day, String month, String year) {
        ElementUtils.selectDropdownByValue(dayDropdownLocator, day);
        ElementUtils.selectDropdownByValue(monthDropdownLocator, month);
        ElementUtils.selectDropdownByValue(yearDropdownLocator, year);
    }

    // Check newsletter and special offers
    public void checkNewsletter() {
        ElementUtils.checkCheckbox(newsletterCheckboxLocator);
    }

    public void checkSpecialOffers() {
        ElementUtils.checkCheckbox(offersCheckboxLocator);
    }

    // Enter Address Details
    public void enterFirstName(String firstName) {
         KeyboardUtils.sendKeys(firstNameInputLocator, firstName);
    }

    public void enterLastName(String lastName) {
         KeyboardUtils.sendKeys(lastNameInputLocator, lastName);
    }

    public void enterCompany(String company) {
         KeyboardUtils.sendKeys(companyInputLocator, company);
    }

    public void enterAddress1(String address) {
         KeyboardUtils.sendKeys(address1InputLocator, address);
    }

    public void enterAddress2(String address) {
         KeyboardUtils.sendKeys(address2InputLocator, address);
    }

    public void selectCountry(String country) {
         ElementUtils.selectDropdownByVisibleText(countryDropdownLocator, country);
    } 

    public void enterState(String state) {
        KeyboardUtils.sendKeys(stateInputLocator, state);
    }

    public void enterCity(String city) {
        KeyboardUtils.sendKeys(cityInputLocator, city);
    }

    public void enterZipCode(String zipcode) {
        KeyboardUtils.sendKeys(zipcodeInputLocator, zipcode);
    }

    public void enterMobileNumber(String mobile) {
        KeyboardUtils.sendKeys(mobileNumberInputLocator, mobile);
    }

    // Submit the form
    public void clickCreateAccount() {
        ElementUtils.click(createAccountButtonLocator);
    } 

    
    public void FillAllRegisterationForm(String gender, String name, String password, String day,
        String month, String year, String firstName, String lastName, String company, String address1,
        String address2, String country, String state, String city, String zipcode, String mobileNumber) {
            // Select title
            selectTitle(gender);

            // Fill credentials
            enterName(name);
            enterPassword(password);

            // Date of Birth
            selectDOB(day, month, year);

            // Check newsletter / offers
            checkNewsletter();
            checkSpecialOffers();

            // Address details
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

            // Submit form
            clickCreateAccount();
        }


    


}
