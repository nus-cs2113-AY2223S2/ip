import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

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

        String input = console.nextLine();

        while(!input.equals("bye")){
            // Dynamic mark conditional
            if(input.indexOf("mark") > -1 && input.indexOf("unmark") == -1){
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index > Task.getTasksArray().size() - 1){
                    System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + barrier + "\n");
                } else {
                    Task.getTasksArray().get(index).mark();
                }
                input = "handled";
            }
            // Dynamic unmark conditional
            if(input.indexOf("unmark") > -1){
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index > Task.getTasksArray().size() - 1){
                    System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + barrier + "\n");
                } else {
                    Task.getTasksArray().get(index).unmark();
                }
                input = "handled";
            }

            switch(input){
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
                case "handled": {
                    break;
                }
                case "help": {
                    help();
                    break;
                }
                default: {
                    System.out.println(barrier + "\n");
                    Task temp = new Task(input);
                    System.out.println("added: " + temp.printTask());
                    System.out.println(barrier + "\n");
                }
            }
            input = console.nextLine();
        }

        System.out.println(exit);
    }

    public static void help(){
        String barrier = "____________________________________________________________";
        System.out.println(barrier + "\n\nAvast! Here be the commands ye can use to make me do yer bidding!\n- list: lists all current tasks\n- mark x: marks task x as complete\n- unmark x: unmarks task x as complete\n- bye: exists Dule\n- anything else: adds a task with the given description\n" + barrier + "\n");
    }
}
