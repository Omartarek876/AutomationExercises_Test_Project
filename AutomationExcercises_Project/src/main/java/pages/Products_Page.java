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
    
    public void hoverProductAndClick(String productId) {
        By addElementToCartLocator = By.cssSelector("a[data-product-id='" + productId + "']");
    //    By PostAddedElementToCartLocator = By.cssSelector("a[data-product-id='" + (productId+3) +"']");

    //    ActionsUtils.scrollToElement(PostAddedElementToCartLocator);
        ActionsUtils.hoverAndClick(addElementToCartLocator);
        ElementUtils.click(continueButtonLocator);
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



     
}
