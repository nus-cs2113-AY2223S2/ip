import java.util.Scanner;

/**
 * I/O class
 * Read user input.
 * Print output.
 */
public class UI {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Formatter FORMATTER = new Formatter();
    private final static String NEW_TASK_CAPTION = "      Got it. I've added this task:";
    private final static String LIST_CAPTION = "      Here are the tasks in your list:";


    /**
     * Print the list of tasks.
     *
     * @param tasks
     * @param count
     */
    public void listCurrentTasks(Task[] tasks, int count){
        FORMATTER.drawSeparationLine();
        System.out.println(LIST_CAPTION);
        for (int i=1; i<=count; i+=1){
            FORMATTER.printIndentation(8);
            System.out.print(i+".");
            System.out.print(tasks[i-1]);
            System.out.print('\n');
        }
        FORMATTER.drawSeparationLine();
    }

    /**
     * Echo back the newly created task to user.
     *
     * @param numTasks
     * @param task
     */
    public void echoNewTask(int numTasks, Task task){
        FORMATTER.drawSeparationLine();
        System.out.println(NEW_TASK_CAPTION);
        FORMATTER.printIndentation(8);
        System.out.println(task);
        System.out.println("      Now you have "+numTasks+" tasks in the list.");
        FORMATTER.drawSeparationLine();
    }

    /**
     * Echo back the change of task status to users.
     *
     * @param task
     * @param caption
     */
    public void updateTaskStatus(Task task, String caption){
        FORMATTER.drawSeparationLine();
        System.out.println(caption);
        FORMATTER.printIndentation(8);
        System.out.println(task);
        FORMATTER.drawSeparationLine();

    }

    /**
     * Print hello and logo.
     *
     * @param logo
     * @param hello
     */
    public void greet(String[] logo, String[] hello){
        Tool tool = new Tool();

        FORMATTER.drawSeparationLine();
        FORMATTER.printIndentation(6);
        System.out.print("Hello from\n");
        tool.printStringArray(logo);
        FORMATTER.drawSeparationLine();
        tool.printStringArray(hello);
        FORMATTER.drawSeparationLine();
    }

    /**
     * Print bye and exit the program.
     *
     * @param bye
     */
    public void sayBye(String bye){
        FORMATTER.drawSeparationLine();
        FORMATTER.printIndentation(6);
        System.out.print(bye);
        FORMATTER.drawSeparationLine();
    }

    public void printError(String errMessage){
        FORMATTER.drawSeparationLine();
        FORMATTER.printIndentation(4);
        System.out.print(errMessage);
        FORMATTER.drawSeparationLine();
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
