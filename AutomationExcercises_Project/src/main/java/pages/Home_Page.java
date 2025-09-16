/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import utils.*;
import base.*;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Omar Tarek
 */
public class Home_Page {
    
    private String browser;
    
    private By signUpLoginLocator = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By productsLocator = By.xpath("//a[contains(text(),'Products')]");
    private By cartLocator = By.xpath("//a[contains(text(),'Cart')]");
    private By contactUsLocator = By.xpath("//a[contains(text(),'Contact us')]");
    private By logoutLocator = By.xpath("//a[contains(text(),'Logout')]");
    private By homeLogoLocator = By.xpath("//a[@class='logo pull-left']");
    private By testcasesLocator = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");
    private By testcasesPageHeaderLocator = By.xpath("//*[@id=\"form\"]/div/div[2]/h5/span");
    private By HomePageHeaderLocator = By.xpath("//*[@id=\"slider-carousel\"]/div/div[1]/div[1]/h1");
    private By FeaturedListLocator = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]");
    private By featuredProductsLocator = By.cssSelector("div.features_items div.single-products");  
    private By womenAddCategory = By.xpath("//*[@id=\"accordian\"]/div[1]/div[1]/h4/a/span/i");
    private By menAddCategory   = By.xpath("//*[@id=\"accordian\"]/div[2]/div[1]/h4/a/span/i");
    private By kidsAddCategory  = By.xpath("//*[@id=\"accordian\"]/div[3]/div[1]/h4/a/span/i");
    private By dressLocator     = By.xpath("//*[@id=\"Women\"]/div/ul/li[1]/a");
    private By topsLocator      = By.xpath("//*[@id=\"Women\"]/div/ul/li[2]/a");
    private By sareeLocator     = By.xpath("//*[@id=\"Women\"]/div/ul/li[3]/a");
    private By tshirtLocator    = By.xpath("//*[@id=\"Men\"]/div/ul/li[1]/a");
    private By jeansLocator     = By.xpath("//*[@id=\"Men\"]/div/ul/li[2]/a");
    private By kidsDressLocator = By.xpath("//*[@id=\"Kids\"]/div/ul/li[1]/a");
    private By kidsTopsLocator  = By.xpath("//*[@id=\"Kids\"]/div/ul/li[2]/a");
    private By loggedAsLocator = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a");
    private By DeleteAccountLocator = By.cssSelector("a[href='/delete_account']");
    private By LogoutLocator = By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a");
    private By footerLocator = By.cssSelector("footer"); 
    private By subscriptionHeaderLocator = By.className("single-widget");
    private By subscriptionEmailLocator = By.id("susbscribe_email");
    private By SubscriptionArrowLocator = By.id("subscribe");
    private By successMessageLocator = By.cssSelector("div.alert-success");
private By recommendedProductNames = By.cssSelector("#recommended-item-carousel .productinfo p");

        private By allSearchResultsLocator = By.cssSelector(".product-image-wrapper");
    private By continueButtonLocator = By.cssSelector("button.close-modal, #cartModal button[data-dismiss='modal']"); 


    
    public Home_Page(String Browser) throws IOException {
        this.browser = Browser;
        BaseDriver.initializeDriver(browser);
    }   
    
    public Home_Page (){}
    
    // Method to return all featured product WebElements
    public List<WebElement> getFeaturedProductsList() {
        return ElementUtils.getElements(featuredProductsLocator);
    }
    
    public String homePageheader() {
        return ElementUtils.getText(HomePageHeaderLocator);
    }
    
    public void clickSignUp_Login() {
        ElementUtils.click(signUpLoginLocator);
    }

    public void clickProducts() {
        ElementUtils.click(productsLocator);
    }

    public void clickCart() {
        ElementUtils.click(cartLocator);
    }

    public void clickContactUs() {
        ElementUtils.click(contactUsLocator);
    }

    public void clickLogout() {
        ElementUtils.click(logoutLocator);
    }

    public void clickHomeLogo() {
        ElementUtils.click(homeLogoLocator);
    }
    
    // Click a featured product by name (dynamic locator)
    public void clickFeaturedProduct(String productName) {
        By productLocator = By.xpath("//div[@class='features_items']//div[@class='single-products']" +
                                 "[.//h2[contains(text(),'" + productName + "')]]");
      ElementUtils.click(productLocator);
     }
    
    public String LoggedAsText()
    {
        return ElementUtils.getText(loggedAsLocator);
    }
    
    public void DeleteAccount()
    {
        ElementUtils.click(DeleteAccountLocator);
    }
    
    public void LogOut()
    {
        ElementUtils.click(LogoutLocator);
    }
    
        
    public void clickTestcases (){
        ElementUtils.click(testcasesLocator);
    }
    
    public String testcasesPageHeader (){
       return ElementUtils.getText(testcasesPageHeaderLocator);
    }
    
    public void scrollToFooter ()
    {
        ActionsUtils.scrollToElement(footerLocator);
    }
    
    public String subscriptionHeader ()
    {
        return ElementUtils.getText(subscriptionHeaderLocator);
    }
    
    public void sendSubscriptionEmail(String email)
    {
        KeyboardUtils.sendKeys(subscriptionEmailLocator, email);
        ElementUtils.click(SubscriptionArrowLocator);
    }
    
    public String getSuccessMessage()
    {
        return ElementUtils.getText(successMessageLocator);
    }
 
    
// Click any category by name (Women, Men, Kids, etc.)
public void clickCategory(String categoryName) {
    By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + categoryName + "')]");
    ActionsUtils.scrollToElement(locator);
    ElementUtils.click(locator);
}

// Click any subcategory by name (Dress, Tshirts, Tops & Shirts, etc.)
public void clickSubCategory(String categoryName , String subCategoryName) {
    By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + subCategoryName + "')]");
    clickCategory(categoryName);
    ActionsUtils.scrollToElement(locator);
    ElementUtils.click(locator);
}

// Get text of a category (for verification)
public String getCategoryText(String categoryName) {
    By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + categoryName + "')]");
    return ElementUtils.getText(locator);
}

// Get text of a subcategory (for verification)
public String getSubCategoryText(String subCategoryName) {
    By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + subCategoryName + "')]");
    return ElementUtils.getText(locator);
}

// Get text of a subcategory (for verification)
public String getSubCategoryTitle(String subCategoryName) {
    By titleLocator = By.xpath("//h2[@class='title text-center']");
    return ElementUtils.getText(titleLocator);
}

public void clickBrand(String brandName) {
    By brandLocator = By.xpath("//div[@class='brands-name']//a[contains(normalize-space(),'" + brandName + "')]");
    ElementUtils.click(brandLocator);
}


public String getBrandPageTitle(String brandName) {
   // clickBrand(brandName);
    By brandTitleLocator = By.xpath("//h2[@class='title text-center']");
    return ElementUtils.getText(brandTitleLocator);
}


public List<String> addedProductNames = new ArrayList<>();
public List<String> addAllSearchResultsToCart() {
    int totalProducts = ElementUtils.getElements(allSearchResultsLocator).size();

    for (int i = 1; i <= totalProducts; i++) {
        // Dynamic locators
        By currentProductName = By.xpath("(//div[@class='productinfo text-center']/p)[" + i + "]");
        By currentProductAddBtn = By.xpath("(//div[@class='productinfo text-center'])[" + i + "]//a[contains(@class,'add-to-cart')]");

        try {
            // Get product name
            ActionsUtils.scrollToElement(currentProductName);
            String productName = ElementUtils.getText(currentProductName);
            addedProductNames.add(productName);
            System.out.println("product number "+i+" : " + currentProductName);

            // Add to cart
            ActionsUtils.scrollToElement(currentProductAddBtn);
            ElementUtils.click(currentProductAddBtn);

            // Handle "Continue Shopping" popup
            try {
                ElementUtils.click(continueButtonLocator);
            } catch (Exception ignorePopup) {
                System.out.println("No popup appeared for product index " + i);
            }

            System.out.println("Added to cart: " + productName);

        } catch (Exception e) {
            System.out.println("Skipping product index " + i + " due to: "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    return addedProductNames;
}


private By recommendedItemsSection = By.xpath("//h2[normalize-space()='recommended items']");

// Scroll لآخر الصفحة
public void scrollToBottom() {
    ActionsUtils.scrollToElement(footerLocator);// assuming عندك Utils بتعمل scroll
}

// Get Recommended Items section title
public String getRecommendedItemsTitle() {
    return ElementUtils.getText(recommendedItemsSection);
}

    public void hoverProductAndAddToCart(String productId) {
        By addElementToCartLocator = By.cssSelector("a[data-product-id='" + productId + "']");
    //    By PostAddedElementToCartLocator = By.cssSelector("a[data-product-id='" + (productId+3) +"']");

    //    ActionsUtils.scrollToElement(PostAddedElementToCartLocator);
        ActionsUtils.hoverAndClick(addElementToCartLocator);
        ElementUtils.click(continueButtonLocator);
    }
   

public List<String> addedRecommendedProductNames = new ArrayList<>();
public List<String> hoverAndAddMultipleRecommendedProducts(List<String> productIds) {

    for (String productId : productIds) {
        try {
            // Locator for Add to cart button
            By recommendedAddToCartLocator = By.cssSelector("#recommended-item-carousel a[data-product-id='" + productId + "']");
            
            // Locator for the product name (based on productId position)
            By productNameLocator = By.xpath("//div[@id='recommended-item-carousel']//a[@data-product-id='" + productId + "']/ancestor::div[@class='productinfo text-center']/p");

            // Hover + Click Add
            ActionsUtils.scrollToElement(recommendedAddToCartLocator);
            ActionsUtils.hoverAndClick(recommendedAddToCartLocator);

            // Close popup
            try {
                ElementUtils.click(continueButtonLocator);
            } catch (Exception ignorePopup) {
                System.out.println("No popup for productId: " + productId);
            }

            // Get and store product name
            String productName = ElementUtils.getText(productNameLocator);
            addedRecommendedProductNames.add(productName);

            System.out.println("Added Recommended Product: " + productName);

        } catch (Exception e) {
            System.out.println("Skipping recommended productId " + productId + " due to: " 
                               + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    return addedRecommendedProductNames;
}


}

