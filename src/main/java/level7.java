import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class level7 {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void readFileContents(String filePath, int i, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String line = s.nextLine();
        String line2;
        int j = i;
        while (s.hasNext()) {
            line = s.nextLine();
            if (line.charAt(3) == 'T') {
                line2 = "todo " + line.substring(9);
                todo(j, line2, tasks, done, type);
            }
            else if (line.charAt(3) == 'D') {
                int bracket = line.indexOf('(');
                line2 = "deadline " + line.substring(9, bracket) + "/by " + line.substring(bracket+5, line.length()-1);
                deadline(j, line2, tasks, done, type);
            }
            else {
                int bracket = line.indexOf('(');
                int colon = line.indexOf(':');
                line2 = "event " + line.substring(9, bracket) + "/from "
                        + line.substring(bracket+6, colon-2) + "/to " + line.substring(colon+2, line.length()-1);
                event(j, line2, tasks, done, type);
            }
            if (line.charAt(6) == 'X') {
                done.remove(j);
                done.add(j, "[X]");
            }
            j++;
        }
    }

    public static void mark(int i, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        int number = Integer.parseInt(line.substring(5));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        done.remove(number - 1);
        done.add(number - 1, "[X]");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + type.get(number - 1) + "[X]" + tasks.get(number - 1));
    }

    public static void unmark(int i, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        int number = Integer.parseInt(line.substring(7));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        done.remove(number - 1);
        done.add(number - 1, "[ ]");
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  " + type.get(number - 1) + "[ ]" + tasks.get(number - 1));
    }

    public static void delete(int i, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        int number = Integer.parseInt(line.substring(7));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + type.get(number - 1) + done.get(number - 1) + tasks.get(number - 1));
        System.out.println("Now you have " + (i - 1) + " tasks in the list.");
        tasks.remove(number - 1);
        done.remove(number - 1);
        type.remove(number - 1);
    }
    public static void todo(int i, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        if (line.equals("todo")) {
            throw new DukeException();
        }
        tasks.add(line.substring(4));
        type.add("[T]");
        done.add("[ ]");
    }

    public static void deadline(int i, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) {
        int slash = line.indexOf("/");
        String description = line.substring(8, slash-1);
        String by = line.substring(slash+4);
        tasks.add(description + " (by: " + by + ")");
        type.add("[D]");
        done.add("[ ]");
    }

    public static void event(int i, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) {
        int slash1 = line.indexOf("/");
        int slash2 = line.indexOf("/", slash1+1);
        String description = line.substring(5, slash1-1);
        String from = line.substring(slash1+6, slash2-1);
        String to = line.substring(slash2+4);
        tasks.add(description + " (from " + from + " to: " + to + ")");
        type.add("[E]");
        done.add("[ ]");
    }
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String filePath = "Duke.txt";
        String line;
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<String> done = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();
        int i = 0;
        try {
            readFileContents(filePath, i, tasks, done, type);
            i = tasks.size();
        } catch (FileNotFoundException | DukeException e) {
            i = 0;
        }
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.startsWith("bye")) {
                String toFile = "Here are the tasks in your list:";
                for (int j = 0; j < i; j++) {
                    try {
                        toFile = toFile + System.lineSeparator() + (j + 1) + "." + type.get(j) + done.get(j) + tasks.get(j);
                    } catch (IndexOutOfBoundsException e) {
                        j++;
                    }
                }
                try {
                    writeToFile(filePath, toFile);
                    break;
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
            if (line.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    try {
                        System.out.println((j + 1) + "." + type.get(j) + done.get(j) + tasks.get(j));
                    } catch (IndexOutOfBoundsException e) {
                        j++;
                    }
                }
            }
            else if (line.startsWith("mark")) {
                try {
                    mark(i, line, tasks, done, type);
                } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                    System.out.println("Oops! That isn't a task in your list.");
                    System.out.println("Please input only one integer after 'mark', separated by a single whitespace.");
                    System.out.println("There are " + i + " tasks in your list, so do not input an integer " +
                            "smaller than 1 or larger than " + i + ".");
                }
            }
            else if (line.startsWith("unmark")) {
                try {
                    unmark(i, line, tasks, done, type);
                } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                    System.out.println("Oops! That isn't a task in your list.");
                    System.out.println("Please input only one integer after 'mark', separated by a single whitespace.");
                    System.out.println("There are " + i + " tasks in your list, so do not input an integer " +
                            "smaller than 1 or larger than " + i + ".");
                }
            }
            else if (line.startsWith("todo")) {
                try {
                    todo(i, line, tasks, done, type);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + type.get(i) + "[ ]" + tasks.get(i));
                    System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                    i++;
                } catch (DukeException e) {
                    System.out.println("Oops! The description of a todo cannot be left empty.");
                }
            }
            else if (line.startsWith("deadline")) {
                try {
                    deadline(i, line, tasks, done, type);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + type.get(i) + "[ ]" + tasks.get(i));
                    System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops! Did you forget the slash?");
                    System.out.println("When specifying deadline, please follow the format below:");
                    System.out.println("deadline (task) /by (deadline)");
                }
            }
            else if (line.startsWith("event")) {
                try {
                    event(i, line, tasks, done, type);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + type.get(i) + "[ ]" + tasks.get(i));
                    System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops! Did you forget the slash?");
                    System.out.println("When specifying timeframe, please follow the format below:");
                    System.out.println("event (task) /from (starting time) /to (ending time)");
                }
            }
            else if (line.startsWith("delete")) {
                try {
                    delete(i, line, tasks, done, type);
                    i--;
                } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                    System.out.println("Oops! That isn't a task in your list.");
                    System.out.println("Please input only one integer after 'delete', separated by a single whitespace.");
                    System.out.println("There are " + i + " tasks in your list, so do not input an integer " +
                            "smaller than 1 or larger than " + i + ".");
                }
            }
            else {
                if (!line.startsWith("bye")) {
                    System.out.println("Oops! I'm sorry, I don't understand that command. Try one of these instead:");
                    System.out.println("list   mark   unmark   todo   deadline   event");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

