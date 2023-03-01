import java.util.ArrayList;

public class Parser {

    /** Parses user command and calls relevant functions */
    public static void parseCommand(String taskInfo, ArrayList<Task> tasks) {
        if (taskInfo.equals("list")) {
            getList(tasks);

            //mark, unmark, or add
        } else {
            int taskDescriptionIndex = taskInfo.indexOf(" ");
            String taskType = taskInfo.substring(0, taskDescriptionIndex);
            String taskDescription = taskInfo.substring(taskDescriptionIndex + 1);

            switch (taskType) {
            case "delete":
                int deleteTaskNo = Integer.parseInt(taskDescription) - 1;
                Tasklist.deleteTask(tasks, deleteTaskNo);
                break;

            case "mark":
                int markTaskNo = Integer.parseInt(taskDescription) - 1;
                Tasklist.markTask(tasks, markTaskNo);
                System.out.println("This task is done: " + Ui.printTask(tasks.get(markTaskNo)));
                break;

            case "unmark":
                int unmarkTaskNo = Integer.parseInt(taskDescription) - 1;
                Tasklist.unmarkTask(tasks, unmarkTaskNo);
                System.out.println("This task is done: " + Ui.printTask(tasks.get(unmarkTaskNo)));
                break;

            case "todo":
                Tasklist.addTodo(tasks, taskDescription);
                break;

            case "deadline":
                Tasklist.addDeadline(tasks, taskDescription);
                break;

            case "event":
                Tasklist.addEvent(tasks, taskDescription);
                break;

            case "find":
                findTask(tasks, taskDescription);
                break;

            default:
                System.out.println("That's an invalid task :(");
            }

        }
    }

    /** Prints list of all tasks */
    public static void getList(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            System.out.println("Here are your current tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + Ui.printTask(tasks.get(i)));
            }
        } else {
            System.out.println("There are no current tasks (yay!)");
        }
    }

    /** Finds tasks based on a specific keyword */
    public static void findTask(ArrayList<Task> tasks, String taskDescription) {
        int taskNo = 0;
        for (Task task: tasks) {
            if (task.description.contains(taskDescription)) {
                taskNo += 1;

                if (taskNo == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }

                System.out.println(taskNo + "." + Ui.printTask(task));
            }
        }
        if (taskNo == 0) {
            System.out.println("No matching tasks in your list :(");
        }
    }
}
