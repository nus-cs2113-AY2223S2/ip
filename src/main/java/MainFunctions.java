public class MainFunctions {
    public static final String LOGO =
            "\t __   __\n"
                    + "\t|  | |  |   ____     _  _   _    _\n"
                    + "\t|  |_|  |  / _  \\   | |/_\\ | |  | |\n"
                    + "\t|   _   | | |_|  \\  |  /   | \\_/  |\n"
                    + "\t|__| |__|  \\___/\\_\\ |_|     \\__/|_|\n";
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    public static void printWelcome() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(SPACER + "Hello! I'm Haru");
        System.out.println(SPACER + "What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void printGoodbye() {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void invalidInput() {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Please enter a valid input.");
        System.out.println(DIVIDER);
    }

    public static void listTasks(int userTextCount, Task[] storedUserTasks) {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Here are the tasks in your lists:");
        for(int i=0;i<userTextCount;i++){
            System.out.println(SPACER+(i+1)+"."+storedUserTasks[i].toString());
        }
        System.out.println(DIVIDER);
    }

    public static void markTask(int taskIndex, Task[] storedUserTasks) {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Nice! I've marked this task as done:");
        storedUserTasks[taskIndex].isDone = true;
        System.out.println(SPACER+storedUserTasks[taskIndex].toString());
        System.out.println(DIVIDER);
    }

    public static void unmarkTask(int taskIndex, Task[] storedUserTasks) {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"OK, I've marked this task as not done yet:");
        storedUserTasks[taskIndex].isDone = false;
        System.out.println(SPACER+storedUserTasks[taskIndex].toString());
        System.out.println(DIVIDER);
    }

    public static void createTodo(String description, Task[] storedUserTasks) {
        storedUserTasks[Duke.userTextCount] = new Todo(description);
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Got it. I've added this task:");
        System.out.println(SPACER+SPACER+storedUserTasks[Duke.userTextCount].toString());
        Duke.userTextCount++;
        System.out.println(SPACER+"Now you have " + Duke.userTextCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void createDeadline(String description, String by, Task[] storedUserTasks) {
        storedUserTasks[Duke.userTextCount] = new Deadline(description,by);
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Got it. I've added this task:");
        System.out.println(SPACER+SPACER+storedUserTasks[Duke.userTextCount].toString());
        Duke.userTextCount++;
        System.out.println(SPACER+"Now you have " + Duke.userTextCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void createEvent(String description, String from, String to, Task[] storedUserTasks) {
        storedUserTasks[Duke.userTextCount] = new Event(description,from,to);
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Got it. I've added this task:");
        System.out.println(SPACER+storedUserTasks[Duke.userTextCount].toString());
        Duke.userTextCount++;
        System.out.println(SPACER+"Now you have " + Duke.userTextCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

}
