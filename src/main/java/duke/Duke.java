package duke;

import duke.common.Common;
import duke.ui.TextUi;

public class Duke {

    private static TextUi ui = new TextUi();

    public static void main(String[] args) {
        start();
        ui.executeInputUntilExit();
    }

    private static void start() {
        Common.dataFile.moveDataToArrayList();
        ui.showWelcomeMessage();
    }

}

