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
public class TC03_InvalidLogin {
    
    Home_Page Homepage;
    Login_Page InvalidLogin_Page;
    
        // Array holding user credentials loaded from JSON file
    static LoginUsers[] ListOfUsers;
    
        /**
     * Data Provider returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "InvalidLoginData")
    public LoginUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    @Test(dataProvider = "InvalidLoginData")
    public void TC02_ValidLogin(LoginUsers InvalidLoginUsers) {
                BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickSignUp_Login();
        assertTrue(InvalidLogin_Page.LoginHeader().contains("Login to your account") , "'Login to your account' is invisible");
        System.out.println("'Login to your account' is visible");
        
        InvalidLogin_Page.login(InvalidLoginUsers.LoginEmail , InvalidLoginUsers.LoginPassword);
        assertTrue(InvalidLogin_Page.InvalidLoginErrorMsg().contains("Your email or password is incorrect!") 
                , "the error msg is invisible");
        
    }


    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        ListOfUsers = HelperClass.ReadLoginUsers("InvalidLoginData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage = new Home_Page("chrome");
        InvalidLogin_Page = new Login_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC03 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC03 - END");
    }

}
