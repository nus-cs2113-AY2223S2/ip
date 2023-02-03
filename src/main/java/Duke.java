import java.util.Scanner;

public class Duke {
    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    //list tasks
    public static void listTasks(Task[] list, int count){
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= count; i++) {
            System.out.println(i + "." + list[i - 1]);
        }
        printLine();
    }

    //add a new task
    public static void addTask(Task[] list, int count, String command){
        Task t = new Task(command);
        list[count] = t;
        //count++;
        printLine();
        System.out.println("added: " + t.description); //new item added
        printLine();
    }

    //mark task as done
    public static void markTask(Task[] tasks, String[] input){
        int index = Integer.parseInt(input[1]);
        Task t = tasks[index-1];
        t.markAsDone();
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        printLine();
    }

    //mark task as not done
    public static void unmarkTask(Task[] tasks, String[] input){
        int index = Integer.parseInt(input[1]);
        Task t = tasks[index-1];
        t.markNotDone();
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        printLine();
    }

    //to-do
    public static void addTodo(Task[] tasks, int count, String[] input){
        Task t = new Todo(input[1]);
        tasks[count] = t;
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    //deadline
    public static void addDeadline(Task[] tasks, int count, String[] input){
        String[] doBy = input[1].split("by ", 2);
        Task t = new Deadline(doBy[0], doBy[1]);
        tasks[count] = t;
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    //event
    public static void addEvent(Task[] tasks, int count, String[] input){
        String[] start = input[1].split("from ", 2);
        String[] end = start[1].split("to ", 2);
        Task t = new Event(start[0], end[0], end[1] );
        tasks[count] = t;
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    public static void handleCommands(Scanner in, String command, Task[] tasks) {
        int count = 0; //keep track of number of tasks
        
        while (!(command.equals("bye"))) {
            //list tasks
            if (command.equals("list")) {
                listTasks(tasks, count);
            } else {
                String[] input = command.split(" ", 2); //only check the first word for "mark" or "unmark"
                switch (input[0]) {
                    //mark task
                    case "mark":
                        markTask(tasks, input);
                        break;
                    //un-mark task
                    case "unmark":
                        unmarkTask(tasks, input);
                        break;
                    //to-do
                    case "todo":
                        addTodo(tasks, count, input);
                        count++;
                        break;
                    //deadline
                    case "deadline":
                        addDeadline(tasks, count, input);
                        count++;
                        break;
                    //event
                    case "event":
                        addEvent(tasks, count, input);
                        count++;
                        break;
                    //unlabelled
                    default:
                        addTask(tasks, count, command);
                        count++;
                        break;
                }
            }
            command = in.nextLine(); //read next command
        }
    }

    public static void main(String[] args) {
        greetUser();

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        Task[] tasks = new Task[100]; //store tasks in an array

        handleCommands(in, command, tasks);

        sayBye(); //exit loop when command received is "bye"
    }
}

