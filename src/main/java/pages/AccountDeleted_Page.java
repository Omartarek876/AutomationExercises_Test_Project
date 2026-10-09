/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import org.openqa.selenium.By;
import utils.ElementUtils;

/**
 *
 * @author Omar Tarek
 */
public class AccountDeleted_Page {
          
    private By continueButton = By.xpath("//a[contains(text(),'Continue')]");

    public void clickContinue() {
        ElementUtils.click(continueButton);
    }

}
