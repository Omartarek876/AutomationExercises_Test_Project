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
public class TC01_RegisterUser {
    
    Home_Page Homepage1;
    SignUp_Page SignUp_Page1;
    AccountCreated_Page accountCreated_Page1;
    AccountDeleted_Page AccountDeleted_Page1;
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
    public void TC01_Register_User(CreateAccountUsers UserTestData)
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage1.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage1.clickSignUp_Login();
        assertTrue(SignUp_Page1.SignupHeader1().contains("New User Signup!") 
                , "the 'New User Signup!' is invisible");
        System.out.println("'new User SignUp!' is visible");
        
        SignUp_Page1.signup(UserTestData.SignUpName, UserTestData.SignUpEmail);
        assertTrue(SignUp_Page1.SignupHeader2().contains("ENTER ACCOUNT INFORMATION")
                , "the 'ENTER ACCOUNT INFORMATION' is invisible");
        System.out.println("the SignUp header 'ENTER ACCOUNT INFORMATION' is visible ");
        
        SignUp_Page1.FillAllRegisterationForm(UserTestData.gender, UserTestData.SignUpName, UserTestData.password, UserTestData.day, UserTestData.month, 
              UserTestData.year, UserTestData.firstName, UserTestData.lastName, UserTestData.company, UserTestData.address1, UserTestData.address2, UserTestData.country,
              UserTestData.state, UserTestData.city, UserTestData.zipcode, UserTestData.mobileNumber );
        System.out.println("the registeration process is done");
        
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/account_created");
        System.out.println("the account is created");
        
        accountCreated_Page1.clickContinue();
        assertTrue(Homepage1.LoggedAsText().contains("Logged in as " + UserTestData.SignUpName));
        System.out.println("you logged in successfully as " + UserTestData.SignUpName);

/*        
        Homepage1.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/delete_account");
        System.out.println("the account is deleted");
        
        AccountDeleted_Page1.clickContinue();
        assertTrue(Homepage1.homePageheader().contains("Automation")
                , "the home page is invisible");       */ 
    }
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException  {
        ListOfUsers = HelperClass.ReadCreateAccountUsers("CreateAccountData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage1 = new Home_Page("chrome");
        SignUp_Page1 = new SignUp_Page();
        accountCreated_Page1 = new AccountCreated_Page();
        AccountDeleted_Page1 = new AccountDeleted_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC01 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC01 - END");
    }
}
