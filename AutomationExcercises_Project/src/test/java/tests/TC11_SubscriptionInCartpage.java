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
 * TC11 - Subscription In Cart Page Test Class
 *
 * This test verifies that the subscription functionality
 * works properly in the Cart page footer section.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */
public class TC11_SubscriptionInCartpage {
    
    private Home_Page Homepage;
    private Cart_Page CartPage;

    @Test
    public void TC11_SubscriptionInCartpage() {   
        // Step 1: Navigate to URL
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is invisible");
        System.out.println("The home page is visible");

        // Step 2: Navigate to Cart
        Homepage.clickCart();

        // Step 3: Scroll to footer
        CartPage.scrollToFooter();
        System.out.println(CartPage.subscriptionHeader());
        assertTrue(CartPage.subscriptionHeader().contains("SUBSCRIPTION"), 
                "Text 'SUBSCRIPTION' is invisible");
        System.out.println("Text 'SUBSCRIPTION' is visible");

        // Step 4: Enter email and verify success message
        Homepage.sendSubscriptionEmail("ot482002@gmail.com");
        System.out.println(CartPage.getSuccessMessage());
        assertTrue(CartPage.getSuccessMessage().contains("You have been successfully subscribed!"), 
                "Success message 'You have been successfully subscribed!' is invisible");
        System.out.println("Success message 'You have been successfully subscribed!' is visible");
    }   
    
    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
        
    }

    @BeforeMethod
    public void setUpMethod() throws IOException {
        System.out.println("TC11 - START");
        Homepage = new Home_Page("chrome");
        CartPage = new Cart_Page();
    }

    @AfterMethod
    public void tearDownMethod() {
        System.out.println("TC11 - END");
        base.BaseDriver.quitDriver();   
    }
}
