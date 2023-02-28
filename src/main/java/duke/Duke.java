package duke;

import duke.common.Common;
import duke.ui.TextUi;

/**
 * The Duke class is responsible for starting the chatbot application.
 * It contains a main method that starts the application and interaction with the user.
 */
public class Duke {

    private static TextUi ui = new TextUi();

    /** Run the application until termination.  */
    public static void main(String[] args) {
        start();
        ui.executeInputUntilExit();
    }

    /** Starts the application by loading the data from storage and shows welcome message  */
    private static void start() {
        Common.dataFile.moveDataToArrayList();
        ui.showWelcomeMessage();
    }

}

