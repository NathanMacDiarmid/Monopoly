import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyViewController implements ActionListener {

    private MonopolyView view;
    private MonopolyModel model;

    public MonopolyViewController(MonopolyModel model, MonopolyView view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedMenu = e.getActionCommand();

        if(selectedMenu == "Save Game"){
            if (view.saveGame() == JOptionPane.YES_OPTION) {
                model.exportToXmlFile();
            }
        }
        if(selectedMenu == "Load Previous Game"){
            if (view.loadSavedGame() == JOptionPane.YES_OPTION) {
                model.importFromXmlFile();
            }
        }
    }
}
