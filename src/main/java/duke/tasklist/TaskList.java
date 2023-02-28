package duke.tasklist;

import duke.DukeException;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String line = "__________________________________________________________";
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static Task getTask(Integer x) {
        return tasks.get(x-1);
    }
    public static int getNumTasks() {
        return numTasks;
    }

    public static void validTask(String[] userInput) throws IndexOutOfBoundsException{
        if (userInput.length < 2 && (userInput[0].equals("todo") ||
                userInput[0].equals("event") || userInput[0].equals("deadline"))) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void markValidTask(String[] userInput) throws IndexOutOfBoundsException {
        int x = Integer.parseInt(userInput[1]);
        if (tasks.get(x-1) == null || tasks.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(x-1).markAsDone(userInput[0]);
    }

    public static void readTask(List<String> taskList) throws IOException {
        for(String task: taskList) {
            Task t;
            String type = task.substring(1,2); //type of task
            String status = task.substring(4,5); //"X" or " "
            if (type.equals("T")) {
                String descriptor = task.substring(7);
                t = new Todo(descriptor);
            } else if (type.equals("D")) {
                String descriptor = task.substring(7, task.indexOf("(by: "));
                String by = task.substring(task.indexOf("(by: ")+5, task.indexOf(")"));
                t = new Deadline(descriptor,by);
            } else if (type.equals("E")) {
                String descriptor = task.substring(7, task.indexOf("(from: "));
                String from = task.substring(task.indexOf("(from: ")+7, task.indexOf("to: "));
                String to = task.substring(task.indexOf("to: ")+4, task.indexOf(")"));
                t = new Event(descriptor, from, to);
            } else {
                throw new IOException();
            }
            tasks.add(t);
            tasks.get(tasks.size()-1).addIsDone(status);
        }
        numTasks = tasks.size();
        Ui.printMessage(Ui.CommandType.LIST);
    }

    public static void addTask(String userInput) throws DukeException {
        Task t;
        String[] words = userInput.split(" ");
        validTask(words);
        String descriptor = userInput.substring(userInput.indexOf(words[1]), userInput.length());
        if (words[0].equals("todo")) {
            t = new Todo(descriptor);
            Ui.printMessage(t, Ui.CommandType.TODO);
        } else if (words[0].equals("deadline")) {
            String by = descriptor.split("/by ")[1];
            descriptor = descriptor.split("/by ")[0];
            t = new Deadline(descriptor, by);
            Ui.printMessage(t, Ui.CommandType.DEADLINE);
        } else if (words[0].equals("event")) {
            String to = descriptor.split("/to ")[1];
            String from = descriptor.split(" /")[1];
            descriptor = descriptor.split("/")[0];
            t = new Event(descriptor, from, to);
            Ui.printMessage(t, Ui.CommandType.EVENT);
        } else {
            throw new IndexOutOfBoundsException();
        }
        tasks.add(t);
        numTasks = tasks.size();
    }

    public static void deleteTask(String userInput) throws DukeException {
        String taskNum = userInput.substring(userInput.length()-1);
        int x = Integer.parseInt(taskNum);
        if (tasks.get(x-1) == null || tasks.size() == 0) {
            throw new DukeException();
        }
        Task temp = tasks.get(x-1);
        tasks.remove(x-1);
        numTasks = tasks.size();
        Ui.printMessage(temp, Ui.CommandType.DELETE);
    }
}
