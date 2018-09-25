package net.usvishakh.astrofun;

import java.awt.*;

/**
 ** Implements a Solar System class with the Sun and nine planets.
 ** @see Sun
 ** @see Planet
 ** @author Umesh Nair, May 1999
 **/
public class SolarSystem {
    private Sun sun;
    private Planet[] planets;
    private int nPlanets;
    private AstroCalendar instance;

    /**
     ** Constructs a <B>SolarSystem</B> objects at an instance
     **/
    public SolarSystem (AstroCalendar instance) {
        this.instance = instance;
        sun = new Sun(instance);
        nPlanets = 9;
        planets = new Planet[nPlanets];
        planets[0] = new Mercury(instance);
        planets[1] = new Venus(instance);
        planets[2] = new Earth(instance);
        planets[3] = new Mars(instance);
        planets[4] = new Jupiter(instance);
        planets[5] = new Saturn(instance);
        planets[6] = new Uranus(instance);
        planets[7] = new Neptune(instance);
        planets[8] = new Pluto(instance);
    }

    /**
     ** Returns the <B>AstroCalendar</B> object associated with
     ** the details of the <B>SolarSystem</B> object.
     **/
    public AstroCalendar getInstance() {
        return instance;
    }

    /**
     ** Returns the <B>Sun</B> objects in the solar system.
     **/
    public Sun getSun() {
        return sun;
    }

    /**
     ** Returns a particular planet in the solar system
     ** @param index Any of the constants Planet.MERCURY to Planet.PLUTO
     ** @return the <B>Planet</B> object. null if invalid.
     **/
    public Planet getPlanet(int index) {
        if (index < 0 || index > nPlanets) {
            return null;
        } else {
            return planets[index];
        }
    }

    /**
     ** Returns the number of planets in the solar system
     **/
    public int getNumberOfPlanets() {
        return nPlanets;
    }
}
