package tasklist;

public class TasklistUi {

    private TaskList taskList;

    public TasklistUi(TaskList taskList) {
        this.taskList = taskList;
    }

    // Output

    protected void printTaskWithNumber(int taskNo) {
        System.out.println((taskNo + 1) + ". " + taskList.tasks.get(taskNo).toString());
    }

    protected void printTaskWithoutNumber(int taskNo) {
        System.out.println(taskList.tasks.get(taskNo).toString());
    }

    protected void printAddTaskMessage(int taskNo) {
        System.out.println("Got it. I have added this task:");
        printTaskWithoutNumber(taskNo - 1);
        System.out.println("Now you have " + taskList.noOfTasks + " tasks in the list");
    }

    protected void printDeleteTaskMessage(int taskNo) {
        System.out.println("Noted. I've removed this task:");
        printTaskWithoutNumber(taskNo - 1);
        System.out.println("Now you have " + taskList.noOfTasks + " tasks in the list");
    }

    protected void printBlankArgumentError(String arg) {
        System.out.println(arg + " cannot be blank.");
    }

    protected void printMissingKeywordError(String keyword) {
        System.out.println(keyword + " keyword is missing.");
    }

    protected void printNegTaskNoError() {
        System.out.println("Negative task number entered.");
    }

    protected void printOOBTaskNoError() {
        System.out.println("Task number does not exist, there are only " + taskList.noOfTasks +
                 " tasks in total.");
    }

    protected void printFromBeforeToError() {
        System.out.println("Please ensure that /from is before /to.");
        System.out.println("Cos I am too lazy to code for both cases.");
    }

    protected void printAllTasks() {
        if (taskList.noOfTasks == 0) {
            System.out.println("No tasks yet. Please input a task.");
        }
        for (int i = 0; i < taskList.noOfTasks; i++) {
            printTaskWithNumber(i);
        }
    }

    protected void printAlreadyDoneMessage() {
        System.out.println("Already done.");
    }

    protected void printMarkDoneMessage(int taskNo) {
        System.out.println("Nice! I have marked this task as done.");
        printTaskWithoutNumber(taskNo - 1);
    }

    protected void printNotDoneMessage() {
        System.out.println("Not done yet. Please finish it.");
    }

    protected void printUnmarkDoneMessage(int taskNo) {
        System.out.println("Ok I have marked this as not done yet.");
        printTaskWithoutNumber(taskNo - 1);
    }

    protected void printTaskFoundMessage(String keyword) {
        System.out.println("These tasks contain the keyword " + keyword);
    }

    protected void printDelimiterFoundError() {
        System.out.println("| character found in arguments. Please ensure that your arguments " + 
                "do not contain the character |.");
    }
}

