package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Abstract class representing a fixed star at a specified instance.
 ** This class does not override the abstract methods of HeavenlyBody
 ** class. The subclasses will.
 ** @see HeavenlyBody
 ** @see Planet
 ** @author Umesh Nair, May 1999
 **/
public abstract class Star extends HeavenlyBody {
    /**
     ** Constructor
     **/
    protected Star(AstroCalendar instance) {
        super(instance);
    }
}
