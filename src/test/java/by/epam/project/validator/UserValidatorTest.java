package by.epam.project.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class UserValidatorTest {
    private final String ALPHABETIC_STRING = "^(\\p{IsAlphabetic}+){1,50}$";
    private final String PASSWORD = "(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z]).{5,16}";
    private final String LOGIN = "^[\\w][.\\w]{4,48}[\\w]$";

    private Pattern passwordTest = Pattern.compile(PASSWORD);
    private Pattern loginTest = Pattern.compile(LOGIN);
    private Pattern alphabeticStringTest = Pattern.compile(ALPHABETIC_STRING);


    @DataProvider
    public Object[][] testPassword() {
        return new Object[][]{
                {"shsh", false},
                {"fhg1Hfyd", true},
                {"rfrfrefref", false},
                {"Adgfjr1g2", true},

        };
    }

    @DataProvider
    public Object[][] testLogin() {
        return new Object[][]{
                {"hgjfhrjr", true},
                {"-", false},
                {"hfhf_y", true},
                {".fder", false},

        };
    }

    @DataProvider
    public Object[][] testName() {
        return new Object[][]{
                {"Anastasia", true},
                {"dhfed", true},
                {"df1", false}

        };
    }


    @Test(dataProvider = "testPassword")
    public void validationPasswordTest(String password, boolean expectedResult) {

        Assert.assertEquals(passwordTest.matcher(password).matches(), expectedResult);

    }

    @Test(dataProvider = "testLogin")
    public void validationLoginTest(String login, boolean expectedResult) {

        Assert.assertEquals(loginTest.matcher(login).matches(), expectedResult);

    }

    @Test(dataProvider = "testName")
    public void validationNameTest(String name, boolean expectedResult) {
        Assert.assertEquals(alphabeticStringTest.matcher(name).matches(), expectedResult);

    }

}