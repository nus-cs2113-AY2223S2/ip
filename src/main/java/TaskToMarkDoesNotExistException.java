public class TaskToMarkDoesNotExistException extends Exception {
    protected String command;

    public TaskToMarkDoesNotExistException(String command) {
        this.command = command;
    }

    public void printErrorMessage() {
        System.out.println("You can only " + command + " tasks that are currently in the list!");
        System.out.println("You only have " + Task.numberOfTasks + " tasks in your list.");
    }
}
