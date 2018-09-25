package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements a Graphical View of the Solar System.
 ** @see SolarSystemView
 ** @see TableView
 ** @author Umesh Nair, May 1999
 **/
public class GraphicalView extends SolarSystemView {
    /**
     ** Constructor
     **/
    public GraphicalView(int x, int y, int viewWidth, int viewHeight) {
        super(x, y, viewWidth, viewHeight, "Sun-centered View");
        setCenterPlanet(null);
    }

    /**
     ** Overrides Container.paint()
     **/
    public void paint (Graphics g) {
        try {
            super.paint(g);
            String titleString = "Graphical View - ";
            if (getCenterPlanet() == null) {
                titleString += getSolarSystem().getSun().name();
            } else {
                titleString += getCenterPlanet().name();
            }
            titleString += "-centered";
            setTitle(titleString);
            int cover = 50;
            int i;
            int xp, yp;
            SolarSystem ss = getSolarSystem();
            if (ss != null) {
                int x = getSize().width / 2;
                int y = getSize().height / 2;
                int dist = (x < y ? x : y) - cover;

                // Draw the zodiac outline
                g.drawOval(x - dist, y - dist, 2 * dist, 2 * dist);
                Angle alpha = new Angle(0.0);
                for (i=0; i < 12 ; ++i) {
                    xp = x + (int)(dist * alpha.cos()+0.5);
                    yp = y + (int)(dist * alpha.sin()+0.5);
                    g.drawLine(x, y, xp, yp);
                    alpha = Angle.sumOf(alpha, 30.0);
                }
                Planet cp = getCenterPlanet();
                Sun sun = ss.getSun();
                if (cp == null) {   // Sun-centric
                    sun.display(g, x, y);
                } else {
                    Angle pLong = sun.getLongitudeAtPlanet(cp);
                    xp = x + (int)(dist * pLong.cos() + 0.5);
                    yp = y + (int)(dist * pLong.sin() + 0.5);
                    sun.display(g, xp, yp);
                }
                for (i=0;i < ss.getNumberOfPlanets();++i) {
                    Planet p = ss.getPlanet(i);
                    Angle pLong;
                    if (cp == null) {
                        pLong = p.getHelioCentricLongitude();
                        xp = x + (int)(dist * pLong.cos() + 0.5);
                        yp = y + (int)(dist * pLong.sin() + 0.5);
                        p.display(g, xp, yp);
                    } else if (p == cp) {  // Put this planet at the center
                        p.display(g, x, y);
                    } else {
                        pLong = p.getLongitudeWithRespectTo(cp);
                        xp = x + (int)(dist * pLong.cos() + 0.5);
                        yp = y + (int)(dist * pLong.sin() + 0.5);
                        p.display(g, xp, yp);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}

