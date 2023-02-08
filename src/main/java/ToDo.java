public class ToDo extends Task {


    public ToDo(String taskDescription) {
        super(taskDescription);
        this.taskChar = "[T]";
        System.out.print("Added: ");
        print();
    }

}
