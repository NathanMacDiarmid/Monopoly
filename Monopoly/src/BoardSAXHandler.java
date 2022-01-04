import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class BoardSAXHandler extends DefaultHandler {
    /**
     * This class handles SAX events that are generated when parsing the board xml file.
     *
     * Created and documented by Matthew Belanger - 101144323
     */

    private StringBuilder currentValue = new StringBuilder();
    List<Property> result;
    Property currentProperty;
    Jail jail;

    public List<Property> getResult() {
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
        if (qName.equalsIgnoreCase("Property")) {
            currentProperty = new Property();
        }
    }

    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("name")) {
            if(currentValue.toString().equals("Go")){
                currentProperty = new Go("Go", 0, 0);
            }
            else if(currentValue.toString().equals("Jail")){
                currentProperty = new Jail("Jail", 0, 0);
                this.jail = (Jail) currentProperty;
            }
            else if(currentValue.toString().equals("Go To Jail")){
                currentProperty = new GoToJail("Go To Jail", 0, 0, jail);
            }
            else if(currentValue.toString().equals("Free Parking")){
                currentProperty = new FreeParking("Free Parking", 0, 0);
            }
            else if(currentValue.toString().equals("Electric Company") || currentValue.toString().equals("Water Works")){
                currentProperty = new Utilities("", 0, 0);
            }
            else if(currentValue.toString().equals("Taxi (Railroad)") || currentValue.toString().equals("Tunnel Cart (Railroad)")
                    || currentValue.toString().equals("OC Transpo (Railroad)") || currentValue.toString().equals("O Train (Railroad)")
                    || currentValue.toString().equals("Enterprise (Railroad)") || currentValue.toString().equals("Thalys (Railroad)")
                    || currentValue.toString().equals("EuroStar (Railroad)") || currentValue.toString().equals("Train (Railroad)")
                    || currentValue.toString().equals("Plane (Railroad)") || currentValue.toString().equals("Boat (Railroad)")){
                currentProperty = new Railroad("", 0, 0);
            }
            currentProperty.setName(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("cost")) {
            currentProperty.setCost(Integer.parseInt(currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("rent")) {
            currentProperty.setRent(Integer.parseInt(currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("hasHouse")) {
            currentProperty.setHasHouse(Boolean.parseBoolean(currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("hasHotel")) {
            currentProperty.setHasHotel(Boolean.parseBoolean(currentValue.toString()));
        }
        // end of loop
        if (qName.equalsIgnoreCase("Property")) {
            result.add(currentProperty);
        }

    }

    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }
}
