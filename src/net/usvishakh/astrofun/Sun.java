package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements the Sun, center of solar system
 ** @see HeavenlyBody
 ** @see Planet
 ** @author Umesh Nair, may 1999
 **/
public class Sun extends Star {
    /**
     ** Constructs a Sun object for an instance
     **/
    public Sun(AstroCalendar instance) {
        super(instance);
    }

    /**
     ** Finds Sun's longitude with respect to a planet
     ** @param a <B>Planet</B> object with respect to which the
     ** Sun's longitude is to be determined
     ** @return The longitude expressed as an <B>Angle</B> object.
     **/
    public Angle getLongitudeAtPlanet(Planet p) {
        return Angle.sumOf(p.getHelioCentricLongitude(), 180.0);
    }

    /**
     ** Overrides HeavenlyBody.display()
     ** @see HeavenlyBody#display(Graphics,int,int)
     **/
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.red);
        int d = AstroFrame.getPlanetSize();
        int x1 = x - d/2;
        int y1 = y - d/2;
        g.drawOval(x1, y1, d, d);
        g.fillOval(x1, y1, d, d);
        g.setColor(Color.white);
        g.drawString("S", x,y);
    }

    /**
     ** Overrides HeavenlyBody.display()
     **/
    public String name() {
        return "Sun";
    }
}

