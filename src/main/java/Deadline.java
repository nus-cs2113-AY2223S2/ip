public class Deadline extends Todo {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String getTimings() {
        return this.by;
    }

}