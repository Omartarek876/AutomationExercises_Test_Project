/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import utils.*;
import org.openqa.selenium.By;

/**
 *
 * @author Omar Tarek
 */
public class Products_Page {
    
    private By searchBarLocator = By.id("search_product");
    private By searchButtonLocator = By.id("submit_search");
    private By FirstProductDetailsLocator = By.cssSelector("a[href='/product_details/1']");
    private By ForthProductDetailsLocator = By.cssSelector("a[href='/product_details/4']");
   //name, category, price, availability, condition, brand
    private By productNameLocator = By.xpath("//div[@class='product-information']/h2");
    private By productCategoryLocator = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    private By productPriceLocator = By.xpath("//div[@class='product-information']//span/span");
    private By productAvailabilityLocator = By.xpath("//div[@class='product-information']//p[contains(normalize-space(.),'Availability')]");
    private By productConditionLocator = By.xpath("//div[@class='product-information']//p[contains(normalize-space(.),'Condition')]");
    private By productBrandLocator = By.xpath("//div[@class='product-information']//p[contains(normalize-space(.),'Brand')]");
    private By searchedProductsLocator = By.cssSelector("h2.title.text-center");
    
    public void clickSearchButton() {
        ElementUtils.click(searchButtonLocator);
    }
    
    public void searchProduct(String productName)
    {
        KeyboardUtils.sendKeys(searchBarLocator, productName);
        clickSearchButton();
    }
    
    public void clickOnFirstProduct(){
        ActionsUtils.scrollToElement(ForthProductDetailsLocator);
        ElementUtils.click(FirstProductDetailsLocator);
    }

    public String getProductName(){
        return ElementUtils.getText(productNameLocator);
    } 
    
    public String getProductCategory(){
        return ElementUtils.getText(productCategoryLocator);
    }

    public String getProductPrice(){
        return ElementUtils.getText(productPriceLocator);
    }

    public String getProductAvailability(){
        return ElementUtils.getText(productAvailabilityLocator);
    }

    public String getProductCondition(){
        return ElementUtils.getText(productConditionLocator);
    }

    public String getProductBrand(){
        return ElementUtils.getText(productBrandLocator);
    }
    
    public String searchedProductsHeader(){
        return ElementUtils.getText(searchedProductsLocator);
    }
    


}
