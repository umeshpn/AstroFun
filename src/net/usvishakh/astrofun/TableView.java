package net.usvishakh.astrofun;

import javax.swing.*;
import java.awt.*;

/**
 ** Implements a view showing the Solar System in atabular format.
 ** @see SolarSystemView
 ** @see GraphicalView
 ** @author Umesh Nair, May 1999
 **/
public class TableView extends SolarSystemView {
    /**
     ** Constructor
     **/
    TableView(int x, int y, int viewWidth, int viewHeight) {
        super(x, y, viewWidth, viewHeight,"Tabular view");
        setCenterPlanet(null);
    }

    /**
     ** Overrides Container.paint()
     **/
    public void paint(Graphics g) {
        super.paint(g);
        String titleString = "Tabular View - ";
        if (getCenterPlanet() == null) {
            titleString += getSolarSystem().getSun().name();
        } else {
            titleString += getCenterPlanet().name();
        }
        titleString += "-centered";
        setTitle(titleString);
        int rowSpace = AstroFrame.getRowSpace();
        int row = 2 * rowSpace;
        int column1 = 10;
        int column2 = 90;
        int column3 = 200;
        int x = (int)(getSize().width / 2);
        int y = (int)(getSize().height / 2);
        SolarSystem ss = getSolarSystem();
        g.setColor(Color.blue);
        g.drawString(ss.getInstance().toString(),column1, row);
        row += 10;
        g.drawString(ss.getInstance().gmt().toString(),column1, row);
        row += 10;
        g.drawString("Julian Day = " +
                ss.getInstance().getJulianDayNumber(), column1, row);
        row += rowSpace;
        Sun s = ss.getSun();
        Planet cp = getCenterPlanet();
        g.setFont(new Font("Monospaced", Font.BOLD, 12));
        g.setColor(Color.blue);
        g.drawString("------", column1, row);
        g.drawString("----------", column2, row);
        g.drawString("---------", column3, row);
        row += 10;
        g.drawString("Symbol", column1, row);
        g.drawString("Planet/Sun", column2, row);
        g.drawString("Longitude", column3, row);
        row += 10;
        g.drawString("------", column1, row);
        g.drawString("----------", column2, row);
        g.drawString("---------", column3, row);
        row += rowSpace;
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        s.display(g, column1+10, row);
        g.setColor(Color.black);
        g.drawString(s.name(), column2, row);
        if (cp == null) {    // Sun at center
        } else {
            g.setColor(Color.black);
            g.drawString(s.getLongitudeAtPlanet(cp).toString(),
                    column3, row);
        }
        row += rowSpace;
        for (int i=0; i < ss.getNumberOfPlanets(); ++i) {
            Planet p = ss.getPlanet(i);
            p.display(g, column1+10, row);
            g.setColor(Color.black);
            g.drawString(p.name(), column2, row);
            if (p == cp) {
            } else {
                if (cp == null) {
                    g.setColor(Color.black);
                    g.drawString(p.getHelioCentricLongitude().toString(),
                            column3, row);
                } else {
                    g.setColor(Color.black);
                    g.drawString(p.getLongitudeWithRespectTo(cp).toString(),
                            column3, row);
                }
            }
            row += rowSpace;
        }
    }
}

