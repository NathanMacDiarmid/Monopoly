public class Player {
    private String name;
    private int money;
    private int position;

    public Player(String name) {
        this.name = name;
        this.money = 1500;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return this.position;
    }

    public void addPosition(int position) {
            this.position = (this.position + position) % 23;
    }

    public int getMoney() {
        return money;
    }

    public void removeMoney(int money) {
        this.money += money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

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
    public void rent(Property property){
        this.money -= property.getRent();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", position=" + position +
                '}';
    }
}
