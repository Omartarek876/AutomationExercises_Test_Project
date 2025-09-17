package pages;

import java.util.ArrayList;
import java.util.List;
import utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Products_Page {
    
    private By searchBarLocator = By.id("search_product");
    private By searchButtonLocator = By.id("submit_search");
    private By firstProductDetailsLocator = By.cssSelector("a[href='/product_details/1']");
    private By fourthProductDetailsLocator = By.cssSelector("a[href='/product_details/4']");
    
    // product details
    private By productNameLocator = By.xpath("//div[@class='product-information']/h2");
    private By productCategoryLocator = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    private By productPriceLocator = By.xpath("//div[@class='product-information']//span/span");
    private By productAvailabilityLocator = By.xpath("//div[@class='product-information']//p[contains(normalize-space(.),'Availability')]");
    private By productConditionLocator = By.xpath("//div[@class='product-information']//p[contains(normalize-space(.),'Condition')]");
    private By productBrandLocator = By.xpath("//div[@class='product-information']//p[contains(normalize-space(.),'Brand')]");
    
    // search results
    private By searchedProductsLocator = By.cssSelector("h2.title.text-center");
    
    // cart flow
    private By continueButtonLocator = By.cssSelector("button.close-modal, #cartModal button[data-dismiss='modal']"); 
    private By cartLocator = By.xpath("//a[contains(text(),'Cart')]");
    private By quantityLocator = By.id("quantity");
    private By addToCartLocator = By.cssSelector("button.btn.btn-default.cart");
    private By deleteFromCartLocator = By.cssSelector("#product-1 > td.cart_delete > a");
    private By emptyCartMsg = By.cssSelector("#empty_cart > p");
    private By allSearchResultsLocator = By.cssSelector(".product-image-wrapper");

       
    
    // Review section locators
private By reviewSectionHeader = By.xpath("//a[normalize-space()='Write Your Review']");
private By reviewNameField = By.id("name");
private By reviewEmailField = By.id("email");
private By reviewTextArea = By.id("review");
private By reviewSubmitButton = By.id("button-review");
private By reviewSuccessMessage = By.xpath("//*[contains(text(),'Thank you for your review.')]");


    // ---------- Methods ----------
    
    public void clickSearchButton() {
        ElementUtils.click(searchButtonLocator);
    }
    
    public void searchProduct(String productName) {
        KeyboardUtils.sendKeys(searchBarLocator, productName);
        clickSearchButton();
    }
    
    // getters for product details
    public String getProductName() {
        return ElementUtils.getText(productNameLocator);
    } 
    
    public String getProductCategory() {
        return ElementUtils.getText(productCategoryLocator);
    }

    public String getProductPrice() {
        return ElementUtils.getText(productPriceLocator);
    }

    public String getProductAvailability() {
        return ElementUtils.getText(productAvailabilityLocator);
    }

    public String getProductCondition() {
        return ElementUtils.getText(productConditionLocator);
    }

    public String getProductBrand() {
        return ElementUtils.getText(productBrandLocator);
    }
    
    public String searchedProductsHeader() {
        return ElementUtils.getText(searchedProductsLocator);
    }
    
    public void viewProduct(String productId) {
        By productDetailsLocator = By.cssSelector("a[href='/product_details/" + productId + "']");
    
        ActionsUtils.scrollToElement(productDetailsLocator);
        ElementUtils.click(productDetailsLocator);
    }
      
    public void clickCart() {
        ElementUtils.click(cartLocator);
    }
    
    public void enterQuantity (String quantity)
    {
        KeyboardUtils.clearAndSendKeys(quantityLocator, quantity);
    }
       
    public void addToCart ()
    {
        ElementUtils.click(addToCartLocator);
        ElementUtils.click(continueButtonLocator);
    }
    
    
    public void deleteFromCart()
    {
        ElementUtils.click(deleteFromCartLocator);
    }
    
    public String getEmptyCartMsg()
    {
        return ElementUtils.getText(emptyCartMsg);
    }

// Get review section header text
public String getReviewSectionHeader() {
    ActionsUtils.scrollToElement(reviewSubmitButton);
    return ElementUtils.getText(reviewSectionHeader);
}


// Fill review form
public void SubmitProductReview(String name, String email, String reviewText) {
    KeyboardUtils.sendKeys(reviewNameField, name);
    KeyboardUtils.sendKeys(reviewEmailField, email);
    KeyboardUtils.sendKeys(reviewTextArea, reviewText);
    ElementUtils.click(reviewSubmitButton);
}

// Get review success message text
public String getReviewSuccessMessage() {
    return ElementUtils.getText(reviewSuccessMessage);
}
  
    
    
    
    
    
    
    
    
public List<String> addedDesiredProductNames = new ArrayList<>();
public List<String> addProductsToCartByIds(String[] productIds) {
    for (String productId : productIds) {
        try {
            // Dynamic locators
            By currentProductName = By.xpath("//a[@data-product-id='" + productId + "']/ancestor::div[@class='productinfo text-center']//p");
            By currentProductAddBtn = By.cssSelector("a[data-product-id='" + productId + "']");

            // Scroll and get product name
            ActionsUtils.scrollToElement(currentProductName);
            String productName = ElementUtils.getText(currentProductName);
            addedDesiredProductNames.add(productName);

            // Add to cart
            ActionsUtils.scrollToElement(currentProductAddBtn);
            ElementUtils.click(currentProductAddBtn);

            // Handle "Continue Shopping" popup
            try {
                ElementUtils.click(continueButtonLocator);
            } catch (Exception ignorePopup) {
                System.out.println("No popup appeared for productId: " + productId);
            }

            System.out.println("Added to cart (by ID " + productId + "): " + productName);

        } catch (Exception e) {
            System.out.println("Skipping productId " + productId + " due to: "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    return addedDesiredProductNames;
}




private By SubscriptionArrowLocator = By.id("subscribe");
private By recommendedItemsSection = By.xpath("//h2[normalize-space()='recommended items']");

    // Scroll لآخر الصفحة
public void scrollToBottom() {
    ActionsUtils.scrollToElement(SubscriptionArrowLocator);// assuming عندك Utils بتعمل scroll
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
