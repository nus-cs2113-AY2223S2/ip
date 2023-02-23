import java.util.ArrayList;

public class Parser {
    public Parser() {};

    public Task parseTask (String input) {
        String[] words = input.split(" ");
        if (isValidTodo(words)) {
            return new ToDo(input.substring(5));
        } else if (isValidDeadline(words)) {
            int idxOfSlash = input.indexOf('/');
            return new Deadline(input.substring(9, idxOfSlash), input.substring(idxOfSlash + 4));
        } else if (isValidEvent(words)) {
            int idxOfSlash = input.indexOf('/');
            return new Event(input.substring(6, idxOfSlash),
                    input.substring(idxOfSlash + 6, input.indexOf('/', idxOfSlash + 1)),
                    input.substring(input.indexOf('/', idxOfSlash + 1) + 4));
        } else {
            return null;
        }
    }

    private static boolean isValidEvent(String[] words) {
        return words[0].equals("event") && words.length != 1;
    }

    private static boolean isValidDeadline(String[] words) {
        return words[0].equals("deadline") && words.length != 1;
    }

    private static boolean isValidTodo(String[] words) {
        return words[0].equals("todo") && words.length != 1;
    }
}
