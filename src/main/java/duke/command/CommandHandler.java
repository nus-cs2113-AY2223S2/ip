package duke.command;

import duke.exceptions.ListTooLarge;

import java.util.Scanner;

public class CommandHandler {

    TaskManager myList = new TaskManager();

    /**
     * Prints the current items and status in the list
     */
    public void handleListCommand() {
        myList.printList();
    }

    /**
     * Sets the item to be marked as done
     */
    public void handleMarkAsDone(String index) {
        try {
            myList.markAsDone(Integer.parseInt(index) - 1);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException arraySizeException) {
            System.out.println("No such item exists! Please select a number within the list size");
        } catch (NumberFormatException notANumber) {
            System.out.println("Please enter a number");
        }
    }

    /**
     * Sets the item to be marked as undone
     */
    public void handleMarkAsUndone(String index) {
        try {
            myList.markAsUndone(Integer.parseInt(index) - 1);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException arraySizeException) {
            System.out.println("No such item exists! Please select a number within the list size");
        } catch (NumberFormatException notANumber) {
            System.out.println("Please enter a number");
        }
    }

    /**
     * Adds todo item to list
     */
    public void handleTodoCommand(String description) {
        try {
            myList.addToList(description);
        } catch (ListTooLarge error) {
            System.out.println(error.getMessage());
        } catch (IndexOutOfBoundsException outOfBounds) {
            System.out.println("Please input all the necessary details");
        }

    }

    /**
     * checks if user input is valid and adds the event to list
     *
     * @param userInputArray array of string containing user deadline details
     */
    public void handleDeadlineCommand(String[] userInputArray) {
        try {
            String description = userInputArray[0];
            String dueBy = userInputArray[1];
            myList.addToList(description, dueBy);
        } catch (ListTooLarge error) {
            System.out.println(error.getMessage());
        } catch (IndexOutOfBoundsException outOfBounds) {
            System.out.println("Please input all the necessary details");
        }
    }

    /**
     * checks if user input is valid and adds the event to list
     *
     * @param userInput String containing user event details
     */
    public void handleEventCommand(String userInput) {
        try {
            final String[] userInputArray = userInput.trim().split("/from|/to");
            String description = userInputArray[0];
            String startTime = userInputArray[1];
            String endTime = userInputArray[2];
            myList.addToList(description, startTime, endTime);
        } catch (ListTooLarge error) {
            System.out.println(error.getMessage());
        } catch (IndexOutOfBoundsException outOfBounds) {
            System.out.println("Please input all the necessary details");
        }

    }

    /**
     * Takes in index of task and delete from list
     *
     * @param index position of the task to be removed
     */
    public void handleDeleteCommand(String index) {
        try {
            myList.deleteTask(Integer.parseInt(index) - 1);
        } catch (IndexOutOfBoundsException outOfBounds) {
            System.out.println("Please choose an appropriate index");
        } catch (NumberFormatException notANumber) {
            System.out.println("Please enter a number");
        }
    }

    /**
     * prints instructions to use the program if
     * an unknown command is entered
     */
    public static void handleUnknownCommand() {
        System.out.println("I don't understand...\n" +
                "Try these commands: <event> /from <start> /to <end>\n" +
                "<deadline> /by <end> \n" +
                "<todo>\n" +
                "<list>");
    }

}
