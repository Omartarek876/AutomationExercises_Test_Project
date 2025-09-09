/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.ActionsUtils;
import utils.ElementUtils;
import utils.KeyboardUtils;

/**
 *
 * @author Omar Tarek
 */
public class Login_Page {
    
    private String browser;
    
    private By loginHeaderLocator = By.xpath("//*[@id='form']/div/div/div[1]/div/h2"); // "Login to your account" header
    private By emailInputLocator = By.xpath("//input[@data-qa='login-email']"); // Email input field
    private By passwordInputLocator = By.xpath("//input[@data-qa='login-password']"); // Password input field
    private By loginButtonLocator = By.xpath("//button[@data-qa='login-button']"); // Login button
    private By newUserSignupHeaderLocator = By.xpath("//h2[contains(text(),'New User Signup!')]"); // New User Signup header
    private By errorMessageLocator = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"); // Login error message
    private By forgotPasswordLinkLocator = By.xpath("//a[contains(text(),'Forgot Password?')]"); // Forgot Password link



    
    public void enterEmail(String email)
    {
        KeyboardUtils.sendKeys(emailInputLocator, email);
    }

    public void enterPassword(String password)
    {
        KeyboardUtils.sendKeys(passwordInputLocator, password);
    }

    public void clickLoginButton()
    {
        ElementUtils.click(loginButtonLocator);
    }

    public void login(String email, String password)
    {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String NewUserSignupHeader() {
         return ElementUtils.getText(newUserSignupHeaderLocator);
    }

    public String LoginHeader() {
         return ElementUtils.getText(loginHeaderLocator);
    }


}
