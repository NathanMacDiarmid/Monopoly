import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class BoardSAXHandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    List<Property> result;
    Property currentProperty;

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
