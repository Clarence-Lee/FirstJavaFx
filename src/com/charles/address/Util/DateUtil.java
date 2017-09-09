package com.charles.address.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by Administrator on 2017/9/9.
 * Helper functions for handing dates.
 */
public class DateUtil {
    //The date pattern that is used for convector
    private static final String DATE_PATTERN = "dd.MM.yyy";

    //The date formatter
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /*
    Return the given date as the Formatter String.
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /*
    Convert a String in the format of the defined
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        }catch (DateTimeParseException e) {
            return null;
        }
    }

    /*
    Check the String whether it is a valid date
     */
    public static boolean validDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}
