/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  File name     :  Clock.java
*  Purpose       :  Provides a class defining methods for the ClockSolver class
*  @author       :  B.J. Johnson
*  Date written  :  2017-02-28
*  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
*                   for Homework 4, part 1.  Includes the following:
*
*  Notes         :  None right now.  I'll add some as they occur.
*  Warnings      :  None
*  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Revision Histor
*  ---------------
*            Rev      Date     Modified by:  Reason for change/modification
*           -----  ----------  ------------  -----------------------------------------------------------
*  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;
public class Clock {
    /**
    *  Class field definitions go here
    */
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
    private static final double INVALID_ARGUMENT_VALUE = -1.0;
    private static final double MAXIMUM_DEGREE_VALUE = 360.0;
    private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
    private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
    public static double timeSlice = 60.0;
    public static double angle = 0.0;
    public static double seconds = 0.0;
    private static double hourHandAngle = 0.0;
    private static double minuteHandAngle = 0.0;
    private static double handAngle = 0.0;
    private static int minutesString = 0;
    private static int hoursString = 0;
    private static int secondsString = 0;
    /**
    *  Constructor goes here
    */
    public Clock() {
        // :)
    }

    /**
    *  Methods go here
    *
    *  Method to calculate the next tick from the time increment
    *  @return double-precision value of the current clock tick
    */
    public double tick() {
        seconds += timeSlice;
        getHourHandAngle();
        getMinuteHandAngle();
        getHandAngle();
        return seconds;
    }

    /**
    *  Method to validate the angle argument
    *  @param   argValue  String from the main programs args[0] input
    *  @return  double-precision value of the argument
    *  @throws  NumberFormatException
    */
    public static double validateAngleArg( String argValue ) throws NumberFormatException {
        //need to handle non-negative reals that are less than 360 degrees.
        //check if within 0-360 degrees.
            Double argumentValue = Double.parseDouble(argValue);
            if ((argumentValue < 0.0) || (argumentValue > 360.0)) {
                return INVALID_ARGUMENT_VALUE;
                // throw new NumberFormatException("invalid angle arg");
            }
            angle = argumentValue;

        return angle;
    }
    /**
    *  Method to validate the optional time slice argument
    *  @param  argValue  String from the main programs args[1] input
    *  @return double-precision value of the argument or -1.0 if invalid
    *  note: if the main program determines there IS no optional argument supplied,
    *         I have elected to have it substitute the string "60.0" and call this
    *         method anyhow.  That makes the main program code more uniform, but
    *         this is a DESIGN DECISION, not a requirement!
    *  note: remember that the time slice, if it is small will cause the simulation
    *         to take a VERY LONG TIME to complete!
    */
    public static double validateTimeSliceArg( String argValue ) {
        //need to handle positive real numbers that are less than 1800 seconds.
        //should use a default time slice of 60 seconds.
        //check if within 0-1800 seconds.
        Double argumentValue = Double.parseDouble(argValue);
        if (argumentValue < 0.0 || argumentValue > 1800.0) {
            return INVALID_ARGUMENT_VALUE;
        }
        timeSlice = argumentValue;
        return timeSlice;
    }
    /**
    *  Method to calculate and return the current position of the hour hand
    *  @return double-precision value of the hour hand location
    */
    public double getHourHandAngle() {
        //what angle is the hour hand at?
        //seconds after initialization, multiplied by HOUR_HAND_DEGREES_PER_SECOND
        hourHandAngle = (seconds * HOUR_HAND_DEGREES_PER_SECOND);
        //how do i find the seconds after initialization?
        //360 - hour hand angle
        return hourHandAngle;
    }
    /**
    *  Method to calculate and return the current position of the minute hand
    *  @return double-precision value of the minute hand location
    */
    public double getMinuteHandAngle() {
        //what angle is the minute hand at?
        //seconds after initialization, multiplied by MINUTE_HAND_DEGREES_PER_SECOND
        minuteHandAngle = (seconds * MINUTE_HAND_DEGREES_PER_SECOND)%360.0;
        //360 - minute hand angle
        return minuteHandAngle;
    }
    /**
    *  Method to calculate and return the angle between the hands
    *  @return double-precision value of the angle between the two hands
    */
    public double getHandAngle() {
        //difference between both angles, no matter what orientation.
        //Angle furthest from zero minus angle closest to 0.
        handAngle = Math.abs(hourHandAngle - minuteHandAngle);

        return handAngle;
    }
    /**
    *  Method to fetch the total number of seconds
    *   we can use this to tell when 12 hours have elapsed
    *  @return double-precision value the total seconds private variable
    */
    public double getTotalSeconds() {
        return seconds;
    }
    /**
    *  Method to return a String representation of this clock
    *  @return String value of the current clock
    */
    public String toString() {
        //take total number of seconds value
        //create another variable that is equal to current total number of seconds
        //from the total seconds, subtract hours and subtract minutes to get seconds mod 60.
        double secondsForString = seconds;
        //i have the total amount of seconds stored as secondsForString.
        hoursString = (int)Math.floor(secondsForString / 3600);
        //i want to take the total number of seconds divided by 3600 to get the total number of hours.
        minutesString = (int)Math.floor((secondsForString - hoursString * 3600)/60);
        //total seconds - hours, converted to minutes
        //the minutes string should take the difference between the total number of seconds and the total amount of time in
        //the hour and divide it by 60.
        secondsString = (int)secondsForString - (hoursString * 3600 + minutesString * 60);
        //the seconds string should be total seconds - hours - minutes.
        return hoursString + ":" + String.format("%02d", minutesString) + ":" + String.format("%02d", secondsString);
    }
    /**
    *  The main program starts here
    *  remember the constraints from the project description
    *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
    *  be sure to make LOTS of tests!!
    *  remember you are trying to BREAK your code, not just prove it works!
    */
    public static void main( String args[] ) {
        System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
        "--------------------------\n" );
        System.out.println( "  Creating a new clock: " );
        Clock clock = new Clock();
        System.out.println( "    New clock created: " + clock.toString() );
        // System.out.println( "    Testing validateAngleArg()....");
        try {
            System.out.println((-1.0 == clock.validateAngleArg("-3.4")) ? " - Correct: -1.0" : angle  );
            System.out.println((-1.0 == clock.validateAngleArg("-0.000001")) ? " - Correct: -1.0" : angle );
            System.out.println((-1.0 == clock.validateAngleArg("-90.0")) ? " - Correct: -1.0" : angle );
            System.out.println((-1.0 == clock.validateAngleArg("360.00001")) ? " - Correct: -1.0" : angle );
            System.out.println((30.0 == clock.validateAngleArg("30.0")) ? " - Correct: 30.0" : angle );
            System.out.println((60.0 == clock.validateAngleArg("60.0")) ? " - Correct: 60.0" : angle );
            System.out.println((135.0001 == clock.validateAngleArg("135.0001")) ? " - Correct: 135.0001" : angle );
            System.out.println((180.0 == clock.validateAngleArg("180.0")) ? " - Correct: 180.0" : angle );
            System.out.println((90.0 == clock.validateAngleArg("90.0")) ? " - Correct: 90.0" : angle );
            System.out.println("\ntimeSlice tests\n");
            System.out.println((60.0 == clock.validateTimeSliceArg("60.000")) ? " - Correct: 60.0" : timeSlice);
            System.out.println((-1.0 == clock.validateTimeSliceArg("-34.0")) ? " - Correct: -1.0" : timeSlice);
            System.out.println((-1.0 == clock.validateTimeSliceArg("1800.00001")) ? " - Correct: -1.0" : timeSlice);
            System.out.println((60.0 == clock.validateTimeSliceArg("60")) ? " - Correct: 60.0" : timeSlice);
            System.out.println((12.00001 == clock.validateTimeSliceArg("12.00001")) ? " - Correct: 12.00001" : timeSlice);
            System.out.println((360.0 == clock.validateTimeSliceArg("360")) ? " - Correct: 360.0" : timeSlice);
            System.out.println((342.001 == clock.validateTimeSliceArg("342.001")) ? " - Correct: 342.001" : timeSlice);
        } catch( Exception e ) {                 
            System.out.println ( " - Exception thrown: " + e.toString() );
         }
    }
}
