package Onandon.exception;

// This class is for maintaining and checking the exceptions which occur during executing the program.
public class ExceptionChecker {
    protected Boolean isNotTodo;
    protected Boolean isNotDeadline;
    protected Boolean isNotEvent;
    protected Boolean isNotMark;
    protected Boolean isNotUnmark;
    protected Boolean isNotList;
    protected Boolean isNotDelete;
    protected Boolean isNotExit;

    // checks whether there would be exception, and throws the exception.
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

        if(inputText.length() == 0){
            throw new OnandonEmptyException();
        } else if(this.isNotTodo && this.isNotDeadline && this.isNotEvent && this.isNotMark && this.isNotUnmark && this.isNotList && this.isNotDelete && this.isNotExit){
            throw new OnandonUnknownException();
        } else if(split.length <= 1 && this.isNotList && this.isNotExit){
            throw new OnandonNotaskException();
        }
    }
}
