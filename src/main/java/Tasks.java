import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tasks {
    public static final String DELETE_TASK_ALERT = "Noted. I have removed the following task:";
    public static final String UNMARK_TASK_ALERT = "OK, I've marked this task as not done yet:";
    public static final String MARK_TASK_ALERT = "Nice! I've marked this task as done:";
    public static final String PRINT_LIST_ALERT = "Here are the tasks in your list:";
    public static final String ADD_TASK_ALERT = "Got it. I've added this task:\n";
    public static final String SAVE_FILE_PARTITION = " / ";
    protected ArrayList<Task> tasks;
    private int tasksCount;
    
    public Tasks() {
        // implement 1-based indexing, ignore list[0], allows for easier use of numbering
        tasks = new ArrayList<Task>();
        tasks.add(null);
        tasksCount = 0;
    }
    
    public int getTasksCount() {
        return tasksCount;
    }
    
    public void printTask(int taskNumber) {
        System.out.println(taskNumber + "." + tasks.get(taskNumber).toString());
    }
    
    public void printListCount() {
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
    }
    
    public void initTasks(File dir, File file) {
        checkSaveFile(dir, file);
        try {
            readSaveFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }
    
    public void checkSaveFile(File dir, File file) {
        // check if directory dir is present, else create one
        if (!dir.isDirectory()) {
            if (!dir.mkdir()) {
                System.out.println("Error: Data directory cannot be created");
            }
        }
        
        // check if file is present, else create one
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: Save file cannot be created");
        }
    }
    
    public void readSaveFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String taskString = s.nextLine();
            readTask(taskString);
        }
    }
    
    public void readTask(String taskString) {
        String[] commandAndArg = splitCommandAndArg(taskString);
        String command = commandAndArg[0];
        String mark = commandAndArg[1];
        String description = commandAndArg[2];
        boolean isMark = isMark(mark);
        
        switch (command) {
        case "T":
            loadTodo(isMark, description);
            break;
        case "D":
            String by = commandAndArg[3];
            loadDeadline(isMark, description, by);
            break;
        case "E":
            String from = commandAndArg[3];
            String to = commandAndArg[4];
            loadEvent(isMark, description, from, to);
            break;
        default:
            break;
        }
    }
    
    private String[] splitCommandAndArg(String line) {
        String[] splitStrings = line.split(SAVE_FILE_PARTITION);
        return splitStrings; // assumed that there when used there is no exception where it cannot be split
    }
    
    private boolean isMark(String mark) {
        return mark.contains("1");
    }
    
    private void loadTodo(boolean isMark, String description) {
        Todo newTodo = new Todo(description);
        tasksCount++;
        tasks.add(newTodo);
        if (isMark) {
            tasks.get(tasksCount).markTask(true);
        }
    }
    
    private void loadDeadline(boolean isMark, String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        tasksCount++;
        tasks.add(newDeadline);
        if (isMark) {
            tasks.get(tasksCount).markTask(true);
        }
    }
    
    private void loadEvent(boolean isMark, String description, String from, String to) {
        Event newEvent = new Event(description, from, to);
        tasksCount++;
        tasks.add(newEvent);
        if (isMark) {
            tasks.get(tasksCount).markTask(true);
        }
    }
    
    public void saveTasks(File saveFile) throws IOException {
        // TODO: overwrite and save tasks into scanner everytime a command is made
        FileWriter fw = new FileWriter(saveFile);
        fw.flush();
        String fileContent = getFileContent();
        fw.write(fileContent);
        fw.close();
    }
    
    private String getFileContent() {
        String output = "";
        for (int i = 1; i <= tasksCount; i++) {
            Task task = tasks.get(i);
            String taskType = task.getTaskType();
            output = output.concat(taskType + SAVE_FILE_PARTITION);
            if (task.getIsDone()) {
                output = output.concat("1" + SAVE_FILE_PARTITION);
            } else {
                output = output.concat("0" + SAVE_FILE_PARTITION);
            }
            String taskContent = task.getTaskContent();
            output = output.concat(taskContent + '\n');
        }
        return output;
    }
    
    public void addTask(Task newTask) {
        tasksCount++;
        tasks.add(newTask);
        
        System.out.println(ADD_TASK_ALERT + newTask.toString());
        printListCount();
    }
    
    public void markTaskDone(int taskNumber) {
        tasks.get(taskNumber).markTask(true);
        System.out.println(MARK_TASK_ALERT);
        printTask(taskNumber);
    }
    
    public void markTaskUndone(int taskNumber) {
        tasks.get(taskNumber).markTask(false);
        System.out.println(UNMARK_TASK_ALERT);
        printTask(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        System.out.println(DELETE_TASK_ALERT);
        printTask(taskNumber);
        tasks.remove(taskNumber);
        tasksCount--;
    }
    
    public void clear() {
        tasks.clear();
        
        // reinitialise tasks to make it 1-based indexing
        tasks.add(null);
        tasksCount = 0;
    }
    
    public void printList() {
        System.out.println(PRINT_LIST_ALERT);
        
        for (int i = 1; i <= tasksCount; i++) {
            printTask(i);
        }
        
        printListCount();
    }
}
