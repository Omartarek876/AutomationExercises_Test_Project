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
public class TC18_ViewCategoryProducts {
    
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    
    @Test
    public void TC18_ViewCategoryProducts()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
               
        // Verify Women Category
        assertTrue(Homepage.getCategoryText("Women").contains("WOMEN"), 
                   "Women category is not visible");

        // Verify Men Category
        assertTrue(Homepage.getCategoryText("Men").contains("MEN"), 
                   "Men category is not visible");

        // Verify Kids Category
        assertTrue(Homepage.getCategoryText("Kids").contains("KIDS"), 
                   "Kids category is not visible");
        
        System.out.println("All categories are visible in left sidebar.");
        
        Homepage.clickSubCategory("Men" , "Jeans");
        assertTrue(Homepage.getSubCategoryTitle("Jeans").contains("MEN - JEANS PRODUCTS") , "user is not navigated to that category page");
        
        Homepage.clickSubCategory("Women" , "Dress");
        assertTrue(Homepage.getSubCategoryTitle("Dress").contains("WOMEN - DRESS PRODUCTS") , "user is not navigated to that category page");
     
}   
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
         base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC18 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC18 - END");
    }
    
    
}
