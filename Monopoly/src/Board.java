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
    private final List<Property> properties;
    private int boardType;
    private final static int CARLETON = 0;
    private final static int CANADA = 1;
    private final static int EUROPE = 2;
    private final static String CANADABOARD = "CanadaBoard.xml";
    private final static String EUROPEBOARD = "EuropeBoard.xml";

    /**
     * Default constructor for Board class
     *
     * All properties are based on Carleton Buildings
     */
    public Board(int boardType) {

        this.properties = new ArrayList<>();

        // Initializes properties depending on input type.
        // Cost of properties is 10% of property cost

        if (boardType == CARLETON) {
            this.boardType = CARLETON;
            initCarleton();
        }

        else if (boardType == CANADA) {
            this.boardType = CANADA;
            importXMLBoard(CANADABOARD);
        }

        else if (boardType == EUROPE) {
            this.boardType = EUROPE;
            importXMLBoard(EUROPEBOARD);
        }

    }

    public Board(List<Property> p){
        this.properties = p;
    }

    /**
     * This method is a private method, for internal use only.
     * It initializes the Carleton playing Board.
     *
     * Created and documented by Nathan MacDiarmid - 101098993
     */
    private void initCarleton() {
        Go Go = new Go("Go", 0, 0);

        Property Loeb = new Property("Loeb Building", 60, 6);
        Property Southam = new Property("Southam Hall", 60, 6);

        Utilities electric = new Utilities("Electric Company", 150, 0);

        Railroad Taxi = new Railroad("Taxi", 200, 25);

        Property Paterson = new Property("Paterson Hall", 100, 10);
        Property Nesbitt = new Property("Nesbitt Building", 100, 10);
        Property Mackenzie = new Property("Makenzie Building", 120, 12);

        Jail jail = new Jail("Jail", 0, 0);

        Property Azreli = new Property("Azreli Theatre", 140, 14);
        Property Steacie = new Property("Steacie Building", 140, 14);
        Property Tory = new Property("Tory Building", 160, 16);

        Railroad TunnelCart = new Railroad("Tunnel Cart", 200, 25);

        Property Robertson = new Property("Robertson Hall", 180, 18);
        Property Architecture = new Property("Architecture Building", 180, 18);
        Property Canal = new Property("Canal Building", 200, 20);

        FreeParking FreeParking = new FreeParking("Free Parking", 0, 0);

        Property Glengarry = new Property("Glengarry House", 220, 22);
        Property Lennox = new Property("Lennox House", 220, 22);
        Property Renfrew = new Property("Renfrew House", 240, 24);

        Railroad OCTranspo = new Railroad("OC Transpo", 200, 25);

        Property Lanark = new Property("Lanark House", 260, 26);
        Property Grenville = new Property("Grenville House", 260, 26);
        Property Dundas = new Property("Dundas House", 280, 28);

        GoToJail goToJail = new GoToJail("Go To Jail", 0, 0, jail);

        Property Prescott = new Property("Prescott House", 300, 30);
        Property Frontenac = new Property("Frontenac House", 300, 30);
        Property Leeds = new Property("Leeds House", 320, 32);

        Railroad OTrain = new Railroad("O Train", 200, 25);

        Property Nicol = new Property("Nicol Building", 350, 35);

        Utilities water = new Utilities("Water Works", 150, 0);

        Property Minto = new Property("Minto CASE", 400, 40);


        // adds properties to property list

        this.properties.add(Go);

        this.properties.add(Loeb);
        this.properties.add(Southam);
        this.properties.add(Taxi);
        this.properties.add(Paterson);
        this.properties.add(Nesbitt);
        this.properties.add(Mackenzie);

        this.properties.add(jail);

        this.properties.add(Azreli);
        this.properties.add(electric);
        this.properties.add(Steacie);
        this.properties.add(Tory);
        this.properties.add(TunnelCart);
        this.properties.add(Robertson);
        this.properties.add(Architecture);
        this.properties.add(Canal);

        this.properties.add(FreeParking);

        this.properties.add(Glengarry);
        this.properties.add(Lennox);
        this.properties.add(Renfrew);
        this.properties.add(OCTranspo);
        this.properties.add(Lanark);
        this.properties.add(Grenville);
        this.properties.add(water);
        this.properties.add(Dundas);

        this.properties.add(goToJail);

        this.properties.add(Prescott);
        this.properties.add(Frontenac);
        this.properties.add(Leeds);
        this.properties.add(OTrain);
        this.properties.add(Nicol);
        this.properties.add(Minto);
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

    private String tabGenerator(int tabs){
        String s = "";
        for(int i = 0; i < tabs; i++){
            s += "\t";
        }
        return s;
    }

    public String toXML(int tabs){
        String s = tabGenerator(tabs) + "<Board>\n";
        s += tabGenerator(tabs+1) + "<boardType>" + this.boardType + "</boardType>";
        for (Property property : properties) {
            s += property.toXML(tabs + 1);
        }
        s += tabGenerator(tabs) + "</Board>\n";
        return s;
    }

    public Board importXMLBoard(String filename) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            InputStream is = new FileInputStream(filename);
            SAXParser saxParser = factory.newSAXParser();

            BoardSAXHandler handler = new BoardSAXHandler();

            saxParser.parse(is, handler);

            List<Property> properties = handler.getResult();

            return new Board(properties);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        Board board = new Board(CARLETON);
        System.out.println("The board you selected couldn't be imported. \n Carleton has been loaded instead.");
        return board;
    }
}
