import java.util.ArrayList;
import java.util.List;

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
     @attribute propertiesOwned is a List of properties owned by this player
     @attribute jailed is a boolean attribute that determines whether each Player is in jail.
     */
    private String name;
    private int money;
    private int position;
    private int positionTracker;
    private List<Property> propertiesOwned;
    private boolean jailed;
    private static final int BOARDSIZE = 32;


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
        this.propertiesOwned = new ArrayList<>();
        this.positionTracker = 0;
        boolean jailed = false;
    }

    public List<Property> getPropertiesArray() {
        return this.propertiesOwned;
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
     * Default getter for the jailed attribute.
     * @return the boolean jailed attribute
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public boolean getJailed() {
        return this.jailed;
    }

    /**
     * Default setter for the jailed attribute.
     * @param jailed the boolean that jailed is to be set to.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    /**
     * Adds the dice roll to the @attribute position
     * @param position the integer returned from the Dice.roll() method
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     * Edited and enhanced by Matthew Belanger - 101144323, Tao Lufula 101164153
     */
    public void addPosition(int position) {
        if (!jailed) {
            this.positionTracker += position;
            this.position = (this.position + position) % BOARDSIZE;
        }
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

    /**
     * This method adds the given amount to the Player's money
     * @param money
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * Keep track of the player on the board to know when a player has a done a full round around the board
     *
     */
    public void updatePositionTracker(){
        if(this.positionTracker >= BOARDSIZE){
            this.positionTracker = this.position;
        }
    }

    /**
     * Getter method for positionTracker
     *
     * @return int number of player's moves in one cycle
     *
     * Created and documented by Tao Lufula - 101164153
     */
    public int getPositionTracker() {
        return positionTracker;
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
            this.propertiesOwned.add(property);
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
     * This method will remove the current player's ownership from all properties they own
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void removeProperties(){
        for(int i = 0; i < this.propertiesOwned.size(); i++){
            this.propertiesOwned.get(i).setOwner(null);
        }
    }

    /**
     * This method returns a String representation of all the properties this Player owns
     * @return String of all the name of the properties this player owns
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public String getProperties(){
        String s = "Properties owned: ";

        for(int i = 0; i < this.propertiesOwned.size(); i++){
            s += "\n" + this.propertiesOwned.get(i).getName();
        }
        return s ;
    }

    /**
     * This method returns a String representation of all the properties this Player owns but in a format for the
     * HouseAndHotelController
     * @return String of all the name of the properties this player owns
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public String getPropertiesForController(){
        String s = "";

        for(int i = 0; i < this.propertiesOwned.size(); i++){
            s += "<br>" + this.propertiesOwned.get(i).getName();
        }
        return s ;
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

    public int getAmountofRailroads() {
        int count = 0;
        for (Property p: propertiesOwned) {
            if (p instanceof Railroad) {
                count++;
            }
        }
        return count;
    }

    public int getAmountOfUtilities() {
        int count = 0;
        for (Property p: propertiesOwned) {
            if (p instanceof Utilities) {
                count++;
            }
        }
        return count;
    }

    public List<Property> getPropertiesOwned() {
        return propertiesOwned;
    }


    /**
     * This method allows the player to buy a house for one of their properties.
     *
     * @param propertyName
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void buyHouse(String propertyName){
       for(int i = 0; i < propertiesOwned.size(); i++){
           if(propertiesOwned.get(i).getName().equals(propertyName)){
               propertiesOwned.get(i).setHasHouse(true);
           }
       }
       this.money -= 50;
    }

    /**
     * This method allows the player to buy a hotel for one of their properties.
     *
     * @param propertyName
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void buyHotel(String propertyName){
        for(int i = 0; i < propertiesOwned.size(); i++){
            if(propertiesOwned.get(i).getName().equals(propertyName)){
                propertiesOwned.get(i).setHasHotel(true);
            }
        }
        this.money -= 100;
    }
}
