public class Utilities extends Property{

    public Utilities (String name, int cost, int rent) {
        super(name, cost, rent);
    }


    @Override
    public String toString() {
        return "Utility{" +
                "name=" + super.getName()+
                "cost=" + super.getCost() +
                ", rent=" + super.getRent() +
                ", owner=" + super.getOwner() +
                "" +
                '}' +
                "\n" +
                "\n" +
                "If " + super.getOwner()+ " owns 1 utility, rent is player roll * 4 " +
                "\n" +
                "If " + super.getOwner()+ " owns 2 utilities, rent is player roll * 10 \n";
    }
}
