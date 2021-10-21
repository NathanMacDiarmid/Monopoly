import java.util.ArrayList;
import java.util.List;

public class Board {

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

    /**
     * Default constructor for Board class
     *
     * All properties are based on Carleton Buildings
     */
    public Board() {

        this.properties = new ArrayList<>();

        // Initializes properties
        // Cost of properties is 10% of property cost

        Property Go = new Property("Go", 0, 0);

        Property Loeb = new Property("Loeb Building", 60, 6);
        Property Southam = new Property("Southam Hall", 60, 6);

        Property Paterson = new Property("Paterson Hall", 100, 10);
        Property Nesbitt = new Property("Nesbitt Building", 100, 10);
        Property Mackenzie = new Property("Makenzie Building", 120, 12);

        Property Azreli = new Property("Azreli Theatre", 140, 14);
        Property Steacie = new Property("Steacie Building", 140, 14);
        Property Tory = new Property("Tory Building", 160, 16);

        Property Robertson = new Property("Robertson Hall", 180, 18);
        Property Architecture = new Property("Architecture Building", 180, 18);
        Property Canal = new Property("Canal Building", 200, 20);

        Property Glengarry = new Property("Glengarry House", 220, 22);
        Property Lennox = new Property("Lennox & Addington House", 220, 22);
        Property Renfrew = new Property("Renfrew House", 240, 24);

        Property Lanark = new Property("Lanark House", 260, 26);
        Property Grenville = new Property("Grenville House", 260, 26);
        Property Dundas = new Property("Dundas House", 280, 28);

        Property Prescott = new Property("Prescott House", 300, 30);
        Property Frontenac = new Property("Frontenac House", 300, 30);
        Property Leeds = new Property("Leeds House", 320, 32);

        Property Nicol = new Property("Nicol Building", 350, 35);
        Property Minto = new Property("Minto CASE", 400, 40);

        // adds properties to property list

        this.properties.add(Go);

        this.properties.add(Loeb);
        this.properties.add(Southam);

        this.properties.add(Paterson);
        this.properties.add(Nesbitt);
        this.properties.add(Mackenzie);

        this.properties.add(Azreli);
        this.properties.add(Steacie);
        this.properties.add(Tory);

        this.properties.add(Robertson);
        this.properties.add(Architecture);
        this.properties.add(Canal);

        this.properties.add(Glengarry);
        this.properties.add(Lennox);
        this.properties.add(Renfrew);

        this.properties.add(Lanark);
        this.properties.add(Grenville);
        this.properties.add(Dundas);

        this.properties.add(Prescott);
        this.properties.add(Frontenac);
        this.properties.add(Leeds);

        this.properties.add(Nicol);
        this.properties.add(Minto);

    }

    /**
     * Default getter for a specific property in property list
     * @param position the int index of a property
     * @return information on Property of type Property
     */
    public Property getProperty(int position) {
        return this.properties.get(position);
    }

}
