public class DukeCommands {
    public static String spacer = "__________________________________________\n";
    public static DukeTasks[] list = new DukeTasks[100];
    static Integer taskLength = 0;

    // Still requires addition of speach lines

    public static void addToList(String taskName) {
        list[taskLength] = new DukeTasks(taskName);
        taskLength += 1;
        System.out.println("added: " + taskName);
        System.out.println(spacer);
    }

    public static void listTasks() {
        Integer taskIndex = 1;
        for (DukeTasks task : list) {
            if (taskIndex > taskLength) {
                break;
            }
            System.out.println(taskIndex + ".[" + task.getStatusIcon() + "] " + task.getDescription());
            taskIndex += 1;
        }
        System.out.println(spacer);
    }
    
    public static void markTask(Integer taskIndex) {
        list[taskIndex - 1].markDone();
        System.out.println("  [" + list[taskIndex - 1].getStatusIcon() + "] " + list[taskIndex - 1].getDescription());
        System.out.println(spacer);
    }
    
    public static void unmarkTask(Integer taskIndex) {
        list[taskIndex - 1].unmarkDone();
        System.out.println("  [" + list[taskIndex - 1].getStatusIcon() + "] " + list[taskIndex - 1].getDescription());
        System.out.println(spacer);
    }
}
