import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tasks {
    protected ArrayList<Task> tasks;
    private int tasksCount;
    
    public Tasks() {
        // implement 1-based indexing, ignore list[0]
        tasks = new ArrayList<Task>();
        tasks.add(null);
        tasksCount = 0;
    }
    
    public int getTasksCount() {
        return tasksCount;
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
        // TODO: read from the scanner
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
        String[] splitStrings = line.split(" / ");
        return splitStrings; // assumed that there when used there is no exception where it cannot be split
    }
    
    private boolean isMark(String mark) {
        return mark.contains("1");
    }
    
    private void loadTodo(boolean isMark, String description) {
        // TODO
        Todo newTodo = new Todo(description);
        tasksCount++;
        tasks.add(newTodo);
        if (isMark) {
            tasks.get(tasksCount).markTask(true);
        }
    }
    
    private void loadDeadline(boolean isMark, String description, String by) {
        // TODO
        
        Deadline newDeadline = new Deadline(description, by);
        tasksCount++;
        tasks.add(newDeadline);
        if (isMark) {
            tasks.get(tasksCount).markTask(true);
        }
    }
    
    private void loadEvent(boolean isMark, String description, String from, String to) {
        // TODO
        
        Event newEvent = new Event(description, from, to);
        tasksCount++;
        tasks.add(newEvent);
        if (isMark) {
            tasks.get(tasksCount).markTask(true);
        }
    }
    
    public void saveTasks(File saveFile) {
        // TODO: overwrite and save tasks into scanner everytime a command is made
        
    }
    public void addTask(Task newTask) {
        tasksCount++;
        tasks.add(newTask);
        
        System.out.println("Got it. I've added this task:" + System.lineSeparator() +
                newTask.toString() + System.lineSeparator() +
                "Now you have " + tasksCount + " tasks in the list.");
    }
    
    public void markTaskDone(int taskNumber) {
        tasks.get(taskNumber).markTask(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskNumber + "." + tasks.get(taskNumber).toString());
    }
    
    public void markTaskUndone(int taskNumber) {
        tasks.get(taskNumber).markTask(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskNumber + "." + tasks.get(taskNumber).toString());
    }
    
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println(i + "." + tasks.get(i).toString());
        }
        
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
    }
}
