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

        System.out.println("Hello from\n" + logo);
        String greeting = barrier + "\n\nAhoy there! I be Duke.\nWhat can I do for ye?\nFor a list of commands, type 'help' me hearties!\n" + barrier + "\n";
        String exit = barrier + "\n\nFarewell! Hope to see ye again soon, ye scallywag!\n" + barrier;
        System.out.println(greeting);

        // Scanner declaration and initial input
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        // Read input such that it is not "bye"
        while(!input.equals("bye")){

            // Dynamic mark conditional
            if(input.indexOf("mark") > -1 && input.indexOf("unmark") == -1){
                if(!Character.isDigit(input.charAt(5)) || Integer.parseInt(input.substring(5)) == 0){
                    System.out.println("\nBlast! That isn't a valid number lad!\n" + barrier + "\n");
                } else {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index > Task.getTasksArray().size() - 1){
                        System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + barrier + "\n");
                    } else {
                        Task.getTasksArray().get(index).markAsComplete();
                    }
                }
                // Label the input as handled to dodge the switch
                input = "handled";
            }

            // Dynamic unmark conditional
            if(input.indexOf("unmark") > -1){
                if(!Character.isDigit(input.charAt(5)) || Integer.parseInt(input.substring(5)) == 0){
                    System.out.println("\nBlast! That isn't a valid number lad!\n" + barrier + "\n");
                } else {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index > Task.getTasksArray().size() - 1){
                        System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + barrier + "\n");
                    } else {
                        Task.getTasksArray().get(index).unmarkAsComplete();
                    }
                }

                // Label the input as handled to dodge the switch
                input = "handled";
            }

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

                // Breaks the switch of mark or unmark was called
                case "handled": {
                    break;
                }

                // Prints a list of commands and description
                case "help": {
                    printHelpList();
                    break;
                }

                // Adds a new task if no case is hit
                default: {
                    System.out.println(barrier + "\n");
                    Task temp = new Task(input);
                    System.out.println("added: " + temp.printTask());
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
        System.out.println(barrier + "\n\nAvast! Here be the commands ye can use to make me do yer bidding!\n- list: lists all current tasks\n- mark x: marks task x as complete\n- unmark x: unmarks task x as complete\n- bye: exists Dule\n- anything else: adds a task with the given description\n" + barrier + "\n");
    }
}
