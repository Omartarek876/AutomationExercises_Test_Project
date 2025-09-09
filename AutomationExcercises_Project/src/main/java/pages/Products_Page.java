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

    public void clickSearchButton() {
        ElementUtils.click(searchButtonLocator);
    }
    
    public void searchProduct(String productName)
    {
        clickSearchButton();
        KeyboardUtils.sendKeys(searchBarLocator, productName);
    }
}
