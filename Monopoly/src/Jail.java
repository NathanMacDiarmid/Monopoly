import java.util.ArrayList;
import java.util.List;

public class Jail extends Property{

    private final List<Player> jailedPlayers;
    private final int[] jailedTurns = {0, 0, 0, 0};
    /**
     * Default constructor for Jail
     *
     * @param name is the String name of the Property
     * @param cost is the int cost of the Property
     * @param rent is the int rent cost of the Property
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public Jail(String name, int cost, int rent) {
        super(name, cost, rent);
        jailedPlayers = new ArrayList<>();
    }

    /**
     * This method adds the player to the list of jailedPlayers.
     * @param player the player that is going to Jail.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void addToJailList(Player player) {
        jailedPlayers.add(player);
    }

    /**
     * This method controls a players turn while in jail.
     * @param player the player whose turn it is
     * @param isDoubles checks if they rolled a double.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void inJail(Player player, boolean isDoubles) {
        int playerIndex = jailedPlayers.indexOf(player);

        jailedTurns[playerIndex]++;
        if (jailedTurns[playerIndex] != 3) {
            if (isDoubles) {
                player.setJailed(false);
                jailedPlayers.remove(player);
                jailedTurns[playerIndex] = 0;
            }
            player.setJailed(true);
        }
        else {
            player.setJailed(false);
            jailedPlayers.remove(player);
            jailedTurns[playerIndex] = 0;
        }
    }
}
