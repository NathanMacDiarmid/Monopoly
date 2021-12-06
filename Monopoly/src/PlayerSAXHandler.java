import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PlayerSAXHandler extends DefaultHandler {
    /**
     * This class handles SAX events that are generated when parsing the players xml file.
     *
     * Created and documented by Matthew Belanger - 101144323
     */

    private StringBuilder currentValue = new StringBuilder();
    List<Player> result;
    Player currentPlayer;
    List<Property> properties;

    public PlayerSAXHandler(List<Property> properties){
        this.properties = properties;
    }

    public List<Player> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        // start of loop
        if (qName.equalsIgnoreCase("Player")) {
            currentPlayer = new Player();
        }
    }

    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("isAI")) {
            if(Boolean.parseBoolean(currentValue.toString())){
                currentPlayer = new AI("");
            }
        }
        if (qName.equalsIgnoreCase("name")) {
            currentPlayer.setName(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("money")) {
            currentPlayer.setMoney(Integer.parseInt(currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("position")) {
            currentPlayer.setPosition(Integer.parseInt(currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("positionTracker")) {
            currentPlayer.setPositionTracker(Integer.parseInt(currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("Property")) {
            for(int i = 0; i < properties.size(); i++){
                if(properties.get(i).getName().equals(currentValue.toString())){
                    currentPlayer.addProperty(properties.get(i));
                    break;
                }
            }
        }
        if (qName.equalsIgnoreCase("jailed")) {
            currentPlayer.setJailed(Boolean.parseBoolean(currentValue.toString()));
        }
        // end of loop
        if (qName.equalsIgnoreCase("Player")) {
            result.add(currentPlayer);
        }

    }

    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }
}
