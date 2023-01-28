import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Text declarations and initial greeting
        String barrier = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = barrier + "\n\nAhoy there! I be\n" + logo + "\nWhat can I do for ye?\nFor a list of commands, type 'help' me hearties!\n" + barrier + "\n";
        String exit = barrier + "\n\nFarewell! Hope to see ye again soon, ye scallywag!\n" + barrier;
        System.out.println(greeting);

        // Scanner declaration and initial input
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        // Read input such that it is not "bye"
        while(!input.equals("bye")){

            // Split user input to check for dynamism
            String originalInput = input;
            String[] splitInput = input.split(" ");
            input = splitInput[0];

            // Switch to check input
            switch(input){

                // Lists all tasks using the Task class printAllTask() method
                case "list": {
                    System.out.println(barrier + "\n");
                    if(Task.getTasksArray().size() == 0){
                        System.out.println("Ye have no tasks yet, ye lazy buccaneer!");
                    } else {
                        Task.printAllTasks();
                    }
                    System.out.println(barrier + "\n");
                    break;
                }

                // Marks a task as complete
                case "mark": {
                    char dynamicInput = splitInput[1].charAt(0);
                    if (!Character.isDigit(dynamicInput) || dynamicInput == '0' || splitInput[1].length() > 1){
                        System.out.println("\nBlast! That isn't a valid number lad!\n" + barrier + "\n");
                    } else {
                            int index = Integer.parseInt(splitInput[1]) - 1;
                        if (index > Task.getTasksArray().size() - 1){
                            System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + barrier + "\n");
                        } else {
                            Task.getTasksArray().get(index).markAsComplete();
                        }
                    }
                    break;
                }

                // Unmarks a previous completed task
                case "unmark": {
                    char dynamicInput = splitInput[1].charAt(0);
                    if (!Character.isDigit(dynamicInput) || dynamicInput == '0' || splitInput[1].length() > 1){
                        System.out.println("\nBlast! That isn't a valid number lad!\n" + barrier + "\n");
                    } else {
                            int index = Integer.parseInt(splitInput[1]) - 1;
                        if (index > Task.getTasksArray().size() - 1){
                            System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + barrier + "\n");
                        } else {
                            Task.getTasksArray().get(index).unmarkAsComplete();
                        }
                    }
                    break;
                }

                // Prints a list of commands and description
                case "help": {
                    printHelpList();
                    break;
                }

                // Adds a new ToDo task
                case "todo": {
                    System.out.println(barrier + "\n");
                    Todo temp = new Todo(originalInput);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(barrier + "\n");
                    break;
                }

                // Adds a new Deadline task
                case "deadline": {
                    int commandStart = originalInput.indexOf("/");
                    String dueDate = originalInput.substring(commandStart + 4);
                    String parsedDescription = originalInput.substring(0, commandStart - 1);
                    parsedDescription += " (by: " + dueDate + ")";
                    System.out.println(barrier + "\n");
                    Deadline temp = new Deadline(parsedDescription, dueDate);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(barrier + "\n");
                    break;
                }

                // Adds a new Event task
                case "event": {
                    String[] splitEventInput = originalInput.split("/");
                    String start = splitEventInput[1].substring(5);
                    String end = splitEventInput[2].substring(3);
                    String parsedDescription = splitEventInput[0] + "(from: " + start + "to: " + end + ")";
                    System.out.println(barrier + "\n");
                    Event temp = new Event(parsedDescription, start, end);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(barrier + "\n");
                    break;
                }

                // Adds a new task if no case is hit
                default: {
                    System.out.println(barrier + "\n");
                    Task temp = new Task(input);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(barrier + "\n");
                }
            }

            // Reads next input
            input = console.nextLine();
        }

        System.out.println(exit);
    }

    // Local Duke method to list all functions
    public static void printHelpList(){
        String barrier = "____________________________________________________________";
        System.out.println(barrier + "\n\nAvast! Here be the commands ye can use to make me do yer bidding!\n- list: lists all current tasks\n- mark x: marks task x as complete\n- unmark x: unmarks task x as complete\n- todo 'description': adds a task to do with the given description\n- deadline 'description' /by 'deadline': adds a deadline task with the given date and description\n- event 'description' /from 'start' /to 'end': adds an event with the start and endtime\n- bye: exits Duke\n- anything else: adds a basic task with the given description\n" + barrier + "\n");
    }

    public static void printListLength(){
        int length = Task.getTasksArray().size();
        if (length == 1) {
            System.out.println("Now you have " + Task.getTasksArray().size() + " task in the list!");
        } else {
            System.out.println("Now you have " + Task.getTasksArray().size() + " tasks in the list!");
        }
    }
}
