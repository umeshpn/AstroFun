package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Defines the abstract base class to represent planets
 ** stars and other astronomical objects.
 ** @see Planet
 ** @see Sun
 ** @author Umesh Nair, May 1999
 **/
public abstract class HeavenlyBody {
    private AstroCalendar instance;

    /**
     ** Constructs an object for an <B>AstroCalendar</B>
     ** object
     **/
    protected HeavenlyBody (AstroCalendar theInstance) {
        instance = theInstance;
    }

    /**
     ** Returns the <B>AstroCalendar</B> object associated
     ** with the HeavenlyBody
     **/
    public AstroCalendar getInstance() {
        return instance;
    }

    /**
     ** Abstract method to display the HeavenlyBody on
     ** a graphical devise.
     ** @see Mercury#display(Graphics g, int x, int y).
     ** @see Sun#display(Graphics g, int x, int y).
     **/
    public abstract void display(Graphics g, int x, int y);

    /**
     ** Abstract method returning the name of the
     ** HeavenlyBody.
     ** @see Mercury#name().
     ** @see Sun#name().
     **/
    public abstract String name();
}

