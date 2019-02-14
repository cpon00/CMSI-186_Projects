/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  File name     :  HighRoll.java
*  Purpose       :  Demonstrates the use of input from a command line for use with DiceSet.java and Die.java
*  Author        :  B.J. Johnson
*  Date          :  2017-02-14
*  Description   :
*  Notes         :  None
*  Warnings      :  None
*  Exceptions    :  None
* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Revision Histor
*  ---------------
*            Rev      Date     Modified by:  Reason for change/modification
*           -----  ----------  ------------  -----------------------------------------------------------
*  @version 1.0.0  2017-02-14  B.J. Johnson  Initial writing and release
*  @version 1.1.0  2019-02-14  Carter Pon    Creation of HighRoll method
* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class HighRoll{
    public static void main( String args[] ) {
        String diceInput = "";
        int numberOfDice = 0;
        String sidesInput = "";
        int numberOfSides = 0;
        String rollIndividualString = "";
        int rollIndividualInteger = 0;
        int highScore = 0;
        BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
        try {
            System.out.print("Number of dice: ");
            diceInput = input.readLine();
            //diceInput is a string.
            numberOfDice = Integer.parseInt(diceInput);
            //numberOfDice = diceInput converted to an integer.
            System.out.print("Number of sides on each die: ");
            sidesInput = input.readLine();
            //string.
            numberOfSides = Integer.parseInt(sidesInput);
            //numberOfDice = sidesInput converted to an integer.
        } catch (Exception e) {
            System.out.println("Invalid DiceSet.");
            return;
        }
        System.out.println("\n Welcome to High Roll!!\n" );
        System.out.println(" Press [1] to: ROLL ALL THE DICE.");
        System.out.println(" Press [2] to: ROLL A SINGLE DIE.");
        System.out.println(" Press [3] to: CALCULATE THE SCORE FOR THIS SET.");
        System.out.println(" Press [4] to: SAVE THIS SCORE AS HIGH SCORE.");
        System.out.println(" Press [5] to: DISPLAY THE HIGH SCORE.");
        System.out.println(" Press [q] to: QUIT THE PROGRAM.");
        DiceSet ds = new DiceSet(numberOfDice, numberOfSides);
        while (true) {
            System.out.print("Enter an input: ");
            String inputLine = null;
            try {
                inputLine = input.readLine();
                switch (inputLine.charAt(0)) {
                    case '1': ds.roll();
                              //rolls DiceSet and returns a string of the DiceSet.
                              System.out.println("DiceSet :" + ds.toString());
                              break;
                    case '2': System.out.print("Which die do you wish to reroll? (Starting at 0...): ");
                              //creates a new user input that determines which die to reroll.
                              //stylistically similar to the previous inputs for numberOfDice, numberOfSides.
                              rollIndividualString = input.readLine();
                              rollIndividualInteger = Integer.parseInt(rollIndividualString);
                              System.out.println("Die at "+ "DiceSet["+ (rollIndividualInteger) +"] " + "changed to " + ds.rollIndividual(rollIndividualInteger) + ".");
                              System.out.println("New DiceSet: " + ds.toString());
                              break;
                    case '3': System.out.println("Current score is: " + ds.sum());
                              //adds all values of the DiceSet
                              break;
                    case '4': if (ds.sum() > highScore) {
                              //if the current score is greater than the high score, change the high score.
                                  highScore = ds.sum();
                                  System.out.println("Wow! High Score is now: " + highScore);
                              }else{
                              //else try again...
                                  System.out.println("git gud kid... Score is too low.");
                              }
                              break;
                    case '5': System.out.println("Current high score is : " + highScore);
                              //print current high score.
                              break;
                    case 'q': System.out.println("Ending HighRoll...");
                              //break the while statement.
                              return;
                    default: System.out.println("Invalid input.");
                             //if input is anything other than specified switch cases.
                }
            }catch (Exception e) {
                System.out.println("Exception: Invalid input.");
            }
        }
    }
}
