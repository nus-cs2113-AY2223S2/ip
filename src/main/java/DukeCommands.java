public class DukeCommands {
    public static String spacer = "__________________________________________\n";
    public static DukeTasks[] list = new DukeTasks[100];
    static Integer taskLength = 0;

    // Still requires addition of speach lines

    /**
     * Adds a task with the specified name to a list, and prints a response to the user. 
     * 
     * @param taskName Name of task added
     */
    public static void addToList(String taskName) {
        list[taskLength] = new DukeTasks(taskName);
        taskLength += 1;
        System.out.println("added: " + taskName);
        System.out.println(spacer);
    }

    /** Prints a list of all tasks and its marked status to user. */
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
    
    /**
     * Marks the specified task by index number, then prints a response to user. 
     * 
     * @param taskIndex Index number of task from list printed by Duke, to be marked
     */
    public static void markTask(Integer taskIndex) {
        list[taskIndex - 1].markDone();
        System.out.println("  [" + list[taskIndex - 1].getStatusIcon() + "] " + list[taskIndex - 1].getDescription());
        System.out.println(spacer);
    }
    
    /**
     * Unmarks the specified task by index number, then prints a response to user. 
     * 
     * @param taskIndex Index number of task from list printed by Duke, to be unmarked
     */
    public static void unmarkTask(Integer taskIndex) {
        list[taskIndex - 1].unmarkDone();
        System.out.println("  [" + list[taskIndex - 1].getStatusIcon() + "] " + list[taskIndex - 1].getDescription());
        System.out.println(spacer);
    }
}
