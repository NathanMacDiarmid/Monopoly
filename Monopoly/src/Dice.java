public class Dice {
    private int dice1;
    private int dice2;

    public Dice() {
        this.dice1 = (int) (Math.random()*(6) + 1);
        this.dice2 = (int) (Math.random()*(6) + 1);
    }

    public int roll() {
        this.dice1 = (int) (Math.random()*(6) + 1);
        this.dice2 = (int) (Math.random()*(6) + 1);

        return dice1 + dice2;
    }
}
