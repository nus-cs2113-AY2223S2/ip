

public class Todo extends Task {
   String description;
    public Todo(String input) throws InvalidInputException{
        if (input.trim().equals("todo")) {
            throw new InvalidInputException();
        }
        this.description = input.substring(4).trim();
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.TODO);
        dateTimeFrom.add(null);
        dateTimeTo.add(null);
        this.print();
    }
    
}
    

