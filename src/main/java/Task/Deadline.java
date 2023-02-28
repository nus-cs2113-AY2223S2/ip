package Task;


public class Deadline extends Task {
    protected String deadlineDay;

    public Deadline(String description, String deadlineDay) {
        super(description);
        this.deadlineDay=deadlineDay;
    }

    public Deadline(String description, String deadlineDay, boolean isDone){
        super(description, isDone);
        this.deadlineDay=deadlineDay;
    }

    @Override
    public String toString(){
        return "[D]"+super.toString()+" ("+this.deadlineDay+")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | "
                + deadlineDay;
    }

    public static Deadline fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        String deadlineDay = parts[3];
        return new Deadline(description, deadlineDay, isDone);
    }

}
