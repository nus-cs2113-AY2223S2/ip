package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskList {
    private ArrayList<Task> tasks;
    private int noOfTasks;

    private boolean isValidTaskNo(int taskNo) {
        if (taskNo <= 0) {
            System.out.println("Negative task number entered, please don't try to crash the program a**h***.");
            return false;
        }
        if (taskNo > noOfTasks) {
            System.out.println("Task number does not exist, there are only " + noOfTasks + " tasks in total.");
            return false;
        }
        return true;
    }

    private void parseTasks(String[] args) {
        switch (args[0]) {
        case "T":
            tasks.add(new ToDo(args[2]));
            tasks.get(tasks.size() - 1).setStatus(args[1].equals("Y"));
            break;
        case "D":
            tasks.add(new Deadline(args[2], args[3]));
            tasks.get(tasks.size() - 1).setStatus(args[1].equals("Y"));
            break;
        case "E":
            tasks.add(new Event(args[2], args[3], args[4]));
            tasks.get(tasks.size() - 1).setStatus(args[1].equals("Y"));
            break;
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
        noOfTasks = 0;
    }

    public TaskList(Scanner s) {
        this.tasks = new ArrayList<>();
        this.noOfTasks = 0;
        while (s.hasNextLine()) {
            noOfTasks++;
            String line = s.nextLine();
            String[] args = line.split("\\|");
            parseTasks(args);
        }
        listTasks();
    }

    private void printAddTaskMessage(int taskNo) {
        System.out.println("Got it. I have added this task:");
        System.out.println(tasks.get(taskNo - 1).toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list");
    }

    private void printDeleteTaskMessage(int taskNo) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskNo-1).toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list");
    }

    private void printBlankArgumentError(String type) {
        System.out.println(type + " cannot be blank.");
    }

    private void printMissingKeywordError(String type) {
        System.out.println(type + " keyword is missing.");
    }

    private boolean hasBlankArgument(String arg, String type) {
        if(arg.isEmpty()) {
            noOfTasks--;
            printBlankArgumentError(type);
            return true;
        }
        return false;
    }

    private boolean hasMissingKeyword(int index, String type) {
        if (index == -1) {
            noOfTasks--;
            printMissingKeywordError(type);
            return true;
        }
        return false;
    }

    public void addToDo(String args) {
        noOfTasks++;
        args = args.trim();
        if(hasBlankArgument(args, "Name")) {
            return;
        }
        tasks.add(new ToDo(args));
        printAddTaskMessage(noOfTasks);
    }

    public void addDeadline(String args) {
        noOfTasks++;
        int indexOfBy = args.indexOf("/by");
        if (hasMissingKeyword(indexOfBy, "/by")) {
            return;
        }
        String name = args.substring(0, indexOfBy);
        name = name.trim();
        if (hasBlankArgument(name, "Name")) {
            return;
        }
        indexOfBy += 3;
        String by = args.substring(indexOfBy);
        by = by.trim();
        if (hasBlankArgument(by, "by")) {
            return;
        }
        tasks.add(new Deadline(name, by));
        printAddTaskMessage(noOfTasks);
    }

    public void addEvent(String args) {
        noOfTasks++;
        int indexOfFrom = args.indexOf("/from");
        int indexOfTo = args.indexOf("/to");
        if (hasMissingKeyword(indexOfFrom, "/from")) {
            return;
        }
        if (hasMissingKeyword(indexOfTo, "/to")) {
            return;
        }
        if (indexOfFrom > indexOfTo) {
            noOfTasks--;
            System.out.println("Please ensure that /from is before /to.");
            System.out.println("Cos I am too lazy to code for both cases.");
            return;
        }
        String name = args.substring(0, indexOfFrom);
        name = name.trim();
        if (hasBlankArgument(name, "Name")) {
            return;
        }
        indexOfFrom += 5;
        String from = args.substring(indexOfFrom, indexOfTo);
        from = from.trim();
        if (hasBlankArgument(from, "from")) {
            return;
        }
        indexOfTo += 3;
        String to = args.substring(indexOfTo);
        to = to.trim();
        if (hasBlankArgument(to, "to")) {
            return;
        }
        tasks.add(new Event(name, from, to));
        printAddTaskMessage(noOfTasks);
    }

    public void listTasks() {
        if (noOfTasks == 0) {
            System.out.println("No tasks yet. Please input a task.");
        }
        for (int i = 0; i < noOfTasks; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(tasks.get(i).toString());
        }
    }

    public void markDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (tasks.get(taskNo - 1).isDone()) {
            System.out.println("Already done.");
        } else {
            tasks.get(taskNo - 1).setStatus(true);
            System.out.println("Nice! I have marked this task as done.");
            System.out.println(tasks.get(taskNo - 1).toString());
        }
    }

    public void unmarkDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (!tasks.get(taskNo - 1).isDone()) {
            System.out.println("Not done yet. Please finish it.");
        } else {
            tasks.get(taskNo - 1).setStatus(false);
            System.out.println("Ok I have marked this as not done yet.");
            System.out.println(tasks.get(taskNo - 1).toString());
        }
    }

    public void deleteTask(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        noOfTasks--;
        printDeleteTaskMessage(taskNo);
        tasks.remove(taskNo - 1);
    }

    public void saveToFile(FileWriter fileWriter) throws IOException {
        for (Task task : tasks) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }
    }
}
