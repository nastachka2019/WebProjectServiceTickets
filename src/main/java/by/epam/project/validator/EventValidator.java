package by.epam.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, реализующий проверку данных при поиске мероприятий (searchcommand)
 *
 * @author Shpakova A.
 */

public class EventValidator {
    private static final String REGEX_SEARCH_LINE = "^.{0,100}$";
    private static final String REGEX_EVENT_NAME = "^(\\p{IsAlphabetic}+){1,50}$";
    private static final String REGEX_EVENT_ADDRESS = "^(\\p{IsAlphabetic}+){1,50}$";


    private static final Pattern patternSearchLine = Pattern.compile(REGEX_SEARCH_LINE);
    private static final Pattern patternEventName = Pattern.compile(REGEX_EVENT_NAME);
    private static final Pattern patternEventAddress = Pattern.compile(REGEX_EVENT_ADDRESS);


    public boolean validateData(String searchLine) {
        Matcher searchLineMatcher = patternSearchLine.matcher(searchLine);
        return searchLineMatcher.matches();
    }

    public boolean validateData(String searchLine, String name, String address) {

        Matcher searchLineMatcher = patternSearchLine.matcher(searchLine);
        Matcher eventNameMatcher = patternEventName.matcher(name);
        Matcher eventAddressMatcher = patternEventAddress.matcher(address);
        return searchLineMatcher.matches()
                && eventAddressMatcher.matches()
                && eventNameMatcher.matches();
    }
}
