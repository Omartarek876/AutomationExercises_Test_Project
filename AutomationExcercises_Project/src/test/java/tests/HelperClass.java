/*
     mvn clean test

     allure serve target/allure-results

     mvn allure:report => for the first time 

    do not forget to add taking screenshots of any testcase failure
    */

package tests;

// import models.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

class CreateAccountUsers {
    public String SignUpName;
    public String SignUpEmail;
    public String gender;
    public String password;
    public String day;
    public String month;
    public String year;
    public String firstName;
    public String lastName;
    public String company;
    public String address1;
    public String address2;
    public String country;
    public String state;
    public String city;
    public String zipcode;
    public String mobileNumber;
}

class LoginUsers {
    public String LoginEmail;
    public String LoginPassword;
}

class ContactUsUsers {
    public String ContactUsFormName;
    public String ContactUsFormEmail;
    public String ContactUsFormSubject;
    public String ContactUsFormMsg;
    public String ContactUsFormFilePath;
}


class paymentData 
{
    public String nameOnCard;
    public String cardNumber;
    public String cvc;
    public String expiryMonth;
    public String expiryYear;
}



public class HelperClass {
    private static final String TestPrjRoot = "src/test/resources/";
    private static final String TestDataFolder = "TestingData/";
    
    public static String ReadFromFile(String fileName, String Key) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        JsonElement e1 = JsonParser.parseReader(reader);
        return e1.getAsJsonObject().get(Key).getAsString();
    }

    /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static CreateAccountUsers[] ReadCreateAccountUsers (String fileName) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        CreateAccountUsers[] ListOfCredentials = new Gson().fromJson(reader, CreateAccountUsers[].class);
        return ListOfCredentials;
    }
    
        /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static LoginUsers[] ReadLoginUsers (String fileName) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        LoginUsers[] ListOfCredentials = new Gson().fromJson(reader, LoginUsers[].class);
        return ListOfCredentials;
    }
    
            /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static ContactUsUsers[] ReadContactUsForms (String fileName) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        ContactUsUsers[] ListOfCredentials = new Gson().fromJson(reader, ContactUsUsers[].class);
        return ListOfCredentials;
    }
    
    
                /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static paymentData[] ReadpaymentData (String fileName) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        paymentData[] ListOfCredentials = new Gson().fromJson(reader, paymentData[].class);
        return ListOfCredentials;
    }
}
