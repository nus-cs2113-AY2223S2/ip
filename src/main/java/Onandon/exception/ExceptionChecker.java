package Onandon.exception;

/**
 * Class for checking whether there would be exception that occurs during the execution
 * of the program.
 */
public class ExceptionChecker {
    protected Boolean isNotTodo;
    protected Boolean isNotDeadline;
    protected Boolean isNotEvent;
    protected Boolean isNotMark;
    protected Boolean isNotUnmark;
    protected Boolean isNotList;
    protected Boolean isNotDelete;
    protected Boolean isNotExit;
    protected Boolean isNotFind;

    /**
     * Check whether there would be any exception and throw the exception if there is any.
     *
     * @param inputText Full String command given by the user.
     * @throws OnandonNotaskException If there is no description in the command.
     * @throws OnandonEmptyException If the length of the command is 0.
     * @throws OnandonUnknownException If the command type is out of range of this program or unknown.
     */
    public void checkException(String inputText) throws OnandonEmptyException, OnandonUnknownException, OnandonNotaskException {
        String[] split = inputText.split(" ");
        String tgt = inputText.split(" ")[0];
        this.isNotTodo = !tgt.equals("todo");
        this.isNotDeadline = !tgt.equals("deadline");
        this.isNotEvent = !tgt.equals("event");
        this.isNotMark = !tgt.equals("mark");
        this.isNotUnmark = !tgt.equals("unmark");
        this.isNotList = !tgt.equals("list");
        this.isNotDelete = !tgt.equals("delete");
        this.isNotExit = !tgt.equals("exit");
        this.isNotFind = !tgt.equals("find");

        if(inputText.length() == 0){
            throw new OnandonEmptyException();
        } else if(this.isNotTodo &&
                this.isNotDeadline &&
                this.isNotEvent &&
                this.isNotMark &&
                this.isNotUnmark &&
                this.isNotList &&
                this.isNotDelete &&
                this.isNotExit &&
                this.isNotFind
        ){
            throw new OnandonUnknownException();
        } else if(split.length <= 1 && this.isNotList && this.isNotExit){
            throw new OnandonNotaskException();
        }
    }
}
