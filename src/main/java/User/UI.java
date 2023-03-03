package User;

public class UI {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "____________________________________________________________\n";
    static String greeting = (line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line);
    static String goodBye = (line + "Bye. Hope to see you again soon!\n" + line);

    public static void printLogo(){
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        printHelp();
    }

    public static void printBye(){
        System.out.println(goodBye);
    }

    public static void printHelp(){
        System.out.println("Here are the list of commands:");
        System.out.println("1. list (to see the current tasks saved)");
        System.out.println("2. todo <task> (for tasks with no particular time to take note of)");
        System.out.println("3. event <task> /from <start of event> /to <end time of event>");
        System.out.println("4. deadline <task> /by <deadline of task> ");
        System.out.println("5. mark <task number> (to mark a task as done)");
        System.out.println("6. unmark <task number> (to mark a task as not done)");
        System.out.println("7. delete <task number> (to remove a task from the list)");
        System.out.println("8. bye (to terminate the programme)");
        System.out.println(line);
    }
    public static void emptyDescription(){
        System.out.println("Please do not leave the description empty!");
        System.out.println(line);
    }

    public static void invalidCommand(){
        System.out.println("Please enter a valid command!");
        System.out.println("Enter command 'help' to see the list of available commands!");
        System.out.println(line);
    }

    public static void indexNotFound(){
        System.out.println("Please ensure the number you have entered is within the list!");
        System.out.println(line);
    }

    public static void listIsEmpty(){
        System.out.println("Invalid command! The list is empty!");
        System.out.println(line);
    }


}
