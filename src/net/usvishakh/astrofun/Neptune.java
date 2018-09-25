package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements planet Neptune.
 ** @see HeavenlyBody
 ** @see Planet
 ** @author Umesh Nair, May 1999
 **/
public class Neptune extends Planet {
    /**
     ** Constructor
     **/
    public Neptune(AstroCalendar instance) {
        super(instance);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public Angle getLongitudeAtEpoch() {
        return new Angle(282.349556);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getPeriod() {
        return 164.79246;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getSemiMajorAxis() {
        return 30.109570;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.gray);
        int d = AstroFrame.getPlanetSize();
        int x1 = x - d/2;
        int y1 = y - d/2;
        g.drawOval(x1, y1, d, d);
        g.fillOval(x1, y1, d, d);
        g.setColor(Color.white);
        g.drawString("N", x,y);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public String name() {
        return "Neptune";
    }
}
