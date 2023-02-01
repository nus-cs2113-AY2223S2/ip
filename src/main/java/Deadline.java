public class Deadline extends Todos {

    protected String type = "D";
    Deadline(String name, boolean marked, String type) {
        super(name, marked, type);
    }

    public String getType() {
        return "[D]";
    }
}