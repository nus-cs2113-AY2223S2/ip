import java.util.Arrays;

public class DukeCommands {
    public static String spacer = "__________________________________________\n";
    static Integer taskLength = 0;

    public static void addToList(String taskName, String[] taskList) {
        taskList[taskLength] = taskName;
        taskLength += 1;
        System.out.println("added: " + taskName);
        System.out.println(spacer);
    }

    public static void listTasks(String[] taskList) {
        Integer taskIndex = 1;
        for (String task : taskList) {
            if (taskIndex > taskLength) {
                break;
            }
            System.out.println(taskIndex + ". " + task);
            taskIndex += 1;
        }
        System.out.println(spacer);
    }
}
