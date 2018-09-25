package net.usvishakh.astrofun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Implements the main Frame and the main() method in the application.
 * This object is also responsible for finding the size of the graphic
 * device the application is running, and setting the sizes of the
 * individual components accordingly.
 @author Umesh Nair, May 1999
 **/
public class AstroFrame extends JFrame {
    private GraphicalView graphicalView;
    private TableView tableView;
    private JDesktopPane desktop;
    private SolarSystem solarSystem;
    private static int sPlanetSize = 15;
    private static int sRowSpace = 25;
    private int centerPlanetId;

    /**
     * Constructs an <B>AstroFrame</B> object.
     **/
    public AstroFrame () {
        super ("AstroFun version 1.0");
        centerPlanetId = -1;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = (int)(screenSize.width * 0.90);
        int windowHeight = (int)(screenSize.height * 0.90);
        AstroFrame.sPlanetSize = (int)(windowHeight * 0.02);
        AstroFrame.sRowSpace = windowHeight / 20;
        int viewWidth = (int) (windowWidth * 0.60);
        int viewHeight = (int) (windowHeight * 0.90);
        int viewSize = (viewWidth < viewHeight ?
                viewWidth : viewHeight);
        int tableWidth = (int) (windowWidth * 0.40);
        int tableHeight = (int) (windowHeight * 0.90);
        setSize(windowWidth, windowHeight);

        // Add Desktop
        desktop = new JDesktopPane();
        desktop.setOpaque(true);
        this.getContentPane().add(desktop, BorderLayout.CENTER);

        Integer defaultLayer = new Integer(1);
        // Add graphicalView to the desktop
        graphicalView = new GraphicalView
                (10, 0, viewSize, viewSize);
        desktop.add(graphicalView, defaultLayer);

        // Add tableView to the desktop
        tableView = new TableView
                (viewSize + 15, 0, tableWidth, tableHeight);
        desktop.add(tableView, defaultLayer);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit (0);
            }
        });

        setJMenuBar(new AstroMenuBar(this));

        solarSystem = new SolarSystem(new AstroCalendar());

        graphicalView.setSolarSystem(solarSystem);
        tableView.setSolarSystem(solarSystem);
        repaint();
    }

    /**
     * Returns the diameter of the circle representing a planet to be
     * drawn on GraphicalView
     @see HeavenlyBody#display(Graphics g, int x, int y)
     @see Mercury
     @see Sun
     @see GraphicalView
     **/
    public static int getPlanetSize() {
        return sPlanetSize;
    }

    /**
     * Returns the space between two rows on TableView
     @see TableView
     **/
    public static int getRowSpace() {
        return sRowSpace;
    }

    /**
     * Assigns a <B>SolarSystem</B> object to the application.
     @param ss The <B>SolarSystem</B>object to be used in the application.
     all subsequent paint() invokations will use this object.
     **/
    public void setSolarSystem (SolarSystem ss) {
        solarSystem = ss;
        graphicalView.setSolarSystem(ss);
        tableView.setSolarSystem(ss);
    }

    /**
     * Returns the <B>SolarSystem</B> object associated with the Frame
     *
     **/
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    /**
     * Sets the planet with respect to which the longitudes are calculated.
     * This will appear in the center in the Graphical View.
     * Valid values - 0 (Planet.MERCURY) to 8 (Planet.PLUTO).
     * A value of -1 denotes that the Sun is in the center.
     *
     **/
    public void setCenterPlanetById(int planetId) {
        centerPlanetId = planetId;
    }

    /**
     * Overrides the paint() method of JFrame.
     * Sets the center planet and paints the two views.
     **/
    public void paint(Graphics g) {
        super.paint(g);
        Planet centerPlanet;
        if (centerPlanetId == -1) {
            centerPlanet = null;
        } else {
            centerPlanet = solarSystem.getPlanet(centerPlanetId);
        }
        graphicalView.setCenterPlanet(centerPlanet);
        tableView.setCenterPlanet(centerPlanet);
        graphicalView.repaint();
        tableView.repaint();
    }

    /**
     * Read the Read/Time from a Dialog Box
     @see DateDialog
     *
     **/
    public void readInstance() {
        DateDialog dateDialog = new DateDialog(this);
        dateDialog.show();
        dateDialog.dispose();
    }


    /**
     * The main() function of the application
     * Creates and displays an <B>AstroFrame</B> object
     **/
    public static void main (String[] args) {
        AstroFrame af = new AstroFrame();
        af.setVisible(true);
    }
}
