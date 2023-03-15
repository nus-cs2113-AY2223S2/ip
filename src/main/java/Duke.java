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

    /**
     * generates a String response to the user input
     * @param input user input
     * @param filecontents all previous command lines stored in an arraylist to be written to text file
     * @param filePath text file to be written to (Duke.txt)
     * @param tasks arraylist of tasks
     * @param formatter for formatting LocalDateTime values to "yyyy-MM-dd'T'HH:mm"
     * @param i current index in tasks for Mark, where necessary
     * @return output response from Duke
     */
    public String getResponse(String input, ArrayList<String> filecontents, String filePath, ArrayList<Task> tasks, DateTimeFormatter formatter, int i) {
        String output = "";
        if (input.startsWith("bye")) {
            String toFile = "";
            for (String filecontent : filecontents) {
                toFile = toFile + filecontent + "\n";
            }
            try {
                Storage.writeToFile(filePath, toFile);
                output = "Bye. Hope to see you again soon!";
            } catch (IOException e) {
                output = "Something went wrong: " + e.getMessage();
            }
        }
        if (input.startsWith("list")) {
            output = "Here are the tasks in your list:\n";
            for (int j = 0; j < i; j++) {
                Task task = tasks.get(j);
                try {
                    output = output + (j + 1) + "." + task.getType() + task.getDone() + task.getDescription();
                    if ((task.getType()).equals("[T]")) {
                        output = output + "\n";
                    }
                    if ((task.getType()).equals("[D]")) {
                        output = output + " (by: " +
                                    DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + ")\n";
                    }
                    if ((task.getType()).equals("[E]")) {
                        output = output + " (from " +
                                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + " to: ";
                        output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime2()) + ")\n";
                    }
                } catch (IndexOutOfBoundsException e) {
                    j++;
                }
            }
        } else if (input.startsWith("mark")) {
            try {
                Mark.mark(i, input, tasks);
                int number = Integer.parseInt(input.substring(5));
                Task task = tasks.get(number - 1);
                output = "Nice! I've marked this task as done:\n";
                output = output + "  " + task.getType() + "[X]" + task.getDescription() + " ";
                if ((task.getType()).equals("[T]")) {
                    output = output + "\n";
                }
                if ((task.getType()).equals("[D]")) {
                    output = output + "(by: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a").format(task.getDateTime1()) + ")\n";
                }
                if ((task.getType()).equals("[E]")) {
                    output = output + "(from " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + " to: ";
                    output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime2()) + ")\n";
                }
                try {
                    Storage.writeToFile(filePath, input);
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
                Mark.unmark(i, input, tasks);
                int number = Integer.parseInt(input.substring(7));
                Task task = tasks.get(number - 1);
                output = "Ok, I've marked this task as not done yet:\n";
                output = output + "  " + task.getType() + "[ ]" + task.getDescription() + " ";
                if ((task.getType()).equals("[T]")) {
                    output = output + "\n";
                }
                if ((task.getType()).equals("[D]")) {
                    output = output + "(by: " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + ")\n";
                }
                if ((task.getType()).equals("[E]")) {
                    output = output + "(from " + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + " to: ";
                    output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime2()) + ")\n";
                }
                try {
                    Storage.writeToFile(filePath, input);
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
                List.todo(tasks, formatter, input);
                Task task = tasks.get(i);
                output = "Got it. I've added this task:\n";
                output = output + "  " + task.getType() + "[ ]" + task.getDescription() + "\n";
                output = output + "Now you have " + (i + 1) + " tasks in the list.\n";
                try {
                    Storage.writeToFile(filePath, input);
                } catch (IOException e) {
                    output = "Something went wrong: " + e.getMessage();
                }
            } catch (DukeException e) {
                output = "Oops! The description of a todo cannot be left empty.\n";
            }
        } else if (input.startsWith("deadline")) {
            try {
                List.deadline(tasks, formatter, input);
                Task task = tasks.get(i);
                output = "Got it. I've added this task:\n";
                output = output + "  " + task.getType() + "[ ]" + task.getDescription() + " (by: ";
                output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + ")\n";
                output = output + "Now you have " + (i + 1) + " tasks in the list.\n";
                try {
                    Storage.writeToFile(filePath, input);
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
                List.event(tasks, formatter, input);
                Task task = tasks.get(i);
                output = "Got it. I've added this task:\n";
                output = output + "  " + task.getType() + "[ ]" + task.getDescription() + " (from: ";
                output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + " to: ";
                output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime2()) + ")\n";
                output = output + "Now you have " + (i + 1) + " tasks in the list.\n";
                try {
                    Storage.writeToFile(filePath, input);
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
                List.delete(tasks, i, input);
                int number = Integer.parseInt(input.substring(7));
                Task task = tasks.get(number - 1);
                output = "Noted. I've removed this task:\n";
                output = output + "  " + task.getType() + task.getDone() + task.getDescription() + "\n";
                output = output + "Now you have " + (i - 1) + " tasks in the list.\n";
                try {
                    Storage.writeToFile(filePath, input);
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
                    Task task = tasks.get(j);
                    String description = task.getDescription();
                    if (description.contains(find)) {
                        try {
                            output = output + listnumber + "." + task.getType() + task.getDone() + task.getDescription();
                            if ((task.getType()).equals("[T]")) {
                                output = output + "\n";
                            }
                            if ((task.getType()).equals("[D]")) {
                                output = output + " (by: " +
                                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + ")\n";
                            }
                            if ((task.getType()).equals("[E]")) {
                                output = output + " (from " +
                                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + " to: ";
                                output = output +DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime2()) + ")\n";
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
                    LocalDateTime datetime = DateTime.toLocalDateTime(description, formatter);
                    output = "Tasks during this time period:\n";
                    int it = 1;
                    for (Task task : tasks) {
                        LocalDateTime dt1 = task.getDateTime1();
                        LocalDateTime dt2 = task.getDateTime2();
                        if (dt1.equals(datetime) || dt2.equals(datetime)) {
                            output = output + it + "." + task.getType() + task.getDone() + task.getDescription();
                            if ((task.getType()).equals("[T]")) {
                                output = output + "\n";
                            }
                            if ((task.getType()).equals("[D]")) {
                                output = output + " (by: " +
                                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + ")\n";
                            }
                            if ((task.getType()).equals("[E]")) {
                                output = output + " (from " +
                                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime1()) + " to: ";
                                output = output + DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a").format(task.getDateTime2()) + ")\n";
                            }
                            it++;
                        }
                    }
                } catch (StringIndexOutOfBoundsException | DateTimeParseException | NumberFormatException e) {
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

