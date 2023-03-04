package IPChat;

import ipchatExceptions.IPChatExceptions;

import java.util.Locale;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IPChat {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int tasksCount = 0; // used camelCase
    private static int checkInput = 0;

    // Saves
    public static void saveContent(ArrayList<Task> tasks) {
        String path = "C:\\Users\\deepa\\OneDrive\\Documents\\OneNote\\ip\\SaveInfo.txt";
        try {
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
            for (int i = 0; i < tasksCount; i += 1) {
                String value = tasks.get(i).saveStuff() + "\n";
                Files.write(Paths.get(path), value.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadContent() throws FileNotFoundException, IPChatExceptions {
        File currFile = new File("C:\\Users\\deepa\\OneDrive\\Documents\\OneNote\\ip\\SaveInfo.txt");
        Scanner input = new Scanner(currFile);
        String taskCase;
        String str;
        int taskNum = 1;

        while (input.hasNext()) {
            String info = input.nextLine();
            String[] arr1 = info.split(" \\| ");
            String[] arr2 = arr1[0].split(" ");
            taskCase = arr2[0];
            str = Integer.toString(taskNum);

            switch (taskCase) {
            case "deadline":
                deadlineTasks(arr1[0]);
                if (arr1[1].equals("1")) {
                    markDone(str);
                }
                break;
            case "todo":
                toDoTasks(arr1[0]);
                if (arr1[1].equals("1")) {
                    markDone(str);
                }
                break;
            case "event":
                eventTasks(arr1[0]);
                if (arr1[1].equals("1")) {
                    markDone(str);
                }
                break;
            default:
            }
            taskNum += 1;
        }
        input.close();
    }

    // Says bye to the user and exits the program
    public static void sayBye(String statements) throws IPChatExceptions {
        if (statements.length() != 3) {
            throw new IPChatExceptions("Too long a goodbye makes me emotional and too short a goodbye makes me feel ignored!\n");
        } else {
            System.out.println("------------------------------------------");
            System.out.println("Bye, Hope to see you soon");
            System.out.println("------------------------------------------");
            checkStatements();
        }
        System.exit(0);
    }

    public static void checkStatements() {
        checkInput = 1;
    }

    // Create a list of the task
    public static void listTasks(String statements) throws IPChatExceptions {
        if (tasksCount == 0) {
            throw new IPChatExceptions("Please give an input");
        } else {
            System.out.println("------------------------------------------");
            System.out.println("Here is the list of tasks for the day! All the best :) \n");
            for (int i = 0; i < tasksCount; i += 1) {
                System.out.println((i + 1) + ". " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).toString());
            }
            System.out.println("------------------------------------------");
        }
    }

    // Marks a task as done
    public static void markDone (String statements) throws IPChatExceptions {
        if (tasksCount != 0) {
            try {
                int taskIndex = Integer.parseInt(statements.substring(statements.lastIndexOf(" ") + 1)) - 1; // changed index to taskIndex
                tasks.get(taskIndex).markAsDone();
                System.out.println("I have marked the task as done");
                System.out.println("------------------------------------------");
                System.out.println("  " + tasks.get(taskIndex).toString() + "\n");
            } catch (NullPointerException e) {
                System.out.println("Invalid Task Number");
            }
        } else {
            throw new IPChatExceptions("To start listing");
        }
    }

    // tasks to do
    public static void toDoTasks (String statements) {
        if (statements.length() == 4) {
            System.out.println("Please continue");
        } else {
            int todoLength = statements.length();
            String todoName = statements.substring(5, todoLength);
            tasks.add(tasksCount, new ToDo(todoName));
            System.out.println("------------------------------------------");
            System.out.println("Got it. I've added this task:\n" + tasks.get(tasksCount).toString());
            tasksCount += 1;
            System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
            System.out.println("------------------------------------------");
        }
    }

    // Tasks having deadlines
    public static void deadlineTasks(String statements) {
        if (statements.length() == 8) {
            System.out.println("Please provide correct input");
        } else {
            int end = statements.indexOf("/");
            String deadlineName = statements.substring(9, end);
            int deadlineLength = statements.length();
            String by = statements.substring(end + 4, deadlineLength);
            LocalDate date = LocalDate.parse(by);
            LocalDate day = LocalDate.parse(by);
            tasks.add(tasksCount, new Deadline(deadlineName, by));
            System.out.println("------------------------------------------");
            System.out.println("Date of deadline: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
            System.out.println("Day of deadline: " + day.getDayOfWeek());
            System.out.println("Got it. I've added this task:\n");
            System.out.println(tasks.get(tasksCount).toString());
            tasksCount += 1;
            System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
            System.out.println("------------------------------------------");
        }
    }

    // Events of the task
    public static void eventTasks(String statements) {
        if (statements.length() == 5) {
            System.out.println("Please provide correct input");
        } else if (!statements.contains("/")) {
            System.out.println("Please provide timings");
        } else {
            int fromStart = statements.indexOf(" /from");
            int toStart = statements.indexOf(" /to");

            String eventName = statements.substring(5, fromStart);
            String fromBegin = statements.substring(fromStart + 7, toStart);
            String toBegin = statements.substring(toStart + 5);

            tasks.add(tasksCount, new Event(eventName, fromBegin, toBegin));
            System.out.println("------------------------------------------");
            System.out.println("Got it. I've added this task:\n" + tasks.get(tasksCount).toString());
            tasksCount += 1;
            System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
            System.out.println("------------------------------------------");
        }
    }

    // Delete a task
    public static void deleteTasks(String statements) throws IPChatExceptions {
        if (tasksCount != 0) {
            try {
                int finalNum = Integer.parseInt(statements.substring(statements.lastIndexOf(" ") + 1)) - 1;
                Task removeTask = tasks.get(finalNum);
                tasks.remove(finalNum);
                tasksCount--;
                System.out.println("------------------------------------------");
                System.out.println("Deleting the following items");
                System.out.println("  " + removeTask.toString() + "\n" + "\nYou now have " + tasksCount + " tasks left.\n");
                System.out.println("------------------------------------------");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Task number !!");
            }
        } else {
            throw new IPChatExceptions("Please give tasks to delete!");
        }
    }

    public static void findTasks (String statements) {
        String[] contents = statements.split(" ", 2);
        String mainWord = contents[1];
        int matchCount = 0;

        ArrayList<String> neededTasks = new ArrayList<>();
        for (Task currentTasks: tasks) {
            if (currentTasks.toString().contains(mainWord)) {
                neededTasks.add(currentTasks.toString());
                matchCount += 1;
            }
        }

        if (matchCount != 0) {
            System.out.println("Here are the matching tasks as requested");
            System.out.println("------------------------------------------");
            for (int i = 0 ; i < neededTasks.size(); i += 1) {
                System.out.println((i + 1) + ". " + neededTasks.get(i) + "\n");
            }
        }
    }
    // Compilation
    public static void mySequence() throws IPChatExceptions {
        while (checkInput == 0) {
            Scanner input = new Scanner(System.in);
            String statements = input.nextLine();
            String words = statements.split(" ")[0];
            switch (words) {
            case "bye":
                sayBye(statements);
                break;
            case "list":
                try {
                    listTasks(statements);
                } catch (IPChatExceptions e) {
                    System.out.println("Please give an input");
                }
                break;
            case "done":
                markDone(statements);
                saveContent(tasks);
                break;
            case "todo":
                toDoTasks(statements);
                saveContent(tasks);
                break;
            case "deadline":
                try {
                    deadlineTasks(statements);
                    saveContent(tasks);
                } catch (DateTimeParseException d) {
                    System.out.println("Please enter the correct date format yyyy-mm-dd");
                }
                break;
            case "event":
                eventTasks(statements);
                saveContent(tasks);
                break;
            case "delete":
                deleteTasks(statements);
                saveContent(tasks);
                break;
            case "find":
                findTasks(statements);
                break; 
            default:
                System.out.println("------------------------------------------");
                System.out.println("Please provide accurate readings");
            }
        }
    }

    public static void main(String[] args) throws IPChatExceptions , IOException{
        System.out.println("Hello I'm IPChat, What can I do for you");
        System.out.println("------------------------------------------");
        mySequence();
    }
}
