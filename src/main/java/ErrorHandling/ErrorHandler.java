package ErrorHandling;

public class ErrorHandler {
    public static Boolean isInputValid(String input) {

        if (input.startsWith("event")) {
            if (input.length() < 7) {
                System.out.println("event cannot be empty, please try again!");
                return false;
            } else if (!input.contains("/from") || !input.contains("/to")) {
                System.out.println("Please provide a BOTH a valid from and to \nex: event project meeting /from Mon 2pm /to 4pm");
                return false;
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                System.out.println("Deadline cannot be empty, please try again!");
                return false;
            } else if (!input.contains("/by")) {
                System.out.println("Please provide a valid deadline \nex: deadline return book /by Sunday");
                return false;
            }
        } else if (input.startsWith("todo")) {
            if (input.length() < 6) {
                System.out.println("Todo cannot be empty, please try again!");
                return false;
            }
        }
        return true;
    }
}
