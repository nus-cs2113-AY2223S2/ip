package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskList {
    private Task[] tasks;
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

    public TaskList(int size) {
        this.tasks = new Task[size];
        noOfTasks = 0;
    }

    private void printAddTaskMessage(int taskNo) {
        System.out.println("Got it. I have added this task:");
        System.out.println(tasks[noOfTasks].toString());
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
        tasks[noOfTasks] = new ToDo(args);
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
        indexOfBy+=3;
        String by = args.substring(indexOfBy);
        by = by.trim();
        if (hasBlankArgument(by, "by")) {
            return;
        }
        tasks[noOfTasks] = new Deadline(name, by);
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
        tasks[noOfTasks] = new Event(name, from, to);
        printAddTaskMessage(noOfTasks);
    }

    public void listTasks() {
        if (noOfTasks == 0) {
            System.out.println("No tasks yet. Please input a task.");
        }
        for (int i = 1; i <= noOfTasks; i++) {
            System.out.print(i + ". ");
            System.out.println(tasks[i].toString());
        }
    }

    public void markDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (tasks[taskNo].isDone()) {
            System.out.println("Already done.");
        } else {
            tasks[taskNo].setStatus(true);
            System.out.println("Nice! I have marked this task as done.");
            System.out.println(tasks[taskNo].toString());
        }
    }

    public void unmarkDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (!tasks[taskNo].isDone()) {
            System.out.println("Not done yet. Please finish it.");
        } else {
            tasks[taskNo].setStatus(false);
            System.out.println("Ok I have marked this as not done yet.");
            System.out.println(tasks[taskNo].toString());
        }
    }
}
