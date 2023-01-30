import java.util.Scanner;

/**
 * I/O class
 * Read user input.
 * Print output.
 */
public class UI {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char INPUT_COMMENT_MARKER = '#';
    /**
     * Print the list of tasks.
     *
     * @param tasks
     * @param count
     */
    public void listCurrentTasks(Task[] tasks, int count){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();
        formatter.drawSeparationLine();
        System.out.println("      Here are the tasks in your list:");
        for (int i=1; i<=count; i+=1){
            formatter.printIndentation(8);
            System.out.print(i+".");
            System.out.print(tasks[i-1]);
            System.out.print('\n');
        }
        formatter.drawSeparationLine();
    }

    /**
     * Echo back the newly created task to user.
     *
     * @param numTasks
     * @param task
     */
    public void echoNewTask(int numTasks, Task task){
        Formatter formatter = new Formatter();;
        formatter.drawSeparationLine();
        System.out.println("      Got it. I've added this task:");
        formatter.printIndentation(8);
        System.out.println(task);
        System.out.println("      Now you have "+numTasks+" tasks in the list.");
        formatter.drawSeparationLine();
    }

    /**
     * Echo back the change of task status to users.
     *
     * @param task
     * @param caption
     */
    public void updateTaskStatus(Task task, String caption){
        Formatter formatter = new Formatter();;
        formatter.drawSeparationLine();
        System.out.println(caption);
        formatter.printIndentation(8);
        System.out.println(task);
        formatter.drawSeparationLine();

    }

    /**
     * Print hello and logo.
     *
     * @param logo
     * @param hello
     */
    public void greet(String[] logo, String[] hello){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();

        formatter.drawSeparationLine();
        formatter.printIndentation(6);
        System.out.print("Hello from\n");
        tool.printStringArray(logo);
        formatter.drawSeparationLine();
        tool.printStringArray(hello);
        formatter.drawSeparationLine();
    }

    /**
     * Print bye and exit the program.
     *
     * @param bye
     */
    public void sayBye(String bye){
        Formatter formatter = new Formatter();
        formatter.drawSeparationLine();
        formatter.printIndentation(6);
        System.out.print(bye);
        formatter.drawSeparationLine();
    }

    /**
     * Read user input.
     *
     * @return
     */
    public String readInput() {
//        Scanner in = new Scanner(System.in);
//        String inputCommand = in.nextLine();
//        return inputCommand;
        String inputLine;
        if(SCANNER.hasNextLine()){
            inputLine = SCANNER.nextLine();
        }else{
            inputLine = null;
            System.exit(0);
        }

        // silently consume all blank and comment lines
//        while (inputLine.trim().isEmpty() || inputLine.trim().charAt(0) == INPUT_COMMENT_MARKER) {
//            inputLine = SCANNER.nextLine();
//        }
        return inputLine;
    }

}
