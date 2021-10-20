import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void play(){
        boolean running = true;
        int diceValue = 0;
        int playerTurn = 0;

        //Check to make sure there are at least 2 players
        if(this.players.size() < 2){
            System.out.println("Game was not initialized with enough players, EXITING");
            return;
        }
        else{
            System.out.println("Welcome to Monopoly, let's play");
        }

        while(running){
            System.out.println(this.players.get(playerTurn).getName() + " it is your turn");

            //Get user input
            Scanner rollInput = new Scanner(System.in);
            System.out.println("Enter 'roll' to roll the Dice or 'quit' to quit the game");
            String input = rollInput.nextLine();

            //Check user input
            if(input.equals("roll")){
                diceValue = this.die.roll();
                System.out.println("You rolled a " + diceValue);
                players.get(playerTurn).addPosition(diceValue);
            }
            else if(input.equals("quit")){
                running = false;
                continue;
            }
            else{
                System.out.println("Command not recognized");
                continue;
            }

            System.out.println("You landed on " + this.board.getProperty(this.players.get(playerTurn).getPosition()).toString());

            playerTurn = (playerTurn + 1) % players.size();
        }
    }

    public static void main(String[] args) {

        Monopoly monopoly = new Monopoly();
        Player p1 = new Player("Nathan");
        Player p2 = new Player("Tao");
        monopoly.addPlayers(p1);
        monopoly.addPlayers(p2);

        monopoly.play();

    }
}
