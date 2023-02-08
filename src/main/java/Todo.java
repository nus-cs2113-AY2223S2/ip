public class Todo extends Task {


    public Todo(String taskDescription) {
        super(taskDescription);
        this.taskChar = "[T]";
        System.out.print("Added: ");
        print();
    }

}
