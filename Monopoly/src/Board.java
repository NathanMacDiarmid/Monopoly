public class Board {

    private CircularLinkedList list;

    public Board() {

        this.list = new CircularLinkedList();

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

        list.addNode(0, Go);

        list.addNode(1, Loeb);
        list.addNode(2, Southam);

        list.addNode(3, Paterson);
        list.addNode(4, Nesbitt);
        list.addNode(5, Mackenzie);

        list.addNode(6, Azreli);
        list.addNode(7, Steacie);
        list.addNode(8, Tory);

        list.addNode(9, Robertson);
        list.addNode(10, Architecture);
        list.addNode(11, Canal);

        list.addNode(12, Glengarry);
        list.addNode(13, Lennox);
        list.addNode(14, Renfrew);

        list.addNode(15, Lanark);
        list.addNode(16, Grenville);
        list.addNode(17, Dundas);

        list.addNode(18, Prescott);
        list.addNode(19, Frontenac);
        list.addNode(20, Leeds);

        list.addNode(21, Nicol);
        list.addNode(22, Minto);

    }

    public Property getProperty(int position) {
        Node current = list.getHead();
        for (int i = 0; i < list.getSize(); i++) {
             if (current.position == position) {
                 return current.property;
             }
             current = current.next;
         }
        return null;
    }

}
