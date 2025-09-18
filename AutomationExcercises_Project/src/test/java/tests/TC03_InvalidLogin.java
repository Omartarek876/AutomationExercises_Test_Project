package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC03_InvalidLogin
 * 
 * This test validates the login functionality with invalid user credentials
 * using data-driven testing from JSON file.
 * 
 * Author: Omar Tarek
 */
public class TC03_InvalidLogin {
    
    Home_Page homePage;
    Login_Page loginPage;
    
    // Array holding user credentials loaded from JSON file
    static LoginUsers[] listOfUsers;
    
    /**
     * Data Provider returns array of User objects for all invalid test users.
     * @return LoginUsers[] of invalid user credentials
     */
    @DataProvider(name = "invalidLoginData")
    public LoginUsers[] userDataProvider() {
        return listOfUsers;
    }
    
    @Test(dataProvider = "invalidLoginData")
    public void TC03_InvalidLogin(LoginUsers invalidLoginUsers) {
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        assertTrue(homePage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        homePage.clickSignUp_Login();
        assertTrue(loginPage.LoginHeader().contains("Login to your account"),
                "'Login to your account' header is not visible");
        System.out.println("'Login to your account' is visible");
        
        loginPage.login(invalidLoginUsers.LoginEmail, invalidLoginUsers.LoginPassword);
        assertTrue(loginPage.InvalidLoginErrorMsg().contains("Your email or password is incorrect!"),
                "The invalid login error message is not visible");
        System.out.println("Invalid login attempt correctly shows error message");
    }

    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
        listOfUsers = HelperClass.ReadLoginUsers("InvalidLoginData.json");
        System.out.println("Number of invalid users loaded: " + listOfUsers.length);

    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC03 - START");
        homePage = new Home_Page("chrome");
        loginPage = new Login_Page();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        base.BaseDriver.quitDriver();   
        System.out.println("TC03 - END");
    }
}
