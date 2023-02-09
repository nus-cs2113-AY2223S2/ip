package duke.models;

public class Remind extends Item {
    public Remind(String description) {
        super(description);
        type = "Remind";
    }

    @Override
    public String toString() {
        return "[R]" + super.toString();
    }
}