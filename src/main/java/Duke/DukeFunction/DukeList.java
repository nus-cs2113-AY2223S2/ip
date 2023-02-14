package Duke.DukeFunction;

import Duke.DukeFunction.DukePrinter;
import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeList {
    private static final ArrayList<DukeTask> taskList = new ArrayList<>();
    private static final String FILE_PATH = "data/list.txt";
    private static final int MAX_TASK = 100;
    public static void addTask(DukeTask task) {
        if(taskList.size() == MAX_TASK) {
            DukePrinter.printStringln("Sorry, the list is full!");
            return;
        }
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        task.printTask();
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        DukePrinter.printLine();
    }
    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            taskList.get(i).printTask(i);
        }
        DukePrinter.printLine();
    }
    public static boolean isValidID(int id) {
        boolean isIDInValid = id < 0 || id >= taskList.size();
        if(isIDInValid) {
            DukePrinter.printErrorln("Sorry, the id is invalid!");
            return false;
        }
        return true;
    }

    public static void markDone(int id) {
        if(isValidID(id)) {
            taskList.get(id).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(id).printTask();
            DukePrinter.printLine();
        }
    }
    public static void unmarkDone(int id) {
        if(isValidID(id)) {
            taskList.get(id).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            taskList.get(id).printTask();
            DukePrinter.printLine();
        }
    }
    public static void deleteTask(int id) {
        if(isValidID(id)) {
            System.out.println("Noted. I've removed this task:");
            taskList.get(id).printTask();
            taskList.remove(id);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            DukePrinter.printLine();
        }
    }
    public static void saveTask() {
        File file = new File(FILE_PATH);
        try {
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).saveTask());
            }
            fileWriter.close();
            DukePrinter.printStringln("Saved successfully!");
        } catch (IOException e) {
            DukePrinter.printErrorln("[IOException] Sorry, I can't save the file, " + e.getMessage());
        }
    }
    public static void loadTask() {
        File file = new File("data/list.txt");
        try {
            if(!file.exists()) {
                DukePrinter.printStringln("No file to load");
                return;
            }
            Scanner fileReader = new Scanner(file);
            String TaskLine;
            while(fileReader.hasNextLine()) {
                TaskLine = fileReader.nextLine();
                String[] taskInfo = TaskLine.split(" \\| ");
                taskInfo[0] = taskInfo[0].trim();
                taskInfo[1] = taskInfo[1].trim();
                taskInfo[2] = taskInfo[2].trim();
                switch (taskInfo[0]) {
                    case "T":
                        DukeTask task = new DukeTask(taskInfo[2]);
                        if(taskInfo[1].equals("1")) {
                            task.markAsDone();
                        }
                        taskList.add(task);
                        break;
                    case "D":
                        taskInfo[3] = taskInfo[3].trim();
                        DukeTask deadline = new DukeDeadline(taskInfo[2], taskInfo[3]);
                        if(taskInfo[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        taskList.add(deadline);
                        break;
                    case "E":
                        taskInfo[3] = taskInfo[3].trim();
                        taskInfo[4] = taskInfo[4].trim();
                        DukeTask event = new DukeEvent(taskInfo[2], taskInfo[3], taskInfo[4]);
                        if(taskInfo[1].equals("1")) {
                            event.markAsDone();
                        }
                        taskList.add(event);
                        break;
                    default:
                        DukePrinter.printErrorln("Sorry, I can't load the file!");
                        break;
                }
            }
            DukePrinter.printStringln("Loaded successfully!");
        } catch (IOException e) {
            DukePrinter.printErrorln("[IOException] Sorry, I can't load the file, " + e.getMessage());
        }
    }
}
