package duke;

public class IllegalEventLengthException extends Exception {
    public IllegalEventLengthException() {
        System.out.println("Oh no! That is not a valid event format!");
        System.out.println("Format:");
        System.out.println("event [description] /from [from_date] /to [to_date]");
    }
}
