package duke;

import duke.task.*;

<<<<<<< HEAD
import java.io.IOException;
=======
import java.util.ArrayList;
>>>>>>> branch-Level-6
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    protected static ArrayList<Task> list = new ArrayList<Task>();
    public static final String OPENING_MSG = "Hello! I'm Dukebot\n\tWhat can I do for you?";
    public static final String CLOSING_MSG = "Goodbye! Hope to see you again soon ^^!";
    public static final String HORIZONTAL = "---------------------------------";
    public static final String MARK_MSG = "Nice! I've marked this task as done:";
    public static final String UNMARK_MSG = "Oki! I've marked this task as not done yet:";
    public static final String UNRECOGNISED_WORD = HORIZONTAL + "\n\tSorry, I don't know what that means!\n" + HORIZONTAL;
    public static final String EMPTY_EVENT = HORIZONTAL + "\n\tCannot add, the description of an event cannot be empty!\n" + HORIZONTAL;
    public static final String EMPTY_DEADLINE = HORIZONTAL + "\n\tCannot add, the description of a deadline cannot be empty!\n" + HORIZONTAL;
    public static final String EMPTY_TODO = HORIZONTAL + "\n\tCannot add, the description of a todo cannot be empty!\n" + HORIZONTAL;

    public static final String EMPTY_LISTNUM = HORIZONTAL + "\n\tI cannot change the status if I don't know the list number!\n" + HORIZONTAL;

    public static final String FILEPATH = "data.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println(HORIZONTAL + "\n\t" + OPENING_MSG + "\n" + HORIZONTAL);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        int numToMark;
        int item = 0;
        loadTasksToFile();

        while (!line.equals("bye")) {
            if (!(line.toLowerCase().startsWith("event") || line.toLowerCase().startsWith("delete") ||line.toLowerCase().startsWith("deadline") || line.toLowerCase().startsWith("todo") || line.equals("list") || line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark"))) {
                System.out.println(UNRECOGNISED_WORD);

            } else {
                // Add event
                if (line.toLowerCase().startsWith("event")) {
                    item = addEvent(line, item);
                    displayNumItemsInList(item);
                    System.out.println(HORIZONTAL);
                }
                // Add deadline
                if (line.toLowerCase().startsWith("deadline")) {
                    item = addDeadline(line, item);
                    displayNumItemsInList(item);
                    System.out.println(HORIZONTAL);
                }
                // Add todo
                if (line.toLowerCase().startsWith("todo")) {
                    item = addToDo(line, item);
                    displayNumItemsInList(item);
                    System.out.println(HORIZONTAL);
                }

                // Display list
                if (line.equalsIgnoreCase("list")) {
                    displayList(item);
                }

                // Mark item as done: constraint - user input begins with "mark"
                if (line.toLowerCase().startsWith("mark")) {
                    toggleDoneStatus(line, item, MARK_MSG, true);
                }
                // Unmark item: constraint - user input begins with "mark"
                if (line.toLowerCase().startsWith("unmark")) {
                    toggleDoneStatus(line, item, UNMARK_MSG, false);
                }
<<<<<<< HEAD
                writeTasksToFile();
=======

                // Delete item
                if (line.toLowerCase().startsWith("delete")) {
                    item = deleteTask(line, item);
                }
>>>>>>> branch-Level-6
            }
            // Read next line
            line = in.nextLine();
        }
        System.out.println(HORIZONTAL + "\n\t" + CLOSING_MSG + "\n" + HORIZONTAL);

    }

    public static void displayTaskAddedMessage(Task task) {
        System.out.println(HORIZONTAL + "\n\tGot it! Added this task: " + "\n\t\t" + task.getDescription());
    }
    public static void displayTaskDeletedMessage(Task task) {
        System.out.println(HORIZONTAL + "\n\tNoted! I have deleted this task: " + "\n\t\t" + task.getDescription());
    }

    public static void displayNumItemsInList(int item) {
        if (item > 1) {
            System.out.println("\tNow you have " + (item) + " tasks in the list");
        } else {
            System.out.println("\tNow you have " + (item) + " task in the list");
        }
    }

    public static int addEvent(String line, int item) {
        try {
            int toIndex = line.indexOf("/to");
            String toDate = line.substring(toIndex + 3);
            int fromIndex = line.indexOf("/from");
            String fromDate = line.substring(fromIndex + 5, toIndex);
            int descriptionIndex = line.indexOf("event");
            String description = line.substring(descriptionIndex + 6, fromIndex);

            Event newEvent = new Event(description, fromDate, toDate);
            list.add(newEvent);
            item++;
            displayTaskAddedMessage(newEvent);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(EMPTY_EVENT);

        } finally {
            return item;

        }
    }

    public static int addDeadline(String line, int item) {
        try {
            int byIndex = line.indexOf("/by");
            String byDate = line.substring(byIndex + 3);
            int descriptionIndex = line.indexOf("deadline");
            String description = line.substring(descriptionIndex + 9, byIndex);

            Deadline newDeadline = new Deadline(description, byDate);
            list.add(newDeadline);

            item++;
            displayTaskAddedMessage(newDeadline);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(EMPTY_DEADLINE);

        } finally {
            return item;

        }
    }

    public static int addToDo(String line, int item) throws IOException {
        try {
            int descriptionIndex = line.indexOf("todo");
            String description = line.substring(descriptionIndex + 5);

            Todo newTodo = new Todo(description);
            list.add(newTodo);
            item++;
            displayTaskAddedMessage(newTodo);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(EMPTY_TODO);

        } finally {
              return item;

        }

    }

    public static int deleteTask(String line, int item) {
        try {
            String inputMessageArray[] = new String[2];
            inputMessageArray = line.split(" ");
            int numToDelete = Integer.parseInt(inputMessageArray[1]) - 1;

            // Check if list item number exists in list
            if (numToDelete >= 0 && numToDelete < item) {
                // Delete item from list
                Task deletedTask = list.remove(numToDelete);
                item--;
                displayTaskDeletedMessage(deletedTask);
                displayNumItemsInList(item);
                System.out.println(HORIZONTAL);

            } else {
                System.out.println("Item number " + (numToDelete + 1) + " does not exist in list");
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println(EMPTY_LISTNUM);
        }
        return item;
    }

    public static void displayList(int item) {
        System.out.println(HORIZONTAL + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < item; i++) {
            System.out.println("\n\t" + (i + 1) + ". " + list.get(i).getDescription() + "\n");
        }
        System.out.println(HORIZONTAL);
    }

    public static void toggleDoneStatus(String line, int item, String msg, boolean status) {
        try {
            String inputMessageArray[] = new String[2];
            inputMessageArray = line.split(" ");
            int numToMark = Integer.parseInt(inputMessageArray[1]) - 1;

            // Check if list item number exists in list
            if (numToMark >= 0 && numToMark < item) {
                // Check if it is already done/not done in list
                if (list.get(numToMark).getIsDone() != status) {
                    // Update IsDone status
                    list.get(numToMark).setIsDone(status);

                    System.out.println(HORIZONTAL + "\n\t" + msg);
                    System.out.println("\n\t\t" + list.get(numToMark).getDescription() + "\n");
                    System.out.println(HORIZONTAL);
                } else {
                    System.out.println("No change, task was already as is");
                }
            } else {
                System.out.println("Item number " + (numToMark + 1) + " does not exist in list");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(EMPTY_LISTNUM);

        }
    }

    public static void loadTasksToFile() throws IOException {
        // @@author tangphi-reused
        // Reused from www.w3schools.com/java/java_files_create.asp
        // with minor modifications
        try {
            File file = new File(FILEPATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println("READER LINE\n" + line); //Do you want this printed in data.txt?
            }
        } catch (IOException e) {
            System.out.println("An IO Exception error occurred");
            e.printStackTrace();
        }

    }

    public static void writeTasksToFile() throws IOException, ClassNotFoundException {
        // https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter(FILEPATH);
            for (Task task : list) {

                if (task instanceof Todo) {
                    myWriter.write(task.getDescription() + "\n");
                }
                else if (task instanceof Event) {
                    myWriter.write(task.getDescription() + "\n");
                }
                else if (task instanceof Deadline) {
                    myWriter.write(task.getDescription() + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An IO Exception error occurred");
            e.printStackTrace();
        }
    }
    // @@author
}
