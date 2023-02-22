package duke.tasks;

import duke.tools.UI;

/**
 * Print bye and exit the program.
 */
public class Bye {
    private final static String BYE = " Bye. Hope to see you again soon!\n";

    public void printBye(){
        UI exit = new UI();
        exit.sayBye(BYE);

    }
}
