public class FreeParking extends Property {
    /**
     * This class is so the model can tell when a player lands on this square not to do anything
     * as free parking does nothing in monopoly its just a square players can land on.
     * @param name
     * @param cost
     * @param rent
     */
    public FreeParking (String name, int cost, int rent) {
        super(name, cost, rent);
    }

    /**
     * Override toString() method
     * Displays Property information in String format
     * @return Property information including attributes and class type
     */
    @Override
    public String toString() {
        return "Free Parking{" +
                "name='" + super.getName() + '\'' + "}";
    }
}
