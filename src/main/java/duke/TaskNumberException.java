package duke;

public class TaskNumberException extends DukeException{
    TaskNumberException(){
        super("OOPS!!! Please input a single valid task number.");
    }
}
