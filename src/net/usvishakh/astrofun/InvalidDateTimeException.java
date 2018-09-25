package net.usvishakh.astrofun;

/**
 ** Class to define the exception thrown when an Invalid
 ** Date/Time is assigned to an <B>AstroCalendar</B> object.
 ** @author Umesh Nair, May 1999.
 **/
public class InvalidDateTimeException
        extends IllegalArgumentException {

    /**
     ** Constructor
     **/
    public InvalidDateTimeException () {
        super();
    }

    /**
     ** Constructor
     **/
    public InvalidDateTimeException (String s) {
        super(s);
    }
}

