public class Go extends Property {
    /**
     * Default constructor for Go
     *
     * @param name String name GO
     * @param cost int Cost for Go
     * @param rent int rent for Go
     *
     * Created and documented by Tao Lufula - 101164153
     */
    public Go (String name, int cost, int rent) {

        super(name, cost, rent);
    }

    /**
     * Overrides the toString() method
     * @return String class declaration
     *
     * Created and documented by Tao Lufula - 101164153
     */
    @Override
    public String toString() {
        return "Go{" +
                "name=" + super.getName()+""+
                '}' +
                "\n" +
                "\n" +
                "Everytime you pass over or land on Go, you will collect 200$";
    }
}
