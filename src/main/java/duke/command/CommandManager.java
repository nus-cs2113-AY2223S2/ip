//package duke.command;
//import duke.task.TaskList;
//import duke.task.Tasks;
//public class CommandManager {
//    //print Duke
//    //get user input
//    private String userInput;
//    private String key;
//    public static final String DIVIDER = "\t____________________________________________________________";
//    public static void sayHi() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.print(DIVIDER + '\n'
//                + " \t Hello! I'm Duke\n\t What can I do for you?\n" + DIVIDER + "\n\n");
//    }
//    public static void sayBye() {
//            System.out.println(DIVIDER + "\n\t Bye. Hope to see you again soon!\n" + DIVIDER);
//    }
//    public CommandManager() {
//        userInput = null;
//        key = null;
//    }
//    public String getUserInput() {
//        return userInput;
//    }
//    public void setUserInput(String input) {
//            this.userInput = input;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//    public void printOutput(Tasks task) {
//        //    String taskLine = printList.get(task.getID());
//        switch (key) {
//        case "mark":
//            System.out.println(DIVIDER + "\n\t Nice! I've marked this task as done:\n\t  " + task);
//            System.out.println(DIVIDER);
//            break;
//        case "unmark":
//            System.out.println(DIVIDER + "\n\t Nice! I've marked this task as not done yet:\n\t  " + task);
//            System.out.println(DIVIDER);
//            break;
//        case "echo":
//            System.out.println(DIVIDER + "\n\t added: " + task);
//            System.out.println(DIVIDER);
//            break;
//        case "add":
//            System.out.println(DIVIDER + "\n\t Got it. I've added this task:\n\t  "
//                    + task);
//            System.out.println("\t Now you have " + TaskList.getNumberOfTasks() + " in your list.\n"
//                    + DIVIDER);
//            break;
//        case "delete":
//            System.out.println(DIVIDER + "\n\t Got it. I've deleted this task:\n\t  "
//                    + task);
//            System.out.println("\t Now you have " + (TaskList.getNumberOfTasks() - 1) + " in your list.\n"
//                    + DIVIDER);
//        }
//    }
//    public void printOutput() {
//        int totalNumberOfTasks = TaskList.getNumberOfTasks();
//        System.out.println(DIVIDER + "\n\t Here are the tasks in your list:");
//        for (int num = 1; num <= totalNumberOfTasks; ++num) {
//            Tasks thisTask = TaskList.getTaskList().get(num - 1);
//            System.out.println("\t  " + num + ". " + thisTask);
//        }
//        System.out.println(DIVIDER);
//    }
//}

