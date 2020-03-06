package by.epam.project.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class that implements user data validation
 *
 * @author Shpakova A.
 */
public class UserValidator {
    private Map<String, String> errorMessages = new HashMap<>();

    private static final String ALPHABETIC_STRING = "^(\\p{IsAlphabetic}+){1,50}$";
    private static final String LOGIN = "^[\\w][.\\w]{4,48}[\\w]$";  //без знаков пунктуации, _ - можно
    private static final String PASSWORD = "(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z]).{5,16}"; //обязательно цифры и буква/ы в верхн. регистре
    private static final String PHONE = "[0-9]{12}";

    public Map<String, String> validateData(String name, String surname, String login, String phone, String password, String confirmPassword) {
        errorMessages = validateData(name, surname, login, phone);
        Pattern regexPassword = Pattern.compile(PASSWORD);
        Matcher matcherPassword = regexPassword.matcher(password);
        Matcher matcherConfirmedPassword = regexPassword.matcher(confirmPassword);
        if (!matcherPassword.matches() || !matcherConfirmedPassword.matches()) {
            errorMessages.put("errorPassword", "Password does not match the requirements");
        }
        return errorMessages;
    }


    public boolean validatePass(String password) {
        Pattern regexPassword = Pattern.compile(PASSWORD);
        Matcher matcherPassword = regexPassword.matcher(password);
        return matcherPassword.matches();
    }

    public Map<String, String> validateData(String name, String surname, String login, String phone) {

        Pattern regexAlphabeticString = Pattern.compile(ALPHABETIC_STRING);
        Pattern regexLogin = Pattern.compile(LOGIN);
        Pattern regexPhone = Pattern.compile(PHONE);

        Matcher matcherName = regexAlphabeticString.matcher(name);
        Matcher matcherSurname = regexAlphabeticString.matcher(surname);
        Matcher matcherLogin = regexLogin.matcher(login);
        Matcher matcherPhone = regexPhone.matcher(phone);

        if (!matcherName.matches()) {
            errorMessages.put("errorName", "Your name should be from letters only");
        }

        if (!matcherSurname.matches()) {
            errorMessages.put("errorSurname", "Your surname should be from letters only");
        }

        if (!matcherLogin.matches()) {
            errorMessages.put("errorLogin", "Login does not match the requirements");
        }

        if (!matcherPhone.matches()) {
            errorMessages.put("errorPhone", "Phone number does not match the requirements");
        }
        return errorMessages;
    }

}


