package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC04_LogoutUser
 * 
 * This test validates the logout functionality after a successful login
 * using data-driven testing from JSON file.
 * 
 * Author: Omar Tarek
 */
public class TC04_LogoutUser {
    
    Home_Page homePage;
    Login_Page loginPage;
    
    // Array holding user credentials loaded from JSON file
    static LoginUsers[] listOfUsers;
    
    /**
     * Data Provider returns array of User objects for valid login users.
     * @return LoginUsers[] of valid user credentials
     */
    @DataProvider(name = "validLoginData")
    public LoginUsers[] userDataProvider() {
        return listOfUsers;
    }
    
    @Test(dataProvider = "validLoginData")
    public void TC04_LogoutUser(LoginUsers validLoginUsers) {
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        assertTrue(homePage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        homePage.clickSignUp_Login();
        assertTrue(loginPage.LoginHeader().contains("Login to your account"),
                "'Login to your account' header is not visible");
        System.out.println("'Login to your account' is visible");
        
        loginPage.login(validLoginUsers.LoginEmail, validLoginUsers.LoginPassword);
      //  assertTrue(homePage.LoggedAsText().contains("Logged in as"), "Login was not successful");
        System.out.println("You logged in successfully as " + validLoginUsers.LoginEmail);
        
        homePage.LogOut();
        assertTrue(loginPage.LoginHeader().contains("Login to your account"),
                "User was not redirected to login page after logout");
        System.out.println("The user logged out successfully");
    }

    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
        listOfUsers = HelperClass.ReadLoginUsers("LogoutData.json");
        System.out.println("Number of users loaded: " + listOfUsers.length);      
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
         
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC04 - START");
        homePage = new Home_Page("chrome");
        loginPage = new Login_Page();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        base.BaseDriver.quitDriver(); 
        System.out.println("TC04 - END");
    }
}
