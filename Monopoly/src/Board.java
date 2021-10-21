import java.util.ArrayList;
import java.util.List;

public class Board {

    // declared as final as the board is constant
    private final List<Property> properties;

    public Board() {

        this.properties = new ArrayList<>();

        Property Go = new Property("Go", 0, 0);

        Property Loeb = new Property("Loeb Building", 60, 2);
        Property Southam = new Property("Southam Hall", 60, 4);

        Property Paterson = new Property("Paterson Hall", 100, 6);
        Property Nesbitt = new Property("Nesbitt Building", 100, 6);
        Property Mackenzie = new Property("Makenzie Building", 120, 8);

        Property Azreli = new Property("Azreli Theatre", 140, 10);
        Property Steacie = new Property("Steacie Building", 140, 10);
        Property Tory = new Property("Tory Building", 160, 12);

        Property Robertson = new Property("Robertson Hall", 180, 14);
        Property Architecture = new Property("Architecture Building", 180, 14);
        Property Canal = new Property("Canal Building", 200, 16);

        Property Glengarry = new Property("Glengarry House", 220, 18);
        Property Lennox = new Property("Lennox & Addington House", 220, 18);
        Property Renfrew = new Property("Renfrew House", 240, 20);

        Property Lanark = new Property("Lanark House", 260, 22);
        Property Grenville = new Property("Grenville House", 260, 22);
        Property Dundas = new Property("Dundas House", 280, 24);

        Property Prescott = new Property("Prescott House", 300, 26);
        Property Frontenac = new Property("Frontenac House", 300, 26);
        Property Leeds = new Property("Leeds House", 320, 28);

        Property Nicol = new Property("Nicol Building", 350, 35);
        Property Minto = new Property("Minto CASE", 400, 50);

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

    public Property getProperty(int position) {
        return this.properties.get(position);
    }

}
