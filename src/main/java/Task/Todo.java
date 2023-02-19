package Task;

public class Todo extends Task{
    public Todo (String description){
        super(description);
    }

    public Todo (String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String toString(){
        return "[T]"+super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    public static Todo fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        return new Todo(description, isDone);
    }
}
