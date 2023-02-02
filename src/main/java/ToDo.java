public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    public String getType(){
        return "T";
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "  [T][" + getStatusIcon() + "] " + description;
    }
}
