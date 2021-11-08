import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoController implements ActionListener {
    MonopolyModel pinfo;
    private Object cpi;

    public InfoController(MonopolyModel pinfo) {
            this.pinfo = pinfo;
        }

    /**
     * This method will create a JOptionPane when the "Player info" button is pressed.
     * It shows the current player's Name , Properties Owned.
     * @param e
     *
     * Created and documented by Mehedi Mostofa - 101154128
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.cpi)){
            Player playerName = pinfo.getPlayer();
            String playerProp = pinfo.getPropertyInfo();
            JOptionPane.showMessageDialog(null, "Player : " +playerName);
            JOptionPane.showMessageDialog(null, "\n You own : " +playerProp);
        }
    }
}

