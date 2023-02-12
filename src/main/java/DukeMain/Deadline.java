package DukeMain;

public class Deadline extends Todos {

    protected String type = "D";

    Deadline(String name, boolean isMarked, String type) {
        super(name, isMarked, type);
    }

    public String getType() {
        return "[D]";
    }
}