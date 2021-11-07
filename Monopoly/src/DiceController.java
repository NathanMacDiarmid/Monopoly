import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceController implements ActionListener {
    MonopolyModel model;
    public DiceController(MonopolyModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rollValue = model.roll();
        JOptionPane.showMessageDialog(null, "You rolled a " + rollValue);
        model.playTurn(rollValue);
    }
}
