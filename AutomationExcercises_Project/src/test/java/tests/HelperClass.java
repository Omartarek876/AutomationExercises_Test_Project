/*
     Useful commands for running tests with Maven and Allure:
     
     mvn clean test                  => Run all tests from scratch
     allure serve target/allure-results  => Serve Allure report locally

*/

package tests;

// import models.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Data model for account creation users
 */
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

/**
 * Data model for login users
 */
class LoginUsers {
    public String LoginEmail;
    public String LoginPassword;
}

/**
 * Data model for contact us form submissions
 */
class ContactUsUsers {
    public String ContactUsFormName;
    public String ContactUsFormEmail;
    public String ContactUsFormSubject;
    public String ContactUsFormMsg;
    public String ContactUsFormFilePath;
}

/**
 * Data model for payment details
 */
class paymentData {
    public String nameOnCard;
    public String cardNumber;
    public String cvc;
    public String expiryMonth;
    public String expiryYear;
}

/**
 * Data model for product reviews
 */
class reviewsData {
    public String reviewerName;
    public String reviewerEmail;
    public String reviewText;
}

/**
 * HelperClass
 * 
 * Utility class for reading test data files (JSON) and mapping them 
 * into Java objects using Gson. 
 * Provides reusable methods for loading different test datasets.
 */
public class HelperClass {
    // Paths to testing resources
    private static final String TestPrjRoot = "src/test/resources/";
    private static final String TestDataFolder = "TestingData/";
    
    /**
     * Read a single string value from a JSON file based on the provided key.
     * 
     * @param fileName JSON file name
     * @param Key Key inside the JSON to retrieve value
     * @return String value
     * @throws FileNotFoundException if file not found
     */
    public static String ReadFromFile(String fileName, String Key) throws FileNotFoundException {
        FileReader reader = new FileReader(TestPrjRoot + TestDataFolder + fileName);
        JsonElement e1 = JsonParser.parseReader(reader);
        return e1.getAsJsonObject().get(Key).getAsString();
    }

    /**
     * Read array of CreateAccountUsers from JSON file
     * 
     * @param fileName JSON file name
     * @return Array of CreateAccountUsers
     * @throws FileNotFoundException if file not found
     */
    public static CreateAccountUsers[] ReadCreateAccountUsers(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TestPrjRoot + TestDataFolder + fileName);
        CreateAccountUsers[] ListOfCredentials = new Gson().fromJson(reader, CreateAccountUsers[].class);
        return ListOfCredentials;
    }
    
    /**
     * Read array of LoginUsers from JSON file
     * 
     * @param fileName JSON file name
     * @return Array of LoginUsers
     * @throws FileNotFoundException if file not found
     */
    public static LoginUsers[] ReadLoginUsers(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TestPrjRoot + TestDataFolder + fileName);
        LoginUsers[] ListOfCredentials = new Gson().fromJson(reader, LoginUsers[].class);
        return ListOfCredentials;
    }
    
    /**
     * Read array of ContactUsUsers from JSON file
     * 
     * @param fileName JSON file name
     * @return Array of ContactUsUsers
     * @throws FileNotFoundException if file not found
     */
    public static ContactUsUsers[] ReadContactUsForms(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TestPrjRoot + TestDataFolder + fileName);
        ContactUsUsers[] ListOfCredentials = new Gson().fromJson(reader, ContactUsUsers[].class);
        return ListOfCredentials;
    }
    
    /**
     * Read array of paymentData from JSON file
     * 
     * @param fileName JSON file name
     * @return Array of paymentData
     * @throws FileNotFoundException if file not found
     */
    public static paymentData[] ReadpaymentData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TestPrjRoot + TestDataFolder + fileName);
        paymentData[] ListOfCredentials = new Gson().fromJson(reader, paymentData[].class);
        return ListOfCredentials;
    }
    
    /**
     * Read array of reviewsData from JSON file
     * 
     * @param fileName JSON file name
     * @return Array of reviewsData
     * @throws FileNotFoundException if file not found
     */
    public static reviewsData[] ReadReviewData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TestPrjRoot + TestDataFolder + fileName);
        reviewsData[] ListOfCredentials = new Gson().fromJson(reader, reviewsData[].class);
        return ListOfCredentials;
    }
}
