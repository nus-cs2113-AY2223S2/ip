package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static void handleCommand(String input, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(tasks);
        try {
            String[] words = input.split(" ");
            switch (words[0]) {
            case "list":
                taskList.printList();
                break;

            case "mark":
                taskList.markAsDone(input);
                break;

            case "unmark":
                taskList.markAsUndone(input);
                break;

            case "todo":
                taskList.addTodo(input);
                break;

            case "deadline":
                taskList.addDeadline(input);
                break;

            case "event":
                taskList.addEvent(input);
                break;

            case "delete":
                taskList.deleteTask(input);
                break;

            default:
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            ui.printLine();
            System.out.println(e.getMessage());
            ui.printLine();
        } catch (IOException ex) {
            ui.printLine();
            System.out.println("Unable to save data");
            ui.printLine();
        }
    }
}
