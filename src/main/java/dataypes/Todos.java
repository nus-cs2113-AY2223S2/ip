package dataypes;

/**
 * A Class that is an extension of the {@link Task}, by creating different types of tasks.
 *
 * @author Muthya Narayanachary Akhil
 */
public class Todos extends Task implements TaskFileHandler {
    /**
     * The constructor sets the description.
     * @param description The description of the {@link Todos} object.
     */
    public Todos(String description) {
        super(description);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + getStatusAndDescription());
    }

    /**
     * An emmpty constructor to initialize the Todos Class
     */
    public Todos () {}


    @Override
    public String getStatusAndDescription() {
        return "[T]" + super.getStatusAndDescription();
    }
    public String enCode() {
        return "T # " + super.enCode();
    }
}

//random comment