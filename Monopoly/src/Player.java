public class Player {
    /**
    This is the Player class.

    These are the players that move around the board buying
    and selling Properties.

    Has attributes
    @attribute name is the name of the Player
    @attribute  money is the amount of money the Player has
    money is initialized to $1500 always
    @attribute position is where the Player is on the board
     */
    private String name;
    private int money;
    private int position;

    /**
     * Default constructor for Player
     * @param name the name of the Player
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public Player(String name) {
        this.name = name;
        this.money = 1500;
        this.position = 0;
    }

    /**
     * Default getter for the @attribute name
     * @return the name of the Player
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public String getName() {
        return name;
    }

    /**
     * Default getter for the @attribute position
     * @return the position of the Player on the board
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Adds the dice roll to the @attribute position
     * @param position the integer returned from the Dice.roll() method
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     * Edited and enhanced by Matthew Belanger - 101144323
     */
    public void addPosition(int position) {
            this.position = (this.position + position) % 23;
    }

    /**
     * Default getter for the @attribute money
     * @return the int amount of money a Player has
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * This method first checks that the player has enough money to buy the Property, if they do then the
     * Property's owner is set to this Player and the cost of the Property us subtracted from the Player's money
     * @param property
     * @return boolean which indicates whether the buy was successful or not
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public boolean buy(Property property) {
        if(property.getCost() > this.money){
            return false;
        }
        else {
            property.setOwner(this);
            this.money -= property.getCost();
            return true;
        }
    }

    /**
     * This method subtracts rent amount from the player
     *
     * @param property
     *
     * Created and documented by Tao Lufula - 101164153
     */
    public void rent(Property property){
        this.money -= property.getRent();
    }

    /**
     * Overrides the toString() default method to output Player
     * attributes and and class type
     * @return String of Player attributes
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", position=" + position +
                '}';
    }
}
