import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonopolyModelTest {

    @Test
    public void buyPropertyTest() {
        MonopolyModel model = new MonopolyModel();
        model.addPlayer("Player1");
        model.addPlayer("Player2");
        model.getPlayer().addPosition(5);
        assertEquals(true, model.buyProperty(0));
    }

    @Test
    public void checkProperty() {
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