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
public class TC07_TestcasesPageNavigation {
    
     Home_Page Homepage;

    @Test
    public void TC07_TestcasesPageNavigation()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickTestcases();
        System.out.println(Homepage.testcasesPageHeader());
        assertTrue(Homepage.testcasesPageHeader().contains("Below is the list of test Cases for you to practice the Automation. Click on the scenario for detailed Test Steps:")
                , "the user is not navigated to test cases page successfully");
        System.out.println("the user is navigated to test cases page successfully");       
    }
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException  {
        Homepage = new Home_Page("chrome");
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC07 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC07 - END");
    }
    
}
