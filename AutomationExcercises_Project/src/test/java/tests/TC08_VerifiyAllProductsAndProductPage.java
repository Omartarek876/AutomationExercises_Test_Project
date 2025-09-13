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
public class TC08_VerifiyAllProductsAndProductPage {
    
     Home_Page Homepage;
     Products_Page ProductsPage;

    @Test
    public void TC08_VerifiyAllProductsAndProductPage()
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink() ,"https://automationexercise.com/products");
        
        ProductsPage.clickOnFirstProduct();
      
        assertFalse(ProductsPage.getProductName().isEmpty(), "Product name is not visible!");
        assertFalse(ProductsPage.getProductCategory().isEmpty(), "Product category is not visible!");
        assertFalse(ProductsPage.getProductPrice().isEmpty(), "Product price is not visible!");
        assertFalse(ProductsPage.getProductAvailability().isEmpty(), "Product availability is not visible!");
        assertFalse(ProductsPage.getProductCondition().isEmpty(), "Product condition is not visible!");
        assertFalse(ProductsPage.getProductBrand().isEmpty(), "Product brand is not visible!");
        System.out.println("all the product details is visible");
       
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
