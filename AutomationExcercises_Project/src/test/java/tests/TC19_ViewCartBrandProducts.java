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
public class TC19_ViewCartBrandProducts {
    
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    
    @Test
    public void TC19_ViewCartBrandProducts()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        
        String Brand1 = "Madame";
        String Brand2 = "Polo";

                
        Homepage.clickBrand("Madame");
        assertTrue(Homepage.getBrandPageTitle("Madame").contains(Brand1.toUpperCase()) , "user is not navigated to brand page");
        System.out.println("user is navigated to brand page" + Brand1);
        Homepage.clickBrand("Polo");
        assertTrue(Homepage.getBrandPageTitle("Polo").contains(Brand2.toUpperCase()) , "user is not navigated to brand page");
        System.out.println("user is navigated to brand page" + Brand2);

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
        System.out.println("TC19 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC19 - END");
    }
    
    
}
