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
public class TC13_ProductQuantityInCart {
    
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    
    @Test
    public void TC13_ProductQuantityInCart()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts(); 
        productsPage.viewProduct("1");
        String Quantity = "4";
        productsPage.enterQuantity(Quantity);
        productsPage.addToCart();
        productsPage.clickCart();
        
        assertTrue(CartPage.getProductQuantityInCart("1").contains(Quantity), "Product quantity mismatch");
        System.out.println("product quantity match");
        
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
        System.out.println("TC13 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC13 - END");
    }
    
    
     
}
