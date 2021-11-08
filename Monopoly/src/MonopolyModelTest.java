import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonopolyModelTest {

    @Test
    public void buyPropertyTest() {
        MonopolyModel model = new MonopolyModel();
        model.addPlayer("Player1");
        model.addPlayer("Player2");
        model.getPlayer().addPosition(5);
        assertTrue(model.buyProperty(0));
    }

    /**
     * This test verifies the correct returns of the checkProperty() method
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    @Test
    public void checkProperty() {
        MonopolyModel model = new MonopolyModel();
        model.addPlayer("Player1");
        model.addPlayer("Player2");

        // verifies the property Go cannot be bought
        assertFalse(model.checkProperty());

        // moves Player1 to Mackenzie building
        model.getPlayer().addPosition(5);

        // verifies the property can be bought
        assertTrue(model.checkProperty());

        // sets the owner of Mackenzie to Player1
        model.getBoard().getProperty(5).setOwner(model.getPlayer());

        // verifies the property is owned by a player
        assertFalse(model.checkProperty());

        // moves Player1 to Free Parking
        model.getPlayer().addPosition(12);

        // verifies that Free Parking cannot be bought
        assertFalse(model.checkProperty());

        // changes players turn
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();

        // moves Player2 to Mackenzie Building
        model.getPlayer().addPosition(5);

        // verifies the property cannot be bought by Player2
        assertFalse(model.checkProperty());

    }

    @Test
    public void playerRent() {
    }

    @Test
    public void payRent() {
    }

    @Test
    public void getRent() {
    }

    @Test
    public void playTurn() {
    }
}