<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
>>>>>>> branch-Level-7

public class Psyduck {
    private static Storage storage = new Storage();
    private static String filepath = "save.txt";
    private static boolean shouldExit = false;

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static int taskCount = 0;

    public static int getTaskCount() {
        return taskCount;
    }

    public static Task getTask(int taskNum) {
        return tasks.get(taskNum-1); //array is 0-indexed, taskNum is 1-indexed
    }

    public static Task getNewestTask() {
        return tasks.get(taskCount - 1); //array is 0-indexed, taskNum is 1-indexed
    }

    public static void setShouldExit(boolean shouldExit) {
        Psyduck.shouldExit = shouldExit;
    }

    public static void addToDo(String description) {
        ToDo newTask = new ToDo(description);
        tasks.add(newTask);
        taskCount++;
    }


    public static void addDeadline(String description, String by) {
        Deadline newTask = new Deadline(description, by);
        tasks.add(newTask);
        taskCount++;
    }

    public static void addEvent(String description, String from, String to) {
        Event newTask = new Event(description, from, to);
        tasks.add(newTask);
        taskCount++;
    }

    public static void removeTask(int taskNum) {
        tasks.remove(taskNum-1);
        taskCount--;

    }
    public static void listTasks() {
        Command.linePrint();
        if (taskCount == 0) { //list is empty
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.print(Integer.toString(i + 1) + ".");
                System.out.println(tasks.get(i));
            }
        }
        Command.linePrint();
    }

    public static void greet() {
        Command.linePrint();
        System.out.println("( ´ ▽ ` )ﾉ Hi I am Psyduck! How can I help you?");
        Command.linePrint();
    }


    public static void sayBye() {
        System.out.println("Bye see you soon! (⌒ー⌒)ﾉ");
        Command.linePrint();
    }

    public static void main(String[] args)  {
        greet();
        try {
            storage.readFile(filepath);
        } catch (FileNotFoundException e) {
            Command.linePrint();
            System.out.println("No past file record is made, will create a " +
                    "new save file after terminating the program.");
            Command.linePrint();
        }
        do {
            Command.processCommands();
        } while (!shouldExit);
        sayBye();
        try {
            storage.writeToFile(filepath);
        } catch (IOException e) {
            System.out.println("Error occurred when saving file. Aborting.");
        }
    }
}
