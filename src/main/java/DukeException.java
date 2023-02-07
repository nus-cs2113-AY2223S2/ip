public class DukeException extends Exception {

    public DukeException() {}

    public DukeException(String message) {
        super(message);
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________\n");
    }
}
