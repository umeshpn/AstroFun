package net.usvishakh.astrofun;

import java.text.*;

/**
 * Class to abstract an angle expressed in degrees
 * Objects automatically normalizes itself in the 0-360 range.
 * Defines arithmetic and trigonometric operations.
 @author Umesh Nair, May 1999
 **/
public class Angle {
    /**
     * Degrees in a Radian
     **/
    public static final double D_IN_R = 57.2957795;

    private static final double MAXIMUM = 360.0;
    private static final double MINIMUM = 0.0;
    private static final double SIZE = MAXIMUM - MINIMUM;

    private double value;

    /**
     * Constructs an Angle object from a number.
     @param val Any floating point value representing the
     angle as degrees. Will be automatically converted to
     0-360 range.
     **/
    public Angle (double val) {
        value = val;
        normalize();
    }

    /**
     * Sets a value (in degrees) to the object.
     @param val Any floating point value representing the
     angle as degrees. Will be automatically converted to
     0-360 range.
     **/
    public void setValue (double val) {
        value = val;
        normalize();
    }

    /**
     * Adds two angles and returns the result
     @param alpha An <B>Angle</B> object.
     @param beta An <B>Angle</B> object.
     @return An <B>Angle</B> object the sum of the angles
     **/
    public static Angle sumOf(Angle alpha, Angle beta) {
        return new Angle (alpha.getValue() + beta.getValue());
    }

    /**
     * Adds two angles and returns the result
     @param alpha An <B>Angle</B> object.
     @param val angle expressed as a floating point number.
     @return An <B>Angle</B> object the sum of the angles.
     **/
    public static Angle sumOf(Angle angle, double val) {
        return new Angle (angle.getValue() + val);
    }

    /**
     * Adds two angles and returns the result
     @param val angle expressed as a floating point number.
     @param alpha An <B>Angle</B> object.
     @return An <B>Angle</B> object the sum of the angles.
     **/
    public static Angle sumOf(double val, Angle angle) {
        return new Angle (angle.getValue() + val);
    }

    /**
     * Finds the difference between two angles
     @param alpha An <B>Angle</B> object.
     @param beta An <B>Angle</B> object.
     @return An <B>Angle</B> object the difference of the angles
     **/
    public static Angle differenceOf(Angle alpha, Angle beta) {
        return new Angle (alpha.getValue() - beta.getValue());
    }

    /**
     * Finds the difference between two angles
     @param alpha An <B>Angle</B> object.
     @param val angle expressed as a floating point number.
     @return An <B>Angle</B> object the difference of the angles.
     **/
    public static Angle differeneOf(Angle angle, double val) {
        return new Angle (angle.getValue() - val);
    }

    /**
     * Finds the difference between two angles
     @param val angle expressed as a floating point number.
     @param alpha An <B>Angle</B> object.
     @return An <B>Angle</B> object the sum of the angles.
     **/
    public static Angle differeneOf(double val, Angle angle) {
        return new Angle (val - angle.getValue());
    }

    /**
     * Multiplies an angle by a value and returns the result
     @param mult a floating point number.
     @param alpha An <B>Angle</B> object.
     @return An <B>Angle</B> object obtained by multiplying
     <I>alpha</I> with <I>mult</I> and normalizing.
     **/
    public static Angle productOf(double mult, Angle angle) {
        return new Angle (angle.getValue() * mult);
    }

    /**
     * Multiplies an angle by a value and returns the result
     @param alpha An <B>Angle</B> object.
     @param mult a floating point number.
     @return An <B>Angle</B> object obtained by multiplying
     <I>alpha</I> with <I>mult</I> and normalizing.
     **/
    public static Angle productOf(Angle angle, double mult) {
        return new Angle (angle.getValue() * mult);
    }

    /**
     * Returns degrees as a floating point value
     @return The value of the angle in degrees
     **/
    public double getValue() {
        return value;
    }

    /**
     * Finds the sine of the angle.
     @return tangent of the angle.
     **/
    public double sin() {
        return(Math.sin(value/D_IN_R));
    }

    /**
     * Finds the cosine of the angle.
     @return tangent of the angle.
     **/
    public double cos() {
        return(Math.cos(value/D_IN_R));
    }

    /**
     * Finds the tangent of the angle.
     @return tangent of the angle.
     **/
    public double tan() {
        return(Math.tan(value/D_IN_R));
    }

    /**
     * Finds the arcsin of a value and returns it as an
     * Angle object.
     @return an <B>Angle</B> object in the range of 0-90 or 270-360
     degrees
     **/
    public static Angle asin(double value) {
        return new Angle (D_IN_R * Math.asin(value));
    }

    /**
     * Finds the arccos of a value and returns it as an
     * Angle object.
     @return an <B>Angle</B> object in the range of 0-180 degrees
     **/
    public static Angle acos(double value) {
        return new Angle (D_IN_R * Math.acos(value));
    }

    /**
     * Finds the arctan of a value and returns it as an
     * Angle object.
     @return an <B>Angle</B> object in the range of 0-90 or 270-360
     degrees
     **/
    public static Angle atan(double value) {
        return new Angle (D_IN_R * Math.atan(value));
    }

    /**
     * Overrides Object.toString()
     **/
    public String toString() {
        String[] zod = { "ARI", "TAU", "GEM",
                "CAN", "LEO", "VIR", "LIB", "SCO",
                "SAG", "CAP", "AQU", "PIS" };
        int seconds = (int)(value * 3600 + 0.5);
        int minutes = seconds / 60;
        seconds %= 60;
        int degrees = minutes / 60;
        minutes %= 60;
        int zodId = degrees / 30;
        int zodDegrees = degrees % 30;
        return (Angle.paddedIntegerString(degrees,3,' ')+":"+
                paddedIntegerString(minutes,2,'0')+":"+
                Angle.paddedIntegerString(seconds,2,'0')+"   ("+
                zod[zodId] + " " +
                Angle.paddedIntegerString(zodDegrees, 2, ' ') + ":" +
                Angle.paddedIntegerString(minutes,2,'0')+ ":" +
                Angle.paddedIntegerString(seconds,2,'0') + ")");
    }

    /**
     * Private method that reduces the angle to 0-360 range
     **/
    private void normalize() {
        while (value < MINIMUM)
            value += SIZE;
        while (value >= MAXIMUM)
            value -= SIZE;
    }

    /**
     * Private method to format a number
     *
     **/

    private static String paddedIntegerString(long num, int size, char c) {
        DecimalFormat df = new DecimalFormat("#0");
        FieldPosition fp = new FieldPosition(NumberFormat.INTEGER_FIELD);
        String s = new String(df.format(num, new StringBuffer(), fp));
        int n = size - fp.getEndIndex();
        String pad = new String();
        while (n-- > 0) {
            pad += c;
        }
        return pad+s;
    }
}

