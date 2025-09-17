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
public class TC17_RemoveProductsFromCart {
    
       
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    
    @Test
    public void TC17_RemoveProductsFromCart()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        String[] productIds = {"1"};  // pick only these
        productsPage.addProductsToCartByIds(productIds);
        
        productsPage.clickCart();
        String addedProduct = CartPage.getProductNameInCart("1");
        
        productsPage.deleteFromCart();
        assertTrue(productsPage.getEmptyCartMsg().contains("empty") , "the product is not deleted successfully");
        // assertFalse(productsPage.getProductName().contains(addedProduct) , "the product is not deleted successfully");
        System.out.println("the product is deleted successfully");
       
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
        System.out.println("TC17 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC17 - END");
    }
    
    
    
}
