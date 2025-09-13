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
public class TC09_SearchProduct {
    
    Home_Page Homepage;
    Products_Page ProductsPage;

    
    @Test
    public void TC09_SearchProduct()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink() ,"https://automationexercise.com/products");
        
        ProductsPage.searchProduct("Men Tshirt");
        System.out.println(ProductsPage.searchedProductsHeader());
        assertTrue(ProductsPage.searchedProductsHeader().contains("SEARCHED PRODUCTS") , "'SEARCHED PRODUCTS' is not visible ");
        System.out.println("'SEARCHED PRODUCTS' is visible");
 
 }
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException  {
        Homepage = new Home_Page("chrome");
        ProductsPage = new Products_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC08 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC08 - END");
    }
    
    
}
