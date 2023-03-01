package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {

    /**
     * Encapsulates print statements for whenever a task is added
     * (as either a todo, deadline, or event).
     * 
     * @param task     Reference to task that is added.
     * @param numTasks Total number of tasks in the list.
     *
     */
    static final String LINE = "____________________________________________________________";

    private static void printAddTaskConfirmation(Task task, int numTasks) {
        System.out.println(LINE);
        System.out.println(
                "Got it. I've added this task:\n" + task + "\nNow you have " + numTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    private static ArrayList<Task> loadFromFile() throws IOException {
        File f = new File("duke.txt");

        f.createNewFile();
        Scanner s = new Scanner(f);

        ArrayList<Task> tasks = new ArrayList<>();

        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskSplit = task.split("\\|");
            switch (taskSplit[1]) {
                case "T":
                    Todo todo = new Todo(taskSplit[2]);
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(taskSplit[2], taskSplit[3]);
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(taskSplit[2], taskSplit[3], taskSplit[4]);
                    tasks.add(event);
                    break;
                default:
                    break;
            }
            if (taskSplit[0].equals("1")) {
                tasks.get(tasks.size() - 1).markDone();
            }
        }
        s.close();

        return tasks;

    }

    private static void changeStatus(ArrayList<Task> tasks, int toChange, String action) {
        System.out.println(LINE);
        switch(action) {
        case "mark":
            tasks.get(toChange).markDone();
            System.out.println("Ok, I've marked this task as done:");
            break;
        case "unmark":
            tasks.get(toChange).unmarkDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            break;
        default:
            break;
        }
        System.out.println(tasks.get(toChange));
        System.out.println(LINE);
        
    }

    private static void handleTodo(ArrayList<Task> tasks, String args) {
        Todo todo = new Todo(args);
        tasks.add(todo);
        printAddTaskConfirmation(todo, tasks.size());
    }

    private static void handleDeadline(ArrayList<Task> tasks, String args) {
    
    }

    private static boolean parseInput(String input, ArrayList<Task> tasks) {
        // Splits into only two sections, one for command name and one for arguments
        try {
            String[] splitIntoArgs = input.split(" ", 2);
            String cmd = splitIntoArgs[0];

            switch (cmd) {
            case "bye":
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                return false;
            case "list":
                System.out.println(LINE);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(Integer.toString(i + 1) + "." + tasks.get(i));
                }
                System.out.println(LINE);
                break;
            case "mark":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                int toMark = Integer.parseInt(splitIntoArgs[1]) - 1;
                if (toMark >= tasks.size()) {
                    throw new IncorrectIndexException();
                }
                changeStatus(tasks, toMark, "mark");
                break;
            case "unmark":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                int toUnmark = Integer.parseInt(splitIntoArgs[1]) - 1;
                if (toUnmark >= tasks.size()) {
                    throw new IncorrectIndexException();
                }
                changeStatus(tasks, toUnmark, "unmark");
                break;
            case "delete":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                int toDelete = Integer.parseInt(splitIntoArgs[1]) - 1;
                System.out.println(LINE);
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(toDelete));
                System.out.println(LINE);
                // remove after printing so that we can still retrieve info about the deleted
                // task
                tasks.remove(toDelete);
                break;
            case "todo":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                handleTodo(tasks, splitIntoArgs[1]);                
                break;
            case "deadline":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                // Split into arguments for description and by field
                String[] argsList = splitIntoArgs[1].split(" /by ");
                if (argsList.length < 2) {
                    throw new InsufficientParametersException();
                }
                Deadline deadline = new Deadline(argsList[0], argsList[1]);
                tasks.add(deadline);
                printAddTaskConfirmation(deadline, tasks.size());
                break;
            case "event":
                // Uses regex to split into arguments for description, from field, and to field.
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                argsList = splitIntoArgs[1].split(" \\/(from|to) ");
                if (argsList.length < 3) {
                    throw new InsufficientParametersException();
                }
                Event event = new Event(argsList[0], argsList[1], argsList[2]);
                tasks.add(event);
                printAddTaskConfirmation(event, tasks.size());
                break;
            default:
                throw new UnknownCommandException();
            }
            return true;
        } catch (UnknownCommandException e) {
            System.out.println(LINE);
            System.out.println("Command not found. Please enter a valid command!");
            System.out.println(LINE);
            return true;
        } catch (InsufficientParametersException e) {
            System.out.println(LINE);
            System.out.println(
                    "You have not provided enough parameters for this command. Please recheck your syntax!");
            System.out.println(LINE);
            return true;
        } catch (IncorrectIndexException e) {
            System.out.println(LINE);
            System.out.println("Please enter a valid index.");
            System.out.println(LINE);
            return true;
        }
    }

    private static void writeToFile (ArrayList<Task> tasks) throws IOException {
        new FileWriter("duke.txt").close(); // reset output file
        FileWriter fw = new FileWriter("duke.txt", true);
        for (Task task : tasks) {
            fw.write(task.storeString());
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        System.out.println(LINE);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(LINE);

        ArrayList<Task> tasks = loadFromFile();

        boolean hasNotExited = true;
        while (hasNotExited) {
            System.out.println();
            String input = in.nextLine();
            hasNotExited = parseInput(input, tasks);
            writeToFile(tasks);           
        } 
        in.close();

}}
