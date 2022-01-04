
public class Property{
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
    private String name;
    private int cost;
    private int rent;
    private Player owner;
    private boolean hasHouse;
    private boolean hasHotel;

    public Property(){
        this.name = "";
        this.cost = 0;
        this.rent = 0;
        this.owner = null;
        this.hasHouse = false;
        this.hasHotel = false;
    }
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
        this.hasHouse = false;
        this.hasHotel = false;
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

    /**
     * Basic setter for the rent attribute
     * @param rent the new amount of rent to be charged.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void updateRent(int rent) {
        this.rent = rent;
    }

    /**
     * Default getter method for rent attribute
     * @return the int rent of the Property
     */
    public int getRent() {
        if(this.hasHotel){
            return this.rent + 30;
        }
        else if(this.hasHouse){
            return this.rent + 10;
        }
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
                ", house=" + hasHouse +
                ", hotel=" + hasHotel +
                '}';
    }

    /**
     * Setter for hasHouse.
     */
    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    /**
     * Getter for hasHouse.
     */
    public boolean hasHouse(){
        return this.hasHouse;
    }

    /**
     * Setter for hasHotel.
     */
    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    /**
     * Getter for hasHotel.
     */
    public boolean hasHotel(){
        return this.hasHotel;
    }

    /**
     * Default setter for name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Default setter for cost.
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Default setter for rent.
     * @param rent
     */
    public void setRent(int rent) {
        this.rent = rent;
    }

    /**
     * Creates tabs for the toXML method.
     *
     * @param tabs
     * @return tabs
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    private String tabGenerator(int tabs){
        String s = "";
        for(int i = 0; i < tabs; i++){
            s += "\t";
        }
        return s;
    }

    /**
     * This method returns a xml representation of the property.
     *
     * @param tabs
     * @return a String xml representation
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public String toXML(int tabs){
        String s = tabGenerator(tabs) + "<Property>\n";
        s += tabGenerator(tabs+1) + "<name>" + this.name + "</name>\n";
        s += tabGenerator(tabs+1) + "<cost>" + this.cost + "</cost>\n";
        s += tabGenerator(tabs+1) + "<rent>" + this.rent + "</rent>\n";
        s += tabGenerator(tabs+1) + "<hasHouse>" + this.hasHouse + "</hasHouse>\n";
        s += tabGenerator(tabs+1) + "<hasHotel>" + this.hasHotel + "</hasHotel>\n";
        s += tabGenerator(tabs) + "</Property>\n";
        return s;
    }
}
