package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements planet Jupitor.
 ** @see HeavenlyBody
 ** @see Planet
 ** @author Umesh Nair, May 1999
 **/
public class Jupiter extends Planet {

    /**
     ** Constructor
     **/
    public Jupiter(AstroCalendar instance) {
        super(instance);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public Angle getLongitudeAtEpoch() {
        return new Angle(90.638185);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getPeriod() {
        return 11.863075;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getSemiMajorAxis() {
        return 5.202561;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.magenta);
        int d = AstroFrame.getPlanetSize();
        int x1 = x - d/2;
        int y1 = y - d/2;
        g.drawOval(x1, y1, d, d);
        g.fillOval(x1, y1, d, d);
        g.setColor(Color.white);
        g.drawString("J", x,y);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public String name() {
        return "Jupiter";
    }
}
