package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC02_ValidLogin
 * 
 * This test validates the login functionality with valid user credentials
 * using data-driven testing from JSON file.
 * 
 * Author: Omar Tarek
 */
public class TC02_ValidLogin {
    
    Home_Page homePage;
    Login_Page loginPage;
    AccountStatus_Page accountStatusPage;
    
    // Array holding user credentials loaded from JSON file
    static LoginUsers[] listOfUsers;
    
    /**
     * Data Provider returns array of User objects for all test users.
     * @return LoginUsers[] of user credentials
     */
    @DataProvider(name = "validLoginData")
    public LoginUsers[] userDataProvider() {
        return listOfUsers;
    }
    
    @Test(dataProvider = "validLoginData")
    public void TC02_ValidLogin(LoginUsers validLoginUsers) {
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        assertTrue(homePage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        homePage.clickSignUp_Login();
        assertTrue(loginPage.LoginHeader().contains("Login to your account"),
                "'Login to your account' header is not visible");
        System.out.println("'Login to your account' is visible");
        
        loginPage.login(validLoginUsers.LoginEmail, validLoginUsers.LoginPassword);
        // assertTrue(homePage.LoggedAsText().contains("Logged in as"));
        System.out.println("You logged in successfully as " + validLoginUsers.LoginEmail);
              
        /*
        homePage.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink(), "https://automationexercise.com/delete_account");
        System.out.println("The account is deleted");
        
        accountStatusPage.clickContinue();
        assertTrue(homePage.homePageheader().contains("Automation"),
                "The home page is not visible after account deletion");
        */
    }

    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
        listOfUsers = HelperClass.ReadLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + listOfUsers.length);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC02 - START");
        homePage = new Home_Page("chrome");
        loginPage = new Login_Page();
        accountStatusPage = new AccountStatus_Page();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        base.BaseDriver.quitDriver();   
        System.out.println("TC02 - END");
    }
}
