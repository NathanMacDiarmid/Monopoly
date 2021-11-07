import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyBoardController implements ActionListener {

    MonopolyModel model;

    public MonopolyBoardController(MonopolyModel model) {
        this.model = model;
    }

    /**
     * This method will create a JOptionPane if a property button is pressed, the pane will display
     * the properties information.
     * @param e
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int i = Integer.parseInt(e.getActionCommand());
        JOptionPane.showMessageDialog(null, model.getBoard().getProperty(i).toString());
    }
}
