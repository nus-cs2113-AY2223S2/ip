package duke.execptions;

import static duke.constants.Constants.LINEBREAK;

public class InvalidTaskException extends Exception {

    public String getMessage() {
        return "Please enter a valid command!\n" + LINEBREAK;
    }

}
