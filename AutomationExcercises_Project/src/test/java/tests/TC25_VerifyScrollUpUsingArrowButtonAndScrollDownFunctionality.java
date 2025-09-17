/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
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
public class TC25_VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality {
    
   
    Home_Page Homepage;

    @Test
    public void TC25_VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.scrollToFooter();
        System.out.println(Homepage.subscriptionHeader());
        assertTrue(Homepage.subscriptionHeader().contains("SUBSCRIPTION") , "text 'SUBSCRIPTION' is invisible");
        System.out.println("text 'SUBSCRIPTION' is visible");
        
        Homepage.clickUpArrow();
        assertTrue(Homepage.homePageSecondHeader().contains("Full-Fledged practice website for Automation Engineers")
                   , "'Full-Fledged practice website for Automation Engineers' text is Invisible on screen");
        System.out.println("'Full-Fledged practice website for Automation Engineers' text is visible on screen");
        
        
}   
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        Homepage = new Home_Page("chrome");
    }

    @AfterClass
    public void tearDownClass() throws Exception {
         base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC25 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC25 - END");
    }
    
 
    
}
