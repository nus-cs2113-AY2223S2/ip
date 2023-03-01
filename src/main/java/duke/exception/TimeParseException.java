package duke.exception;

public class TimeParseException extends DukeException {
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    public TimeParseException(){
        super();
    }

    public static void errorMessage () {
        System.out.println(DIVIDER);
        System.out.println(SPACER + "☹ OOPS!!! Please enter the time format according to 'dd-M-yyyy hh:mm a'");
        System.out.println(DIVIDER);
    }


}

