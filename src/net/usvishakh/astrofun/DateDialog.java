package net.usvishakh.astrofun;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.TimeZone;

/**
 ** A Dialog class to accept Date/Time.
 ** @author Umesh Nair, May 1999
 **/
public class DateDialog extends Dialog {
    // Constants
    static final int AM = 0;
    static final int PM = 1;

    // Controls
    private Button doButton;
    private Button nowButton;
    private TextField yearField;
    private Choice monthChoice, dayChoice, hourChoice, minuteChoice;
    private Choice amPmChoice, timeZoneChoice;
    private AstroCalendar calendar;
    private AstroFrame attachedFrame;
    private Label errorLabel;

    /**
     ** Constructor.
     **/
    public DateDialog (AstroFrame frame) {
        super(frame, "Dialog to set Date", true);
        attachedFrame = frame;
        setSize(600, 200);
        doButton = new Button ("Set Date");
        errorLabel = new Label
                ("                                                               ");
        errorLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC,
                12));
        doButton.addActionListener(new ActionListener (){
            public void actionPerformed (ActionEvent event) {
                try {
                    errorLabel.setText(" ");
                    int year = Integer.parseInt(yearField.getText());
                    int month = monthChoice.getSelectedIndex();
                    int day = dayChoice.getSelectedIndex()+1;
                    int hour = hourChoice.getSelectedIndex()+1;
                    int minute = minuteChoice.getSelectedIndex();
                    int amPm = amPmChoice.getSelectedIndex();
                    if (amPm == DateDialog.AM && hour == 12)
                        hour = 0;
                    else if (amPm == DateDialog.PM && hour != 12)
                        hour += 12;
                    calendar = new AstroCalendar
                            (new GeneralDateFormat(timeZoneChoice.getSelectedItem()),
                                    year, month, day, hour, minute, 0);
                    attachedFrame.setSolarSystem(new SolarSystem(calendar));
                    attachedFrame.repaint();
                    DateDialog.this.setVisible(false);
                } catch (NumberFormatException e){
                    DateDialog.this.showError("ERROR : Year should be numeric");
                } catch (InvalidDateTimeException e) {
                    DateDialog.this.showError("ERROR : invalid Date");
                }
            }
        });
        nowButton = new Button ("Now");
        nowButton.addActionListener(new ActionListener (){
            public void actionPerformed (ActionEvent event) {
                calendar = new AstroCalendar();
                attachedFrame.setSolarSystem(new SolarSystem(calendar));
                attachedFrame.repaint();
                DateDialog.this.setVisible(false);
            }
        });

        int i;
        yearField = new TextField(4);
        monthChoice = new Choice();
        dayChoice = new Choice();
        hourChoice = new Choice();
        minuteChoice = new Choice();
        amPmChoice = new Choice();
        timeZoneChoice = new Choice();

        monthChoice.addItem("January");
        monthChoice.addItem("February");
        monthChoice.addItem("March");
        monthChoice.addItem("April");
        monthChoice.addItem("May");
        monthChoice.addItem("June");
        monthChoice.addItem("July");
        monthChoice.addItem("August");
        monthChoice.addItem("September");
        monthChoice.addItem("October");
        monthChoice.addItem("November");
        monthChoice.addItem("December");

        for (i = 1 ; i < 32 ; ++i) {
            dayChoice.addItem(String.valueOf(i));
        }
        for (i = 1 ; i < 13 ; ++i) {
            hourChoice.addItem(String.valueOf(i));
        }
        for (i = 0 ; i < 60 ; ++i) {
            minuteChoice.addItem(String.valueOf(i));
        }
        amPmChoice.addItem("AM");
        amPmChoice.addItem("PM");

        String[] timeZones = TimeZone.getAvailableIDs();
        for ( i = 0 ; i < timeZones.length ; ++i) {
            timeZoneChoice.addItem(timeZones[i]);
        }

        // Defining layout
        this.setLayout(new GridBagLayout());
        this.addControl(new Label ("Date : "), 3,0,1);
        this.addControl(yearField,GridBagConstraints.RELATIVE,0,1);
        this.addControl(monthChoice,GridBagConstraints.RELATIVE,0,1);
        this.addControl(dayChoice,GridBagConstraints.RELATIVE,0,1);
        this.addControl(new Label("Time : "),3,2,1);
        this.addControl(hourChoice,GridBagConstraints.RELATIVE,2,1);
        this.addControl(minuteChoice,GridBagConstraints.RELATIVE,2,1);
        this.addControl(amPmChoice,GridBagConstraints.RELATIVE,2,1);
        this.addControl(timeZoneChoice,GridBagConstraints.RELATIVE,2,1);
        this.addControl(doButton,5,5,1);
        this.addControl(nowButton, GridBagConstraints.RELATIVE, 5, 1);
        this.addControl(errorLabel, 3, 6, 6);

        calendar = attachedFrame.getSolarSystem().getInstance();
        yearField.setText(Integer.toString(calendar.get(Calendar.YEAR)));
        monthChoice.select(calendar.get(Calendar.MONTH));
        dayChoice.select(calendar.get(Calendar.DAY_OF_MONTH)-1);
        int h = calendar.get(Calendar.HOUR);
        h = ((h == 0) ? 11 : h - 1);
        hourChoice.select(h);
        minuteChoice.select(calendar.get(Calendar.MINUTE));
        amPmChoice.select(calendar.get(Calendar.AM_PM));
        timeZoneChoice.select(calendar.getTimeZone().getID());
    }

    /**
     ** Returns the <B>AstroCalendar</B> object set by the dialog.
     **/
    public AstroCalendar getCalendar () {
        return calendar;
    }

    /**
     ** Shows error message if the date entered is invalid.
     **/
    private void showError (String msg) {
        errorLabel.setText(msg);
        ErrorDialog ed = new ErrorDialog(attachedFrame, msg);
    }

    /**
     ** Private method to add control to the dialog window.
     **/
    private void addControl (Component comp, int x, int y, int width) {
        GridBagLayout gbl = (GridBagLayout)this.getLayout ();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = x; gbc.gridy = y;
        gbc.gridwidth = width; gbc.gridheight = 1;
        gbc.weightx = gbc.weighty = 0;
        this.add(comp);
        gbl.setConstraints(comp, gbc);
    }
}

