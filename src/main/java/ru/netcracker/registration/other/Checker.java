package ru.netcracker.registration.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Вспомогательный класс для проверки regexp
 */
public class Checker {
    public static boolean checkWithRegExp(String theString, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(theString);
        return m.matches();
    }
}
