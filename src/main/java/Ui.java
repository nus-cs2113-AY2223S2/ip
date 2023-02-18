import java.util.Scanner;

public class Ui {
    public static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    public void showGreetings() {
        String greetMsg = HORIZONTAL_LINE
                + System.lineSeparator()
                + "\tHello! I'm Duke"
                + System.lineSeparator()
                + "\tWhat can I do for you?"
                + System.lineSeparator()
                + HORIZONTAL_LINE;
        System.out.println(greetMsg);
    }

    public void showFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showCurrNumOfTask(TaskList taskList) {
        System.out.println("\tNow you have " + taskList.numOfTasks + " tasks in the list.");
    }

    public void showCurrTask(TaskList taskList, int taskIndex) {
        System.out.println("\t\t" + taskList.tasks.get(taskIndex));
    }

    public String getCurrTask(TaskList taskList, int taskIndex) {
        return "\t\t" + taskList.tasks.get(taskIndex);
    }

    public void showTaskIsAdded() {
        System.out.println("\tGot it. I've added this task:");
    }

    public void showTaskIsMarked() {
        System.out.println("\tNice! I've marked this task as done:");
    }

    public void showTaskIsUnmarked() {
        System.out.println("\tOK, I've marked this task as not done yet:");
    }

    public String getTaskIsDeleted() {
        return "\tNoted. I've removed this task." + System.lineSeparator();
    }

    public void showSaveIsSuccessful() {
        System.out.println("\tTasks were saved successfully.");
    }

    public void showEmptyList() {
        System.out.println("\tThere are no tasks in your list currently!");
    }

    public void showNoMatches() {
        System.out.println("\tNo task in your list matches the keyword.");
    }

    public void showFindError() {
        System.out.println("\tKeyword for find cannot be empty.");
    }

    public void showInvalidTodoError() {
        System.out.println("\tDescription of todo cannot be empty.");
    }

    public void showInvalidDeadlineError() {
        System.out.println("\tInvalid Command. There may be some missing deadline fields.");
    }

    public void showInvalidEventError() {
        System.out.println("\tInvalid Command. There may be some missing event fields.");
    }

    public void showMarkError() {
        System.out.println("\tSorry, you need to state a task number after 'mark'.");
    }

    public void showUnmarkError() {
        System.out.println("\tSorry, you need to state a task number after 'unmark'.");
    }

    public void showDeleteError() {
        System.out.println("\tSorry, you need to state a task number after 'delete'.");
    }

    public void showInvalidTaskNumberError() {
        System.out.println("\tSorry, the task does not exist.");
    }

    public void showCreateFileError() {
        System.out.println("An error occurred when creating the data file.");
    }
    public void showFileLoadingError() {
        System.out.println("\tData file was not found.");
    }

    public void showSavingError() {
        System.out.println("\tData was not saved to file.");
    }

    public void showCommandError() {
        System.out.println("\tInvalid Command.");
    }
}
