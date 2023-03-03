import AllCommands.*;
import Support.Storage;
import Support.TaskList;
import Support.Ui;

import java.io.IOException;
import java.util.Scanner;
// Now it is level 9


// PR merged now
// All merged
public class Duke {
    private static TaskList tasks;

    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = storage.readFromFile(tasks);
        } catch (IOException e) {
            System.out.println("The file is not exist, try again: " + e.getMessage());
            tasks = new TaskList();
            storage.makeDirectory();
        }
    }

    public static void run() {
        // Greet the user
        Ui.initialGreet();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String firstWord = line.split(" ")[0];
            boolean hasError;
            hasError = Ui.exceptionCheck(line);

            if (!hasError) {
                if (firstWord.equals("list")) {
                    ListCommand list = new ListCommand(line);
                    list.listCommandMethod(tasks);
                } else if (firstWord.equals("mark")) {
                    MarkCommand mark = new MarkCommand(line);
                    mark.markCommandMethod(tasks);
                    Storage.saveFile(tasks);
                } else if (firstWord.equals("unmark")) {
                    UnmarkCommand unmark = new UnmarkCommand(line);
                    unmark.unmarkCommandMethod(tasks);
                    Storage.saveFile(tasks);
                } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                    AddCommand add = new AddCommand(line);
                    add.addCommandMethod(tasks);
                    Storage.saveFile(tasks);
                } else if (firstWord.equals("delete")) {
                    DeleteCommand delete = new DeleteCommand(line);
                    delete.deleteCommandMethod(tasks);
                    Storage.saveFile(tasks);
                } else if (firstWord.equals("find")) {
                    FindCommand find = new FindCommand(line);
                    find.findCommandMethod(tasks);
                }
            }
            line = in.nextLine();
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}