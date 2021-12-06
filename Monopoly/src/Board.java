import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Board{

    /**
     * This is the Board class
     *
     * Since it is constant and only one will be used,
     * there is one final @attribute that contains properties.
     *
     * There is no ability to add properties.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    private List<Property> properties;
    private int boardType;
    private final static int CARLETON = 0;
    private final static int CANADA = 1;
    private final static int EUROPE = 2;
    private final static int WORLD = 3;
    private final static String CARLETONBOARD = "CarletonBoard.xml";
    private final static String CANADABOARD = "CanadaBoard.xml";
    private final static String EUROPEBOARD = "EuropeBoard.xml";
    private final static String WORLDBOARD = "WorldBoard.xml";



    /**
     * Default constructor for Board class
     *
     * All properties are based on Carleton Buildings
     */
    public Board(int boardType) {

        this.properties = new ArrayList<>();

        importXMLBoard(boardType);

    }

    /**
     * Basic setter for the properties and boardType
     * @param b
     * @param p
     *
     * Created by Matthew Belanger - 101144323
     * Enhanced and documented by Nathan MacDiarmid - 1010198993
     */
    public void setBoard(int b, List<Property> p){
        this.boardType = b;
        this.properties = p;
    }

    /**
     * Basic getter for boardType.
     * @return
     */
    public int getBoardType() {
        return boardType;
    }

    /**
     * Getter for boardType
     * @return
     */
    public int getType() {
        return this.boardType;
    }

    /**
     * Default getter for a specific property in property list
     * @param position the int index of a property
     * @return information on Property of type Property
     */
    public Property getProperty(int position) {
        return this.properties.get(position);
    }

    /**
     * Creates tabs for the toXML method.
     *
     * @param tabs
     * @return tabs
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    private String tabGenerator(int tabs){
        String s = "";
        for(int i = 0; i < tabs; i++){
            s += "\t";
        }
        return s;
    }

    /**
     * This method returns a xml representation of the board.
     *
     * @param tabs
     * @return a String xml representation
     *
     * Created and documented by Matthew Belanger - 101144323
     */
    public String toXML(int tabs){
        String s = tabGenerator(tabs) + "<Board>\n";
        s += tabGenerator(tabs+1) + "<boardType>" + this.boardType + "</boardType>";
        for (Property property : properties) {
            s += property.toXML(tabs + 1);
        }
        s += tabGenerator(tabs) + "</Board>\n";
        return s;
    }

    /**
     * Allows different types of board to be imported from XML files.
     * @param boardType the selection made by the user.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    private void importXMLBoard(int boardType) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        String filename = null;

        if (boardType == CARLETON) {
            filename = CARLETONBOARD;
        }

        else if (boardType == CANADA) {
            filename = CANADABOARD;
        }

        else if (boardType == EUROPE) {
            filename = EUROPEBOARD;
        }

        else if (boardType == WORLD) {
            filename = WORLDBOARD;
        }

        try {
            assert filename != null;
            InputStream is = new FileInputStream(filename);
            SAXParser saxParser = factory.newSAXParser();

            BoardSAXHandler handler = new BoardSAXHandler();

            saxParser.parse(is, handler);

            List<Property> properties = handler.getResult();

            setBoard(boardType, properties);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for properties, only used for testing.
     * @return
     */
    public List<Property> getProperties() {
        return properties;
    }
}
