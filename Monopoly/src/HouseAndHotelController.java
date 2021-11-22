import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HouseAndHotelController implements ActionListener {
    /**
     * This class handles when the Buy House or Hotel button is pressed.
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    private MonopolyModel model;

    public HouseAndHotelController(MonopolyModel model) {
        this.model = model;
    }

    /**
     * This method handles when the button is pressed it will then give the user the option to buy a house or hotel
     * for any of the properties they own. It will check to see if they have enough money and if they have the other
     * requirments to buy a house or hotel.
     *
     * @param e
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean valid = false;
        boolean checkName = false;
        int position = 0;

        while (!valid) {
            Object[] options = {"Buy House ($50)", "Buy Hotel ($100)", "Cancel"};

            JPanel mainPanel = new JPanel();
            mainPanel.add(new JLabel("<html> Enter the name of the property" + model.getPlayer().getPropertiesForController()));
            JTextField getProperty = new JTextField(10);
            mainPanel.add(getProperty);

            int result = JOptionPane.showOptionDialog(null, mainPanel, "Buy House or Hotel", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

            if(result == JOptionPane.CANCEL_OPTION){
                return;
            }

            for(int i = 0; i < model.getPlayer().getPropertiesOwned().size(); i++){
                if(model.getPlayer().getPropertiesOwned().get(i).getName().equals(getProperty.getText())){
                    checkName = true;
                    position = i;
                }
            }

            if(!checkName){
                JOptionPane.showMessageDialog(null, "Invalid property name entered");
                continue;
            }
            else if (result == JOptionPane.YES_OPTION) {
                if (model.getPlayer().getMoney() < 50) {
                    JOptionPane.showMessageDialog(null, "Sorry, you don't have enough money for this");
                    continue;
                }
                if(model.getPlayer().getPropertiesOwned().get(position).hasHouse()){
                    JOptionPane.showMessageDialog(null, "Sorry, you already have a house on this property");
                    continue;
                }
                if(model.getPlayer().getPropertiesOwned().get(position) instanceof Railroad || model.getPlayer().getPropertiesOwned().get(position) instanceof Utilities){
                    JOptionPane.showMessageDialog(null, "Sorry, you can't buy a house on this property type");
                    continue;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Congrats you bought a house for " + getProperty.getText());
                    model.getPlayer().buyHouse(getProperty.getText());
                    valid = true;
                }
            }
            else if (result == JOptionPane.NO_OPTION) {
                if (model.getPlayer().getMoney() < 100) {
                    JOptionPane.showMessageDialog(null, "Sorry, you don't have enough money for this");
                    continue;
                }
                if(!model.getPlayer().getPropertiesOwned().get(position).hasHouse()){
                    JOptionPane.showMessageDialog(null, "Sorry, you need a house on this property before you can buy a hotel");
                    continue;
                }
                if(model.getPlayer().getPropertiesOwned().get(position).hasHotel()){
                    JOptionPane.showMessageDialog(null, "Sorry, you already have a hotel on this property");
                    continue;
                }
                if(model.getPlayer().getPropertiesOwned().get(position) instanceof Railroad || model.getPlayer().getPropertiesOwned().get(position) instanceof Utilities){
                    JOptionPane.showMessageDialog(null, "Sorry, you can't buy a hotel on this property type");
                    continue;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Congrats you bought a house for " + getProperty.getText());
                    model.getPlayer().buyHotel(getProperty.getText());
                    valid = true;
                }
            }
        }
    }
}
