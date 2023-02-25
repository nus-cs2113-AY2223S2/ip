import java.util.ArrayList;

public class Ui {
    private static final String LINE = "___________________________________________________";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public Ui() {
    }
    public void printWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println(LINE);
    }


    public void printAcknowledgement(ArrayList<Task> tasks) {
        System.out.println(LINE);
        int lastIndexOfTasks = tasks.size() - 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(lastIndexOfTasks).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printList(ArrayList<Task> tasks) {
        System.out.println(LINE);

        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks.get(i).toString());
        }
        System.out.println(LINE);

    }

    public void printMarkFeedback(ArrayList<Task> tasks, Integer index) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }
    public void printDeleteFeedback(ArrayList<Task> tasks, String nameOfDeletedTask){
        System.out.println(LINE);
        System.out.println("Roger. I've removed this task:");
        System.out.println(nameOfDeletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printUnmarkFeedback(ArrayList<Task> tasks, Integer index) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }
    public void showCannotEditFile(){
        System.out.println("Unable to write/append to file.");
    }
    public void showLoadingError(){
        System.out.println("Could not read file.");
    }
    public void showInvalidCommand(){
        System.out.println("No such commands found! Please try again!");
        System.out.println(LINE);
    }

    public void showInsufficientEventArgs(){
        System.out.println("Not enough commands to execute \"event\"");
        System.out.println(LINE);
    }
    public void showInsufficientDeadlineArgs(){
        System.out.println("Not enough commands to execute \"deadline\"");
        System.out.println(LINE);
    }
    public void showInsufficientTodoArgs(){
        System.out.println("Unable to add todo: No tasks given.");
        System.out.println(LINE);
    }

    public void printByeMessage() {
        System.out.println(LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE);
        System.exit(0);
    }
}
