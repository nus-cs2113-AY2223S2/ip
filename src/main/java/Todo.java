public class Todo extends Task {
   String description;
    public Todo(String input) {
        this.description = input.replace("todo ", "");
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.TODO);
        this.print();
    }
    
}
    

