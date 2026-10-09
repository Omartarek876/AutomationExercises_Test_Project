/**
 * Products Page Object Class
 *
 * This class represents the Products Page in the Automation Exercise application.
 * It encapsulates product search, product details, cart operations,
 * review submission, and recommended items functionality.
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */

package pages;

import java.util.ArrayList;
import java.util.List;
import utils.*;
import org.openqa.selenium.By;

public class Products_Page {

    // ---------------- Locators ----------------

    // Search
    private final By searchBarLocator          = By.id("search_product");
    private final By searchButtonLocator       = By.id("submit_search");
    private final By searchedProductsHeader    = By.cssSelector("h2.title.text-center");
    private final By allSearchResultsLocator   = By.cssSelector(".product-image-wrapper");

    // Product details (on Product Details page)
    private final By productNameLocator        = By.cssSelector("div.product-information h2");
    private final By productCategoryLocator    = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    private final By productPriceLocator       = By.cssSelector("div.product-information span span");
    private final By productAvailabilityLocator= By.xpath("//div[@class='product-information']/p[contains(normalize-space(.),'Availability')]");
    private final By productConditionLocator   = By.xpath("//div[@class='product-information']/p[contains(normalize-space(.),'Condition')]");
    private final By productBrandLocator       = By.xpath("//div[@class='product-information']/p[contains(normalize-space(.),'Brand')]");

    // Cart flow
    private final By quantityLocator           = By.id("quantity");
    private final By addToCartButtonLocator    = By.cssSelector("button.btn.btn-default.cart");
    private final By continueButtonLocator     = By.cssSelector("button.close-modal, #cartModal button[data-dismiss='modal']");
    private final By cartLinkLocator           = By.xpath("//a[contains(text(),'Cart')]");
    private final By deleteFromCartLocator     = By.cssSelector("#product-1 td.cart_delete a");
    private final By emptyCartMsgLocator       = By.cssSelector("#empty_cart p");

    // Reviews
    private final By reviewSectionHeader       = By.xpath("//a[normalize-space()='Write Your Review']");
    private final By reviewNameField           = By.id("name");
    private final By reviewEmailField          = By.id("email");
    private final By reviewTextArea            = By.id("review");
    private final By reviewSubmitButton        = By.id("button-review");
    private final By reviewSuccessMessage      = By.xpath("//*[contains(text(),'Thank you for your review.')]");

    // Recommended items
    private final By subscriptionArrowLocator  = By.id("subscribe");
    private final By recommendedItemsSection   = By.xpath("//h2[normalize-space()='recommended items']");


    // ---------------- Methods ----------------

    /**
     * Search for a product by name.
     *
     * @param productName name of the product to search
     */
    public void searchProduct(String productName) {
        KeyboardUtils.sendKeys(searchBarLocator, productName);
        ElementUtils.click(searchButtonLocator);
    }

    /**
     * Get the header text of searched products section.
     *
     * @return header text
     */
    public String getSearchedProductsHeader() {
        return ElementUtils.getText(searchedProductsHeader);
    }

    /**
     * Open product details page dynamically by productId.
     *
     * @param productId unique identifier of the product
     */
    public void viewProduct(String productId) {
        By productDetailsLocator = By.cssSelector("a[href='/product_details/" + productId + "']");
        ActionsUtils.scrollToElement(productDetailsLocator);
        ElementUtils.click(productDetailsLocator);
    }

    // ---------- Product Details ----------

    /** @return product name */
    public String getProductName() { return ElementUtils.getText(productNameLocator); }

    /** @return product category */
    public String getProductCategory() { return ElementUtils.getText(productCategoryLocator); }

    /** @return product price */
    public String getProductPrice() { return ElementUtils.getText(productPriceLocator); }

    /** @return product availability status */
    public String getProductAvailability() { return ElementUtils.getText(productAvailabilityLocator); }

    /** @return product condition */
    public String getProductCondition() { return ElementUtils.getText(productConditionLocator); }

    /** @return product brand */
    public String getProductBrand() { return ElementUtils.getText(productBrandLocator); }

    // ---------- Cart Flow ----------

    /**
     * Enter quantity before adding to cart.
     *
     * @param quantity number of items
     */
    public void enterQuantity(String quantity) {
        KeyboardUtils.clearAndSendKeys(quantityLocator, quantity);
    }

    /** Add product to cart and close popup */
    public void addToCart() {
        ElementUtils.click(addToCartButtonLocator);
        ElementUtils.click(continueButtonLocator);
    }

    /** Navigate to cart page */
    public void clickCart() {
        ElementUtils.click(cartLinkLocator);
    }

    /** Delete product from cart */
    public void deleteFromCart() {
        ElementUtils.click(deleteFromCartLocator);
    }

    /**
     * Get the empty cart message.
     *
     * @return empty cart message text
     */
    public String getEmptyCartMessage() {
        return ElementUtils.getText(emptyCartMsgLocator);
    }

    // ---------- Reviews ----------

    /**
     * Get review section header text.
     *
     * @return review header text
     */
    public String getReviewSectionHeader() {
        ActionsUtils.scrollToElement(reviewSubmitButton);
        return ElementUtils.getText(reviewSectionHeader);
    }

    /**
     * Fill in and submit a product review.
     *
     * @param name       reviewer name
     * @param email      reviewer email
     * @param reviewText review content
     */
    public void submitProductReview(String name, String email, String reviewText) {
        KeyboardUtils.sendKeys(reviewNameField, name);
        KeyboardUtils.sendKeys(reviewEmailField, email);
        KeyboardUtils.sendKeys(reviewTextArea, reviewText);
        ElementUtils.click(reviewSubmitButton);
    }

    /**
     * Get success message after submitting review.
     *
     * @return review success message
     */
    public String getReviewSuccessMessage() {
        return ElementUtils.getText(reviewSuccessMessage);
    }

    // ---------- Bulk Add Products ----------

    /** Stores names of added products */
    public List<String> addedDesiredProductNames = new ArrayList<>();

    /**
     * Add multiple products to cart by product IDs.
     *
     * @param productIds array of product IDs
     * @return list of added product names
     */
    public List<String> addProductsToCartByIds(String[] productIds) {
        for (String productId : productIds) {
            try {
                By currentProductName = By.xpath("//a[@data-product-id='" + productId + "']/ancestor::div[@class='productinfo text-center']//p");
                By currentProductAddBtn = By.cssSelector("a[data-product-id='" + productId + "']");

                ActionsUtils.scrollToElement(currentProductName);
                String productName = ElementUtils.getText(currentProductName);
                addedDesiredProductNames.add(productName);

                ActionsUtils.scrollToElement(currentProductAddBtn);
                ElementUtils.click(currentProductAddBtn);

                try { ElementUtils.click(continueButtonLocator); }
                catch (Exception ignore) { System.out.println("No popup for productId: " + productId); }

                System.out.println("Added to cart: " + productName);

            } catch (Exception e) {
                System.out.println("Skipping productId " + productId + " due to " + e.getMessage());
            }
        }
        return addedDesiredProductNames;
    }

    // ---------- Recommended Items ----------

    /** Scroll to bottom of the page */
    public void scrollToBottom() {
        ActionsUtils.scrollToElement(subscriptionArrowLocator);
    }

    /**
     * Get the title of recommended items section.
     *
     * @return recommended items section title
     */
    public String getRecommendedItemsTitle() {
        return ElementUtils.getText(recommendedItemsSection);
    }

    /**
     * Hover over a product and add it to cart.
     *
     * @param productId product ID to add
     */
    public void hoverProductAndAddToCart(String productId) {
        By addToCartLocator = By.cssSelector("a[data-product-id='" + productId + "']");
        ActionsUtils.hoverAndClick(addToCartLocator);
        ElementUtils.click(continueButtonLocator);
    }

    /** Stores names of added recommended products */
    public List<String> addedRecommendedProductNames = new ArrayList<>();

    /**
     * Hover and add multiple recommended products by product IDs.
     *
     * @param productIds list of product IDs
     * @return list of added recommended product names
     */
    public List<String> hoverAndAddMultipleRecommendedProducts(List<String> productIds) {
        for (String productId : productIds) {
            try {
                By recommendedAddToCartLocator = By.cssSelector("#recommended-item-carousel a[data-product-id='" + productId + "']");
                By productNameLocator = By.xpath("//div[@id='recommended-item-carousel']//a[@data-product-id='" + productId + "']/ancestor::div[@class='productinfo text-center']/p");

                ActionsUtils.scrollToElement(recommendedAddToCartLocator);
                ActionsUtils.hoverAndClick(recommendedAddToCartLocator);

                try { ElementUtils.click(continueButtonLocator); }
                catch (Exception ignore) { System.out.println("No popup for productId: " + productId); }

                String productName = ElementUtils.getText(productNameLocator);
                addedRecommendedProductNames.add(productName);

                System.out.println("Added Recommended Product: " + productName);

            } catch (Exception e) {
                System.out.println("Skipping recommended productId " + productId + " due to " + e.getMessage());
            }
        }
        return addedRecommendedProductNames;
    }
}
