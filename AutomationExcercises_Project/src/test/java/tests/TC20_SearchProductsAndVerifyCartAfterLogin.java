/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
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
public class TC20_SearchProductsAndVerifyCartAfterLogin {
    
    Home_Page Homepage;
    Products_Page ProductsPage;
    Cart_Page CartPage;
    Login_Page validLogin_Page;
    
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
    public void TC20_SearchProductsAndVerifyCartAfterLogin(LoginUsers validLoginUsers)
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink() ,"https://automationexercise.com/products");

        ProductsPage.searchProduct("women");
        System.out.println(ProductsPage.searchedProductsHeader());
        assertTrue(ProductsPage.searchedProductsHeader().contains("SEARCHED PRODUCTS") , "'SEARCHED PRODUCTS' is not visible ");
        System.out.println("'SEARCHED PRODUCTS' is visible");
        
        List<String> addedElements = Homepage.addAllSearchResultsToCart();
        System.out.println(" all the products related to search are added into the cart");
        
        Homepage.clickCart();
        Homepage.clickSignUp_Login();
                assertTrue(validLogin_Page.LoginHeader().contains("Login to your account") , "'Login to your account' is invisible");
        System.out.println("'Login to your account' is visible");
        
        validLogin_Page.login(validLoginUsers.LoginEmail , validLoginUsers.LoginPassword);
        assertTrue(Homepage.LoggedAsText().contains("Logged in as"));
        System.out.println("you logged in successfully as " + validLoginUsers.LoginEmail);
        
        validLogin_Page.clickCart();
        CartPage.verifyProductsInCart(addedElements);
}   
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        ListOfUsers = HelperClass.ReadLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage = new Home_Page("chrome");
        ProductsPage = new Products_Page();
        CartPage = new Cart_Page();
        validLogin_Page = new Login_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
         base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC20 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC20 - END");
    }
    
       
}
