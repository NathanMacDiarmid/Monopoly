import javax.swing.*;

public class AI extends Player{
    /**
     * This is the AI class it is identical to the player class the reasoning for it is so the model can
     * differentiate between a player and an AI and handle them differently.
     *
     * @param name
     */

    public AI(String name) {
        super(name);
    }

    /**
     * This method handles the AI logic of a turn, the logic is very simple as the AI will attempt to buy
     * whatever they land on.
     *
     * Created and documented by Matthew Belanger - 101144323
     * Refactored and enhanced by Nathan MacDiarmid - 101098993
     */
    public void AITurn(MonopolyModel model, MonopolyView view){
        int AIRollValue = model.roll();
        model.getPlayer().addPosition(AIRollValue);
        model.setUtilityRent(AIRollValue);
        model.goToJail();

        if(model.getPlayer().getPositionTracker() >= 32) {
            model.getPlayer().addMoney(200);
        }

        if (model.checkProperty()) {
            boolean successfulBuy = model.buyProperty(JOptionPane.YES_OPTION);
            if(successfulBuy){
                if (model.getBoard().getProperty(model.getPlayer().getPosition()) instanceof Railroad) {
                    model.setRailroadRent();
                }
                view.AIBuy(model.getPlayer().getName(), model.getBoard().getProperty(model.getPlayer().getPosition()).getName());
            }
        }
    }

}
