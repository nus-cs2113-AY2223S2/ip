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
    public static ArrayList<Task> tasksList = new ArrayList<>();
    public static final String filePath = "src/main/java/duke/storage/duke.txt";


    public static void main(String[] args) {

        try {
            List<Task> taskLists = Storage.convertToArray(filePath);
            tasksList.addAll(taskLists);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
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
            if (DukeException.hasError(input)){
                continue;
            }
            command(input);
        } while (!input.equals("bye"));
    }

    private static void command(String input) {
        String[] inputWords = input.split(" ");
        switch (inputWords[0]){
        case "todo":
            input = input.replaceFirst("todo", "").trim();
            UI.printTodo(input);
            Todo t = new Todo(input, "T");
            tasksList.add(t);
            UI.printTaskList(tasksList.size());
            break;
        case "deadline":
            input = input.replaceFirst("deadline", "").trim();
            int indexOfSlash = input.indexOf("/");
            String taskName = input.substring(0, indexOfSlash - 1);
            String by = input.substring(indexOfSlash + 4);
            UI.printDeadline(taskName, by);
            Deadline d = new Deadline(taskName, "D", by);
            tasksList.add(d);
            UI.printTaskList(tasksList.size());
            break;
        case "event":
            input = input.replaceFirst("event", "").trim();
            indexOfSlash = input.indexOf("/");
            int lastIndexOfSlash = input.lastIndexOf("/");
            taskName = input.substring(0, indexOfSlash - 1);
            String start = input.substring(indexOfSlash + 6, lastIndexOfSlash - 1);
            String end = input.substring(lastIndexOfSlash + 4);
            UI.printEvent(taskName, start, end);
            Event e = new Event(taskName, "E", start, end);
            tasksList.add(e);
            UI.printTaskList(tasksList.size());
            break;
        case "list":
            try{
                printList();
            } catch (EmptyListError err){
                UI.printMessage("There is nothing inside the list");
            }
            break;
        case "mark":
            int taskNum = Integer.parseInt(inputWords[1]) - 1;
            UI.printMessage("Nice! I've marked this task as done:");
            tasksList.get(taskNum).markAsDone();
            System.out.println("  [" + tasksList.get(taskNum).getStatusIcon() + "] " + tasksList.get(taskNum).description);
            break;
        case "unmark":
            taskNum = Integer.parseInt(inputWords[1]) - 1;
            UI.printMessage("OK, I've marked this task as not done yet:");
            tasksList.get(taskNum).markAsUndone();
            System.out.println("  [" + tasksList.get(taskNum).getStatusIcon() + "] " + tasksList.get(taskNum).description);
            break;
        case "delete":
            taskNum = Integer.parseInt(inputWords[1]) - 1;
            UI.printMessage("Noted. I've removed this task:");
            System.out.println("  " + tasksList.get(taskNum).toString());
            tasksList.remove(taskNum);
            UI.printTaskList(tasksList.size());
            break;
        case "bye":
            UI.showByeMessage();
            try {
                Storage.writeToFile(filePath, "");
                for (Task task_i : tasksList){
                    Storage.appendToFile(filePath, task_i.textToSave() + System.lineSeparator());
                }
            } catch (IOException err) {
                System.out.println("Something went wrong: " + err.getMessage());
            }
            break;
        default: //never reached
            UI.printMessage("added: " + input);
            Task u = new Task(input, "");
            tasksList.add(u);
        }
    }

    private static void printList() throws EmptyListError {
        if (tasksList.isEmpty()){
            throw new EmptyListError();
        }
        UI.printMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println((i + 1) + "." + tasksList.get(i).toString());
        }
    }
}
