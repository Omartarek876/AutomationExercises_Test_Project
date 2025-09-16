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
public class TC11_SubscriptionInCartpage {
    
    
    Home_Page Homepage;
    Cart_Page CartPage;

    
    @Test
    public void TC11_SubscriptionInCartpage()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickCart();
        CartPage.scrollToFooter();
        System.out.println(CartPage.subscriptionHeader());
        assertTrue(CartPage.subscriptionHeader().contains("SUBSCRIPTION") , "text 'SUBSCRIPTION' is invisible");
        System.out.println("text 'SUBSCRIPTION' is visible");
        
        Homepage.sendSubscriptionEmail("ot482002@gmail.com");
        System.out.println(CartPage.getSuccessMessage());
        assertTrue(CartPage.getSuccessMessage().contains("You have been successfully subscribed!") , "success message 'You have been successfully subscribed!' is Invisible");
        System.out.println("success message 'You have been successfully subscribed!' is visible");

 }   
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        Homepage = new Home_Page("chrome");
        CartPage = new Cart_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC11 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC11 - END");
    }
    
    
    
}
