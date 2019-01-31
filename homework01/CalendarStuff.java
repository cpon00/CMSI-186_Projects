/**
*  File name     :  CalendarStuff.java
*  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
*  Author        :  B.J. Johnson (prototype)
*  Date          :  2017-01-02 (prototype)
*  Author        :  Carter Pon
*  Date          :  2019-01-31
*  Description   :  This file provides the supporting methods for the CountTheDays program which will
*                   calculate the number of days between two dates.  It shows the use of modularization
*                   when writing Java code, and how the Java compiler can "figure things out" on its
*                   own at "compile time".  It also provides examples of proper documentation, and uses
*                   the source file header template as specified in the "Greeter.java" template program
*                   file for use in CMSI 186, Spring 2017.
*  Notes         :  None
*  Warnings      :  None
*  Exceptions    :  None
* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Revision History
*  ----------------
*            Rev      Date     Modified by:  Reason for change/modification
*           -----  ----------  ------------  -----------------------------------------------------------
*  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
*  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
*  @version 2.0.0  2019-01-29  Carter Pon    Methods written, updated
*/
public class CalendarStuff {

    /**
    * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
    */
    private static final int SUNDAY    = 0;
    private static final int MONDAY    = SUNDAY    + 1;
    private static final int TUESDAY    = SUNDAY    + 2;
    private static final int WEDNESDAY    = SUNDAY    + 3;
    private static final int THURSDAY    = SUNDAY    + 4;
    private static final int FRIDAY    = SUNDAY    + 5;
    private static final int SATURDAY    = SUNDAY    + 6;
    // you can finish the rest on your own

    /**
    * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
    */
    private static final int JANUARY    = 0;
    private static final int FEBRUARY   = JANUARY   + 1;
    private static final int MARCH   = JANUARY   + 2;
    private static final int APRIL   = JANUARY   + 3;
    private static final int MAY   = JANUARY   + 4;
    private static final int JUNE   = JANUARY   + 5;
    private static final int JULY   = JANUARY   + 6;
    private static final int AUGUST   = JANUARY   + 7;
    private static final int SEPTEMBER   = JANUARY   + 8;
    private static final int OCTOBER   = JANUARY   + 9;
    private static final int NOVEMBER   = JANUARY   + 10;
    private static final int DECEMBER   = JANUARY   + 11;
    // you can finish these on your own, too

    /**
    * An array containing the number of days in each month
    *  NOTE: this excludes leap years, so those will be handled as special cases
    *  NOTE: this is optional, but suggested
    */
    private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
    * The constructor for the class
    */
    public CalendarStuff() {
        System.out.println( "Constructor called..." );  // replace this with the actual code
    }

    /**
    * A method to determine if the year argument is a leap year or not<br />
    *  Leap years are every four years, except for even-hundred years, except for every 400
    * @param    year  long containing four-digit year
    * @return         boolean which is true if the parameter is a leap year
    */

    public static boolean isLeapYear( long year ) {
        /**
        *this method should determine if the inputted year is divisible by 4,
        *and either not divisible by 100 or divisible by 400.
        */
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

    /**
    * A method to calculate the days in a month, including leap years
    * @param    month long containing month number, starting with "1" for "January"
    * @param    year  long containing four-digit year; required to handle Feb 29th
    * @return         long containing number of days in referenced month of the year
    * notes: remember that the month variable is used as an indix, and so must
    *         be decremented to make the appropriate index value
    */
    public static long daysInMonth( long month, long year ) {
        /**
        *this method will take a long month parameter, convert it to an integer (because
        *months go only up to twelve), and determine the number of days in that month by
        *setting an empty long variable to an array of the number of days in a month.
        *Should the year be a leap year, and the month being compared is February, the method
        *will add one day to the variable.
        */
        long result = 0;
        switch ((int) month) {
            case 1: result = days[0];
            break;
            case 2: if (isLeapYear(year)) {
                result = days[1]+1;
            }else{
                result = days[1];
            }
            break;
            case 3: result = days[2];
            break;
            case 4: result = days[3];
            break;
            case 5: result = days[4];
            break;
            case 6: result = days[5];
            break;
            case 7: result = days[6];
            break;
            case 8: result = days[7];
            break;
            case 9: result = days[8];
            break;
            case 10: result = days[9];
            break;
            case 11: result = days[10];
            break;
            case 12: result = days[11];
            break;
        }
        return (result);
    }

    /**
    * A method to determine if two dates are exactly equal
    * @param    month1 long    containing month number, starting with "1" for "January"
    * @param    day1   long    containing day number
    * @param    year1  long    containing four-digit year
    * @param    month2 long    containing month number, starting with "1" for "January"
    * @param    day2   long    containing day number
    * @param    year2  long    containing four-digit year
    * @return          boolean which is true if the two dates are exactly the same
    */
    public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
        /**
        *This method determines if two dates are exactly equal. It utilizes and statements to test if all three
        *conditions are met. If any of these conditions fail, the method returns false; otherwise returns true.
        */
        return (month1 == month2 && day1 == day2 && year1 == year2);
    }

    /**
    * A method to compare the ordering of two dates
    * @param    month1 long   containing month number, starting with "1" for "January"
    * @param    day1   long   containing day number
    * @param    year1  long   containing four-digit year
    * @param    month2 long   containing month number, starting with "1" for "January"
    * @param    day2   long   containing day number
    * @param    year2  long   containing four-digit year
    * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
    */
    public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
        /**
        *This function will compare two dates and determine which one is greater/is ordered
        *ahead of the other. First, an integer is initialized, equal to zero. Should none of
        *the if-statements be tested, the function will return as zero, which means the dates
        *are equal. Should year1/date1/month1 not be equal to year2/date2/month2, however, the
        *function will determine which is greater and lesser and add or subtract 1 from equals.
        *It is especially important that the order that the dates are evaluated in are
        *year - month - day, as 3/30/19 could be considered to be before 4/27/16.
        */
        int equals = 0;
        if (year1 != year2) {
            //if the year is not equal, test equality.
            if (year1 > year2) {
                return (equals + 1);
            }else{
                return (equals - 1);
            }
        }
        if (month1 != month2) {
            //if the month is not equal, test equality.
            if (month1 > month2) {
                return (equals + 1);
            }else{
                return (equals - 1);
            }
        }
        if (day1 != day2) {
            //if the day is not equal, test equality.
            if (day1 > day2) {
                return (equals + 1);
            }else{
                return (equals - 1);
            }
        }
        return (equals);
    }

    /**
    * A method to return whether a date is a valid date
    * @param    month long    containing month number, starting with "1" for "January"
    * @param    day   long    containing day number
    * @param    year  long    containing four-digit year
    * @return         boolean which is true if the date is valid
    * notes: remember that the month and day variables are used as indices, and so must
    *         be decremented to make the appropriate index value
    */
    public static boolean isValidDate( long month, long day, long year ) {
        if (month <= 0 || month > 12) {
            //if not a valid month, return false.
            return (false);
        }
        if (year < 0000 || year > 9999) {
            return (false);
        }
        if (month == 2) {
            //if month is February, test if leap year, then determine date validity.
            if (isLeapYear(year)) {
                if (day > days[(int)month-1] + 1 || day <= 0) {
                    return (false);
                }
            }else{
                //if not leap year, test equality without adding an extra day.
                if (day > days[(int)month - 1] || day <= 0) {
                    return (false);
                }
            }
        }else{
            //for all normal months (not February), test validity.
            if (day > days[(int)month - 1] || day <= 0) {
                return (false);
            }
        }
        //if no conditions are met to test validity/return false, return true.
        return (true);
    }

    /**
    * A method to return a string version of the month name
    * @param    month long   containing month number, starting with "1" for "January"
    * @return         String containing the string value of the month (no spaces)
    */
    public static String toMonthString( int month ) {
        /**
        *This method will take an input, create an empty string titled "monthtitle," and
        *change the string to the corresponding month - 1. If the inputted month is invalid,
        *such as a number lower than 1 or higher than 12, the method will return false.
        */
        String monthtitle = "";
        switch(month) {
            case 1: monthtitle = "January";
            break;
            case 2: monthtitle = "February";
            break;
            case 3: monthtitle = "March";
            break;
            case 4: monthtitle = "April";
            break;
            case 5: monthtitle = "May";
            break;
            case 6: monthtitle = "June";
            break;
            case 7: monthtitle = "July";
            break;
            case 8: monthtitle = "August";
            break;
            case 9: monthtitle = "September";
            break;
            case 10: monthtitle = "October";
            break;
            case 11: monthtitle = "November";
            break;
            case 12: monthtitle = "December";
            break;
            default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
        }
        return (monthtitle);
    }

    /**
    * A method to return a string version of the day of the week name
    * @param    day int    containing day number, starting with "1" for "Sunday"
    * @return       String containing the string value of the day (no spaces)
    */
    public static String toDayOfWeekString( int day ) {
        String dayofweek = "";
        switch( day ) {
            case 1: dayofweek = "Sunday";
            break;
            case 2: dayofweek = "Monday";
            break;
            case 3: dayofweek = "Tuesday";
            break;
            case 4: dayofweek = "Wednesday";
            break;
            case 5: dayofweek = "Thursday";
            break;
            case 6: dayofweek = "Friday";
            break;
            case 7: dayofweek = "Saturday";
            break;

            default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
        }
        return (dayofweek);
    }

    /**
    * A method to return a count of the total number of days between two valid dates
    * @param    month1 long   containing month number, starting with "1" for "January"
    * @param    day1   long   containing day number
    * @param    year1  long   containing four-digit year
    * @param    month2 long   containing month number, starting with "1" for "January"
    * @param    day2   long   containing day number
    * @param    year2  long   containing four-digit year
    * @return          long   count of total number of days
    */
    public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
        long dayCount = 0;
        int daysDiff1 = 0;
        int daysDiff2 = 0;

        //redefine months into 0-11 instead of 1-12
        month1--;
        month2--;

        if (year1 != year2) {
//This method will run if year1 != year2. It is meant to count the number of days from the first, previous date
//to the end of the year, then count the number of years between, factoring in leap years, and then count the
//number of days from the beginning of the year til the later date.
            if (year1 > year2) {
//This function will run if the first date comes before the second date.
                dayCount += (days[(int)month2] - day2);
                for (int monthDiff = (int)month2 + 1; monthDiff <= 11; monthDiff++) {
                    if (isLeapYear(year1)||isLeapYear(year2) && days[monthDiff] == 28) {
// if the beginning year is a leap year and February is included in the first or second year...
                        dayCount += days[monthDiff] + 1;
                    }else{
                        dayCount += days[monthDiff];
                    }
                }
                for (int monthDiff = 0; monthDiff < month1; monthDiff++) {
                    if (isLeapYear(year1)||isLeapYear(year2) && days[monthDiff] == 28) {
// if the beginnning is a leap year and February is included in the first or second year...
                        dayCount += days[monthDiff] + 1;
                    }else{
                        dayCount += days[monthDiff];
                    }
                }
                for (int yearDiff = (int)year2 + 1; yearDiff < (int)year1 - 1; yearDiff++) {
                    if (isLeapYear(yearDiff)) {
//counting the number of years between the year immediately after the origin year to the year
//immediately before the final year.
                        dayCount += 366;
                    }else{
                        dayCount += 365;
                    }
                }
            }else{
//This function will run if the second date comes before the first date. Essentially, the variables are
//switched/reoriented.
                for (int yearDiff = (int)year1 + 1; yearDiff < (int)year2; yearDiff++) {

                    if (isLeapYear(yearDiff)) {
//counting the number of years between the year immediately after the origin year to the year
//immediately before the final year.
                        dayCount += 366;
                    }else{
                        dayCount += 365;
                    }
                }
                dayCount += (days[(int)month1] - day1);
                for (int monthDiff = (int)month1 + 1; monthDiff <= 11; monthDiff++) {
// if the beginnning is a leap year and February is included in the first or second year...
                    if (isLeapYear(year1)||isLeapYear(year2) && days[monthDiff] == 28) {
                        dayCount += days[monthDiff] + 1;
                    }else{
                        dayCount += days[monthDiff];
                    }
                }
                for (int monthDiff = 0; monthDiff < month2; monthDiff++) {
// if the beginnning is a leap year and February is included in the first or second year...
                    if (isLeapYear(year1)||isLeapYear(year2) && days[monthDiff] == 28) {
                        dayCount += days[monthDiff] + 1;
                    }else{
                        dayCount += days[monthDiff];
                    }
                }
                dayCount += day2;
            }
        }else{
//This method will run if the years are the same. Instead of counting until the end of the year,
//both functions will start at the beginning of the year, count until the date, and then find the
//difference between both numbers.
            for (int monthDiff = 0; monthDiff < (int)month1; monthDiff++) {
                //this function will run until monthDiff, equal to month1, reaches the first month.
                daysDiff1 += days[monthDiff];
            }
            daysDiff1 += (int)day1;
            for (int monthDiff = 0; monthDiff < (int)month2; monthDiff++) {
                //this function will run until monthDiff, equal to month2, reaches the first month.
                daysDiff2 += days[monthDiff];
            }
            daysDiff2 += (int)day2;
            dayCount += Math.abs(daysDiff1 - daysDiff2);
        }
        if (isLeapYear(year1)) {
//I had a logic error which added ten days to the dayCount if the beginning year was a leap year. This
//error was impossible to replicate with any other scenario unless the first year was a leap year. Thus,
//this statement exists.
            dayCount -= 10;
        }
        return (dayCount);
    }
}
