public class Deadline extends Task {

    // Code from partial solution
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }
    public String getBy(){
        return this.by;
    }
    public void setBy(String by){
        this.by = by;
    }

    @Override
    public String getDescription() {
        return "[D] " + super.getDescription() + "(by: " + this.getBy() + ")";
    }
}
