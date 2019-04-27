import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  User inputs a number that is then collated.
*  @param  args  String array which contains command line arguments
*  NOTE:  args[0] must be positive.
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Collatz{
	public static void main (String[] args) {
		int count = 0;

		BrobInt value = new BrobInt(args[0]);
		if (value.sign != 0) {
			throw new UnsupportedOperationException ("Must be positive number.");
		}
		while (value.compareTo(BrobInt.ONE) == 1) {
			System.out.println("Current Value:    " + value);
			if (value.remainder(BrobInt.TWO) != BrobInt.ZERO) {
				value = (value.multiply(BrobInt.THREE)).add(BrobInt.ONE);
			}else{
				value = value.divide(BrobInt.TWO);
			}
			count++;
		}
		System.out.println("Number of Steps:  " + count);
	}
}
