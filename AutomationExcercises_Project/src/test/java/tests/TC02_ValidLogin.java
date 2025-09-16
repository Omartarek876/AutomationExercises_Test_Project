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
public class TC02_ValidLogin {
    
    Home_Page Homepage;
    Login_Page validLogin_Page;
    AccountDeleted_Page AccountDeleted_Page;
    
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
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickSignUp_Login();
        assertTrue(validLogin_Page.LoginHeader().contains("Login to your account") , "'Login to your account' is invisible");
        System.out.println("'Login to your account' is visible");
        
        validLogin_Page.login(validLoginUsers.LoginEmail , validLoginUsers.LoginPassword);
        assertTrue(Homepage.LoggedAsText().contains("Logged in as"));
        System.out.println("you logged in successfully as " + validLoginUsers.LoginEmail);
              
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
