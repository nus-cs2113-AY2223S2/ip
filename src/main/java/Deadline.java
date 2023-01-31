public class Deadline extends Task {
    private String byWhen;

    public Deadline(String name, String by) {
        super(name, "D");
        this.byWhen = by;
    }

    public String getByWhen() {
        return byWhen;
    }
}
