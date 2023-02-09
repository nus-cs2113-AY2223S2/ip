package duke.exceptions;

import static duke.constants.Constants.LINEBREAK;

public class InvalidArgsException extends Exception{
    public String getMessage(){
        return "The command has the wrong number of arguments!\n" + LINEBREAK;
    }
}
