/**
 * Home Page Object Class
 * 
 * This class represents the Home Page in the Automation Exercise application.
 * It contains all locators and actions related to the homepage functionality.
 * 
 * Follows the Page Object Model (POM) design pattern.
 * 
 * Author: Omar Tarek
 */

package pages;

import utils.*;
import base.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Home_Page {
    
    // ------------------- Variables -------------------
    private String browser;

    // ------------------- Locators -------------------
    // Navigation
    private By signUpLoginLocator   = By.cssSelector("a[href='/login']");
    private By productsLocator      = By.cssSelector("a[href='/products']");
    private By cartLocator          = By.cssSelector("a[href='/view_cart']");
    private By contactUsLocator     = By.cssSelector("a[href='/contact_us']");
    private By logoutLocator        = By.cssSelector("a[href='/logout']");
    private By homeLogoLocator      = By.cssSelector("a.logo.pull-left");
    private By testcasesLocator     = By.cssSelector("a[href='/test_cases']");
    
    // Headers
    private By testcasesPageHeaderLocator = By.cssSelector("#form h5 span");
    private By HomePageHeaderLocator      = By.cssSelector("#slider-carousel h1");
    private By H2TextLocator              = By.cssSelector("#slider-carousel h2");
    
    // Featured Products
    private By FeaturedListLocator     = By.cssSelector("div.features_items");
    private By featuredProductsLocator = By.cssSelector("div.features_items div.single-products");
    
    // Categories
    private By womenAddCategory = By.cssSelector("#accordian > div:nth-child(1) h4 > a");
    private By menAddCategory   = By.cssSelector("#accordian > div:nth-child(2) h4 > a");
    private By kidsAddCategory  = By.cssSelector("#accordian > div:nth-child(3) h4 > a");

    // Subcategories
    private By dressLocator     = By.cssSelector("#Women a[href*='Dress']");
    private By topsLocator      = By.cssSelector("#Women a[href*='Tops']");
    private By sareeLocator     = By.cssSelector("#Women a[href*='Saree']");
    private By tshirtLocator    = By.cssSelector("#Men a[href*='Tshirts']");
    private By jeansLocator     = By.cssSelector("#Men a[href*='Jeans']");
    private By kidsDressLocator = By.cssSelector("#Kids a[href*='Dress']");
    private By kidsTopsLocator  = By.cssSelector("#Kids a[href*='Tops']");
    
    // Account
    private By loggedAsLocator = By.cssSelector("ul.nav.navbar-nav li b");
    private By DeleteAccountLocator  = By.cssSelector("a[href='/delete_account']");
    private By LogoutLocator         = By.cssSelector("a[href='/logout']");
    
    // Page Layout
    private By footerLocator = By.tagName("footer"); 
    private By headerLocator = By.id("header");
    private By UpArrowLocator = By.id("scrollUp");
    
    // Subscription
    private By subscriptionHeaderLocator = By.cssSelector(".single-widget h2");
    private By subscriptionEmailLocator  = By.id("susbscribe_email");
    private By SubscriptionArrowLocator  = By.id("subscribe");
    private By successMessageLocator     = By.cssSelector("div.alert-success");
    
    // Recommended Items
    private By recommendedProductNames  = By.cssSelector("#recommended-item-carousel .productinfo p");
    private By recommendedItemsSection = By.xpath("//h2[normalize-space()='recommended items']");
    
    // Search Results
    private By allSearchResultsLocator = By.cssSelector(".product-image-wrapper");
    private By continueButtonLocator   = By.cssSelector("button.close-modal, #cartModal button[data-dismiss='modal']"); 

    
    // ------------------- Constructors -------------------
    
    /** Constructor with browser setup */
    public Home_Page(String Browser) throws IOException {
        this.browser = Browser;
        BaseDriver.initializeDriver(browser);
    }   
    
    /** Default constructor */
    public Home_Page (){}
    

    // ------------------- Page Actions -------------------
    
    /** Get all featured products on the page */
    public List<WebElement> getFeaturedProductsList() {
        return ElementUtils.getElements(featuredProductsLocator);
    }
    
    /** Get main homepage header text */
    public String homePageheader() {
        return ElementUtils.getText(HomePageHeaderLocator);
    }
    
    /** Get secondary homepage header text (h2) */
    public String homePageSecondHeader() {
        return ElementUtils.getText(H2TextLocator);
    }
    

    // ------------------- Navigation -------------------
    
    /** Navigate to Sign Up / Login page */
    public void clickSignUp_Login() { ElementUtils.click(signUpLoginLocator); }
    
    /** Navigate to Products page */
    public void clickProducts()     { ElementUtils.click(productsLocator); }
    
    /** Navigate to Cart page */
    public void clickCart()         { ElementUtils.click(cartLocator); }
    
    /** Navigate to Contact Us page */
    public void clickContactUs()    { ElementUtils.click(contactUsLocator); }
    
    /** Perform logout */
    public void clickLogout()       { ElementUtils.click(logoutLocator); }
    
    /** Click on Home Logo */
    public void clickHomeLogo()     { ElementUtils.click(homeLogoLocator); }
    

    // ------------------- Featured Products -------------------
    
    /** Click featured product dynamically by product name */
    public void clickFeaturedProduct(String productName) {
        By productLocator = By.xpath("//div[@class='features_items']//div[@class='single-products']" +
                                     "[.//h2[contains(text(),'" + productName + "')]]");
        ElementUtils.click(productLocator);
    }
    

    // ------------------- User Account -------------------
    
    /** Get "Logged in as" text */
    public String LoggedAsText() { return ElementUtils.getText(loggedAsLocator); }
    
    /** Delete account */
    public void DeleteAccount()  { ElementUtils.click(DeleteAccountLocator); }
    
    /** Log out user */
    public void LogOut()         { ElementUtils.click(LogoutLocator); }
    

    // ------------------- Testcases -------------------
    
    /** Navigate to Test Cases page */
    public void clickTestcases () { ElementUtils.click(testcasesLocator); }
    
    /** Get Test Cases page header */
    public String testcasesPageHeader () { return ElementUtils.getText(testcasesPageHeaderLocator); }
    

    // ------------------- Scrolling -------------------
    
    /** Scroll to footer section */
    public void scrollToFooter ()  { ActionsUtils.scrollToElement(footerLocator); }
    
    /** Scroll to header section */
    public void scrollToHeader()   { ActionsUtils.scrollToElement(headerLocator); }
    
    /** Scroll to "Up Arrow" button */
    public void scrollToUpArrow()  { ActionsUtils.scrollToElement(UpArrowLocator); }
    

    // ------------------- Subscription -------------------
    
    /** Get subscription section header */
    public String subscriptionHeader () { return ElementUtils.getText(subscriptionHeaderLocator); }
    
    /** Enter email and click subscribe */
    public void sendSubscriptionEmail(String email) {
        KeyboardUtils.sendKeys(subscriptionEmailLocator, email);
        ElementUtils.click(SubscriptionArrowLocator);
    }
    
    /** Get subscription success message */
    public String getSuccessMessage() { return ElementUtils.getText(successMessageLocator); }
    

    // ------------------- Categories & Subcategories -------------------
    
    /** Click category by name */
    public void clickCategory(String categoryName) {
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + categoryName + "')]");
        ActionsUtils.scrollToElement(locator);
        ElementUtils.click(locator);
    }
    
    /** Click subcategory by category name and subcategory name */
    public void clickSubCategory(String categoryName , String subCategoryName) {
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + subCategoryName + "')]");
        clickCategory(categoryName);
        ActionsUtils.scrollToElement(locator);
        ElementUtils.click(locator);
    }
    
    /** Get category text */
    public String getCategoryText(String categoryName) {
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + categoryName + "')]");
        return ElementUtils.getText(locator);
    }
    
    /** Get subcategory text */
    public String getSubCategoryText(String subCategoryName) {
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + subCategoryName + "')]");
        return ElementUtils.getText(locator);
    }
    
    /** Get subcategory title */
    public String getSubCategoryTitle(String subCategoryName) {
        By titleLocator = By.xpath("//h2[@class='title text-center']");
        return ElementUtils.getText(titleLocator);
    }
    

    // ------------------- Brands -------------------
    
    /** Click brand dynamically */
    public void clickBrand(String brandName) {
        By brandLocator = By.xpath("//div[@class='brands-name']//a[contains(normalize-space(),'" + brandName + "')]");
        ElementUtils.click(brandLocator);
    }
    
    /** Get brand page title */
    public String getBrandPageTitle(String brandName) {
        By brandTitleLocator = By.xpath("//h2[@class='title text-center']");
        return ElementUtils.getText(brandTitleLocator);
    }
    

    // ------------------- Cart Management -------------------
    
    /** Add all visible search results to cart */
    public List<String> addedProductNames = new ArrayList<>();
    public List<String> addAllSearchResultsToCart() {
        int totalProducts = ElementUtils.getElements(allSearchResultsLocator).size();

        for (int i = 1; i <= totalProducts; i++) {
            // Dynamic locators for product name & add-to-cart
            By currentProductName   = By.xpath("(//div[@class='productinfo text-center']/p)[" + i + "]");
            By currentProductAddBtn = By.xpath("(//div[@class='productinfo text-center'])[" + i + "]//a[contains(@class,'add-to-cart')]");

            try {
                ActionsUtils.scrollToElement(currentProductName);
                String productName = ElementUtils.getText(currentProductName);
                addedProductNames.add(productName);

                // Add to cart
                ActionsUtils.scrollToElement(currentProductAddBtn);
                ElementUtils.click(currentProductAddBtn);

                // Handle popup
                try { ElementUtils.click(continueButtonLocator); }
                catch (Exception ignorePopup) { }

            } catch (Exception e) {
                System.out.println("Skipping product index " + i + " due to: "
                        + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
        
        return addedProductNames;
    }

    /** Add products to cart by their IDs */
    public List<String> addedDesiredProductNames = new ArrayList<>();
    public List<String> addProductsToCartByIds(String[] productIds) {
        for (String productId : productIds) {
            try {
                By currentProductName   = By.xpath("//a[@data-product-id='" + productId + "']/ancestor::div[@class='productinfo text-center']//p");
                By currentProductAddBtn = By.cssSelector("a[data-product-id='" + productId + "']");

                ActionsUtils.scrollToElement(currentProductName);
                String productName = ElementUtils.getText(currentProductName);
                addedDesiredProductNames.add(productName);

                ActionsUtils.scrollToElement(currentProductAddBtn);
                ElementUtils.click(currentProductAddBtn);

                try { ElementUtils.click(continueButtonLocator); }
                catch (Exception ignorePopup) { }

            } catch (Exception e) {
                System.out.println("Skipping productId " + productId + " due to: "
                        + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
        return addedDesiredProductNames;
    }
    

    // ------------------- Recommended Items -------------------
    
    /** Get recommended items section title */
    public String getRecommendedItemsTitle() {
        return ElementUtils.getText(recommendedItemsSection);
    }
    
    /** Hover on product and add it to cart */
    public void hoverProductAndAddToCart(String productId) {
        By addElementToCartLocator = By.cssSelector("a[data-product-id='" + productId + "']");
        ActionsUtils.hoverAndClick(addElementToCartLocator);
        ElementUtils.click(continueButtonLocator);
    }

    /** Hover and add multiple recommended products by IDs */
    public List<String> addedRecommendedProductNames = new ArrayList<>();
    public List<String> hoverAndAddMultipleRecommendedProducts(List<String> productIds) {
        for (String productId : productIds) {
            try {
                By recommendedAddToCartLocator = By.cssSelector("#recommended-item-carousel a[data-product-id='" + productId + "']");
                By productNameLocator = By.xpath("//div[@id='recommended-item-carousel']//a[@data-product-id='" + productId + "']/ancestor::div[@class='productinfo text-center']/p");

                ActionsUtils.scrollToElement(recommendedAddToCartLocator);
                ActionsUtils.hoverAndClick(recommendedAddToCartLocator);

                try { ElementUtils.click(continueButtonLocator); }
                catch (Exception ignorePopup) { }

                String productName = ElementUtils.getText(productNameLocator);
                addedRecommendedProductNames.add(productName);

            } catch (Exception e) {
                System.out.println("Skipping recommended productId " + productId + " due to: " 
                                   + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
        return addedRecommendedProductNames;
    }
    

    // ------------------- Utility -------------------
    
    /** Scroll to bottom of page */
    public void scrollToBottom() {
        ActionsUtils.scrollToElement(SubscriptionArrowLocator);
    }
    
    /** Scroll up using arrow */
    public void clickUpArrow () { ElementUtils.click(UpArrowLocator); }
}
