
public class EmptyDescriptionException extends Exception{
    private static final String DIVIDER  = "______________________________";
    public void emptyDescriptionTodo() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a todo cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    public void emptyDescriptionEvent() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a event cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    public void emptyDescriptionDeadline() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a deadline cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    public void emptyDescriptionNumber() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The task number cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }
}
