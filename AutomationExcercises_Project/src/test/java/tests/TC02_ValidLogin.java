/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package tests;

import java.io.FileNotFoundException;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountCreated_Page;
import pages.AccountDeleted_Page;
import pages.Home_Page;
import pages.SignUp_Page;
import org.testng.annotations.*;
import pages.Login_Page;
import utils.BrowserUtils;


/**
 *
 * @author Omar Tarek
 */
public class TC02_ValidLogin {
    
    Home_Page Homepage2;
    Login_Page validLogin_Page;
    AccountDeleted_Page AccountDeleted_Page2;
    
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
    public void TC02_ValidLogin(LoginUsers validLoginUsers) {
                BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage2.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage2.clickSignUp_Login();
        assertTrue(validLogin_Page.LoginHeader().contains("Login to your account") , "'Login to your account' is invisible");
        System.out.println("'Login to your account' is visible");
        
        validLogin_Page.login(validLoginUsers.LoginEmail , validLoginUsers.LoginPassword);
        assertTrue(Homepage2.LoggedAsText().contains("Logged in as"));
        System.out.println("you logged in successfully as " + validLoginUsers.LoginEmail);

/*        
        Homepage2.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/delete_account");
        System.out.println("the account is deleted");
        
        AccountDeleted_Page2.clickContinue();
        assertTrue(Homepage2.homePageheader().contains("Automation")
                , "the home page is invisible"); */
    }


    @BeforeClass
    public void setUpClass()  throws FileNotFoundException  {
        ListOfUsers = HelperClass.ReadValidLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage2 = new Home_Page("chrome");
        validLogin_Page = new Login_Page();
        AccountDeleted_Page2 = new AccountDeleted_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC02 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC02 - END");
    }
}
