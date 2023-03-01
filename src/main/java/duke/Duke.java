package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

// IO and task-storage
import java.util.Scanner;
import java.util.ArrayList;

// Writing and Saving to File
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

// Date/Time Formatter
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Duke {
    /* --- GLOBAL VARIABLES --- */
    // Print on start
    private static final String LOGO = " ____        _        \n|  _ / _   _| | _____ \n| | | | | | | |/ / _ /\n| |_| | |_| |   <  __/\n|____/ /__,_|_|/_/___|\n";

    // Horizontal Rule to act as a divider
    private static final String HORIZONTAL_RULE = "________________________________________________________________________________";

    // Sets the date & time
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.US);



    /* --- FUNCTIONS --- */

    /**
     * Prints horizontal rule
     */
    public static void printHorizontalRule() {
        System.out.println(HORIZONTAL_RULE);
    }

    /**
     * Either creates a save file if one does not exist,
     * or processes saved contents of a text file
     * Entries are parsed in order
     */
    public static void startFileProcessing(ArrayList<Task> tasks) throws IndexOutOfBoundsException {
        try {
            File saveFile = new File("duke.txt");

            if (saveFile.createNewFile()) {
                System.out.println("You do not have a save file! Creating one now...");
            } else {
                System.out.println("You already have a file! Processing contents...");
            }
            final Scanner READ_FILE = new Scanner(saveFile);
            int counter = 0;
            while (READ_FILE.hasNext()) {
                String[] savedTask = READ_FILE.nextLine().split(" / ");

                switch (savedTask[0]) {
                    case "T": {
                        String temp = savedTask[2];
                        Todo newTodo = new Todo(temp);
                        tasks.add(newTodo);

                        if (savedTask[1].equals("X")) {
                            tasks.get(counter).setComplete();
                        }
                        break;
                    }

                    case "D": {
                        String[] deadlineBreakdown = savedTask[2].split(" \\(By: ");
                        String by = deadlineBreakdown[1].substring(0, deadlineBreakdown[1].length() - 1);
                        Deadline deadline = new Deadline(deadlineBreakdown[0], LocalDateTime.parse(by, FORMATTER).format(FORMATTER));
                        tasks.add(deadline);

                        if (savedTask[1].equals("X")) {
                            tasks.get(counter).setComplete();
                        }
                        break;
                    }

                    case "E": {
                        String[] eventBreakdown = savedTask[2].split(" \\(From: ");
                        String eventDescription = eventBreakdown[0];
                        String[] timeBreakdown = eventBreakdown[1].split(" To: ");
                        String from = timeBreakdown[0];
                        String to = timeBreakdown[1].substring(0, timeBreakdown[1].length() - 1);

                        Event event = new Event(eventDescription, LocalDateTime.parse(from, FORMATTER).format(FORMATTER), LocalDateTime.parse(to, FORMATTER).format(FORMATTER));
                        tasks.add(event);

                        if (savedTask[1].equals("X")) {
                            tasks.get(counter).setComplete();
                        }
                        break;
                    }
                }
                counter++;
            }

        } catch (IOException e) {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }

    /**
     * Saves tasks upon being given the command 'bye'
     * For each task in the arraylist, it saves the task in the format of
     * type / completion status / task description
     */
    public static void endFileProcessing(ArrayList<Task> tasks) {
        try {
            System.out.println("Saving current tasks...");
            FileWriter saveFile = new FileWriter("duke.txt");

            for (int i = 0; i < tasks.size(); ++i) {
                String task = String.valueOf(tasks.get(i));
                String[] splitTaskDescription = task.split("] ", 3);
                saveFile.write(tasks.get(i).getType() + " / " + tasks.get(i).getStatus() + " / " + splitTaskDescription[2] + '\n');
            }
            saveFile.close();
        } catch (IOException e) {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }

    /**
     * Prints an acknowledgement, based on the type of task.
     * Also prints the description of the task and the current number of tasks
     */
    public static void printAcknowledgement(String taskType, String description, String size) {
        printHorizontalRule();
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + size);
        printHorizontalRule();
    }

    /**
     * Removes the command word for creating tasks, ie. to do, deadline or event
     */
    public static String removeCommandWord(String userInput) {
        return userInput.split(" ", 2)[1];
    }

    /**
     * Extracts the description given by the user for deadline tasks
     */
    public static String getDescriptionForDeadline(String input) {
        return input.split(" /by ", 2)[0];
    }

    /**
     * Extracts the deadline for deadline tasks
     */
    public static String getDeadline(String input) {
        return input.split(" /by ", 2)[1];
    }

    /**
     * Extracts the description given by the user for event tasks
     */
    public static String getDescriptionForEvent(String input) {
        return input.split(" /from ", 2)[0];
    }

    /**
     * Extracts the start and end timing for event tasks
     */
    public static String[] getTimings(String input) {

        String[] timings = new String[2];
        String timing = input.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];

        return timings;
    }


    /* --- MAIN FUNCTION --- */
    public static void main(String[] args) {
        // tasks for storing tasks + taskCounter to track next empty cell
        ArrayList<Task> tasks = new ArrayList<>();

        // Setting up input reading
        final Scanner INPUT = new Scanner(System.in);

        // Start up sequence
        System.out.println(LOGO);
        printHorizontalRule();
        startFileProcessing(tasks);
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        printHorizontalRule();

        // Reads inputs until "bye" is sent
        while (true) {
            String userInput = INPUT.next();
            userInput += INPUT.nextLine();

            // Extracts first word in input
            // Used to check if tasks are to be marked/unmarked
            String firstWord = userInput.split(" ", 2)[0].toLowerCase();

            // initializing variables
            int taskIndex;
            String withoutCommand = "";
            String description = "";

            switch (firstWord) {
                case ("bye"): {
                    // Shut down process
                    printHorizontalRule();
                    endFileProcessing(tasks);
                    System.out.println("Shutting Down! Hope to see you again soon!");
                    printHorizontalRule();
                    return;
                }
                case ("find"): {
                    try {
                        // Gets the description
                        description = removeCommandWord(userInput);

                        int counter = 0;

                        // If the description of the task matches the description, the task gets printed
                        printHorizontalRule();
                        System.out.println("The index given here is not reflective of the task's actual index.\nHere are all tasks which contain " + description + " in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            if (tasks.get(i).contains(description)) {
                                System.out.println((counter + 1) + ". " + tasks.get(i));
                                counter++;
                            }
                        }
                        printHorizontalRule();

                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Wrong usage of deadline. Format is: find DESCRIPTION");
                        System.out.println("You entered: " + userInput);
                        printHorizontalRule();
                    }
                    break;
                }
                case ("list"): {
                    // Prints all contents of task Array
                    printHorizontalRule();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    // Prints total number
                    System.out.println("Total number of tasks: " + tasks.size());
                    printHorizontalRule();
                    break;
                }
                case ("mark"): {
                    try {
                        // Sets specified task to complete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                        (tasks.get(taskIndex - 1)).setComplete();

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Marking task \"" + (tasks.get(taskIndex - 1)).getTask() + "\" as complete!");
                        printHorizontalRule();

                    } catch (IndexOutOfBoundsException e) {
                        // Invalid index
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    } catch (NumberFormatException e) {
                        // Invalid input - a string or non-integer
                        printHorizontalRule();
                        System.out.println("Please give an integer!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("unmark"): {
                    try {
                        // Sets specified task to incomplete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                        (tasks.get(taskIndex - 1)).setIncomplete();

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Marking task \"" + (tasks.get(taskIndex - 1)).getTask() + "\" as incomplete!");
                        printHorizontalRule();
                    } catch (IndexOutOfBoundsException e) {
                        // Invalid index
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    } catch (NumberFormatException e) {
                        // Invalid input - a string or non-integer
                        printHorizontalRule();
                        System.out.println("Please give an integer!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("delete"): {
                    try {
                        // Sets specified task to incomplete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Deleting task: \"" + (tasks.get(taskIndex)).getTask() + "\"");
                        printHorizontalRule();

                        // Deletes task
                        tasks.remove(taskIndex);

                    } catch (IndexOutOfBoundsException e) {
                        // Invalid index
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    } catch (NumberFormatException e) {
                        // Invalid input - a string or non-integer
                        printHorizontalRule();
                        System.out.println("Please give an integer!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("todo"): {
                    try {
                        // Removes command word to get the description
                        String todoDescription = removeCommandWord(userInput);

                        // Creates a new task
                        Todo newTodo = new Todo(todoDescription);
                        tasks.add(newTodo);

                        // Prints acknowledgement
                        printAcknowledgement("Todo", todoDescription, String.valueOf(tasks.size()));
                    } catch (IndexOutOfBoundsException e) {
                        // Invalid input
                        printHorizontalRule();
                        System.out.println("Invalid command for todo. Cannot have a blank description!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("deadline"): {
                    try {
                        // Removes the command word
                        withoutCommand = removeCommandWord(userInput);

                        // Obtains description - name of deadline task
                        description = getDescriptionForDeadline(withoutCommand);

                        // Obtains date/time deadline
                        String by = getDeadline(withoutCommand);

                        // Creates a new deadline, parsing the date and time
                        Deadline deadline = new Deadline(description, LocalDateTime.parse(by, FORMATTER).format(FORMATTER));
                        tasks.add(deadline);

                        // Prints acknowledgement
                        printAcknowledgement("Deadline", description, String.valueOf(tasks.size()));
                    } catch (IndexOutOfBoundsException e) {
                        // Invalid input
                        printHorizontalRule();
                        System.out.println("Wrong usage of deadline. Format is: deadline DESCRIPTION /by YYYY-MM-DD HH:MM");
                        System.out.println("You entered: " + userInput);
                        printHorizontalRule();
                    } catch (DateTimeParseException e) {
                        // Invalid time input
                        printHorizontalRule();
                        System.out.println("Wrong date and time format used! The required format is YYYY-MM-DD HH:MM");
                        printHorizontalRule();
                    }
                    break;
                }
                case ("event"): {
                    try {
                        // Removes command word
                        withoutCommand = removeCommandWord(userInput);

                        // Obtains description - name of the event task
                        description = getDescriptionForEvent(withoutCommand);

                        // Returns in the format [from, to]
                        String[] timings = getTimings(withoutCommand);

                        // Creates a new event, parsing the date and time
                        Event event = new Event(description, LocalDateTime.parse(timings[0], FORMATTER).format(FORMATTER), LocalDateTime.parse(timings[1], FORMATTER).format(FORMATTER));
                        tasks.add(event);

                        // Prints acknowledgement
                        printAcknowledgement("Event", description, String.valueOf(tasks.size()));
                    } catch (IndexOutOfBoundsException e) {
                        // Invalid input
                        printHorizontalRule();
                        System.out.println("Wrong usage of event. Format is: event DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM");
                        System.out.println("You entered: " + userInput);
                        printHorizontalRule();
                    } catch (DateTimeParseException e) {
                        // Invalid date time input
                        printHorizontalRule();
                        System.out.println("Wrong date and time format used! The required format is YYYY-MM-DD HH:MM");
                        printHorizontalRule();
                    }
                    break;
                }
                default: {
                    // If none of the above, invalid command - prints error informing user
                    printHorizontalRule();
                    System.out.println(userInput + " is an invalid command. Please try again!");
                    printHorizontalRule();
                    break;
                }
            }
        }
    }
}
