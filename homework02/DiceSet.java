public DiceSet (int k, int n) {
//Constructor for a set of k dice each with n-sides (k >= 1 and n >= 4).
    public int sum() {
//Returns the present sum of this set of dice.
//This method should return an integer, not a long.
    }
    public void roll() {
//Randomly rolls all of the dice in this set; returns void since it just sets the values.
//Use either of the "toString()" methods to get the values in the set.
    }
    public void rollIndividual(int i) {
//Randomly rolls only the i-th die in this set [indexed from zero].
    }
    public int getIndividual(int i) {
//Gets the value of the i-th die in this set.
    }
    public String toString() {
//Returns a string-y representation of this set of dice, e.g., "[3][9][12][4]".
    }
    public static String toString(DiceSet ds) {
//Classwide version of the preceding instance method.
    }
    public boolean isIdentical(DiceSet ds) {
//Returns true if this set is identical to the set ds passed as an argument.
    }
    public static void main(String[] args) {
//The built-in test program for this class.
    }
}
