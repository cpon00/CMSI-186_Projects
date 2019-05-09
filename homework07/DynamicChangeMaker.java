/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Program to take arguments from command line and determine the most efficient 
 *               combination of those arguments to reach a sum
 * @author    :  Carter Pon
 * Date       :  2019-05-03
 * Description:  This program will takes as input arguments a sequence of coin denominations [distinct 
 * positive integers in no particular order], followed by an arbitrary amount of money [a non-negative 
 * integer], and which outputs the optimal way of making change for that amount using those denominations 
 * [optimal meaning the minimum number of coins], or if change cannot be made, outputs the message IMPOSSIBLE.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2019-05-03  Carter Pon     Initial creation.
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

/*
 *This class is DynamicChangeMaker, a class that will take in an array of coin denomination arguments
 *and output an optimal combination of those specified denominations to reach a sum argument.
*/

public class DynamicChangeMaker {
    static int[] denominations;
    static int target;
   /**
    * In every instance of DynamicChangeMaker, determines value of target sum and value(s) of denominations.
    * @param args the number of coin denominations and the sum target value.
    * @throws IllegalArgumentException if the arguments cannot be parsed as integers or are invalid.
    */
    public DynamicChangeMaker (String[]args) throws IllegalArgumentException {
        String tempArr[] = new String[args[0].length()];
        tempArr = args[0].split(",");
        denominations = new int[tempArr.length];
        boolean goodargs = true;
        if (denominations.length <= 1) {
            goodargs = false;
        }
        for (int i = 0; i < denominations.length; i++) {
            try {
                denominations[i] = Integer.parseInt(tempArr[i]);
            }catch (Exception e) {
                goodargs = false;
            }
            if (denominations[i] <= 0) {
                goodargs = false;
            }
        }        
        target = Integer.parseInt(args[args.length - 1]);
        if (target <= 0) {
            goodargs = false;
        }
        for (int i = 0; i < denominations.length; i++) {
            for (int k = i + 1; k < denominations.length; k++) {
                if (denominations[i] == denominations[k]) {
                    goodargs = false;
                }
            }
        }
    }
        
    
    /**
     * Program determines the optimal amount of change to make a sum target.
     */
    public static Tuple makeChangeWithDynamicProgramming (int[] denominations, int target) {

        for (int i = 0; i < denominations.length; i++) {
            if (denominations[i] <= 0) {
                return Tuple.IMPOSSIBLE;
            }

            for (int k = i + 1; k < denominations.length; k++) {
                if (denominations[i] == denominations[k]) {
                return Tuple.IMPOSSIBLE;
                }
            }  
        }
        if (target <= 0) {
            return Tuple.IMPOSSIBLE;
        }
        
        
        Tuple[][] tupleArray;
        tupleArray = new Tuple[denominations.length][target + 1];
        for (int i = 0; i < denominations.length; i++) {
            for (int k = 0; k < target + 1; k++) {
                if (denominations[i] <= k) {
                        tupleArray[i][k] = new Tuple (denominations.length);
                        for (int p = 0; p < tupleArray[i][k].length(); p++) {
                            if (p == i) {
                                tupleArray[i][k].setElement(p, 1);
                                
                            }else{
                                tupleArray[i][k].setElement(p, 0);
                            }
                        }
                }else{
                    if (k == 0) {
                        tupleArray[i][k] = new Tuple (denominations.length);
                    }else{
                        tupleArray[i][k] = Tuple.IMPOSSIBLE;
                    }
                }
                if (k >= denominations[i]) {
                    if (tupleArray[i][k - denominations[i]].isImpossible()) {
                        tupleArray[i][k] = Tuple.IMPOSSIBLE;                        
                    }else{
                        tupleArray[i][k] = tupleArray[i][k].add(tupleArray[i][k - denominations[i]]);
                    }
                }
                if (i > 0) {
                    if (!tupleArray[i - 1][k].isImpossible()) {
                        if (tupleArray[i][k].isImpossible()) {
                            tupleArray[i][k] = tupleArray[i - 1][k];
                        }
                        if (tupleArray[i][k].total() > tupleArray[i - 1][k].total()) {
                            tupleArray[i][k] = tupleArray[i - 1][k];
                        }
                    }
                }
            }
        }
        return tupleArray[denominations.length - 1][target];
    }
    public static void main (String[]args) {
        DynamicChangeMaker dcm = new DynamicChangeMaker(args);  
        System.out.println(dcm.makeChangeWithDynamicProgramming(denominations, target).toString());
        System.out.println("Optimal number of coins is: " + dcm.makeChangeWithDynamicProgramming(denominations, target).total());             
    }
}