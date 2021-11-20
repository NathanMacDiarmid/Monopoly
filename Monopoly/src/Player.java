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
     */
    private String name;
    private int money;
    private int position;
    private List<Property> propertiesOwned;

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
     * Adds the dice roll to the @attribute position
     * @param position the integer returned from the Dice.roll() method
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     * Edited and enhanced by Matthew Belanger - 101144323
     */
    public void addPosition(int position) {
            this.position = (this.position + position) % 32;
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

    public List<Property> getPropertiesOwned() {
        return propertiesOwned;
    }

    public void buyHouse(String propertyName){
       for(int i = 0; i < propertiesOwned.size(); i++){
           if(propertiesOwned.get(i).getName().equals(propertyName)){
               propertiesOwned.get(i).setHasHouse(true);
           }
       }
       this.money -= 50;
    }

    public void buyHotel(String propertyName){
        for(int i = 0; i < propertiesOwned.size(); i++){
            if(propertiesOwned.get(i).getName().equals(propertyName)){
                propertiesOwned.get(i).setHasHotel(true);
            }
        }
        this.money -= 100;
    }
}
