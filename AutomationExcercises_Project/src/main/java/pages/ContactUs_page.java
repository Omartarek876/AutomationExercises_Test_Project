/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.ActionsUtils;
import utils.AlertUtils;
import utils.ElementUtils;
import utils.KeyboardUtils;

/**
 *
 * @author Omar Tarek
 */
public class ContactUs_page {
    
    private By FormNameLocator = By.xpath("//*[@id=\"contact-us-form\"]/div[1]/input");
    private By FormEmailLocator = By.xpath("//*[@id=\"contact-us-form\"]/div[2]/input");
    private By FormSubjectLocator = By.xpath("//*[@id=\"contact-us-form\"]/div[3]/input");
    private By FormMsgLocator = By.xpath("//*[@id=\"message\"]");
    private By HeaderLocator = By.xpath("//*[@id=\"contact-page\"]/div[2]/div[1]/div/h2");
    private By SubmitButtonLocator = By.xpath("//*[@id=\"contact-us-form\"]/div[6]/input");
    private By UploadFileLocator = By.xpath("//*[@id=\"contact-us-form\"]/div[5]/input");
    private By SubmittedFormLocator = By.xpath("//*[@id=\"contact-page\"]/div[2]/div[1]/div/div[2]");
    private By goHomeButtonLocator = By.cssSelector("a.btn.btn-success");
    
    
    public void entername(String name)
    {
        KeyboardUtils.sendKeys(FormNameLocator, name);
    }
        
    public void enterEmail(String email)
    {
        KeyboardUtils.sendKeys(FormEmailLocator, email);
    }

    public void enterSubject(String subject)
    {
        KeyboardUtils.sendKeys(FormSubjectLocator, subject);
    }
    
    public void enterMsg (String msg)
    {
        KeyboardUtils.sendKeys(FormMsgLocator, msg);
    }

    public void clickSubmitButton()
    {
        ElementUtils.click(SubmitButtonLocator);
    }
    
    public void uploadFile (String path)
    {
        ActionsUtils.uploadFile(UploadFileLocator, path);
    }

    public void FillContactUsForm(String name , String email, String subject , String msg , String path)
    {
        entername(name);
        enterEmail(email);
        enterSubject(subject);
        enterMsg(msg);
        uploadFile(path);
        clickSubmitButton();
        AlertUtils.acceptAlert();
    }
    
    public String ContactUsHeader() {
         return ElementUtils.getText(HeaderLocator);
    } 
    
    public String SubmittedFormMsg ()
    {
        return ElementUtils.getText(SubmittedFormLocator);
    }
    
    public void ClickHomeButton()
    {
        ElementUtils.click(goHomeButtonLocator);
    }
}
