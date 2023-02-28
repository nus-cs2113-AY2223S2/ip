package duke;

import duke.exception.DukeException;
import duke.exception.EmptyListError;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static TaskList tasks;

    public static void main(String[] args) {

        try {
            Storage.checkFileAccess();
            ArrayList<Task> taskLists = Storage.convertToList();
            tasks = new TaskList(taskLists);
//            tasks.addAll(taskLists);
        } catch (FileNotFoundException err) {
            System.out.println("File not found");
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String input;
        Scanner in = new Scanner(System.in);
        UI.showWelcomeMessage();
        do {
            input = in.nextLine();
            if (DukeException.hasError(input)) {
                continue;
            }
            run(input);
        } while (!input.equals("bye"));
    }

    private static void run(String input) {
        String[] inputWords = input.split(" ");
        switch (inputWords[0]) {
        case "todo":
            input = input.replaceFirst("todo", "").trim();
            UI.printTodo(input);
            Todo todo = new Todo(input, "T");
            tasks.addToList(todo);
            UI.printTaskList(tasks.sizeOfList());
            updateFile(tasks);
            break;
        case "deadline":
            input = input.replaceFirst("deadline", "").trim();
            int indexOfSlash = input.indexOf("/");
            String taskName = input.substring(0, indexOfSlash - 1);
            String by = input.substring(indexOfSlash + 4);
            UI.printDeadline(taskName, by);
            Deadline deadline = new Deadline(taskName, "D", by);
            tasks.addToList(deadline);
            UI.printTaskList(tasks.sizeOfList());
            updateFile(tasks);
            break;
        case "event":
            input = input.replaceFirst("event", "").trim();
            indexOfSlash = input.indexOf("/");
            int lastIndexOfSlash = input.lastIndexOf("/");
            taskName = input.substring(0, indexOfSlash - 1);
            String start = input.substring(indexOfSlash + 6, lastIndexOfSlash - 1);
            String end = input.substring(lastIndexOfSlash + 4);
            UI.printEvent(taskName, start, end);
            Event event = new Event(taskName, "E", start, end);
            tasks.addToList(event);
            UI.printTaskList(tasks.sizeOfList());
            updateFile(tasks);
            break;
        case "list":
            try {
                printList();
            } catch (EmptyListError err) {
                UI.printMessage("There is nothing inside the list");
            }
            break;
        case "mark":
            int taskNum = Integer.parseInt(inputWords[1]) - 1;
            UI.printMessage("Nice! I've marked this task as done:");
            tasks.mark(taskNum);
            System.out.println("  [" + tasks.getStatus(taskNum)+ "] " + tasks.getDescription(taskNum));
            updateFile(tasks);
            break;
        case "unmark":
            taskNum = Integer.parseInt(inputWords[1]) - 1;
            UI.printMessage("OK, I've marked this task as not done yet:");
            tasks.unmark(taskNum);
            System.out.println("  [" + tasks.getStatus(taskNum)+ "] " + tasks.getDescription(taskNum));
            updateFile(tasks);
            break;
        case "delete":
            taskNum = Integer.parseInt(inputWords[1]) - 1;
            UI.printMessage("Noted. I've removed this task:");
            System.out.println("  " + tasks.getString(taskNum));
            tasks.removeTask(taskNum);
            UI.printTaskList(tasks.sizeOfList());
            updateFile(tasks);
            break;
        case "bye":
            UI.showByeMessage();
            updateFile(tasks);
            break;
        default: //never reached
            UI.printMessage("Never reached");
        }
    }

    private static void updateFile(TaskList tasks) {
        ArrayList<Task> tasksList = tasks.returnTasks();
        try {
            Storage.writeToFile("");
            for (Task task : tasksList) {
                Storage.appendToFile(task.textToSave() + System.lineSeparator());
            }
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
        }
    }

    private static void printList() throws EmptyListError {
        if (tasks.isEmpty()) {
            throw new EmptyListError();
        }
        UI.printMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.sizeOfList(); i++) {
            System.out.println((i + 1) + "." + tasks.getString(i));
        }
    }
}
