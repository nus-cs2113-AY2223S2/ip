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
import java.lang.String;

public class Duke {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public static void readFileContents(ArrayList<String> filecontents, String filePath, int i, ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String line;
        while (s.hasNext()) {
            line = s.nextLine();
            if (line.startsWith("todo")) {
                todo(dates_and_times, formatter, line, tasks, done, type);
                i++;
            } else if (line.startsWith("deadline")) {
                deadline(dates_and_times, formatter, line, tasks, done, type);
                i++;
            } else if (line.startsWith("event")) {
                event(dates_and_times, formatter, line, tasks, done, type);
                i++;
            } else if (line.startsWith("delete")) {
                delete(dates_and_times, i, line, tasks, done, type);
            } else if (line.startsWith("mark")) {
                mark(i, line, done);
            } else if (line.startsWith("unmark")) {
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
            } else {
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
        } else if (timestr.endsWith("AM") && timestr.startsWith("12")) {
            timestr2 = "00:00";
        } else {
            timestr2 = timestr.substring(0, 5);
        }
        return timestr2;
    }

    public static String DT(String str) {
        String datestr;
        String timestr;
        String datetimestr;
        if (str.length() == 10) {
            datestr = Date(str);
            timestr = "00:00";
            datetimestr = datestr + "T" + timestr;
        } else if (str.length() <= 8) {
            datestr = (LocalDate.now()).toString();
            timestr = Time(str);
            datetimestr = datestr + "T" + timestr;
        } else {
            datestr = str.substring(0, 10);
            datestr = Date(datestr);
            timestr = str.substring(11);
            timestr = Time(timestr);
            datetimestr = datestr + "T" + timestr;
        }
        return datetimestr;
    }

    public static void DateTime(ArrayList<LocalDateTime> dates_and_times, String str, DateTimeFormatter formatter) {
        String datetimestr = DT(str);
        LocalDateTime datetime = LocalDateTime.parse(datetimestr, formatter);
        dates_and_times.add(datetime);
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
        dates_and_times.remove(2 * (number - 1));
        dates_and_times.remove(2 * (number - 1));
    }

    public static void todo(ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        String description = line.substring(4);
        if (description.isBlank()) {
            throw new DukeException();
        }
        tasks.add(description);
        type.add("[T]");
        done.add("[ ]");
        DateTime(dates_and_times, "2015-10-23T03:34", formatter);
        DateTime(dates_and_times, "2015-10-23T03:34", formatter);
    }

    public static void deadline(ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        int slash = line.indexOf("/");
        String description = line.substring(8, slash - 1);
        if (description.isBlank()) {
            throw new DukeException();
        }
        String by = line.substring(slash + 4);
        DateTime(dates_and_times, by, formatter);
        tasks.add(description);
        type.add("[D]");
        done.add("[ ]");
        DateTime(dates_and_times, "2015-10-23T03:34", formatter);
    }
    
    public static void event(ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, String line, ArrayList<String> tasks, ArrayList<String> done, ArrayList<String> type) throws DukeException {
        int slash1 = line.indexOf("/");
        int slash2 = line.indexOf("/", slash1 + 1);
        String description = line.substring(5, slash1 - 1);
        if (description.isBlank()) {
            throw new DukeException();
        }
        String from = line.substring(slash1 + 6, slash2 - 1);
        String to = line.substring(slash2 + 4);
        DateTime(dates_and_times, from, formatter);
        DateTime(dates_and_times, to, formatter);
        tasks.add(description);
        type.add("[E]");
        done.add("[ ]");
    }

    public String getResponse(String input, ArrayList<String> filecontents, String filePath, ArrayList<String> type, ArrayList<String> done, ArrayList<String> tasks, ArrayList<LocalDateTime> dates_and_times, DateTimeFormatter formatter, int i) {
        String output = "";
        if (input.startsWith("bye")) {
            String toFile = "";
            for (String filecontent : filecontents) {
                toFile = toFile + filecontent + "\n";
            }
            try {
                writeToFile(filePath, toFile);
                output = "Bye. Hope to see you again soon!";
            } catch (IOException e) {
                output = "Something went wrong: " + e.getMessage();
            }
        }
        if (input.startsWith("list")) {
            output = "Here are the tasks in your list:\n";
            for (int j = 0; j < i; j++) {
                try {
                    output = output + (j + 1) + "." + type.get(j) + done.get(j) + tasks.get(j);
                    if ((type.get(j)).equals("[T]")) {
                        output = output + "\n";
                    }
                    if ((type.get(j)).equals("[D]")) {
                        output = output + " (by: " +
                                    DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j)) + ")\n";
                    }
                    if ((type.get(j)).equals("[E]")) {
                        output = output + " (from " +
                                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j)) + " to: ";
                        output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j + 1)) + ")\n";
                    }
                } catch (IndexOutOfBoundsException e) {
                    j++;
                }
            }
        } else if (input.startsWith("mark")) {
            try {
                mark(i, input, done);
                int number = Integer.parseInt(input.substring(5));
                output = "Nice! I've marked this task as done:\n";
                output = output + "  " + type.get(number - 1) + "[X]" + tasks.get(number - 1) + " ";
                if ((type.get(number - 1)).equals("[T]")) {
                    output = output + "\n";
                }
                if ((type.get(number - 1)).equals("[D]")) {
                    output = output + "(by: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a").format(dates_and_times.get(2 * (number - 1))) + ")\n";
                }
                if ((type.get(number - 1)).equals("[E]")) {
                    output = output + "(from " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * (number - 1))) + " to: ";
                    output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * (number - 1) + 1)) + ")\n";
                }
                try {
                    writeToFile(filePath, input);
                } catch (IOException e) {
                    output = "Something went wrong " + e.getMessage() + "\n";
                }
            } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                output = "Oops! That isn't a task in your list.\n";
                output = output + "Please input only one integer after 'mark', separated by a single whitespace.\n";
                output = output + "There are " + i + " tasks in your list, so do not input an integer " +
                            "smaller than 1 or larger than " + i + ".\n";
            }
        } else if (input.startsWith("unmark")) {
            try {
                unmark(i, input, done);
                int number = Integer.parseInt(input.substring(7));
                output = "Ok, I've marked this task as not done yet:\n";
                output = output + "  " + type.get(number - 1) + "[ ]" + tasks.get(number - 1) + " ";
                if ((type.get(number - 1)).equals("[T]")) {
                    output = output + "\n";
                }
                if ((type.get(number - 1)).equals("[D]")) {
                    output = output + "(by: " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * (number - 1))) + ")\n";
                }
                if ((type.get(number - 1)).equals("[E]")) {
                    output = output + "(from " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * (number - 1))) + " to: ";
                    output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * (number - 1) + 1)) + ")\n";
                }
                try {
                    writeToFile(filePath, input);
                } catch (IOException e) {
                    output = "Something went wrong: " + e.getMessage();
                }
            } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                output = "Oops! That isn't a task in your list.\n";
                output = output + "Please input only one integer after 'mark', separated by a single whitespace.\n";
                output = output + "There are " + i + " tasks in your list, so do not input an integer " +
                        "smaller than 1 or larger than " + i + ".\n";
            }
        } else if (input.startsWith("todo")) {
            try {
                todo(dates_and_times, formatter, input, tasks, done, type);
                output = "Got it. I've added this task:\n";
                output = output + "  " + type.get(i) + "[ ]" + tasks.get(i) + "\n";
                output = output + "Now you have " + (i + 1) + " tasks in the list.\n";
                try {
                    writeToFile(filePath, input);
                } catch (IOException e) {
                    output = "Something went wrong: " + e.getMessage();
                }
            } catch (DukeException e) {
                output = "Oops! The description of a todo cannot be left empty.\n";
            }
        } else if (input.startsWith("deadline")) {
            try {
                deadline(dates_and_times, formatter, input, tasks, done, type);
                output = "Got it. I've added this task:\n";
                output = output + "  " + type.get(i) + "[ ]" + tasks.get(i) + " (by: ";
                output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * i)) + ")\n";
                output = output + "Now you have " + (i + 1) + " tasks in the list.\n";
                try {
                    writeToFile(filePath, input);
                } catch (IOException e) {
                    output = "Something went wrong: " + e.getMessage();
                }
            } catch (IndexOutOfBoundsException e) {
                output = "Invalid format. When specifying deadline, please follow the format below:\n";
                output = output + "deadline (task) /by (deadline)\n";
            } catch (DukeException e) {
                output = "You might have left the description empty.\n";
            } catch (DateTimeParseException | NumberFormatException e) {
                output = "Oops! You've entered an invalid date/time.\n";
                output = output + "Date format-> YYYY-MM-DD or DD-MM-YYYY or MM-DD-YYYY\n";
                output = output + "Time format-> 18:00 or 06:00 PM\n";
                output = output + "Example: 2015-10-23 03:34\n";
            }
        } else if (input.startsWith("event")) {
            try {
                event(dates_and_times, formatter, input, tasks, done, type);
                output = "Got it. I've added this task:\n";
                output = output + "  " + type.get(i) + "[ ]" + tasks.get(i) + " (from: ";
                output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * i)) + " to: ";
                output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * i + 1)) + ")\n";
                output = output + "Now you have " + (i + 1) + " tasks in the list.\n";
                try {
                    writeToFile(filePath, input);
                } catch (IOException e) {
                    output = "Something went wrong: " + e.getMessage();
                }
            } catch (IndexOutOfBoundsException e) {
                output = "Invalid format. When specifying timeframe, please follow the format below:\n";
                output = output + "event (task) /from (starting time) /to (ending time)\n";
            } catch (DukeException e) {
                output = "You might have left the description empty.\n";
            } catch (DateTimeParseException | NumberFormatException e) {
                output = "Oops! You've entered an invalid date/time.\n";
                output = output + "Date format-> YYYY-MM-DD or DD-MM-YYYY or MM-DD-YYYY\n";
                output = output + "Time format-> 18:00 or 06:00 PM\n";
                output = output + "Example: 2015-10-23 03:34\n";
                output = output + "If time is left out, I'll assume it to be 00:00.\n";
                output = output + "If date is left out, I'll assume it to be today.\n";
            }
        } else if (input.startsWith("delete")) {
            try {
                delete(dates_and_times, i, input, tasks, done, type);
                int number = Integer.parseInt(input.substring(7));
                output = "Noted. I've removed this task:\n";
                output = output + "  " + type.get(number - 1) + done.get(number - 1) + tasks.get(number - 1) + "\n";
                output = output + "Now you have " + (i - 1) + " tasks in the list.\n";
                try {
                    writeToFile(filePath, input);
                } catch (IOException e) {
                    output = "Something went wrong: " + e.getMessage();
                }
                i--;
            } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                output = "Oops! That isn't a task in your list.\n";
                output = output + "Please input only one integer after 'delete', separated by a single whitespace.\n";
                output = output + "There are " + i + " tasks in your list, so do not input an integer " +
                        "smaller than 1 or larger than " + i + ".\n";
            }
        } else if (input.startsWith("find")) {
            if (input.equals("find") || (input.substring(5)).isBlank()) {
                output = "Oops! The description cannot be left empty.\n";
            } else {
                String find = input.substring(5);
                output = "Here are the matching tasks in your list:\n";
                int listnumber = 1;
                for (int j = 0; j < tasks.size(); j++) {
                    String task = tasks.get(j);
                    if (task.contains(find)) {
                        try {
                            System.out.print(listnumber + "." + type.get(j) + done.get(j) + tasks.get(j));
                            if ((type.get(j)).equals("[T]")) {
                                output = output + "\n";
                            }
                            if ((type.get(j)).equals("[D]")) {
                                output = output + " (by: " +
                                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j)) + ")\n";
                            }
                            if ((type.get(j)).equals("[E]")) {
                                output = output + " (from " +
                                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j)) + " to: ";
                                output = output +DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j + 1)) + ")\n";
                            }
                            listnumber++;
                        } catch (IndexOutOfBoundsException e) {
                            j++;
                        }
                    }
                }
            }
        } else if (input.startsWith("dated")) {
            if (input.equals("dated") || (input.substring(5)).isBlank()) {
                output = "Oops! The description cannot be left empty.\n";
            } else {
                String description = input.substring(6);
                try {
                    String datetimestr = DT(description);
                    LocalDateTime datetime = LocalDateTime.parse(datetimestr, formatter);
                    output = "Tasks during this time period:\n";
                    int b = 1;
                    for (int j = 0; j < tasks.size(); j++) {
                        LocalDateTime dt = dates_and_times.get(j * 2);
                        if (dt.equals(datetime)) {
                            try {
                                output = output + b + "." + type.get(j) + done.get(j) + tasks.get(j);
                                if ((type.get(j)).equals("[T]")) {
                                    output = output + "\n";
                                }
                                if ((type.get(j)).equals("[D]")) {
                                    output = output + " (by: " +
                                            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j)) + ")\n";
                                }
                                if ((type.get(j)).equals("[E]")) {
                                    output = output + " (from " +
                                            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j)) + " to: ";
                                    output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(dates_and_times.get(2 * j + 1)) + ")\n";
                                }
                                b++;
                            } catch (IndexOutOfBoundsException e) {
                                j++;
                            }
                        }
                    }
                } catch (DateTimeParseException | NumberFormatException e) {
                    output = "Oops! You've entered an invalid date/time.\n";
                    output = output + "Date format-> YYYY-MM-DD or DD-MM-YYYY or MM-DD-YYYY\n";
                    output = output + "Time format-> 18:00 or 06:00 PM\n";
                    output = output + "Example: 2015-10-23 03:34\n";
                    output = output + "If time is left out, I'll assume it to be 00:00.\n";
                    output = output + "If date is left out, I'll assume it to be today.\n";
                }
            }
        } else {
            if (!input.startsWith("bye")) {
                output = "Oops! I'm sorry, I don't understand that command. Try one of these instead:\n";
                output = output + "list   mark   unmark   todo   deadline   event   find   dated\n";
            }
        }
        return output;
    }
}