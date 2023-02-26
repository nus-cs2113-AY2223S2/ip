package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.ArrayList;

public class Parser {

    private static Ui ui;
    private static boolean isExit = false;

    public static void processInput(String line, TaskList taskList) throws DukeException {
        try {
                String[] words = line.split(" ");
                if (line.startsWith("todo")) {
                    registerTodo(taskList.lists, line);
                } else if (line.startsWith("deadline")) {
                    registerDeadline(taskList.lists, line);
                } else if (line.startsWith("event")) {
                    registerEvent(taskList.lists, line);
                } else if (line.startsWith("list")) {
                    taskList.listTasks();
                } else if (line.startsWith("mark") || line.startsWith("unmark") || line.startsWith("delete")) {
                    taskList.markOrDeleteTask(words, line);
                } else if (line.startsWith("find")) {
                    taskList.searchForTask(line);
                } else if (line.equals("bye")) {
                    ui.printExiting();
                    isExit = true;
                } else {
                    throw new DukeException();
                }
                Storage.saveDataFromInput(taskList.lists);
        } catch (DukeException e){
            System.out.println("I am not a chat bot, please do not chat to me.");
        }
    }

    public static ArrayList<Task> registerDukeFileTasks (ArrayList<Task> lists, String[] inputLines) {
        String type = inputLines[0].strip();
        boolean isDone = (inputLines[1].equals("1")) ? true : false;
        if (type.equals("T")) {
            String description = inputLines[2];
            lists.add(new Todo(description));
        } else if (type.equals("D")) {
            String description = inputLines[2];
            String deadline = inputLines[3].strip();
            lists.add(new Deadline(description, deadline));
        } else if (type.equals("E")) {
            String description = inputLines[2];
            String start = inputLines[3];
            String end = inputLines[4];
            lists.add(new Event(description, start, end));
        }

        if (isDone) {
            lists.get(lists.size() - 1).markAsDone();
        }
        return lists;
    }

    public static void registerTodo(ArrayList<Task> lists, String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLine = line.split(" ", 2);
            Task item = new Todo(inputLine[1]);
            lists.add(item);
            ui.printAddTask(item);
            ui.printListSize(lists.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
        }
    }

    public static void registerDeadline(ArrayList<Task> lists, String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split(" /by ");
            String description = inputLines[0];
            String deadline = inputLines[1];
            Task item = new Deadline(description, deadline);
            lists.add(item);
            ui.printAddTask(item);
            ui.printListSize(lists.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
        }
    }

    public static void registerEvent(ArrayList<Task> lists, String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split(" /from ");
            String description = inputLines[0];
            inputLines = inputLines[1].split(" /to ");
            String start = inputLines[0];
            String end = inputLines[1];
            Task item = new Event(description, start, end);
            lists.add(item);
            ui.printAddTask(item);
            ui.printListSize(lists.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
        }
    }

    public static boolean toExit() {
        return isExit;
    }
}
