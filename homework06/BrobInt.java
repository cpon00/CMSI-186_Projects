/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* File name  :  BrobInt.java
* Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
* @author    :  B.J. Johnson
* Date       :  2017-04-04
* Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
* Notes      :  None
* Warnings   :  None
*
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Revision History
* ================
*   Ver      Date     Modified by:  Reason for change or modification
*  -----  ----------  ------------  ---------------------------------------------------------------------
*  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
*  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
*                                     validateDigits, two reversers, and valueOf methods; revamped equals
*                                     and compareTo methods to use the Java String methods; ready to
*                                     start work on subtractByte and subtractInt methods
*
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BrobInt {

    public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
    public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
    public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
    public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
    public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
    public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
    public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
    public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
    public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
    public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
    public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

    /// Some constants for other intrinsic data types
    ///  these can help speed up the math if they fit into the proper memory space
    public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
    public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
    public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
    public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

    /// These are the internal fields
    public  String internalValue = "";        // internal String representation of this BrobInt
    public  int   sign          = 0;         // "0" is positive, "1" is negative
    private String reversed      = "";        // the backwards version of the internal String representation
    public int chunks            = 0;
    public int BILLION           = 1000000000;
    public int intZERO              = 0;
    public List<Integer> intArrayList = new ArrayList<>();

    private static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
    private static final boolean DEBUG_ON = true;
    private static final boolean INFO_ON  = true;

    /**
    *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
    *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
    *   for later use
    *  @param  value  String value to make into a BrobInt
    */
    public BrobInt( String value ) {
        this.internalValue = value;
        validateDigits();
        if (value.charAt(0) == '-') {
            sign = 1;
            value = value.replace("-", "");
        }else if (value.charAt(0) == '+') {
            value = value.replace("+", "");
        }
        this.chunks = (int)(Math.ceil(value.length()/9.0));
        int stop = value.length();
        int start = stop - 9;
        if (start < 0) {
            start = 0;
        }
        for (int i = chunks; i > 0; i--) {
            this.intArrayList.add(Integer.parseInt(value.substring(start,stop)));
            stop -= 9;
            if (i == 2) {
                start = 0;
            }else{
                start -= 9;
            }
        }
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to validate that all the characters in the value are valid decimal digits
    *  @return  boolean  true if all digits are good
    *  @throws  IllegalArgumentException if something is hinky
    *  note that there is no return false, because of throwing the exception
    *  note also that this must check for the '+' and '-' sign digits
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public boolean validateDigits() throws IllegalArgumentException {

        for (int loc = 0; loc < internalValue.length(); loc++) {
            if (loc == 0 && internalValue.charAt(0) == '-' || internalValue.charAt(0) == '+') {
                loc = 1;
            }
            try {
                    Integer.parseInt(String.valueOf(internalValue.charAt(loc)));
                }catch (Exception e) {
                    System.out.println(String.valueOf(e));
                    throw new IllegalArgumentException ("bad arg");
            }
        }
        return true;
    }


    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to reverse the value of this BrobInt
    *  @return BrobInt that is the reverse of the value of this BrobInt
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt reverser() {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to reverse the value of a BrobIntk passed as argument
    *  Note: static method
    *  @param  bint         BrobInt to reverse its value
    *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static BrobInt reverser( BrobInt bint ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to add the value of a BrobInt passed as argument to this BrobInt using byte array
    *  @param  bint         BrobInt to add to this
    *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt add( BrobInt bint ) {
        String result = "";
        int remainder = 0;
        int sum = 0;
        if (bint.sign == this.sign) {
            if (this.compareTo(bint) == 1 || this.compareTo(bint) == 0) {
                for (int i = 0; i < this.intArrayList.size(); i++) {
                    if (i >= bint.intArrayList.size()) {

                        sum = this.intArrayList.get(i) + remainder;
                        result = String.valueOf(String.format("%09d",sum)) + result;
                    }else{
                        sum = this.intArrayList.get(i) + bint.intArrayList.get(i) + remainder;
                        if (sum >= BILLION) {
                            sum -= BILLION;
                            result = String.valueOf(String.format("%09d", sum)) + result;
                            remainder = 1;
                        }else{
                            result = String.valueOf(String.format("%09d", sum)) + result;
                            remainder = 0;
                        }
                    }
                }
                if (remainder == 1) {
                    result = "1" + result;
                }

            }else if (bint.sign == this.sign && this.compareTo(bint) == -1){
                for (int i = 0; i < bint.intArrayList.size(); i++) {
                    if (i >= this.intArrayList.size()) {
                        sum = bint.intArrayList.get(i) + remainder;
                        result = String.valueOf(sum) + result;
                    }else{
                        sum = bint.intArrayList.get(i) + this.intArrayList.get(i) + remainder;
                        if (sum >= BILLION) {
                            sum -= BILLION;
                            result = String.valueOf(String.format("%09d", sum)) + result;
                            remainder = 1;
                        }else{
                            result = String.valueOf(String.format("%09d", sum)) + result;
                            remainder = 0;
                        }
                    }
                }
            }
            if (this.sign == 1) {
                result = "-" + result;
            }
        }else{
            if (this.sign == 1) {
                if (this.compareTo(bint) == 1 || this.compareTo(bint) == 0) {
                   return(this.subtract(bint));
                }else{
                    return(bint.subtract(this));
                }
            }else if (bint.sign == 1) {
                if (this.compareTo(bint) == -1 || this.compareTo(bint) == 0) {
                    return(bint.subtract(this));
                }else{
                    return(this.subtract(bint));
                }
            }
        }
        return removeLeadingZeros(new BrobInt(result));
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
    *  @param  bint         BrobInt to subtract from this
    *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt subtract( BrobInt bint ) {
        String result = "";
        int remainder = 0;
        int sum = 0;
        if (this.compareTo(bint) == 1 || this.compareTo(bint) == 0) {
            for (int i = 0; i < this.intArrayList.size(); i++) {
                if (i >= bint.intArrayList.size()) {
                    sum = this.intArrayList.get(i) - remainder;
                    result = String.valueOf(sum) + result;
                }else{
                    sum = this.intArrayList.get(i) - bint.intArrayList.get(i) - remainder;
                    if (sum < intZERO) {
                        sum = BILLION + sum;
                        result = String.valueOf(String.format("%09d", sum)) + result;
                        remainder = 1;
                    }else{
                        result = String.valueOf(String.format("%09d", sum)) + result;
                        remainder = 0;
                    }
                }
            }
            if (this.sign == 1 && String.valueOf(result) != "0") {
                result = "-" + result;
            }
        }else{
            for (int i = 0; i < bint.intArrayList.size(); i++) {
                if (i >= this.intArrayList.size()) {
                    sum = bint.intArrayList.get(i) - remainder;
                    result = String.valueOf(sum) + result;
                }else{
                    sum = bint.intArrayList.get(i) - this.intArrayList.get(i) - remainder;
                    if (sum < intZERO) {
                        sum = BILLION + sum;
                        result = String.valueOf(String.format("%09d", sum)) + result;
                        remainder = 1;
                    }else{
                        result = String.valueOf(String.format("%09d", sum)) + result;
                        remainder = 0;
                    }
                }
            }
            if (bint.sign == 0) {
                result = "-" + result;
            }
        }
        return removeLeadingZeros(new BrobInt(result));
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
    *  @param  bint         BrobInt to multiply this by
    *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt multiply( BrobInt bint ) {
        BrobInt product = ZERO;
        BrobInt factor1 = new BrobInt (this.internalValue);
        BrobInt factor2 = new BrobInt (bint.internalValue);
        int productsign = 0;
        if (this.sign != bint.sign) {
            productsign = 1;
            factor1.sign = 0;
            factor2.sign = 0;
        }
        if (factor1.compareTo(ZERO) == 0 || factor2.compareTo(ZERO) == 0) {
            return ZERO;
        }
        while (factor1.compareTo(ONE) == 1 || factor1.compareTo(ONE) == 0) {
            if (factor1.intArrayList.get(0) % 2 != 0) {
                product = product.add(factor2);
                System.out.println("Product:    " + product);
            }
            factor1 = factor1.divide(TWO);
            factor2 = factor2.add(factor2);
            System.out.println("Factor1:    " + factor1 + "     factor2:    " + factor2);
        }
        if (productsign == 1) {
            product = new BrobInt ("-" + product);
        }
        return product;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
    *  @param  bint         BrobInt to divide this by
    *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt divide( BrobInt bint ) {
        String powerOfTen = "0";
        String culmination = "1";
        BrobInt quotient = ZERO;
        BrobInt dividendTemp = new BrobInt (this.internalValue);
        BrobInt divisorTemp = new BrobInt (bint.internalValue);
        if (this.compareTo(ZERO) == 0 || bint.compareTo(ZERO) == 0) {
            return ZERO;
        }
        while (dividendTemp.compareTo(divisorTemp) == 1 || dividendTemp.compareTo(divisorTemp) == 0) {
            culmination = "1";
            divisorTemp.internalValue = bint.internalValue;
            while (dividendTemp.compareTo(new BrobInt (divisorTemp.internalValue + powerOfTen)) == 1) {
                divisorTemp.internalValue += powerOfTen;
                culmination = culmination + powerOfTen;
            }
            dividendTemp = dividendTemp.subtract(new BrobInt(divisorTemp.internalValue));
            quotient = quotient.add(new BrobInt(culmination));
        }
        if (bint.sign != this.sign) {
            quotient = new BrobInt ("-" + quotient.internalValue);
        }
        return quotient;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to get the remainder of division of this BrobInt by the one passed as argument
    *  @param  bint         BrobInt to divide this one by
    *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt remainder( BrobInt bint ) {
        BrobInt remainder = ZERO;
        remainder = this.subtract(this.divide(bint).multiply(bint));
        return remainder;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to compare a BrobInt passed as argument to this BrobInt
    *  @param  bint  BrobInt to compare to this
    *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
    *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
    *        It takes into account the length of the two numbers, and if that isn't enough it does a
    *        character by character comparison to determine
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public int compareTo( BrobInt bint ) {

        if (this.intArrayList.size() > bint.intArrayList.size()) {
            return 1;
        }else if (this.intArrayList.size() < bint.intArrayList.size()) {
            return -1;
        }

        for (int i = this.intArrayList.size() - 1; i >= 0; i--) {
            if (this.intArrayList.get(i).compareTo(bint.intArrayList.get(i)) > 0) {
                return 1;
            }else if (this.intArrayList.get(i).compareTo(bint.intArrayList.get(i)) < 0) {
                return -1;
            }
        }
        return 0;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to check if a BrobInt passed as argument is equal to this BrobInt
    *  @param  bint     BrobInt to compare to this
    *  @return boolean  that is true if they are equal and false otherwise
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public boolean equals( BrobInt bint ) {
        return (internalValue.equals( bint.toString() ));
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to return a BrobInt given a long value passed as argument
    *  @param  value    long type number to make into a BrobInt
    *  @return BrobInt  which is the BrobInt representation of the long
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static BrobInt valueOf( long value ) throws NumberFormatException {
        BrobInt bi = null;
        try { bi = new BrobInt( Long.valueOf( value ).toString() ); }
        catch( NumberFormatException nfe ) { throw new NumberFormatException( "\n  Sorry, the value must be numeric of type long." ); }
        return bi;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to return a String representation of this BrobInt
    *  @return String  which is the String representation of this BrobInt
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public String toString() {
        return internalValue;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to remove leading zeros from a BrobInt passed as argument
    *  @param  bint     BrobInt to remove zeros from
    *  @return BrobInt that is the argument BrobInt with leading zeros removed
    *  Note that the sign is preserved if it exists
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt removeLeadingZeros( BrobInt bint ) {
        Character sign = null;
        String returnString = bint.toString();
        int index = 0;

        if( allZeroDetect( bint ) ) {
            return bint;
        }
        if( ('-' == returnString.charAt( index )) || ('+' == returnString.charAt( index )) ) {
            sign = returnString.charAt( index );
            index++;
        }
        if( returnString.charAt( index ) != '0' ) {
            return bint;
        }

        while( returnString.charAt( index ) == '0' ) {
            index++;
        }
        returnString = bint.toString().substring( index, bint.toString().length() );
        if( sign != null ) {
            returnString = sign.toString() + returnString;
        }
        return new BrobInt( returnString );

    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to return a boolean if a BrobInt is all zeros
    *  @param  bint     BrobInt to compare to this
    *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public boolean allZeroDetect( BrobInt bint ) {
        for( int i = 0; i < bint.toString().length(); i++ ) {
            if( bint.toString().charAt(i) != '0' ) {
                return false;
            }
        }
        return true;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to display an Array representation of this BrobInt as its bytes
    *  @param   d  byte array from which to display the contents
    *  NOTE: may be changed to int[] or some other type based on requirements in code above
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public void toArray( byte[] d ) {
        //System.out.println( "Array contents: " + Arrays.toString( d ) );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to display a prompt for the user to press 'ENTER' and wait for them to do so
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public void pressEnter() {
        String inputLine = null;
        try {
            //System.out.print( "      [Press 'ENTER' to continue]: >> " );
            inputLine = input.readLine();
        }
        catch( IOException ioe ) {
            //System.out.println( "Caught IOException" );
        }

    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  the main method redirects the user to the test class
    *  @param  args  String array which contains command line arguments
    *  NOTE:  we don't really care about these, since
    * we test the BrobInt class with the BrobIntTester
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static void main( String[] args ) {
        //System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
        //System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
        //BrobInt b1 = new BrobInt(args[0]);
        BrobInt number1 = new BrobInt(args[0]);
        BrobInt number2 = new BrobInt(args[1]);
        System.out.println(number1.multiply(number2));
        System.exit( 0 );

    }
}
