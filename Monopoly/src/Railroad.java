public class Railroad extends Property {

    public Railroad(String name, int cost, int rent) {
        super(name + "\n" + "(Railroad)", cost, rent);
    }

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
