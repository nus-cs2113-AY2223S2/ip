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
        System.out.println("Got it. I've added this task:");
        switch (taskType) {
        case TODO:
            System.out.println("[T][ ] " + command.getTaskDescription());
            break;
        case EVENT:
            System.out.println("[E][ ] " + command.getTaskDescription() + "(from: "
                    + command.getFrom() + " to: " + command.getTo() + ")");
            break;
        case DEADLINE:
            System.out.println("[D][ ] " + command.getTaskDescription() + "(by: "
                    + command.getBy() + ")");
            break;
        }
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list");
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

        Task taskObj = list.get(taskNumber - 1);
        System.out.println("OK, I've marked this task as not done yet:");
        if (taskObj instanceof Deadline) {
            Deadline deadline = (Deadline) taskObj;
            System.out.println("[E][ ] " + taskObj.getTaskName() + "(by: " +
                    ((Deadline) taskObj).getByWhen() + ") ");
        } else if (taskObj instanceof Event) {
            Event event = (Event) taskObj;
            System.out.println("[E][ ] " + event.getTaskName() + "(from: "
                    + event.getStartWhen() + " to: " + event.getEndWhen() + ")");
        } else {
            Todo todo = (Todo) taskObj;
            System.out.println("[T][ ] " + todo.getTaskName());
        }
    }

    /**
     * This method is used to display an unmarked task as done in the task list.
     *
     * @param list       the list of tasks
     * @param taskNumber the taskNumber index that is to be marked
     */
    public void validMark(ArrayList<Task> list, int taskNumber) {
        Task taskObj = list.get(taskNumber - 1);
        System.out.println("Nice! I've marked this task as done:");
        if (taskObj instanceof Deadline) {
            Deadline deadline = (Deadline) taskObj;
            System.out.println("[E][X] " + taskObj.getTaskName() + "(by: " +
                    ((Deadline) taskObj).getByWhen() + ") ");
        } else if (taskObj instanceof Event) {
            Event event = (Event) taskObj;
            System.out.println("[E][X] " + event.getTaskName() + "(from: "
                    + event.getStartWhen() + " to: " + event.getEndWhen() + ")");
        } else {
            Todo todo = (Todo) taskObj;
            System.out.println("[T][X] " + todo.getTaskName());
        }
    }

}
