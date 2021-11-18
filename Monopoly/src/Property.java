public class Property {
    /**
     * This is the Property class
     *
     * This initializes properties that are for purchase on the board.
     *
     * @attribute name is the String name of the Property
     * @attribute cost is the int cost of the Property
     * @attribute rent is the int rent cost for the Property
     * @attribute owner is the Player owner of the Property (ie who receives rent)
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     * Further formatted,assessed code and documentation edited by Mehedi Mostofa - 101154128
     */
    private final String name;
    private final int cost;
    private int rent;
    private Player owner;

    /**
     * Default constructor for
     * @param name is the String name of the Property
     * @param cost is the int cost of the Property
     * @param rent is the int rent cost of the Property
     */
    public Property(String name, int cost, int rent) {
        this.name = name;
        this.cost = cost;
        this.rent = rent;
        this.owner = null;
    }

    /**
     * Default setter for owner attribute
     * @param player the Player that now owns the Property
     */
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Default getter method for owner attribute
     * @return the Player that currently owns the Property
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Default getter method for cost attribute
     * @return the int cost of the Property
     */
    public int getCost() {
        return this.cost;
    }

    public void updateRent(int rent) {
        this.rent = rent;
    }

    /**
     * Default getter method for rent attribute
     * @return the int rent of the Property
     */
    public int getRent() {
        return this.rent;
    }

    /**
     * Default getter method for name attribute
     * @return name of property
     */
    public String getName(){
        return this.name;
    }

    /**
     * Override toString() method
     * Displays Property information in String format
     * @return Property information including attributes and class type
     */
    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", rent=" + rent +
                ", owner=" + owner +
                '}';
    }
}
