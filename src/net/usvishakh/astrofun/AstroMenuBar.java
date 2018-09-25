package net.usvishakh.astrofun;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 ** Implements the menu bar on the main window.
 **
 **/
public class AstroMenuBar extends JMenuBar {
    private JRadioButtonMenuItem[] centerItems;
    private ButtonGroup centerButtonGroup;
    private ButtonGroup lfButtonGroup;

    private AstroFrame attachedFrame;

    /**
     ** Constructs the menu bar for a frame.
     **
     **/
    public AstroMenuBar(AstroFrame frame) {
        attachedFrame = frame;

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem setDateItem = new JMenuItem("Set Date", 'S');
        setDateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.readInstance();
            }
        });
        fileMenu.add(setDateItem);

        JMenuItem exitItem = new JMenuItem("Exit", 'E');
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        add(fileMenu);

        // Center Menu
        JMenu centerMenu = new JMenu("Center");

        centerButtonGroup = new ButtonGroup();

        JRadioButtonMenuItem centerSunItem =
                new JRadioButtonMenuItem ("Sun", true);
        centerSunItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.SUN);
            }
        });
        centerMenu.add(centerSunItem);
        centerButtonGroup.add(centerSunItem);

        JRadioButtonMenuItem centerMercuryItem =
                new JRadioButtonMenuItem ("Mercury");
        centerMercuryItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.MERCURY);
            }
        });
        centerMenu.add(centerMercuryItem);
        centerButtonGroup.add(centerMercuryItem);

        JRadioButtonMenuItem centerVenusItem =
                new JRadioButtonMenuItem ("Venus");
        centerVenusItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.VENUS);
            }
        });
        centerMenu.add(centerVenusItem);
        centerButtonGroup.add(centerVenusItem);

        JRadioButtonMenuItem centerEarthItem =
                new JRadioButtonMenuItem ("Earth");
        centerEarthItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.EARTH);
            }
        });
        centerMenu.add(centerEarthItem);
        centerButtonGroup.add(centerEarthItem);

        JRadioButtonMenuItem centerMarsItem =
                new JRadioButtonMenuItem ("Mars");
        centerMarsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.MARS);
            }
        });
        centerMenu.add(centerMarsItem);
        centerButtonGroup.add(centerMarsItem);

        JRadioButtonMenuItem centerJupiterItem =
                new JRadioButtonMenuItem ("Jupiter");
        centerJupiterItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.JUPITER);
            }
        });
        centerMenu.add(centerJupiterItem);
        centerButtonGroup.add(centerJupiterItem);

        JRadioButtonMenuItem centerSaturnItem =
                new JRadioButtonMenuItem ("Saturn");
        centerSaturnItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.SATURN);
            }
        });
        centerMenu.add(centerSaturnItem);
        centerButtonGroup.add(centerSaturnItem);

        JRadioButtonMenuItem centerUranusItem =
                new JRadioButtonMenuItem ("Uranus");
        centerUranusItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.URANUS);
            }
        });
        centerMenu.add(centerUranusItem);
        centerButtonGroup.add(centerUranusItem);

        JRadioButtonMenuItem centerNeptuneItem =
                new JRadioButtonMenuItem ("Neptune");
        centerNeptuneItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.NEPTUNE);
            }
        });
        centerMenu.add(centerNeptuneItem);
        centerButtonGroup.add(centerNeptuneItem);

        JRadioButtonMenuItem centerPlutoItem =
                new JRadioButtonMenuItem ("Pluto");
        centerPlutoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attachedFrame.setCenterPlanetById(Planet.PLUTO);
            }
        });
        centerMenu.add(centerPlutoItem);
        centerButtonGroup.add(centerPlutoItem);
        add(centerMenu);

        // Look & Feel Menu
        JMenu lfMenu = new JMenu("Look and Feel");
        lfButtonGroup = new ButtonGroup();

        // Adding Windows Look & Feel
        JRadioButtonMenuItem windowsItem =
                new JRadioButtonMenuItem("Windows");
        windowsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setLFStyle(
                        "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
        });
        lfMenu.add(windowsItem);
        lfButtonGroup.add(windowsItem);

        // Adding Motif Look & Feel
        JRadioButtonMenuItem motifItem =
                new JRadioButtonMenuItem("Motif");
        motifItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setLFStyle(
                        "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }
        });
        lfMenu.add(motifItem);
        lfButtonGroup.add(motifItem);

        // Adding Java Metal Look & Feel
        JRadioButtonMenuItem javaItem =
                new JRadioButtonMenuItem("Java", true);
        javaItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setLFStyle(
                        "javax.swing.plaf.metal.MetalLookAndFeel");
            }
        });
        lfMenu.add(javaItem);
        lfButtonGroup.add(javaItem);
        add(lfMenu);
    }

    /**
     ** Sets the "Look and Feel" for the Window
     **
     **/
    private void setLFStyle(String style) {
        try {
            UIManager.setLookAndFeel(style);
            SwingUtilities.updateComponentTreeUI(attachedFrame);
        } catch (UnsupportedLookAndFeelException e1) {
            ErrorDialog ed = new ErrorDialog
                    (attachedFrame, "Unsupported LookAndFeel: " + style);
        } catch (ClassNotFoundException e2) {
            ErrorDialog ed = new ErrorDialog
                    (attachedFrame,"LookAndFeel class not found: " + style);
        } catch (InstantiationException e3) {
            ErrorDialog ed = new ErrorDialog
                    (attachedFrame, "Could not load LookAndFeel: " + style);
        } catch (IllegalAccessException e2) {
            ErrorDialog ed = new ErrorDialog
                    (attachedFrame,"Cannot use LookAndFeel: " + style);
        }
    }
}

