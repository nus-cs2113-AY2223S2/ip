import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bro.Parser;
import bro.Storage;
import bro.TaskList;
import bro.Ui;
import static bro.Messages.FILE_NOT_FOUND;

import static bro.Ui.printGreeting;
import static bro.Ui.printReply;

public class Bro {
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        TaskList tasks;
        try {
            tasks = Storage.load(new TaskList());
        } catch (FileNotFoundException e) {     // create a new file to save the list of tasks
            printReply(FILE_NOT_FOUND);
            File f = new File("tasks.txt");
            if (f.createNewFile()) {                  // throws IOException
                printReply(" New File \"tasks.txt\" created at: " + f.getAbsolutePath());
            }
            tasks = Storage.load(new TaskList());
        }
        printGreeting();
        Parser p = new Parser();
        while (p.haveInput()) {
            String[] arrayOfInputs = ui.getUserCommand();
            p.parseInput(tasks, arrayOfInputs);
        }
    }
}
