package net.usvishakh.astrofun;

import java.util.*;
import java.text.*;
import java.awt.*;

/**
 * Stores a day/time combination with time zone.
 * Represents an instance in time.
 * provides various astronomical calculations.
 @author Umesh Nair, May 1999
 @see GeneralDateFormat
 **/
public class AstroCalendar extends GregorianCalendar {
    // Constants
    public static final double DAYS_IN_CENTURY = 36525.0;
    public static final double DAYS_IN_YEAR = 365.25 ;
    public static final double DAYS_IN_MONTH = 30.60001 ;
    public static final int MS_IN_S = 1000;
    public static final int MS_IN_M = MS_IN_S * 60;
    public static final int MS_IN_H = MS_IN_M * 60;
    public static final int MS_IN_D = MS_IN_H * 24;

    // epochs
    public static final int EPOCH_1900_PRE_NOON = 0;
    public static final int EPOCH_1960_LIB_MID = 1;
    public static final int EPOCH_1990_PRE_MID = 2;
    public static final int EPOCH_2000_1ST_NOON = 3;

    private static final double epochJulianDayNumbers [] =
            { 2415020.0, 2437200.5, 2447891.5, 2451545.0 };

    private DateFormat dateFormat;
    private TimeZone timeZone;
    private double julianDayNumber;
    private boolean isJDNCalculated;

    public static final double DAYS_PER_DEGREE = 365.25/360.0;

    /**
     * Default constructor.
     * Stores current system time with the time zone of
     * current location
     **/
    public AstroCalendar() {
        this.timeZone = TimeZone.getDefault();
        this.dateFormat = new GeneralDateFormat (timeZone.getID());
        isJDNCalculated = false;
    }

    /**
     * Constructs an <B>AstroCalendar</B> object from a
     * <B>DateFormat</B> object and the date/time
     **/
    public AstroCalendar(DateFormat df, int year, int month, int day, int hour,
                         int minute, int second) {
        this.dateFormat = df;
        this.timeZone = df.getTimeZone();
        this.setTimeZone(this.timeZone);
        if (AstroCalendar.isValid(year, month, day, hour,
                minute, second) == false)
            throw new InvalidDateTimeException();
        set(year, month, day, hour, minute, second);
        isJDNCalculated = false;
    }

    /**
     * Constructs an <B>AstroCalendar</B> object from a
     * <B>DateFormat</B> object and another <B>AstroCalendar</B>
     * object
     * Adjusts the time according to the new timezone.
     **/
    public AstroCalendar(DateFormat df, AstroCalendar ac) {
        this.dateFormat = df;
        this.timeZone = df.getTimeZone();
        this.setTimeZone(this.timeZone);
        this.setTime(ac.getTime());
        isJDNCalculated = false;
    }

    /**
     * Static method to check whether a particular date value
     * is valid
     @return true If the date is valid.
     @return false If the date is not valid.
     *
     **/
    public static boolean isValid(int year, int month,
                                  int day, int hour,
                                  int minute, int second) {
        if (year < 1 || year > 9999)
            return false;
        if (month < 0 || month > 11)
            return false;
        if (day < 1 || day > AstroCalendar.maxDays(year, month))
            return false;
        if (hour < 0 || hour > 23)
            return false;
        if (minute < 0 || minute > 59)
            return false;
        if (second < 0 || second > 59)
            return false;
        return true;
    }

    /**
     * Static method givingthe maximum days (i.e., the last
     * date) of a month
     @param y year (needed to check leap year)
     @param m month
     @return number of days in that month
     **/
    public static int maxDays(int y, int m) {
        return 30+(m+1+m/7)%2-
                ((m==1)?(((y%400==0)||((y%4 == 0)&&(y%100!=0)))?1:2):0);
    }

    /**
     * Constructs another <B>AstroCalendar</B> object representing
     * the GMT (Greenwich Standard Time) of the instance
     **/
    public AstroCalendar gmt() {
        return new AstroCalendar (new GeneralDateFormat ("GMT"),
                this);
    }

    /**
     * Returns the Julian day number of the instance
     **/
    public double getJulianDayNumber() {
        if (isJDNCalculated) {
            return this.julianDayNumber;
        } else {
            isJDNCalculated = true;
            int year = this.get(YEAR);
            int month = this.get(MONTH) + 1;
            int day = this.get(DAY_OF_MONTH);
            int hour = this.get(HOUR_OF_DAY);
            int minute = this.get(MINUTE);
            int second = this.get(SECOND);
            if (this.get(ERA) == BC) {
                year = 1 - year ;
            }
            if (month < 3) {
                --year;
                month += 12;
            }
            int century = year / 100;
            int quadCentury = (2 - century + century / 4);
            int jDay1 = (int)(DAYS_IN_YEAR * year);
            int jDay2 = (int)(DAYS_IN_MONTH * (month + 1));
            int jDay3 = jDay1 + jDay2 + day + quadCentury;
            julianDayNumber = (1720994.5 + jDay3
                    + (((hour * 60 + minute) * 60 + second ) * 1000.0
                    - this.getTimeZone().getRawOffset()) / 86400000 );
            return julianDayNumber;
        }
    }

    /**
     * Overrides Object.toString()
     @return a <B>String</B> object giving the full details
     of the instance
     **/
    public String toString() {
        String retString = new String(timeZone.getID());
        if (timeZone.inDaylightTime(this.getTime()) == true) {
            retString = retString + "(DS)";
        }
        retString = retString + " " ;
        retString = retString + dateFormat.format(this.getTime());
        return retString;
    }

    /**
     * Expresses the time as the total milliseconds since
     * an epoch
     @return the long number returned by the protected method
     Calendar.getTimeInMillis()
     **/
    public long getTotalMillies() {
        return getTimeInMillis();
    }

    /**
     * Returns the number of days elapsed from a given epoch
     @param a constant denoting a planet.
     @return number of days since the epoch as a floating point
     **/
    public double daysFromEpoch(int epoch) {
        return getJulianDayNumber() - epochJulianDayNumbers[epoch];
    }

    /**
     * Returns the number of Julian centuries elapsed from a
     * given epoch
     @param a constant indicating the planet.
     @return number of days since the epoch as a floating point
     **/
    public double julianCenturiesFromEpoch(int epoch) {
        return ((getJulianDayNumber() - epochJulianDayNumbers[epoch])
                / DAYS_IN_CENTURY);
    }
    public static double julianDayOfEpoch(int epoch) {
        return epochJulianDayNumbers[epoch];
    }
}

