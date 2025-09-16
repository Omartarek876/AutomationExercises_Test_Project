
package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 *
 * @author Omar Tarek
 */
public class TC23_VerifyAddressDetailsInCheckoutPage {
    
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
    public void TC23_VerifyAddressDetailsInCheckoutPage(CreateAccountUsers UserTestData)
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickSignUp_Login();
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
        assertTrue(Homepage.LoggedAsText().contains(UserTestData.SignUpName));
       
        Homepage.clickProducts();
        String[] productIds = {"1", "2" };  // pick only these
        List<String> addedProducts = productsPage.addProductsToCartByIds(productIds);
        System.out.println("Products added by IDs: " + addedProducts);
        
        productsPage.clickCart();
        assertTrue(CartPage.getshoppingCartHeader().contains("Shopping Cart") , "cart page is not displayed");
        System.out.println("cart page is displayed");
        
        checkoutPage.clickCheckoutButton();
        
        assertTrue(checkoutPage.getDeliveryAddress().contains(UserTestData.address1) 
                , " the delivery address is not same address filled at the time registration of account");
        System.out.println(" the delivery address is same address filled at the time registration of account");
        
        assertTrue(checkoutPage.getBillingAddress().contains(UserTestData.address1) 
                , " the delivery address is not same address filled at the time registration of account");
        System.out.println(" the delivery address is same address filled at the time registration of account");
                
 
        Homepage.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/delete_account");
        System.out.println("the account is deleted");
        
        AccountDeleted_Page.clickContinue();
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
               
 }     
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        
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
