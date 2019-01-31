public class CountTheDays extends CalendarStuff {
    public static void main (String [] args ) {
        long month1 = Long.parseLong(args[0]);
        long day1 = Long.parseLong(args[1]);
        long year1 = Long.parseLong(args[2]);
        long month2 = Long.parseLong(args[3]);
        long day2 = Long.parseLong(args[4]);
        long year2 = Long.parseLong(args[5]);
        if ((CalendarStuff.isValidDate(month1, day1, year1)) && (CalendarStuff.isValidDate(month2, day2, year2))) {
            System.out.println("The number of days between the dates is" + " " + CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2) + ".");
        }else{
            System.out.println("Invalid date. Run it back.");
        }
    }
}
