/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
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
public class TC10_SubscriptionInHomepage {
    
    Home_Page Homepage;

    
    @Test
    public void TC10_SubscriptionInHomepage()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.scrollToFooter();
        System.out.println(Homepage.subscriptionHeader());
        assertTrue(Homepage.subscriptionHeader().contains("SUBSCRIPTION") , "text 'SUBSCRIPTION' is invisible");
        System.out.println("text 'SUBSCRIPTION' is visible");
        
        Homepage.sendSubscriptionEmail("ot482002@gmail.com");
        System.out.println(Homepage.getSuccessMessage());
        assertTrue(Homepage.getSuccessMessage().contains("You have been successfully subscribed!") , "success message 'You have been successfully subscribed!' is Invisible");
        System.out.println("success message 'You have been successfully subscribed!' is visible");
  
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
        System.out.println("TC10 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC10 - END");
    }
    
    
    
}
