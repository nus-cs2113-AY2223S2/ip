package duke.exception;

public class DukeException extends Exception{
    private static final String MARGIN = "*----------------------------*";

    public void todoError(){
        System.out.println(MARGIN);
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        System.out.println(MARGIN);
    }

    public void eventError(){
        System.out.println(MARGIN);
        System.out.println("OOPS!!! The description of a event cannot be empty.");
        System.out.println(MARGIN);
    }

    public void deadlineError(){
        System.out.println(MARGIN);
        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        System.out.println(MARGIN);
    }

    public void emptyListError(){
        System.out.println(MARGIN);
        System.out.println("This task does not exist!");
        System.out.println(MARGIN);
    }

}
