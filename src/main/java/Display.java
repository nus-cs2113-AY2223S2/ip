import java.util.ArrayList;

public class Display {
    private static final String LINE =
            "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\n" +
                    "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝";
    private static final String LOGO = "░██████╗░█████╗░░██████╗░███████╗\n" +
            "██╔════╝██╔══██╗██╔════╝░██╔════╝\n" +
            "╚█████╗░███████║██║░░██╗░█████╗░░\n" +
            "░╚═══██╗██╔══██║██║░░╚██╗██╔══╝░░\n" +
            "██████╔╝██║░░██║╚██████╔╝███████╗\n" +
            "╚═════╝░╚═╝░░╚═╝░╚═════╝░╚══════╝";

    public void welcomeUser() {
        String welcome = "Hello! I'm SAGE, the knowledgeable one\n"
                + "What can I do for you today?\n";
        System.out.println(LOGO);
        System.out.println(welcome);
    }

    public void goodByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks stored in the taskList.
     *
     * @param taskList The TaskList object that contains the tasks to be displayed.
     */
    public void displayTask(TaskList taskList) {
        System.out.println(LINE);
        taskList.listTask();
        System.out.println(LINE);
    }

    /**
     * This method prints a message to the console indicating the addition of a task to the task list and its details.
     * The message includes the type of task, the task description and the task count after adding the task.
     *
     * @param userInput The input entered by the user in the form of an array of strings after character split
     * @param taskType  The type of task being added (TODO, EVENT or DEADLINE)
     * @param taskList  The list of tasks to which the new task is being added
     **/
    public void addedTask(Command command, TaskType taskType, TaskList taskList) {
        System.out.println(LINE);
        switch (taskType) {
        case TODO:
            System.out.println("Got it. I've added this task:");
            System.out.println("[T][ ] " + command.getTaskDescription());
            System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list");
            break;
        case EVENT:
            System.out.println("Got it. I've added this task:");
            System.out.println("[E][ ] " + command.getTaskDescription() + "(from: "
                    + command.getFrom() + " to: " + command.getTo() + ")");
            System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list");
            break;
        case DEADLINE:
            System.out.println("Got it. I've added this task:");
            System.out.println("[D][ ] " + command.getTaskDescription() + "(by: "
                    + command.getBy() + ")");
            System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list");
            break;
        }
        System.out.println(LINE);
    }

    public void missingTodoField() {
        System.out.println(LINE);
        System.out.println("Description ");
        System.out.println(LINE);
    }

    public void taskNotFound() {
        System.out.println("Uh-oh! Task Not Found!");
    }

    public void unknownInput() {
        System.out.println("I don't understand what you mean, please try again!");
    }

    public void invalidUnmark() {
        System.out.println("Task already marked as not completed!");
    }

    public void invalidMark() {
        System.out.println("Task already marked as completed!");
    }

    /**
     * This method is used to display a marked task as not done in the task list.
     *
     * @param list       the list of tasks
     * @param taskNumber the taskNumber index that is to be unmarked
     */
    public void validUnmark(ArrayList<Task> list, int taskNumber) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + list.get(taskNumber - 1).getTaskName());
    }

    /**
     * This method is used to display an unmarked task as done in the task list.
     *
     * @param list       the list of tasks
     * @param taskNumber the taskNumber index that is to be marked
     */
    public void validMark(ArrayList<Task> list, int taskNumber) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + list.get(taskNumber - 1).getTaskName());
    }

}
