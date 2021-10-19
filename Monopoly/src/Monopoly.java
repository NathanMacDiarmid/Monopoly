import java.util.ArrayList;
import java.util.List;

public class Monopoly {
    private Board board;
    private List<Player> players;
    private Dice die;

    public Monopoly() {
        this.board = new Board();
        this.die = new Dice();
        this.players = new ArrayList<>();
    }

    public void addPlayers(Player player) {
        this.players.add(player);
    }

    public Player getPlayer(String name) {
        for (Player value : players) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public int roll() {
        return this.die.roll();
    }

    public Board getBoard() {
        return this.board;
    }

    public void checkAvailable(Property property, Player player) {
        if (property.getOwner() == null) {
            player.buy(property);
        }
        else {
            property.getOwner().addMoney(property.getRent());
            player.removeMoney(property.getRent());
        }
    }

    public static void main(String[] args) {

        Monopoly monopoly = new Monopoly();

        Player p1 = new Player("Nathan");
        Player p2 = new Player("Tao");

        monopoly.addPlayers(p1);
        monopoly.addPlayers(p2);

        p1.addPosition(monopoly.roll());
        p2.addPosition(monopoly.roll());
        p2.addPosition(monopoly.roll());

        System.out.println(p1.getPosition());
        System.out.println(p2.getPosition());


        p1.addPosition(monopoly.roll());

        System.out.println(p1.getPosition());

        System.out.println(monopoly.board.getProperty(p1.getPosition()));

        monopoly.checkAvailable(monopoly.board.getProperty(p1.getPosition()), p1);

        System.out.println(monopoly.board.getProperty(p1.getPosition()));

        p1.addPosition(monopoly.roll());

        System.out.println(p1.getPosition());

        p1.addPosition(monopoly.roll());

        System.out.println(p1.getPosition());

    }
}
