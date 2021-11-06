import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonopolyView extends JFrame {

    private MonopolyModel model;
    private Container pane;
    private final int BOARDLENGTH = 6;

    public MonopolyView(){
        super("Monopoly");

        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        model = new MonopolyModel();

        model.addMonopolyView(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);

        this.createBoard();
        this.playerSetup();

        this.setVisible(true);
    }

    private void createBoard(){
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

        for(int i = 0; i < panels.size(); i++){
            for(int j = 0; j < BOARDLENGTH; j++){
                JButton button = new JButton(model.getBoard().getProperty(j + BOARDLENGTH * i).getName());
                button.setSize(100, 100);
                button.setActionCommand((j + BOARDLENGTH * i) + "");
                button.addActionListener(mbc);
                panels.get(i).add(button);
            }
        }
    }

    private void playerSetup(){
        int numberOfPlayers = 0;
        JPanel mainPanel = new JPanel();
        mainPanel.add(new TextField("Enter number of players:"));
        JTextField getPlayers = new JTextField(10);
        mainPanel.add(getPlayers);
        int result = JOptionPane.showOptionDialog(this, mainPanel, "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (result == JOptionPane.DEFAULT_OPTION) {
            numberOfPlayers = Integer.parseInt(getPlayers.getText());
        }

        for(int i = 0; i < numberOfPlayers; i++){
            JPanel playerAddPanel = new JPanel();
            playerAddPanel.add(new TextField("Enter player name"));
            JTextField getName = new JTextField(10);
            playerAddPanel.add(getName);
            result = JOptionPane.showOptionDialog(this, mainPanel, "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (result == JOptionPane.DEFAULT_OPTION) {

            }
        }
    }

    public static void main(String[] args) {
        new MonopolyView();
    }

}
