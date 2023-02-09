package exceptions;

public class DukeException extends Exception{
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    DukeException(){
        super();
    }

    public static void errorMessage () {
            System.out.println(DIVIDER);
            System.out.println(SPACER + "☹ OOPS!!! An error has occured!.");
            System.out.println(DIVIDER);
    }
}
