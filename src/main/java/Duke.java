import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = Backend.initialisation();
        DukeManager dukeManager = new DukeManager(taskList);

        //Duke starts here
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");
        dukeManager.run();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
