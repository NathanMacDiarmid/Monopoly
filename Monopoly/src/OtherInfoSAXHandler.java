import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class OtherInfoSAXHandler extends DefaultHandler {
    /**
     * This class handles SAX events that are generated when parsing the other info xml file.
     *
     * Created and documented by Matthew Belanger - 101144323
     */

    private StringBuilder currentValue = new StringBuilder();
    private int turn;
    private int boardType;

    public int getTurn() {
        return turn;
    }

    public int getBoardType() {
        return boardType;
    }

    @Override
    public void startDocument() {
        turn = 0;
        boardType = 0;
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
        if (qName.equalsIgnoreCase("OtherInfo")) {
        }
    }

    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("Turn")) {
            turn = Integer.parseInt(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("BoardType")) {
            boardType = Integer.parseInt(currentValue.toString());
        }
    }

    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }
}
