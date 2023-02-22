package tasklist;

public class TasklistUi extends TaskList {

    // Output

    protected void printAddTaskMessage(int taskNo) {
        System.out.println("Got it. I have added this task:");
        System.out.println(tasks.get(taskNo - 1).toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list");
    }

    protected void printDeleteTaskMessage(int taskNo) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskNo-1).toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list");
    }

    protected void printBlankArgumentError(String type) {
        System.out.println(type + " cannot be blank.");
    }

    protected void printMissingKeywordError(String type) {
        System.out.println(type + " keyword is missing.");
    }
}

