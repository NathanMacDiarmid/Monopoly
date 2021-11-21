public class Dice {
    /**
    This is the dice that will be used to roll and move players
    around the board.

    There are two dice as there are typically two dice in Monopoly.
    They are added together when rolled.
    @attribute dice1 is random int between 1 and 6
    @attribute dice2 is a random int between 1 and 6

    Created and documented by Nathan MacDiarmid - 101098993 and Mehedi Mostofa - 101154128
     */
    private int dice1;
    private int dice2;

    /**
     * Default constructor for Dice class
     */
    public Dice() {
        this.dice1 = (int) (Math.random()*(6) + 1);
        this.dice2 = (int) (Math.random()*(6) + 1);
    }

    /**
     * Rolls the dice and adds the random ints together
     * @return the two random ints added together
     */
    public int roll() {
        this.dice1 = (int) (Math.random()*(6) + 1);
        this.dice2 = (int) (Math.random()*(6) + 1);

        return dice1 + dice2;
    }

    /**
     * Rolls the dice and checks if two dices return the same value
     * @return the check of two dice values
     */
    public boolean doubleRoll(){
        return dice1 == dice2;
    }
}
