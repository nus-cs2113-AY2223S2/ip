import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class level8 {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void readFileContents(ArrayList<String> filecontents, String filePath, int i, ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String line;
        while (s.hasNext()) {
            line = s.nextLine();
            filecontents.add(line);
            if (line.startsWith("todo")) {
                todo(dates_and_times, formatter, line, tasks, done, type);
                i++;
            }
            else if (line.startsWith("deadline")) {
                try {
                    deadline(dates_and_times, formatter, line, tasks, done, type);
                    i++;
                } catch (Ignore e) {
                    System.out.print("");
                }
            }
            else if (line.startsWith("event")) {
                try {
                    event(dates_and_times, formatter, line, tasks, done, type);
                    i++;
                }
                catch (Ignore e) {
                    System.out.print("");
                }
            }
            else if (line.startsWith("delete")) {
                delete(dates_and_times, i, line, tasks, done, type);
            }
            else if (line.startsWith("mark")) {
                mark(i, line, done);
            }
            else if (line.startsWith("unmark")) {
                unmark(i, line, done);
            }
        }
    }

    public static String Date(String datestr) {
        String datestr2 = datestr;
        if (datestr.indexOf('-') != 4) {
            int month = Integer.parseInt(datestr.substring(3, 5));
            if (month > 12) {
                datestr2 = datestr.substring(6) + "-" + datestr.substring(0, 3) + datestr.substring(3, 5);
            }
            else {
                datestr2 = datestr.substring(6) + "-" + datestr.substring(3, 6) + datestr.substring(0, 2);
            }
        }
        return datestr2;
    }

    public static String Time(String timestr) {
        String timestr2 = timestr;
        if (timestr.endsWith("PM") && timestr.charAt(0) == '0') {
            int hour = Character.getNumericValue(timestr.charAt(1));
            hour = hour + 12;
            timestr2 = hour + timestr.substring(2, 5);
        }
        else if (timestr.endsWith("AM") && timestr.startsWith("12")) {
            timestr2 = "00:00";
        }
        else {
            timestr2 = timestr.substring(0, 5);
        }
        return timestr2;
    }

    public static void DateTime(ArrayList<LocalDateTime> dates_and_times, String str, DateTimeFormatter formatter) {
        String datestr;
        String timestr;
        String datetimestr;
        if (str.length() == 10) {
            datestr = Date(str);
            timestr = "00:00";
            datetimestr = datestr + "T" + timestr;
            LocalDateTime datetime = LocalDateTime.parse(datetimestr, formatter);
            dates_and_times.add(datetime);
        }
        else if (str.length() <= 8) {
            datestr = (LocalDate.now()).toString();
            timestr = Time(str);
            datetimestr = datestr + "T" + timestr;
            LocalDateTime datetime = LocalDateTime.parse(datetimestr, formatter);
            dates_and_times.add(datetime);
        }
        else {
            datestr = str.substring(0, 10);
            datestr = Date(datestr);
            timestr = str.substring(11);
            timestr = Time(timestr);
            datetimestr = datestr + "T" + timestr;
            LocalDateTime datetime = LocalDateTime.parse(datetimestr, formatter);
            dates_and_times.add(datetime);
        }
    }

    public static void mark(int i, String line, ArrayList<String> done) throws DukeException {
        int number = Integer.parseInt(line.substring(5));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        done.remove(number - 1);
        done.add(number - 1, "[X]");
    }

    public static void unmark(int i, String line, ArrayList<String> done) throws DukeException {
        int number = Integer.parseInt(line.substring(7));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        done.remove(number - 1);
        done.add(number - 1, "[ ]");
    }

    public static void delete(ArrayList<LocalDateTime> dates_and_times, int i, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        int number = Integer.parseInt(line.substring(7));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        tasks.remove(number - 1);
        done.remove(number - 1);
        type.remove(number - 1);
        dates_and_times.remove(2*(number-1));
        dates_and_times.remove(2*(number-1));
    }
    public static void todo(ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        if (line.equals("todo")) {
            throw new DukeException();
        }
        tasks.add(line.substring(4));
        type.add("[T]");
        done.add("[ ]");
        DateTime(dates_and_times, "2015-10-23T03:34", formatter);
        DateTime(dates_and_times, "2015-10-23T03:34", formatter);
    }

    public static void deadline(ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException, Ignore {
        int slash = line.indexOf("/");
        String description = line.substring(8, slash-1);
        if (description.equals("")) {
            throw new DukeException();
        }
        String by = line.substring(slash+4);
        try {
            DateTime(dates_and_times, by, formatter);
            tasks.add(description);
            type.add("[D]");
            done.add("[ ]");
            DateTime(dates_and_times, "2015-10-23T03:34", formatter);
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Oops! You've entered an invalid date/time.");
            System.out.println("Date format-> YYYY-MM-DD or DD-MM-YYYY or MM-DD-YYYY");
            System.out.println("Time format-> 18:00 or 06:00 PM");
            System.out.println("Example: 2015-10-23 03:34");
            throw new Ignore();
        }
    }

    public static void event(ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException, Ignore {
        int slash1 = line.indexOf("/");
        int slash2 = line.indexOf("/", slash1+1);
        String description = line.substring(5, slash1-1);
        if (description.equals("")) {
            throw new DukeException();
        }
        String from = line.substring(slash1+6, slash2-1);
        String to = line.substring(slash2+4);
        try {
            DateTime(dates_and_times, from, formatter);
            DateTime(dates_and_times, to, formatter);
            tasks.add(description);
            type.add("[E]");
            done.add("[ ]");
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Oops! You've entered an invalid date/time.");
            System.out.println("Date format-> YYYY-MM-DD or DD-MM-YYYY or MM-DD-YYYY");
            System.out.println("Time format-> 18:00 or 06:00 PM");
            System.out.println("Example: 2015-10-23 03:34");
            System.out.println("If time is left out, I'll assume it to be 00:00.");
            System.out.println("If date is left out, I'll assume it to be today.");
            throw new  Ignore();
        }
    }
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String filePath = "Duke.txt";
        String line;
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<String> done = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();
        ArrayList<LocalDateTime> dates_and_times = new ArrayList<>();
        ArrayList<String> filecontents = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        int i = 0;
        try {
            readFileContents(filecontents, filePath, i, dates_and_times, formatter, tasks, done, type);
            i = tasks.size();
        } catch (FileNotFoundException | DukeException e) {
            i = 0;
        }
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.startsWith("bye")) {
                String toFile = "";
                for (String filecontent : filecontents) {
                    toFile = toFile + filecontent + System.lineSeparator();
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
                        System.out.print((j + 1) + "." + type.get(j) + done.get(j) + tasks.get(j));
                        if ((type.get(j)).equals("[T]")) {
                            System.out.println("");
                        }
                        if ((type.get(j)).equals("[D]")) {
                            System.out.println(" (by: " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*j)) + ")");
                        }
                        if ((type.get(j)).equals("[E]")) {
                            System.out.print(" (from " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*j)) + " to: ");
                            System.out.println(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*j+1)) + ")");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        j++;
                    }
                }
            }
            else if (line.startsWith("mark")) {
                try {
                    mark(i, line, done);
                    int number = Integer.parseInt(line.substring(5));
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.print("  " + type.get(number - 1) + "[X]" + tasks.get(number - 1) + " ");
                    if ((type.get(number-1)).equals("[T]")) {
                        System.out.println("");
                    }
                    if ((type.get(number-1)).equals("[D]")) {
                        System.out.println("(by: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a").format(dates_and_times.get(2*(number-1))) + ")");
                    }
                    if ((type.get(number-1)).equals("[E]")) {
                        System.out.print("(from " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*(number-1))) + " to: ");
                        System.out.println(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*(number-1)+1)) + ")");
                    }
                    filecontents.add(line);
                } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                    System.out.println("Oops! That isn't a task in your list.");
                    System.out.println("Please input only one integer after 'mark', separated by a single whitespace.");
                    System.out.println("There are " + i + " tasks in your list, so do not input an integer " +
                            "smaller than 1 or larger than " + i + ".");
                }
            }
            else if (line.startsWith("unmark")) {
                try {
                    unmark(i, line, done);
                    int number = Integer.parseInt(line.substring(7));
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.print("  " + type.get(number - 1) + "[ ]" + tasks.get(number - 1) + " ");
                    if ((type.get(number-1)).equals("[T]")) {
                        System.out.println("");
                    }
                    if ((type.get(number-1)).equals("[D]")) {
                        System.out.println("(by: " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*(number-1))) + ")");
                    }
                    if ((type.get(number-1)).equals("[E]")) {
                        System.out.print("(from " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*(number-1))) + " to: ");
                        System.out.println(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*(number-1)+1)) + ")");
                    }
                    filecontents.add(line);
                } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                    System.out.println("Oops! That isn't a task in your list.");
                    System.out.println("Please input only one integer after 'mark', separated by a single whitespace.");
                    System.out.println("There are " + i + " tasks in your list, so do not input an integer " +
                            "smaller than 1 or larger than " + i + ".");
                }
            }
            else if (line.startsWith("todo")) {
                try {
                    todo(dates_and_times, formatter, line, tasks, done, type);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + type.get(i) + "[ ]" + tasks.get(i));
                    System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                    filecontents.add(line);
                    i++;
                } catch (DukeException e) {
                    System.out.println("Oops! The description of a todo cannot be left empty.");
                }
            }
            else if (line.startsWith("deadline")) {
                try {
                    deadline(dates_and_times, formatter, line, tasks, done, type);
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  " + type.get(i) + "[ ]" + tasks.get(i) + " (by: ");
                    System.out.println(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*i)) + ")");
                    System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                    filecontents.add(line);
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid format. When specifying deadline, please follow the format below:");
                    System.out.println("deadline (task) /by (deadline)");
                } catch (DukeException e) {
                    System.out.println("You might have left the description empty.");
                } catch (Ignore e) {
                    System.out.print("");
                }
            }
            else if (line.startsWith("event")) {
                try {
                    event(dates_and_times, formatter, line, tasks, done, type);
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  " + type.get(i) + "[ ]" + tasks.get(i) + " (from: ");
                    System.out.print(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*i)) + " to: ");
                    System.out.println(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2*i+1)) + ")");
                    System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                    filecontents.add(line);
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid format. When specifying timeframe, please follow the format below:");
                    System.out.println("event (task) /from (starting time) /to (ending time)");
                } catch (DukeException e) {
                    System.out.println("You might have left the description empty.");
                } catch (Ignore e) {
                    System.out.print("");
                }
            }
            else if (line.startsWith("delete")) {
                try {
                    delete(dates_and_times, i, line, tasks, done, type);
                    int number = Integer.parseInt(line.substring(7));
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + type.get(number - 1) + done.get(number - 1) + tasks.get(number - 1));
                    System.out.println("Now you have " + (i - 1) + " tasks in the list.");
                    filecontents.add(line);
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

