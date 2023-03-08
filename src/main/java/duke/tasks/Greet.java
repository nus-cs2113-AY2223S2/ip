package duke.tasks;

import duke.tools.Formatter;
import duke.tools.UI;

/**
 * Print logo and hello.
 * Private attributes are logo and hello,
 * Public method to print greeting.
 */
public class Greet {
    private final static String[] LOGO = {" ____        _"
                    , "|  _ \\ _   _| | _____"
                    , "| | | | | | | |/ / _ \\"
                    , "| |_| | |_| |   <  __/"
                    , "|____/ \\__,_|_|\\_\\___|"};

    private final static String[] HELLO = {"Hello! I'm Duke, your task assistant."
                    , "Nice to meet you :D"
                    , "What can I do for you?"};

    private Formatter formatter = new Formatter();
    private UI sayHello = new UI();

    /**
     * Print hello message and logo in an appropriate format.
     */
    public void printHello() {
        formatter.addStringIndentation(LOGO);
        formatter.addStringIndentation(HELLO);
        sayHello.greet(LOGO, HELLO);
    }
}
