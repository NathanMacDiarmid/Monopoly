import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceController implements ActionListener {
    /**
     * The DiceController class
     *
     * This class handles when the dice button is pressed.
     */

    MonopolyModel model;
    int rollValue;

    public DiceController(MonopolyModel model) {
        this.model = model;
    }

    /**
     * This method handles when the dice button is pressed it will perfrom a dice roll and display the value
     * as well as calling the playerTurn method from the model.
     * @param e
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        rollValue = model.roll();
        JOptionPane.showMessageDialog(null, "You rolled a " + rollValue);
        model.playTurn(rollValue);
    }
}
