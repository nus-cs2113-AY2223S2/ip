package duke;

import duke.common.Common;
import duke.ui.TextUi;

/**
 * Starts the chatbot application and interacts with the user.
 */
public class Duke {

    private static TextUi ui = new TextUi();

    /** Run the application until termination.  */
    public static void main(String[] args) {
        start();
        ui.executeInputUntilExit();
    }

    /** Starts the application by loading the data from storage and shows welcome message.  */
    private static void start() {
        Common.dataFile.moveDataToArrayList();
        ui.showWelcomeMessage();
    }

}

