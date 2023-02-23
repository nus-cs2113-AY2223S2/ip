package siri;

import siri.exception.AddTaskIndexOutOfBounds;
import siri.exception.MarkerArrayIndexOutOfBoundsException;
import siri.exception.UnknownCommandException;
import siri.general.Parser;
import siri.task.*;
import siri.general.Ui;
import siri.general.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static final String TASK_FILE = "data/tasklists.txt";
    public Ui ui;
    public static Storage storage;
    public static TaskList tasks;
    public static int indexOfTask = 0;
    public static boolean isExit = false;

    public void runCommandLoopUntilExitCommand() {
        while (isExit == false) {
            String input = ui.readCommand();

            ui.drawSiriChatBox();

            try {
                Parser p = new Parser(input);
                p.parse();
            } catch (UnknownCommandException e) {
                System.out.println("T^T OPPS!!! I'm sorry, but I don't know what that means");
            } catch (AddTaskIndexOutOfBounds e) {
                e.printError();
            } catch (MarkerArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter the task number that you would like to mark / unmark, in the following format:");
                System.out.println("For example if you want to mark / unmark task 2 as done / undone: mark 2 / unmark 2");
            } catch (NumberFormatException e) {
                System.out.println("Please mark / unmark each task one by one, in the following format: ");
                System.out.println("For example if you want to mark / unmark task 2 as done / undone: mark 2 / unmark 2");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please add the tasks in the following format: \n");
                System.out.println("Todo task format: todo task_name");
                System.out.println("Deadline task format: deadline deadline_name /by deadline_date");
                System.out.println("Event task format: event event_name /from event_from_timing /to event_to_timing");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please only mark / unmark / delete task that is available in your task list.");
                System.out.println("You have up to " + indexOfTask + " number of tasks.");
            } catch (IOException e) {
                ui.showLoadingError();
            }
            ui.drawStraightLine();
        }
    }

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            storage.createFile();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.greet();
        runCommandLoopUntilExitCommand();
        storage.overwriteEntireList();
    }

    public static void main(String[] args) throws IOException {
        new Duke(TASK_FILE).run();
    }
}