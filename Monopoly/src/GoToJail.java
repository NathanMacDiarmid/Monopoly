public class GoToJail extends Property {

    private Jail jail;
    static final int GOTOJAILSPACES = 14;

    /**
     * Default constructor for GoToJail
     *
     * @param name is the String name of the Property
     * @param cost is the int cost of the Property
     * @param rent is the int rent cost of the Property
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public GoToJail(String name, int cost, int rent, Jail jail) {
        super(name, cost, rent);
        this.jail = jail;
    }

    /**
     * This method sends the player to Jail
     * @param player the Player being sent to jail.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void goToJail(Player player) {
        player.addPosition(GOTOJAILSPACES);
        player.addMoney(-200); // subtract as passing Go.
        player.setJailed(true);
        jail.addToJailList(player);
    }
}
