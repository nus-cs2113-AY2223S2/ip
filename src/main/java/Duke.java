import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke implements Serializable {
    private static ArrayList<Task>tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        printWelcome();
        //saveData();
        start();

        String input;
        Scanner in = new Scanner(System.in);
       // ArrayList<Task> tasks = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            input = in.nextLine();
            String[] command = input.split(" ", 2);

            switch(command[0]) {
            case "bye":
                printBye();
                isRunning = false;
                break;
            case "list":
                printAllTasks();
                break;
            case "mark":
                try {
                    markTaskDone(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please indicate the task number to be marked.");
                }
                break;
            case "unmark":
                try {
                    markTaskNotDone(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please indicate the task number to be unmarked.");
                }
                break;
            case "todo":
                try {
                    addTodo(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    //throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                try {
                    addDeadline(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    //throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "event":
                try {
                    addEvent(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                }
                break;
            default:
                printInvalidMessage();

            }
        }
    }

    public static void saveData() throws IOException {
        //System.out.println("Entered save data");
        File file = new File("data/list.txt");

        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();

        FileWriter input = new FileWriter(file);

        for (Task task : tasks) {
            input.write(task.toString()); //issue
           // input.write(task);
        }

        input.close();


    }

    public static void loadData() throws IOException {
        File directory = new File("data");

        if (!(directory.isDirectory() && directory.exists())) {
            new File("data").mkdirs();
        } else {
            File file = new File("data/list.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
        }

        Scanner line = new Scanner(directory);

        while (line.hasNext()) {
            //tasks.add(line.nextLine());
        }

    }




    public static void start() {
        try {
            loadData();

        } catch (IOException e) {
           // System.out.println("Unable to load");
        }
    }
    public static void markTaskDone(String command) /*throws DukeException*/ {

        try {
            int taskNumber = Integer.parseInt(command);
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            printMarkDone();
            printTask(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
            //throw in here
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        } catch (DukeException e) {
            System.out.println("The task is already marked as done.");
        }

        try {
            saveData();
        } catch (IOException e) {
            System.out.println("Unable to save.");
        }

    }

    public static void markTaskNotDone(String command)  {

        try {
            int taskNumber = Integer.parseInt(command);
            Task task = tasks.get(taskNumber - 1);
            task.unmarkAsDone();

            printMarkNotDone();
            printTask(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
            //throw in here
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        } catch (DukeException e) {
            System.out.println("The task is already marked as not done.");
        }

        try {
            saveData();
        } catch (IOException e) {
            System.out.println("Unable to save.");
        }
    }
    public static void addTodo(String description) {
        printAddTask();

        Task task = new Todo(description);
        tasks.add(task);

        printTask(task);
        printNoOfTasks(tasks.size());

        try {
            saveData();
        } catch (IOException e) {
            System.out.println("Unable to save.");
        }
    }

    public static void addDeadline(String command) /*throws DukeException*/ {

        if (command.contains("/by")) {
            String[] components = command.split(" /by");

            try {
                Task task = new Deadline(components[0], components[1]);
                tasks.add(task);
                printAddTask();
                printTask(task);
                printNoOfTasks(tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please specify a time for the deadline.");
            }

        } else {
            System.out.println("Invalid format. Remember to use '/by' to indicate the time.");
        }

        try {
            saveData();
        } catch (IOException e) {
            System.out.println("Unable to save.");
        }
    }

    public static void addEvent(String command) {

        if (command.matches("(.*)" + "/from" + "(.*)" + "/to" + "(.*)")) {

            try {
                String[] components = command.split(" /from | /to "); //split string using "/from" and "/to"
                Task task = new Event(components[0], components[1], components[2]);
                tasks.add(task);

                printAddTask();
                printTask(task);
                printNoOfTasks(tasks.size());
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please specify both the starting and ending time of the event");
            }
        } else {
            System.out.println("Incorrect format. Specify events in the format 'event A /from B to /C'");
        }

        try {
            saveData();
        } catch (IOException e) {
            System.out.println("Unable to save.");
        }


    }
    public static void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
            return;
        }

        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(count + "." + task);
            count += 1;
        }
    }
    public static void printTask(Task task) {
        System.out.println(task);
    }
    public static void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }
    public static void printMarkDone() {
        System.out.println("Nice! I've marked this task as done:");
    }
    public static void printMarkNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
    }
    public static void printNoOfTasks(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
    }
    public static void printInvalidMessage() {
        System.out.println("I don't know what that means :-(");
    }
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
