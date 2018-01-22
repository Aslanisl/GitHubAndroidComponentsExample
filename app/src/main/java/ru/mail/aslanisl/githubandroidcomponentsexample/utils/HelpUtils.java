package ru.mail.aslanisl.githubandroidcomponentsexample.utils;

/**
 * Created by Ivan on 22.01.2018.
 */

public class HelpUtils {

    public static boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        if (o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }
}
