import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonopolyView extends JFrame {

    private MonopolyModel model;
    private Container pane;
    private JLabel turnLabel;
    private ArrayList<JButton> propertyButtons;
    private final int BOARDLENGTH = 6;

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
     * Created and documented by Matthew Belanger - 101144323
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

        //Loop through and add all of the properties as buttons to the board.
        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < BOARDLENGTH; j++){
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
        pane.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * This method handles the player setup, it will first ask for the number of players playing and then
     * for each of their names.
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    private void playerSetup(){
        boolean validSetup = false;

        while(!validSetup) {
            int numberOfPlayers = 0;
            JPanel mainPanel = new JPanel();
            mainPanel.add(new TextField("Enter number of players (2-4):"));
            JTextField getPlayers = new JTextField(10);
            mainPanel.add(getPlayers);
            JOptionPane.showOptionDialog(this, mainPanel, "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            numberOfPlayers = Integer.parseInt(getPlayers.getText());

            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                JOptionPane.showMessageDialog(this, "Invalid number of players, must be between 2 to 4 players");
            } else {
                for (int i = 0; i < numberOfPlayers; i++) {
                    JPanel playerAddPanel = new JPanel();
                    playerAddPanel.add(new TextField("Enter player name"));
                    JTextField getName = new JTextField(10);
                    playerAddPanel.add(getName);
                    JOptionPane.showOptionDialog(this, playerAddPanel, "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                    model.addPlayer(getName.getText());
                }
                validSetup = true;
            }
        }
    }

    public void updateStatus(){
        turnLabel.setText(model.getPlayer().getName() + " it is your turn");

        //reset all of the names to clear the current player position.
        for(int i = 0; i < propertyButtons.size(); i++){
            propertyButtons.get(i).setText(model.getBoard().getProperty(i).getName());
        }

        //Set new player positions.
        for(int i = 0; i < model.getPlayers().size(); i++){
            int position = model.getPlayers().get(i).getPosition();
            propertyButtons.get(position).setText("<html>" + model.getBoard().getProperty(position).getName() + "<br>" +model.getPlayers().get(i).getName());
        }

    }

    public static void main(String[] args) {
        new MonopolyView();
    }

}
