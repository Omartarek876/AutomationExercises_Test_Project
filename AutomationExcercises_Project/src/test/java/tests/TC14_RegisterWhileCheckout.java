/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package tests;

import java.io.FileNotFoundException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 *
 * @author Omar Tarek
 */
public class TC14_RegisterWhileCheckout {
    
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;
    SignUp_Page SignUp_Page;
    AccountCreated_Page accountCreated_Page;
    Checkout_Page checkoutPage;
    AccountDeleted_Page AccountDeleted_Page;
    
    
    
    // Array holding user credentials loaded from JSON file
    static CreateAccountUsers[] ListOfUsers;
    
        /**
     * Data Provider returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "usersData")
    public CreateAccountUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    
    @Test(dataProvider = "usersData")
    public void TC14_RegisterWhileCheckout(CreateAccountUsers UserTestData)
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        productsPage.hoverProductAndClick("1");
        productsPage.hoverProductAndClick("2");
        
        productsPage.clickCart();
        assertTrue(CartPage.getshoppingCartHeader().contains("Shopping Cart") , "cart page is not displayed");
        System.out.println("cart page is displayed");
        
        checkoutPage.clickCheckoutButton();
        checkoutPage.clickCheckoutAndsign();
        
        assertTrue(SignUp_Page.SignupHeader1().contains("New User Signup!") 
                , "the 'New User Signup!' is invisible");
        System.out.println("'new User SignUp!' is visible");
        
        SignUp_Page.signup(UserTestData.SignUpName, UserTestData.SignUpEmail);
        assertTrue(SignUp_Page.SignupHeader2().contains("ENTER ACCOUNT INFORMATION")
                , "the 'ENTER ACCOUNT INFORMATION' is invisible");
        System.out.println("the SignUp header 'ENTER ACCOUNT INFORMATION' is visible ");
        
        SignUp_Page.FillAllRegisterationForm(UserTestData.gender, UserTestData.SignUpName, UserTestData.password, UserTestData.day, UserTestData.month, 
              UserTestData.year, UserTestData.firstName, UserTestData.lastName, UserTestData.company, UserTestData.address1, UserTestData.address2, UserTestData.country,
              UserTestData.state, UserTestData.city, UserTestData.zipcode, UserTestData.mobileNumber );
        System.out.println("the registeration process is done");
        
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/account_created");
        System.out.println("the account is created");
        accountCreated_Page.clickContinue();

            
 //       assertTrue(Homepage.LoggedAsText().contains("Logged in as " + UserTestData.SignUpName));
 //       System.out.println("you logged in successfully as " + UserTestData.SignUpName);
        
        Homepage.clickCart();
        checkoutPage.clickCheckoutButton();       
        checkoutPage.addCommentBeforeCheckout("this is a test");
        checkoutPage.clickPlaceOrderButton();
        
        checkoutPage.enterPaymentDetails("Omar Tarek", "4111111111111111", "123", "12", "2028");
        checkoutPage.clickPayAndConfirmOrder(); 
        
        assertTrue(checkoutPage.getSuccessPaymentMsg().contains("Congratulations!")  , "Your order has not been placed successfully!");
        System.out.println("Your order has been placed successfully!");
        checkoutPage.clickContinueAfterSuccess();
        
        
        Homepage.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/delete_account");
        System.out.println("the account is deleted");
        
        AccountDeleted_Page.clickContinue();
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");  
               
 }     
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException  {
        
        ListOfUsers = HelperClass.ReadCreateAccountUsers("CreateAccountData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
        SignUp_Page = new SignUp_Page();
        accountCreated_Page = new AccountCreated_Page();
        checkoutPage = new Checkout_Page();
        AccountDeleted_Page = new AccountDeleted_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC14 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC14 - END");
    }
    
    
  
}
