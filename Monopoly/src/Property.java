public class Property {
    private String name;
    private int cost;
    private int rent;
    private Player owner;

    public Property(String name, int cost, int rent) {
        this.name = name;
        this.cost = cost;
        this.rent = rent;
        this.owner = null;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public int getRent() {
        return this.rent;
    }

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
