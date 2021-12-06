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
    private static final int BOARDSIZE = 32;

    /**
     * This constructor will set up the GUI as well as initialize all attributes, it will call
     * playerSetup and createBoard to handle most of the GUI creation.
     *
     * Created and documented by Matthew Belanger - 101144323
     * Refactored by Tao Lufula - 101164153
     */
    public MonopolyView(){
        super("Monopoly");

        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        if (this.loadSavedGame() == JOptionPane.YES_OPTION) {
            model = MonopolyModel.importFromXmlFile();

            assert model != null;
            model.addMonopolyView(this);
            propertyButtons = new ArrayList<JButton>();

            turnLabel = new JLabel();

            this.createBoard();
            this.updateStatus();
        }
        else {

            model = new MonopolyModel(chooseBoardType());

            model.addMonopolyView(this);
            propertyButtons = new ArrayList<JButton>();

            turnLabel = new JLabel();

            this.playerSetup();
            this.createBoard();
        }

        setupSave();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);

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

        //Add player turn label to center of board
        JPanel centerPanel = new JPanel(new BorderLayout());
        turnLabel.setText(model.getPlayer().getName() + " it is your turn");
        centerPanel.setPreferredSize(new Dimension(400, 400));
        centerPanel.add(turnLabel, BorderLayout.CENTER);

        //Add roll dice button to bottom of board.
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


        //Add a button to allow the player to buy a house or hotel
        HouseAndHotelController hhc = new HouseAndHotelController(model);
        JButton houseAndHotelButton = new JButton("Buy House or Hotel");
        houseAndHotelButton.addActionListener(hhc);
        houseAndHotelButton.setPreferredSize(new Dimension(180, 100));
        centerPanel.add(houseAndHotelButton, BorderLayout.WEST);

        pane.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * This method handles the player setup, it will first ask for the number of players playing and then
     * for each of their names.
     *
     * Created and documented by Matthew Belanger - 101144323, Tao Lufula - 101164153
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
                addPlayerNames(numberOfPlayers);
                validSetup = true;
            }
        }
    }

    /**
     * Sets up the JMenuItem that allows the ability to save the game.
     *
     * Created and documented by Tao Lufula - 101164153
     */
    public void setupSave() {
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        JMenu menu = new JMenu("Menu");
        menubar.add(menu);

        JMenuItem save = new JMenuItem("Save Game");
        MonopolyViewController mvc = new MonopolyViewController(model, this);
        save.addActionListener(mvc);
        save.setEnabled(true);
        menu.add(save);
    }


    /**
     * This method allows players to choose between four different board types.
     *
     * - Carleton
     * - Canada
     * - Europe
     * - World
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public int chooseBoardType() {
        String[] options = {"Carleton", "Canada", "Europe", "World"};

        int x = JOptionPane.showOptionDialog(null, "Please select the type of board you want to play!",
                "Board Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
                options[0]);
        return x;
    }

    /**
     *This method allows players to save the current game
     *
     * @return selected option
     *
     * Created and documented by Tao Lufula - 101164153
     */
    public int saveGame() {
        String[] options = {"YES", "NO"};

        int s = JOptionPane.showOptionDialog(null, "Would you like to save this game?",
                "Saved Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        return s;
    }

    /**
     *This method allows players to choose between continuing an old game and starting a new one
     *
     * @return selected option
     *
     * Created and documented by Tao Lufula - 101164153
     */
    public int loadSavedGame() {
        String[] options = {"Load", "New Game"};

        int z = JOptionPane.showOptionDialog(null, "Would you like to continue the previously saved game?",
                "Load Saved Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        return z;
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

        passGo();

        if (model.checkProperty()) {
            int input = JOptionPane.showOptionDialog(this, "Would you like to buy this property?"
                    + "\n" + model.getPropertyInfo(),
                    "Buy Property", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, null, null);
            if (!model.buyProperty(input)) {
                JOptionPane.showMessageDialog(this, "You did not have enough money to buy the property.");
            }

            else {
                checkRailroad();
                JOptionPane.showMessageDialog(this, "You now have " + handleDifferentCurrencies()
                        + " " + model.getPlayer().getMoney());
            }

        }
        else if (!model.checkProperty()) {
            payRent();
            passGo();
        }
        else if (model.checkIfInJail()) {
            PlayerJailed();
        }

        else if (model.checkIfInJail()) {
            JOptionPane.showMessageDialog(this, "You did not roll doubles.");
        }

    }

    /**
     * Creates a JOptionPane displaying what properties the AI bought on their turn.
     *
     * @param playerName
     * @param propertyName
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public void AIBuy(String playerName, String propertyName){
        JOptionPane.showMessageDialog(this, playerName + " has bought " + propertyName);
    }

    /**
     * Creates a JOptionPane displaying that the player passed Go and collects $200.
     * Reduces code smells.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void passGo() {
        if(model.getPlayer().getPositionTracker() > BOARDSIZE) {
            model.getPlayer().addMoney(200);
            JOptionPane.showMessageDialog(this, "You passed over Go! and collected " + handleDifferentCurrencies() + "200");
        }
        else if (model.getBoard().getProperty(model.getPlayer().getPosition()) instanceof Go) {
            model.getPlayer().addMoney(200);
            JOptionPane.showMessageDialog(this, "You landed on Go! and collected " + handleDifferentCurrencies() + "200");
        }
    }

    /**
     * Checks and updates Railroad rent.
     * Creates a JOptionPane letting the player know.
     * Reduces code smells.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void checkRailroad() {
        if (model.getBoard().getProperty(model.getPlayer().getPosition()) instanceof Railroad) {
            model.setRailroadRent();
            JOptionPane.showMessageDialog(this, "Rent on your Railroads is: " + model.getBoard().getProperty(model.getPlayer().getPosition()).getRent());
        }
    }

    /**
     * Displays a JOptionPan if a player has been sent to Jail.
     * Reduces code smells.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void PlayerJailed() {
        if (model.getPlayer() instanceof AI) {
            JOptionPane.showMessageDialog(this, model.getPlayer().getName() + " went to jail.");
        }
        else {
            JOptionPane.showMessageDialog(this, "Go directly to jail! \nDo not pass Go and do not collect " +
                    handleDifferentCurrencies() + "200!" +
                    "\nRoll doubles or wait three turns.");
        }
    }

    /**
     * Displays a JOptionPane if a player pays rent to another player or AI.
     * Reduces code smells.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void payRent() {
        if ((!model.handleEmptyProperties()) && (model.getPlayer() != model.getPropertyOwner())) {
            JOptionPane.showMessageDialog(this, "You just paid " + handleDifferentCurrencies() +
                    (model.getBoard().getProperty(model.getPlayer().getPosition()).getRent()) + " in rent to " + model.getPropertyOwner());
        }
    }

    /**
     * This method handles the input of players' names.
     * Reduces code smells.
     *
     * @param numberOfPlayers the amount of players that are playing.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public void addPlayerNames(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            if(i == 0) {
                JPanel playerAddPanel = new JPanel();

                JTextField enterPlayersName = new JTextField("Enter player name");
                enterPlayersName.setEditable(false);
                playerAddPanel.add(enterPlayersName);

                JTextField getName = new JTextField(10);
                playerAddPanel.add(getName);
                JOptionPane.showOptionDialog(this, playerAddPanel, "Add first player", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                model.addPlayer(getName.getText());
            }
            else{
                Object[] options = {"Add Player", "Add AI"};

                JPanel playerAddPanel = new JPanel();

                JTextField enterPlayersName = new JTextField("Enter player name");
                enterPlayersName.setEditable(false);
                playerAddPanel.add(enterPlayersName);

                JTextField getName = new JTextField(10);
                playerAddPanel.add(getName);
                int result = JOptionPane.showOptionDialog(this, playerAddPanel, "Add additional players", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

                if(result == JOptionPane.YES_OPTION) {
                    model.addPlayer(getName.getText());
                }
                else if(result == JOptionPane.NO_OPTION){
                    model.addAI(getName.getText() + "_AI");
                }
            }
        }
    }

    /**
     * Handles the String output when playing different types of boards.
     * @return a String type of the currency for said board.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    public String handleDifferentCurrencies() {
        if (model.getBoard().getType() == 0) {
            return "¢";
        }
        else if (model.getBoard().getType() == 1) {
            return "$";
        }
        else {
            return "€";
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
