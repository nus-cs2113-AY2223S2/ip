package btb.exceptions;

import btb.constants.Constant;

public class EmptyTaskNumberException extends Exception {
    public String getMessage() {
        return Constant.TAB_SPACE + "You did not enter a task number (っ °Д °;)っ, please try again!";
    }
}
