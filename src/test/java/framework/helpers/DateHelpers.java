package framework.helpers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelpers {
    /**
     * @param format - see https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
     * @return - now, reformatted so that can be used as a file name
     */
    public static String formattedNow(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }

    /**
     * With thanks to https://stackoverflow.com/users/1731824/lucasls
     * at https://stackoverflow.com/questions/3471397/how-can-i-pretty-print-a-duration-in-java
     *
     * @param duration - the Duration object to make more pretty
     * @return - pretty-printed string
     */
    public static String humanReadableDuration(Duration duration) {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ".toLowerCase())
                .toLowerCase();
    }

    // --Commented out by Inspection START (11/10/2020 20:30):
    //    /**
    //     * @return - now as a date-time stamp to fractions of a second
    //     */
    //    public static String uniqueFileName() {
    //        return formattedNow("yyyy-MM-dd_kkmmss_SSS");
    //    }
    // --Commented out by Inspection STOP (11/10/2020 20:30)
}
