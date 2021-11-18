import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonopolyView extends JFrame {

    /**
     * The MonopolyView class
     *
     * This class is the GUI of the game, it will handle all of the JFrame components that make up our GUI.
     * It will rely on the MonopolyModel to handle all of the logic. It will also use the Controller classes
     * to handle the input from the buttons.
     *
     */
    private final MonopolyModel model;
    private final Container pane;
    private final JLabel turnLabel;
    private final ArrayList<JButton> propertyButtons;
    private final int BOARDLENGTH = 8;

    /**
     * This constructor will set up the GUI as well as initialize all attributes, it will call
     * playerSetup and createBoard to handle most of the GUI creation.
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public MonopolyView(){
        super("Monopoly");

        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        model = new MonopolyModel();

        model.addMonopolyView(this);
        propertyButtons = new ArrayList<JButton>();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);

        turnLabel = new JLabel();

        this.playerSetup();
        this.createBoard();

        this.setVisible(true);
    }

    /**
     * This method handles the creation of the GUI board.
     *
     * Created and documented by Matthew Belanger - 101144323 and Mehedi Mostofa - 101154128
     */
    private void createBoard(){
        //Create grids to contain the properties.
        JPanel grid1 = new JPanel(new GridLayout(1, BOARDLENGTH));
        JPanel grid2 = new JPanel(new GridLayout(BOARDLENGTH, 1));
        JPanel grid3 = new JPanel(new GridLayout(1, BOARDLENGTH));
        JPanel grid4 = new JPanel(new GridLayout(BOARDLENGTH, 1));

        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        panels.add(grid1);
        panels.add(grid2);
        panels.add(grid3);
        panels.add(grid4);

        pane.add(grid1, BorderLayout.NORTH);
        pane.add(grid2, BorderLayout.EAST);
        pane.add(grid3, BorderLayout.SOUTH);
        pane.add(grid4, BorderLayout.WEST);

        MonopolyBoardController mbc = new MonopolyBoardController(model);

        //Loop through and add all of the properties as buttons to the board on the top and right side.
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < BOARDLENGTH; j++){
                JButton button = new JButton(model.getBoard().getProperty(j + BOARDLENGTH * i).getName());
                propertyButtons.add(button);
                button.setPreferredSize(new Dimension(100, 100));
                button.setActionCommand((j + BOARDLENGTH * i) + "");
                button.addActionListener(mbc);
                panels.get(i).add(button);
            }
        }
        //Next update the bottom and left side as these need to be done in reverse order.
        for(int i = 2; i < 4; i++){
            for(int j = BOARDLENGTH - 1; j >= 0; j--){
                JButton button = new JButton(model.getBoard().getProperty(j + BOARDLENGTH * i).getName());
                propertyButtons.add(button);
                button.setPreferredSize(new Dimension(100, 100));
                button.setActionCommand((j + BOARDLENGTH * i) + "");
                button.addActionListener(mbc);
                panels.get(i).add(button);
            }
        }

        //Add starting position for all players.
        String buttonText = "<html>" + model.getBoard().getProperty(0).getName();
        for(int i = 0; i < model.getPlayers().size(); i++){
            buttonText += "<br>" + model.getPlayers().get(i).getName();
        }
        propertyButtons.get(0).setText(buttonText);

        //Add roll dice button to middle of board.
        JPanel centerPanel = new JPanel(new BorderLayout());
        turnLabel.setText(model.getPlayer().getName() + " it is your turn");
        centerPanel.setPreferredSize(new Dimension(400, 400));
        centerPanel.add(turnLabel, BorderLayout.NORTH);
        DiceController dc = new DiceController(model);
        JButton diceButton = new JButton("Roll Die");
        diceButton.addActionListener(dc);
        diceButton.setPreferredSize(new Dimension(200, 200));
        centerPanel.add(diceButton, BorderLayout.SOUTH);

        // Add Player Info button on the right side of the board.
        InfoController cpi = new InfoController(model);
        JButton infoButton = new JButton("Player Info");
        infoButton.addActionListener(cpi);
        infoButton.setPreferredSize(new Dimension(180, 100));
        centerPanel.add(infoButton, BorderLayout.EAST);
        pane.add(centerPanel, BorderLayout.CENTER);


    }

    /**
     * This method handles the player setup, it will first ask for the number of players playing and then
     * for each of their names.
     *
     * Created and documented by Matthew Belanger - 101144323, Tao - 101164153
     */
    private void playerSetup(){
        boolean validSetup = false;

        //Loop until a valid number of players has been entered.
        while(!validSetup) {
            int numberOfPlayers = 0;
            JPanel mainPanel = new JPanel();

            //Set up a JOptionPane that will get user input for number of players.
            JTextField enterNumOfPlayers = new JTextField("Enter number of players (2-4):");
            enterNumOfPlayers.setEditable(false);
            mainPanel.add(enterNumOfPlayers);

            JTextField getPlayers = new JTextField(10);
            mainPanel.add(getPlayers);
            JOptionPane.showOptionDialog(this, mainPanel, "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            try {
                numberOfPlayers = Integer.parseInt(getPlayers.getText());
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number of players, must be between 2 to 4 players");
                continue;
            }

            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                JOptionPane.showMessageDialog(this, "Invalid number of players, must be between 2 to 4 players");
            } else {
                //Get the name of all of the players and add them to the game.
                for (int i = 0; i < numberOfPlayers; i++) {
                    JPanel playerAddPanel = new JPanel();

                    JTextField enterPlayersName = new JTextField("Enter player name");
                    enterPlayersName.setEditable(false);
                    playerAddPanel.add(enterPlayersName);

                    JTextField getName = new JTextField(10);
                    playerAddPanel.add(getName);
                    JOptionPane.showOptionDialog(this, playerAddPanel, "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                    model.addPlayer(getName.getText());
                }
                validSetup = true;
            }
        }
    }

    /**
     * This method updates the view of board, it will change the label of whos turn it is, as well as where the players
     * are on the board.
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void updateStatus(){
        turnLabel.setText(model.getPlayer().getName() + " it is your turn");

        //First update the top and right side of the board
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < BOARDLENGTH; j++) {
                String text = "<html>" + model.getBoard().getProperty(j + BOARDLENGTH * i).getName();

                for(int z = 0; z < model.getPlayers().size(); z++){
                    if(model.getPlayers().get(z).getPosition() == j + BOARDLENGTH * i){
                        text += "<br>" + model.getPlayers().get(z).getName();
                    }
                }
                propertyButtons.get(j + BOARDLENGTH * i).setText(text);
            }
        }
        //Next update the bottom and left side as these need to be done in reverse order.
        for(int i = 2; i < 4; i++){
            int y = 0;
            for(int j = BOARDLENGTH - 1; j >= 0; j--){
                String text = "<html>" + model.getBoard().getProperty(j + BOARDLENGTH * i).getName();

                for(int z = 0; z < model.getPlayers().size(); z++){
                    if(model.getPlayers().get(z).getPosition() == j + BOARDLENGTH * i){
                        text += "<br>" + model.getPlayers().get(z).getName();
                    }
                }
                propertyButtons.get(y + BOARDLENGTH * i).setText(text);
                y++;
            }
        }
    }

    /**
     * The Monopoly model notifies the view whether the property can be bought.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     * Refactored by Tao Lufula - 101164153
     */
    public void checkAvailability() {
        int playerMoney = model.getPlayer().getMoney();

        if (model.checkProperty()) {
            int input = JOptionPane.showOptionDialog(this, "Would you like to buy this property?"
                    + "\n" + model.getPropertyInfo(),
                    "Buy Property", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, null, null);
            if (!model.buyProperty(input)) {
                JOptionPane.showMessageDialog(this, "You did not have enough money to buy the property.");
            }

            else {
                if (model.getBoard().getProperty(model.getPlayer().getPosition()) instanceof Railroad) {
                    model.getBoard().getProperty(model.getPlayer().getPosition()).updateRent(model.getPlayer().getAmountofRailroads());
                    JOptionPane.showMessageDialog(this, "Rent on your Railroads is: " + model.getBoard().getProperty(model.getPlayer().getPosition()).getRent());
                }
                JOptionPane.showMessageDialog(this, "You now have $" + model.getPlayer().getMoney());
            }

        }
        else if (!model.checkProperty()) {
            if((!model.handleEmptyProperties()) && (model.getPlayer() != model.getPropertyOwner())) {
                JOptionPane.showMessageDialog(this, "You just paid " +
                        (playerMoney - model.getPlayer().getMoney()) + " in rent to " + model.getPropertyOwner());
            }
        }


    }

    /**
     * This method displays a JOptionPane which displays the message that the current player has been eliminated.
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void playerEliminated(){
        JOptionPane.showMessageDialog(this, "You have been eliminated from the game");
    }

    /**
     * This method displays a JOptionPane which displays the message that the current player has won.
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void playerWin(){
        JOptionPane.showMessageDialog(this, model.getPlayer().getName()+ " has won Monopoly!!!");
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new MonopolyView();
    }

}
