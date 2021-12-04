import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class TurnSAXHandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    int result;

    public int getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = 0;
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
        if (qName.equalsIgnoreCase("PlayerTurn")) {
        }
    }

    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("Turn")) {
            result = Integer.parseInt(currentValue.toString());
        }
    }

    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }
}
