package duke;

import duke.exceptions.DukeException;
import duke.ui.Ui;

public class Duke {
    public static void main(String[] args) {

        Ui.printWelcome();
        try {
            Ui.getInput();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}
