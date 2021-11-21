public class Utilities extends Property{
    /**
     *Default constructor fot utility
     *
     * @param name String name of the utility
     * @param cost int cost of the utility
     * @param rent  int rent of the utility
     *
     * Created and documented by Tao Lufula - 101164153, Nathan MacDiarmid - 101098993
     */

    public Utilities (String name, int cost, int rent) {
        super(name, cost, rent);
    }

    public int utilitiesOwned(int rollValue){
        return 4*rollValue;
    }

/**
    @Override
    public void updateRent(int amount) {
        if(amount == 1){
            super.updateRent(*4);
        }
        else if(amount == 2){
            super.updateRent(*10);
        }
    }
    */



    /**
     *Overrides the toString() method
     *
     * @return String class description
     *
     *Created and documented by Tao Lufula - 101164153
     */
    @Override
    public String toString() {
        return "Utility{" +
                "name=" + super.getName()+
                "cost=" + super.getCost() +
                ", rent=" + super.getRent() +
                ", owner=" + super.getOwner() +
                "" +
                '}' +
                "\n" +
                "\n" +
                "If " + super.getOwner()+ " owns 1 utility, rent is player roll * 4 " +
                "\n" +
                "If " + super.getOwner()+ " owns 2 utilities, rent is player roll * 10 \n";
    }
}
