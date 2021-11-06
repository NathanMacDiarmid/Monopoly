import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyBoardController implements ActionListener {

    MonopolyModel model;

    public MonopolyBoardController(MonopolyModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = Integer.parseInt(e.getActionCommand());
        JOptionPane.showMessageDialog(null, model.getBoard().getProperty(i).toString());
    }
}
