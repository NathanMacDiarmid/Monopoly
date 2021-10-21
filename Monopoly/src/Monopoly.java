import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monopoly {
    private final Board board;
    private List<Player> players;
    private Dice die;
    int playerTurn;

    public Monopoly() {
        this.board = new Board();
        this.die = new Dice();
        this.players = new ArrayList<>();
        this.playerTurn = 0;
    }

    public void addPlayers(Player player) {
        this.players.add(player);
    }

    // removePlayer needs more code
    public void removePlayer() { this.players.remove(playerTurn);}

    public int roll() {
        return this.die.roll();
    }

    public Player getPlayer(){
        return this.players.get(playerTurn);
    }

    public String getPropertyInfo(){
        return this.board.getProperty(this.players.get(playerTurn).getPosition()).toString();
    }

    public Player getPropertyOwner(){
        return this.board.getProperty(this.players.get(playerTurn).getPosition()).getOwner();
    }

    public boolean playerBuy(){
        return this.players.get(playerTurn).buy(this.board.getProperty(this.players.get(playerTurn).getPosition()));
    }

    public void playerRent(){
        this.players.get(playerTurn).rent(this.board.getProperty(this.players.get(playerTurn).getPosition()));
    }

    public void payRent(){
        this.board.getProperty(this.players.get(playerTurn).getPosition()).getOwner().addMoney(this.board.getProperty(this.players.get(playerTurn).getPosition()).getRent());
    }

    public int getRent(){
        return this.board.getProperty(this.players.get(playerTurn).getPosition()).getRent();
    }

    public void play(){
        boolean running = true;
        int diceValue;

        while (running) {
            if (this.players.size() >= 4) {
                System.out.println("Sorry, max is 4 players");
                break;
            }
            System.out.println("Please input your name:");
            System.out.print(">>> ");

            Scanner playerInput = new Scanner(System.in);
            String name = playerInput.nextLine();

            this.players.add(new Player(name));

            System.out.println("Add another player? Enter 'yes', 'no' or 'quit'");
            System.out.print(">>> ");

            Scanner morePlayers = new Scanner(System.in);
            String addPlayers = morePlayers.nextLine();

            switch (addPlayers) {
                case "yes":
                    System.out.println("Great! Lets add some friends!");
                    break;
                case "no":
                    running = false;
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Command not recognized");
                    break;
            }
        }

        //Check to make sure there are at least 2 players
        if(this.players.size() < 2){
            System.out.println("Game was not initialized with enough players, EXITING");
            return;
        }
        else{
            System.out.println("Welcome to Monopoly, let's play");
            running = true;
        }

        while(running){
            System.out.println(this.getPlayer().getName() + " it is your turn");

            //Get user input
            Scanner rollInput = new Scanner(System.in);
            System.out.println("Enter 'roll' to roll the Dice or 'quit' to quit the game");
            System.out.print(">>> ");
            String input = rollInput.nextLine();

            //Check user input
            if(input.equals("roll")){
                diceValue = this.roll();
                System.out.println("You rolled a " + diceValue);
                this.getPlayer().addPosition(diceValue);

                System.out.println("You landed on " + getPropertyInfo());

                // check if property is not owned
                if(this.getPropertyOwner() == null){
                    // check if lands on Go
                    if (this.board.getProperty(this.getPlayer().getPosition()).equals(this.board.getProperty(0))) {
                        continue;
                    }
                    System.out.println("Would you like to buy this property? You currently have $"
                            + this.getPlayer().getMoney() + "\t Enter 'yes' to Buy or 'no' to continue playing");
                    System.out.print(">>> ");

                    //Get user input
                    Scanner buyInput = new Scanner(System.in);
                    String inputBuy = buyInput.nextLine();

                    //Check user input
                    if(inputBuy.equals("yes")){
                        //give option to buy property
                        if(this.playerBuy()) {
                            System.out.println("You bought the Property!");
                        }
                        else{
                            System.out.println("You do not have enough money to buy this property");
                        }
                    }
                    else if(inputBuy.equals("no")){
                        System.out.println("You did not buy the Property!");
                    }
                }
                else{
                    // if this player is not the owner
                    if(this.getPlayer() != this.getPropertyOwner()) {
                        //pay rent
                        this.playerRent();
                        this.payRent();
                        System.out.println("You paid $" + this.getRent() + " of rent to " + this.getPropertyOwner().getName());
                    }
                }

            }
            else if(input.equals("quit")){
                running = false;
                continue;
            }
            else{
                System.out.println("Command not recognized");
                continue;
            }

            //Check to see if the current player has run out of money
            if(getPlayer().getMoney() <= 0 ){
                this.removePlayer();
                //Check to see if there is only one player left, as if there is they've won
                if(this.players.size() == 1){
                    System.out.println("Game over " + this.players.get(0).getName() + " has won");
                    running = false;
                    continue;
                }
            }

            playerTurn = (playerTurn + 1) % players.size();
        }
    }

    public static void main(String[] args) {

        Monopoly monopoly = new Monopoly();

        monopoly.play();

    }
}
