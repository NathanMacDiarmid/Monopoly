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
        if (this.position + position > 22) {
                this.position += position - 23;
        }
        else {
            this.position += position;
        }
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

    public void buy(Property property) {
        property.setOwner(this);
        this.money -= property.getCost();
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
