import java.util.Scanner;
public class Duke {
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
    public static void markTask(Task[] list, String[] input, int i){
        int index = Integer.parseInt(input[i]);
        Task t = list[index-1];
        t.markAsDone();
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        printLine();
    }

    //mark task as not done
    public static void unmarkTask(Task[] list, String[] input, int i){
        int index = Integer.parseInt(input[i]);
        Task t = list[index-1];
        t.markNotDone();
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        printLine();
    }

    //to-do
    public static void addTodo(Task[] list, int count, String[] input, int i){
        Task t = new Todo(input[i]);
        list[count] = t;
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    //deadline
    public static void addDeadline(Task[] list, int count, String[] input, int i, int j){
        Task t = new Deadline(input[i], input[j]);
        list[count] = t;
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    //event
    public static void addEvent(Task[] list, int count, String[] start, String[] end, int i, int j, int k){
        Task t = new Event(start[i], end[j], end[k] );
        list[count] = t;
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }
    public static void main(String[] args) {
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

        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        Task[] list = new Task[100]; //store tasks in an array
        int count = 0; //keep track of number of tasks in list

        while (!(command.equals("bye"))) {
            //list tasks
            if (command.equals("list")) {
                listTasks(list, count);
            } else {
                String[] input = command.split(" ", 2); //only check the first word for "mark" or "unmark"
                switch (input[0]) {
                    //mark task
                    case "mark":
                        markTask(list, input, 1);
                        break;
                    //un-mark task
                    case "unmark":
                        unmarkTask(list, input, 1);
                        break;
                    //to-do
                    case "todo":
                        addTodo(list, count, input, 1);
                        count++;
                        break;
                    //deadline
                    case "deadline":
                        String[] doBy = input[1].split("by ", 2);
                        addDeadline(list, count, doBy, 0, 1);
                        count++;
                        break;
                    //event
                    case "event":
                        String[] start = input[1].split("from ", 2);
                        String[] end = start[1].split("to ", 2);
                        addEvent(list, count, start, end, 0, 0, 1);
                        count++;
                        break;
                    //unlabelled
                    default:
                        addTask(list, count, command);
                        count++;
                        break;
                }
            }
            command = in.nextLine(); //read next command
        }
        //exit loop when command received is "bye"
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }
}

