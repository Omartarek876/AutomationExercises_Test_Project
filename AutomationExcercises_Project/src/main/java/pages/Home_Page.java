/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import utils.*;
import base.*;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    private By DeleteAccountLocator = By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(5) > a");
    
    
    
    
    public Home_Page(String Browser) {
        this.browser = Browser;
        BaseDriver.initializeDriver(browser);
    }   
    
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

    public void clickCategory(By CategoryLocator){
        ElementUtils.click(CategoryLocator);
    }
    
    public String LoggedAsText()
    {
        return ElementUtils.getText(loggedAsLocator);
    }
    
    public void DeleteAccount()
    {
        ElementUtils.click(DeleteAccountLocator);
    }
    
    
    
    
    
    
   

    
}
