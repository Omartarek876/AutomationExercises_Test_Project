/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;


/**
 *
 * @author Omar Tarek
 */
public class TC16_LoginBeforeCheckout {
    
    Home_Page Homepage;
    Login_Page validLogin_Page;
    AccountDeleted_Page AccountDeleted_Page;
    Products_Page productsPage;
    Cart_Page CartPage;
    Checkout_Page checkoutPage;
    
        // Array holding user credentials loaded from JSON file
    static LoginUsers[] ListOfUsers;
    
        /**
     * Data Provider returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "validLoginData")
    public LoginUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    @Test(dataProvider = "validLoginData")
    public void TC16_LoginBeforeCheckout(LoginUsers validLoginUsers) {
                BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickSignUp_Login();
        assertTrue(validLogin_Page.LoginHeader().contains("Login to your account") , "'Login to your account' is invisible");
        System.out.println("'Login to your account' is visible");
        
        validLogin_Page.login(validLoginUsers.LoginEmail , validLoginUsers.LoginPassword);
        assertTrue(Homepage.LoggedAsText().contains("Logged in as"));
        System.out.println("you logged in successfully as " + validLoginUsers.LoginEmail);
        
        Homepage.clickProducts();
        productsPage.hoverProductAndClick("1");
        productsPage.hoverProductAndClick("2");
        
        productsPage.clickCart();
        checkoutPage.clickCheckoutButton();       
        checkoutPage.addCommentBeforeCheckout("this is a test");
        checkoutPage.clickPlaceOrderButton();
        
        checkoutPage.enterPaymentDetails("Omar Tarek", "4111111111111111", "123", "12", "2028");
        checkoutPage.clickPayAndConfirmOrder(); 
        
        assertTrue(checkoutPage.getSuccessPaymentMsg().contains("Congratulations!")  , "Your order has not been placed successfully!");
        System.out.println("Your order has been placed successfully!");
        checkoutPage.clickContinueAfterSuccess();
        
        
      /* 
       Homepage.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/delete_account");
        System.out.println("the account is deleted");
        
        AccountDeleted_Page.clickContinue();
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");*/
    }


    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        ListOfUsers = HelperClass.ReadLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage = new Home_Page("chrome");
        validLogin_Page = new Login_Page();
        AccountDeleted_Page = new AccountDeleted_Page();
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
        checkoutPage = new Checkout_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC16 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC16 - END");
    }
    
}
