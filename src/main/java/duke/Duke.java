package duke;
import duke.ui.TextUi;

import java.util.Scanner;

public class Duke {

    public static final String MEANINGLESS_SENTENCE_AFTER_KEYWORD = "OPPS!!! The sentence after keyword has no meaning";
    private static TextUi ui = new TextUi();
    public static void main(String[] args) {
        start();
        ui.giveInputUntilExit();
    }

    private static void start() {
        Common.dataFile.moveDataToArrayList();
        ui.showWelcomeMessage();
    }
}

