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
public class TC12_AddProductsInCart {
    
   
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    
    @Test
    public void TC12_AddProductsInCart()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        productsPage.hoverProductAndClick("1");
        productsPage.hoverProductAndClick("2");
        
        productsPage.clickCart();
        // Verify product 1
        assertTrue(CartPage.getProductNameInCart("1").contains("Blue Top"), "Product 1 name mismatch");
        assertTrue(CartPage.getProductPriceInCart("1").contains("Rs. 500"), "Product 1 price mismatch");
        assertTrue(CartPage.getProductQuantityInCart("1").contains("1"), "Product 1 quantity mismatch");
        assertTrue(CartPage.getProductTotalInCart("1").contains("Rs. 500"), "Product 1 total mismatch");

        // Verify product 2
        assertTrue(CartPage.getProductNameInCart("2").contains("Men Tshirt"), "Product 2 name mismatch");
        assertTrue(CartPage.getProductPriceInCart("2").contains("Rs. 400"), "Product 2 price mismatch");
        assertTrue(CartPage.getProductQuantityInCart("2").contains("1"), "Product 2 quantity mismatch");
        assertTrue(CartPage.getProductTotalInCart("2").contains("Rs. 400"), "Product 2 total mismatch");

        System.out.println("Both products, prices, quantities, and totals verified ");
        
        
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
        System.out.println("TC12 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC12 - END");
    }
    
    
    
}
