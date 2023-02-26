package Tasks;

public class Todo extends Task {

    /**
     * This is the constructor of an object of class Todo. It also
     * calls the default constructor of the class it has inherited (Task).
     *
     * @param taskName: name of the task
     */
    public Todo(String taskName) {
        super(taskName, false, "[T]");
    }

    public String toString() {
        return super.toString();
    }

}