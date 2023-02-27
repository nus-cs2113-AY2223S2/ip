import duke.tasks.*;
import duke.exceptions.*;
import duke.files.FileManager;
import duke.input.*;

import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Contains the main method to execute the program. Duke reads
 * saved information from data.txt, greets the user, processes
 * input, then ends and saves tasks to data.txt
 */
public class Duke {
    public static void main(String[] args) throws Exception {

        final Ui UI = new Ui();

        // Text declarations and initial greeting
        final String BARRIER = "____________________________________________________________";

        UI.printGreeting();

        final FileManager STORAGE = new FileManager();

        // Scan data.txt for saved tasks
        try {
            STORAGE.populateTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("Argh! There was an issue fetching yer saved tasks! Ye must be in the 'java' directory!");
        }

        UI.takeInput();

        UI.printExit();

        // Save files to data.txt
        try {
            STORAGE.populateFile();
        } catch (IOException e) {
            System.out.println("Argh! There was an issue saving yer tasks!");
        }
    }
}
