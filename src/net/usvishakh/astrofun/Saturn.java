package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements planet Saturn.
 ** @see HeavenlyBody
 ** @see Planet
 ** @author Umesh Nair, May 1999
 **/
public class Saturn extends Planet {
    /**
     ** Constructor
     **/
    public Saturn(AstroCalendar instance) {
        super(instance);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public Angle getLongitudeAtEpoch() {
        return new Angle(287.690033);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getPeriod() {
        return 29.471362;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getSemiMajorAxis() {
        return 9.554747;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.pink);
        int d = AstroFrame.getPlanetSize();
        int x1 = x - d/2;
        int y1 = y - d/2;
        g.drawOval(x1, y1, d, d);
        g.fillOval(x1, y1, d, d);
        g.setColor(Color.white);
        g.drawString("s", x,y);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public String name() {
        return "Saturn";
    }
}
