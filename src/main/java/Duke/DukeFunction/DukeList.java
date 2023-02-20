package Duke.DukeFunction;

import Duke.DukeCommandLine.DukeException;
import Duke.DukeTask.DukeTask;

import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class DukeList {
    private static final ArrayList<DukeTask> taskList = new ArrayList<>();
    private static final String FILE_PATH = "data/list.txt";
    public void addTask(DukeTask task) throws DukeException {
        try {
            taskList.add(task);
            System.out.println("Got it. I've added this task:");
            task.printTask();
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            taskList.get(i).printTask(i);
        }
    }
    public static void isValidID(int id) throws DukeException {
        boolean isIDInValid = id < 0 || id >= taskList.size();
        if(isIDInValid) {
            throw new DukeException("Sorry, the id is invalid!");
        }
    }

    public void markDone(int id) throws DukeException {
        try {
            isValidID(id);
            taskList.get(id).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(id).printTask();
        } catch (DukeException e) {
            throw new DukeException("[mark] " + e.getMessage());
        }
    }
    public void unmarkDone(int id) throws DukeException {
        try {
            isValidID(id);
            taskList.get(id).unmarkAsDone();
            System.out.println("Nice! I've unmarked this task as done:");
            taskList.get(id).printTask();
        } catch (DukeException e) {
            throw new DukeException("[unmark] " + e.getMessage());
        }
    }
    public void deleteTask(int id) throws DukeException {
        try {
            isValidID(id);
            System.out.println("Noted. I've removed this task:");
            taskList.get(id).printTask();
            taskList.remove(id);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (DukeException e) {
            throw new DukeException("[delete] " + e.getMessage());
        }
    }

    public void storeTask(FileWriter fileWriter) throws IOException {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).saveTask());
            }
        } catch (IOException e) {
            throw e;
        }
    }
    public void findTask(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).containsName(keyword)) {
                taskList.get(i).printTask(i);
            }
        }
    }
}
