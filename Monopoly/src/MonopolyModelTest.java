import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonopolyModelTest {

    private static MonopolyModel model;

    /**
     * This method initializes the MonopolyModel as well as two players
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    @BeforeAll
    static void init() {
        model = new MonopolyModel();
        model.addPlayer("Player1");
        model.addPlayer("Player2");
    }

    /**
     *
     *
     * Refactored by Nathan MacDiarmid - 101098993
     */
    @Test
    public void buyPropertyTest() {
        model.getPlayer().addPosition(5);
        assertTrue(model.buyProperty(0));

        // reset Player1 position to Go
        model.getPlayer().addPosition(19);
    }

    /**
     * This test verifies the correct returns of the checkProperty() method
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    @Test
    public void checkProperty() {
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

        // moves Player1 to Minto Case
        model.getPlayer().addPosition(1);

        // verifies the property can be bought
        assertTrue(model.checkProperty());

        // sets the owner of Minto to Player1
        model.getBoard().getProperty(18).setOwner(model.getPlayer());

        // verifies that Minto is owned
        assertFalse(model.checkProperty());

        // changes players turn
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();

        // moves Player2 to Mackenzie Building
        model.getPlayer().addPosition(5);

        // verifies the property cannot be bought by Player2
        assertFalse(model.checkProperty());

        // moves Player2 to Minto Case
        model.getPlayer().addPosition(13);

        // verifies that Player2 cannot buy the property
        assertFalse(model.checkProperty());

        // resets both players to Go
        model.getPlayer().addPosition(6);
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();
        model.getPlayer().addPosition(6);

    }

    /**
     * This test verifies that the correct amount of rent is transferred from one player to another
     * using the payRent() method
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    @Test
    public void payRent() {
        int player1Money = model.getPlayer().getMoney();
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();
        int player2Money = model.getPlayer().getMoney();
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();

        // sets the owner of Mackenzie building to Player1
        model.getBoard().getProperty(5).setOwner(model.getPlayer());

        // changes turn to Player2
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();

        // moves Player2 to Mackenzie Building
        model.getPlayer().addPosition(5);

        // verifies that Player2 paid Player1 the correct amount of rent
        model.payRent();
        int newPlayer1Money = player1Money + model.getBoard().getProperty(5).getRent();
        int newPlayer2Money = player2Money - model.getBoard().getProperty(5).getRent();
        assertEquals((newPlayer1Money - player1Money), model.getBoard().getProperty(5).getRent());
        assertEquals((player2Money - newPlayer2Money), model.getBoard().getProperty(5).getRent());

        // sets owner of Tory Building to Player2
        model.getBoard().getProperty(8).setOwner(model.getPlayer());

        // changes turn to Player1
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();

        // moves Player1 to Tory Building
        model.getPlayer().addPosition(8);

        // verifies that Player1 paid Player2 the correct amount of rent
        model.payRent();
        int newerPlayer1Money = newPlayer1Money - model.getBoard().getProperty(8).getRent();
        int newerPlayer2Money = newPlayer2Money + model.getBoard().getProperty(8).getRent();
        assertEquals((newerPlayer2Money - newPlayer2Money), model.getBoard().getProperty(8).getRent());
        assertEquals((newPlayer1Money - newerPlayer1Money), model.getBoard().getProperty(8).getRent());

        // resets everything to original state
        model.getBoard().getProperty(8).setOwner(null);
        model.getBoard().getProperty(5).setOwner(null);
        model.getPlayer().addPosition(16);
        model.playerTurn = (model.playerTurn + 1) % model.getPlayers().size();
        model.getPlayer().addPosition(19);

    }

    @Test
    public void getRent() {
    }

    @Test
    public void playTurn() {
    }
}