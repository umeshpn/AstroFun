package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements planet Mercury.
 ** @see HeavenlyBody
 ** @see Planet
 ** @author Umesh Nair, May 1999
 **/
public class Mercury extends Planet {
    /**
     ** Constructor
     **/
    public Mercury(AstroCalendar instance) {
        super(instance);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public Angle getLongitudeAtEpoch() {
        return new Angle(60.750646);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getPeriod() {
        return 0.240852;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public double getSemiMajorAxis() {
        return 0.387099;
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.blue);
        int d = AstroFrame.getPlanetSize();
        int x1 = x - d/2;
        int y1 = y - d/2;
        g.drawOval(x1, y1, d, d);
        g.fillOval(x1, y1, d, d);
        g.setColor(Color.white);
        g.drawString("m", x,y);
    }

    /**
     ** Overrides the abstract method of <B>Planet</B>.
     **/
    public String name() {
        return "Mercury";
    }
}
