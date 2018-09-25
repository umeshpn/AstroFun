package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements planet Pluto.
 ** @see HeavenlyBody
 ** @see Planet
 ** @author Umesh Nair, May 1999
 **/
public class Pluto extends Planet {
    /**
     ** Constructor
     **/
    public Pluto(AstroCalendar instance) {
        super(instance);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public Angle getLongitudeAtEpoch() {
        return new Angle(221.4127);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getPeriod() {
        return 246.77027;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getSemiMajorAxis() {
        return 39.3414;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.darkGray);
        int d = AstroFrame.getPlanetSize();
        int x1 = x - d/2;
        int y1 = y - d/2;
        g.drawOval(x1, y1, d, d);
        g.fillOval(x1, y1, d, d);
        g.setColor(Color.white);
        g.drawString("P", x,y);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public String name() {
        return "Pluto";
    }
}
