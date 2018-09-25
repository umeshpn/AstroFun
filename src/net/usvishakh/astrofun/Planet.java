package net.usvishakh.astrofun;

/**
 ** Abstract class representing a planet at a specified instance.
 ** @see HeavenlyBody
 ** @see Star
 ** @author Umesh Nair, May 1999
 **/
public abstract class Planet extends HeavenlyBody {

    // Planets and Sun
    public static final int SUN = -1;
    public static final int MERCURY = 0;
    public static final int VENUS = 1;
    public static final int EARTH = 2;
    public static final int MARS = 3;
    public static final int JUPITER = 4;
    public static final int SATURN = 5;
    public static final int URANUS = 6;
    public static final int NEPTUNE = 7;
    public static final int PLUTO = 8;
    /**
     ** Gives number of degrees earth traverses in a day
     **/
    public static final double DEGREES_PER_DAY = 360.0 / 365.242191 ;

    /**
     ** Epoch on which the calculations are based
     ** 1990 January 0.0, Or 1989 December 31 0.0 AM
     **/
    public static final int planetEpoch = AstroCalendar.EPOCH_1990_PRE_MID;

    /**
     ** Astronomical details of a planet at a particular instance
     **/
    private Angle helioCentricLongitude;
    private double daysSinceEpoch;

    /**
     ** Constructor
     ** @param theInstance The time at which the planet details are to be
     ** calculated
     **/
    public Planet (AstroCalendar theInstance) {
        super(theInstance);
        helioCentricLongitude = null;
        daysSinceEpoch = theInstance.daysFromEpoch(planetEpoch);
    }

    /**
     ** Gets the heliocentric longirude of the planet
     **/
    public Angle getHelioCentricLongitude() {
        if (helioCentricLongitude == null) {
            helioCentricLongitude =
                    Angle.sumOf(getLongitudeAtEpoch(),
                            DEGREES_PER_DAY * daysSinceEpoch / getPeriod()) ;
        }
        return helioCentricLongitude;
    }

    /**
     ** Gets the heliocentric longirude of the planet
     **/
    public double getDaysSinceEpoch() {
        return daysSinceEpoch;
    }

    /**
     ** Calculates the longitude of this planet with respect to another planet
     **/
    public Angle getLongitudeWithRespectTo (Planet another) {
        Angle l = getHelioCentricLongitude();
        Angle L = another.getHelioCentricLongitude();
        double a = getSemiMajorAxis();
        if (a > another.getSemiMajorAxis()) {
            Angle longitudeDifference = Angle.differenceOf(l, L);
            double tanLambda = longitudeDifference.sin() /
                    (a - longitudeDifference.cos());
            return Angle.sumOf(l, Angle.atan(tanLambda));
        } else {
            Angle longitudeDifference = Angle.differenceOf(L, l);
            double tanLambda =
                    a * longitudeDifference.sin() /
                            (1.0 - a * longitudeDifference.cos());
            return Angle.sumOf(Angle.sumOf(L, 180.0),
                    Angle.atan(tanLambda));
        }
    }

    /**
     ** Abstract method returning the longitude of the planet
     ** at the epoch
     **/
    public abstract Angle getLongitudeAtEpoch();

    /**
     ** Abstract method returning the period of the planet (in years)
     **/
    public abstract double getPeriod();

    /**
     ** Abstract method returning the longer radius of the planet's
     ** orbit
     **/
    public abstract double getSemiMajorAxis();
}
