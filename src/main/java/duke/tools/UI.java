package duke.tools;

import duke.TaskManager;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * I/O class
 * Read user input.
 * Print output.
 */
public class UI {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final static String LIST_CAPTION = "      Here are the tasks in your list:";

    /**
     * Print the list of tasks.
     *
     * @param tasks
     * @param count
     */
    public void listCurrentTasks(ArrayList<Task> tasks, int count){
        Formatter.drawSeparationLine();
        System.out.println(LIST_CAPTION);
        for (int i=1; i<=count; i+=1){
            Formatter.printIndentation(8);
            System.out.print(i+".");
            System.out.print(tasks.get(i-1));
            System.out.print('\n');
        }
        Formatter.drawSeparationLine();
    }

    /**
     * Echo back the newly created task to user.
     *
     * @param numTasks
     * @param task
     */
    public void echoTask(int numTasks, Task task, String caption){
        Formatter.drawSeparationLine();
        System.out.println(caption);
        Formatter.printIndentation(8);
        System.out.println(task);
        System.out.println("      Now you have "+numTasks+" tasks in the list.");
        Formatter.drawSeparationLine();
    }

    /**
     * Echo back the change of task status to users.
     *
     * @param task
     * @param caption
     */
    public void updateTaskStatus(Task task, String caption) {

        Formatter.drawSeparationLine();
        System.out.println(caption);
        Formatter.printIndentation(8);
        System.out.println(task);
        Formatter.drawSeparationLine();

    }

    /**
     * Print hello and logo.
     *
     * @param logo
     * @param hello
     */
    public void greet(String[] logo, String[] hello){
        Tool tool = new Tool();

        Formatter.drawSeparationLine();
        Formatter.printIndentation(6);
        System.out.print("Hello from\n");
        tool.printStringArray(logo);
        Formatter.drawSeparationLine();
        tool.printStringArray(hello);
        Formatter.drawSeparationLine();
    }

    /**
     * Print bye and exit the program.
     *
     * @param bye
     */
    public void sayBye(String bye){
        Formatter.drawSeparationLine();
        Formatter.printIndentation(6);
        System.out.print(bye);
        Formatter.drawSeparationLine();
    }

    public void printError(String errMessage){
        Formatter.drawSeparationLine();
        Formatter.printIndentation(4);
        System.out.print(errMessage);
        Formatter.drawSeparationLine();
    }

    public void foundTasks(Task task, int count){
        Formatter.printIndentation(8);
        System.out.print(count+".");
        System.out.println(task);
    }
    /**
     * Read user input.
     *
     * @return
     */
    public String readInput() {
        String inputLine;
        inputLine = SCANNER.nextLine();
        return inputLine;
    }

}
