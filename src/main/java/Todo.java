public class Todo extends Task {

    public Todo(String description) {
        super(description);
        initialize();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    public void initialize() {
        TaskArray.count++;
        System.out.println(UI.LINE +
                "     Got it. I've added this task:\n" + toString() +
                "\n     Now you have " + TaskArray.count + " tasks in the list.\n" +
                UI.LINE);
    }
    public String toFile() {
        return this.isDone + " | " +"t"+ this.description + "\n";
    }
}