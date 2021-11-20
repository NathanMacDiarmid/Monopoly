public class Railroad extends Property {
    /**
     * Basic constructor for Railroad
     * @param name the name of the Railroad of type String
     * @param cost the cost of the Railroad of type int
     * @param rent the cost of rent of the Railroad of type int
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */

    public Railroad(String name, int cost, int rent) {
        super(name + "\n" + "(Railroad)", cost, rent);
    }

    /**
     * Overrides the updateRent() setter method in the Property parent class
     * Updates the amount of rent that needs to be paid on a Railroad depending on the amount of
     * Railroads that are owned.
     * @param amount the amount of Railroads a player owns.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    @Override
    public void updateRent(int amount) {
        if (amount == 2) {
            super.updateRent(50);
        }

        else if (amount == 3) {
            super.updateRent(100);
        }

        else if (amount == 4) {
            super.updateRent(200);
        }
    }

    public Class<? extends Railroad> getClassType() {
        return this.getClass();
    }

    /**
     * Overrides the toString() method
     * @return the class instance description in a String
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    @Override
    public String toString() {
        return "Railroad{" +
                "name=" + super.getName().replace("(Railroad)", "") +
                "cost=" + super.getCost() +
                ", rent=" + super.getRent() +
                ", owner=" + super.getOwner() +
                "" +
                '}' +
                "\n" +
                "\n" +
                "If a player owns 2 Railroads, rent is 50" +
                "\n" +
                "If a player owns 3 Railroads, rent is 100" +
                "\n" +
                "If a player owns all 4 Railroads, rent is 200";
    }
}
