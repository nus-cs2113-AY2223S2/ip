import java.util.ArrayList;

public class Parser {
    public Parser() {
    }

    ;

    public Task parseTask(String input) {
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


    public static Task readDatabaseTasks(ArrayList<Task> listOfTasks, String item) {
        int idx = item.indexOf("---");
        String input = item.substring(0, idx);
        if (item.contains("--- T |")) {
            ToDo newTodo = new ToDo(input);
            if (item.contains("[X]")) {
                newTodo.isDone = true;
            }
            return newTodo;
        } else if (item.contains("--- E |")) {
            int idxOfSlash = input.indexOf('/');
            Event newEvent = new Event(input.substring(0, idxOfSlash),
                    input.substring(idxOfSlash + 6, input.indexOf('/', idxOfSlash + 1)),
                    input.substring(input.indexOf('/', idxOfSlash + 1) + 4, idx - 1));
            if (item.contains("[X]")) {
                newEvent.isDone = true;
            }
            return newEvent;
        } else if (item.contains("--- D |")) {
            int idxOfSlash = input.indexOf('/');
            Deadline newDeadline = new Deadline(input.substring(0, idxOfSlash), input.substring(idxOfSlash + 4, idx - 1));
            if (item.contains("[X]")) {
                newDeadline.isDone = true;
            }
            return newDeadline;
        }
        return null;
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
