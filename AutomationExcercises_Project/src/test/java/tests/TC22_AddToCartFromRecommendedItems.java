/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;
/**
 *
 * @author Omar Tarek
 */
public class TC22_AddToCartFromRecommendedItems {
    
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    
    @Test
    public void TC22_AddToCartFromRecommendedItems()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.scrollToBottom();
        assertTrue(Homepage.getRecommendedItemsTitle().contains("RECOMMENDED ITEMS"), 
           "Recommended items section title is incorrect or not visible!");
        System.out.println("Verified Recommended Items title successfully.");

        List<String> RecommendedElementsToAdd = Arrays.asList("2", "4");
        List<String> addedElements = Homepage.hoverAndAddMultipleRecommendedProducts(RecommendedElementsToAdd);
        
        Homepage.clickCart();
        CartPage.verifyProductsInCart(addedElements);

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
