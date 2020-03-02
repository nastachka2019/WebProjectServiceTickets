package by.epam.project.validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, реализующий проверку данных при поиске мероприятий (searchcommand)
 *
 * @author Shpakova A.
 */

public class EventValidator {
    private static final String SEARCH_LINE = "^.{0,100}$";
    private static final String EVENT_NAME = "^(\\p{IsAlphabetic}+){1,50}$";
    private static final String EVENT_ADDRESS = "^(\\p{IsAlphabetic}+){1,50}$";
    private static final String PRICE = "^(([1-9]\\d{0,5})|(0))$";

    private static final Pattern patternSearchLine = Pattern.compile(SEARCH_LINE);
//    private static final Pattern patternEventName = Pattern.compile(EVENT_NAME);
//    private static final Pattern patternEventAddress = Pattern.compile(EVENT_ADDRESS);
    private static final Pattern patternPrice = Pattern.compile(PRICE);

    public boolean validateData(String searchLine) {
        Matcher searchLineMatcher = patternSearchLine.matcher(searchLine);
        return searchLineMatcher.matches();
    }

    public boolean validateData(String searchLine, String maxPrice, String minPrice) {
        Matcher searchLineMatcher = patternSearchLine.matcher(searchLine);
//        Matcher eventNameMatcher = patternEventName.matcher(name);
//        Matcher eventAddressMatcher = patternEventAddress.matcher(address);
        Matcher minPriceMatcher = patternPrice.matcher(minPrice);
        Matcher maxPriceMatcher = patternPrice.matcher(maxPrice);
        return searchLineMatcher.matches()
//                && eventAddressMatcher.matches()
//                && eventNameMatcher.matches()
                && minPriceMatcher.matches()
                && maxPriceMatcher.matches();
    }
}
