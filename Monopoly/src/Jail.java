public class Jail extends Property  {

    /**
     *This is the Jail class.
     *
     * The 'Jail' block sends players to jail. Players can leave the jail anytime
     * before 3 turns by rolling a double on both dices.
     *
     * Created and documented by Mehedi Mostofa - 101154128
     */

    MonopolyModel model;
    Player jailPlayer;
    Board board;

    /**
     * Default constructor for Jail
     *
     * Created and documented by Mehedi Mostofa - 101154128
     */

    public Jail(String name, int cost, int rent) {
        super(name, cost, rent);
    }

    /**
     * This method sends the player to jail if the player lands on the 'Jail' property.
     *
     * Created and documented by Mehedi Mostofa - 101154128
     *
     */

    public void goJail() {
        // goes to jail if lands on 'Jail'
        if (this.board.getProperty(this.jailPlayer.getPosition()) instanceof GoToJail) {
            System.out.println("Go to jail for next 3 turns.");
            jailPlayer.addMoney(-50); // -$50 from player's account.
        }
        else {
            model.playTurn(model.roll());
        }
    }

    /**
     * This method checks if the player can roll a double on both dices before 3 turns.
     * If not, the player remains in jail.
     * @param jailPlayer is the player
     *
     * Created and documented by Mehedi Mostofa - 101154128
     */

    public boolean leaveJail(Player jailPlayer, Dice dice){
        jailPlayer.turns++;
        System.out.print("Turn " + jailPlayer.turns);

        if(jailPlayer.turns != 3){ // checks if turns are <3
            jailPlayer.jailed = true;

            if(!dice.doubleRoll()){
                jailPlayer.addMoney(50); // get $50 back in player's account
            }

            jailPlayer.addPosition(model.roll());
        }

        return jailPlayer.jailed;

    }

}