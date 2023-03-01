package Duke;

import java.util.Scanner;

import Duke.cmd.Command;
import Duke.exception.DukeException;
import Duke.tasklist.taskList;
import Duke.ui.textUI;

/**
 * A chatbot program, which interacts with the user to perform various tasks such as adding, deleting, and listing tasks.
 * The program uses ArrayList to store the tasks and text files to read and write the tasks data.
 * The program also catches exceptions if the user enters invalid input.
 */
public class Duke {
    public static void main (String[] args) {
        textUI.printStart ();
        Scanner scan = new Scanner (System.in);
        String input = scan.nextLine ();
        taskList tasks = new taskList ();
        /**
         * Main function that takes input from user and perform actions accordingly
         */
        while (!("bye".equalsIgnoreCase (input))) {
            try {
                Command.checkInput (tasks, input);
            } catch (DukeException de) {
                System.out.println (de.getMessage ());
                System.out.println ("Please enter again: ");
                textUI.printLine ();
            } finally {
                input = scan.nextLine ();
            }
        }
        textUI.printEnd ();
    }
}


