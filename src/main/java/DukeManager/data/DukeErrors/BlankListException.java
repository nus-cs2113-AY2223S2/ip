package DukeManager.data.DukeErrors;

import static DukeManager.common.Messages.LINE_PARTITION;

public class BlankListException extends Exception {
    public String getMessage() {
        return ("The list is empty. Input tasks into the lists before using this command.");
    }
}
