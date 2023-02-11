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
    private static final Formatter FORMATTER = new Formatter();
    private final static String NEW_TASK_CAPTION = "      Got it. I've added this task:";
    private final static String LIST_CAPTION = "      Here are the tasks in your list:";
    public final static String DATA_PATH = "duke_data.text";
    public static File NEW_FILE = new File(DATA_PATH);
    private final static String TEMP_PATH = "temp_duke.text";
    private static File TEMP_FILE = new File(TEMP_PATH);


    public void updateData(String command, int index) throws IOException{
        Scanner read = new Scanner(NEW_FILE);
        String data;
        int count = 0;
        while (read.hasNext()) {

            data = read.nextLine();
            if (count != index) {
                writeToFile(data + "\n", TEMP_PATH);
            } else {
                StringBuilder setter = new StringBuilder(data);
                if(command.equals("mark")){
                    setter.setCharAt(2, '1');
                    writeToFile(setter + "\n", TEMP_PATH);
                }else if(command.equals("unmark")){
                    setter.setCharAt(2, '0');
                    writeToFile(setter + "\n", TEMP_PATH);
                }
            }
            count += 1;
        }
        read.close();

        Files.copy(Paths.get(TEMP_PATH), Paths.get(DATA_PATH), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(TEMP_PATH));


    }
    public void loadData() throws IOException{
        if(NEW_FILE.exists()){
            Scanner readFile = new Scanner(NEW_FILE);
            while(readFile.hasNextLine()){
                String data = readFile.nextLine();
                //Use [] for special characters
                String[] fileData = data.split("[|]");
                if(fileData[0].equals("T")){
                    String description = fileData[2];
                    Task task = new Todo(description);
                    if(fileData[1].equals("1")){
                        task.markDone();
                    }
                    TaskManager.loadTask(task);
                }else if(fileData[0].equals("D")){
                    String description = fileData[2];
                    String deadline = fileData[3];
                    Task task = new Deadline(description, deadline);
                    if(fileData[1].equals("1")){
                        task.markDone();
                    }
                    TaskManager.loadTask(task);
                }else if(data.charAt(0)=='E'){
                    String description = fileData[2];
                    String start = fileData[3];
                    String end = fileData[4];
                    Task task = new Event(description, start, end);
                    if(fileData[1].equals("1")){
                        task.markDone();
                    }
                    TaskManager.loadTask(task);
                }
            }
            readFile.close();
        }
    }

    public void writeToFile(String data, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);
        fileWriter.write(data);
        fileWriter.close();
    }

    public void copyToFile(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        ArrayList<Task> tasks = TaskManager.getALLTasks();
        if(tasks.size()==0){
            fileWriter.write("");
        }else{
            for(Task task: tasks){
                String data = task.convertToData();
                fileWriter.write(data);
            }
        }

        fileWriter.close();
    }
    /**
     * Print the list of tasks.
     *
     * @param tasks
     * @param count
     */
    public void listCurrentTasks(ArrayList<Task> tasks, int count){
        FORMATTER.drawSeparationLine();
        System.out.println(LIST_CAPTION);
        for (int i=1; i<=count; i+=1){
            FORMATTER.printIndentation(8);
            System.out.print(i+".");
            System.out.print(tasks.get(i-1));
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
    public void echoTask(int numTasks, Task task, String caption){
        FORMATTER.drawSeparationLine();
        System.out.println(caption);
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
    public void updateTaskStatus(Task task, String caption) {

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
