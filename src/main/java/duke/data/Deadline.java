package duke.data;

public class Deadline extends Task{
    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String toString(){
        return "[D]" + super.toString() +" (by: " + by +")";
    }

}