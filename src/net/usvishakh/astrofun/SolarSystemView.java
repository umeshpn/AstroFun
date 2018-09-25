package net.usvishakh.astrofun;

import javax.swing.*;
import java.awt.*;

/**
 ** Abstract class to view a Solar system
 ** @see GraphicalView
 ** @see TableView
 ** @author Umesh Nair, May 1999
 **/
public class SolarSystemView extends JInternalFrame {
    private SolarSystem solarSystem;
    private Planet centerPlanet; // Null if Sun

    /**
     ** Constructor
     **/
    public SolarSystemView(int x, int y, int viewWidth,
                           int viewHeight, String title) {
        super(title, true, true, true, true);
        setBounds(x, y, viewWidth, viewHeight);
    }

    /**
     ** Attaches a center planet to the view
     **/
    public void setCenterPlanet(Planet planetAtCenter) {
        centerPlanet = planetAtCenter;
    }

    /**
     ** Attaches a <B>SolarSystem</B> object to the view
     **/
    public void setSolarSystem(SolarSystem ss) {
        solarSystem = ss;
    }

    /**
     ** Gets the <B>SolarSystem</B> object attached to the view
     **/
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    /**
     ** Gets the centerplanet attached to the view
     **/
    public Planet getCenterPlanet() {
        return centerPlanet;
    }
}

