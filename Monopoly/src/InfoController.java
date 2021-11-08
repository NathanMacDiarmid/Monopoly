import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoController implements ActionListener {
    MonopolyModel model;

    public InfoController(MonopolyModel model) {
            this.model = model;
        }

    /**
     * This method will create a JOptionPane when the "Player info" button is pressed.
     * It shows the current player's Name , Properties Owned.
     *
     * Created and documented by Mehedi Mostofa - 101154128 and Matthew Belanger 101144323
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "<html>" + model.getPlayer().toString() + "<br>" + model.getPlayer().getProperties());
    }
}

