package net.usvishakh.astrofun;

import java.text.*;
import java.util.*;

/**
 ** Abstract a Date/Time and timezone.
 ** @see DateFormat
 ** @see SimpleDateFormat
 ** @author Umesh Nair, May 1999
 **/
public class GeneralDateFormat extends SimpleDateFormat {
    /**
     ** Constructor
     ** @param id A <B>String</B> object naming the
     **  Time Zone.
     **/
    public GeneralDateFormat(String id) {
        this.setTimeZone(TimeZone.getTimeZone(id));
        String pattern =
                new String("EEEE, G dd-MMM-yyyy hh:mm:ss a ");
        pattern = pattern  + "(zzzz)";
        this.applyPattern(pattern);
    }

    /**
     ** Constructor
     ** @param tz A <B>TimeZone</B> object denoting
     **  Time Zone.
     **/
    public GeneralDateFormat(TimeZone tz) {
        this.setTimeZone(tz);
        String pattern =
                new String("EEEE, G dd-MMM-yyyy hh:mm:ss a ");
        pattern = pattern  + "(zzzz)";
        this.applyPattern(pattern);
    }
}

