public class Deadline extends Todo {
    String by;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    Deadline(String description, String by) {
        super(description);
        setBy(by);

    }

    @Override
    public char getTypeIcon() {
        return 'D';
    }

    @Override
    public String getDescription() {
        return super.getDescription() + by;
    }
}