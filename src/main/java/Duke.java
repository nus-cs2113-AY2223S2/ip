
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String divider = "\t____________________________________________________________";
        CommandManager.sayHi();
        CommandManager command = new CommandManager();
        command.setUserInput(in.nextLine());
        while (!command.getUserInput().equals("bye")) {
            String[] userInput = command.getUserInput().split(" ", 2);
            String key = userInput[0];
            switch (key) {
            case "mark":
                Tasks markTask =  Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                markTask.setMarked(true);
                command.setUserOutput("mark");
                command.printOutput(markTask);
//                System.out.println(divider + "\n\t Nice! I've marked this task as done:\n\t  " +
//                        "[X] " +  markTask.getItem());
//                System.out.println(divider);
                break;
            case "unmark":
                Tasks unMarkTask = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                unMarkTask.setMarked(false);
                command.setUserOutput("unmark");
                command.printOutput(unMarkTask);
//                System.out.println(divider + "\n\t Nice! I've marked this task as not done yet:\n\t  " +
//                        "[ ] " +  unMarkTask.getItem());
//                System.out.println(divider);
                break;
            case "list":
                int timer = Tasks.getNumberOfTasks();
                System.out.println(divider + "\n\t Here are the tasks in your list:");
                for (int num = 1; num <= timer; ++num) {
                    Tasks thisTask = Tasks.getTaskList().get(num - 1);
                    command.printOutput(thisTask);
                    if (thisTask.isMarked()) {
                        command.setUserOutput();
                        System.out.println("\t " + Integer.toString(num) + ".[X] " + thisTask.getItem());
                    } else {
                        System.out.println("\t " + Integer.toString(num) + ".[ ] " + thisTask.getItem());
                    }
                }
                System.out.println(divider);
                break;
            default:
                Tasks.addToList(new Tasks(command.getUserInput(), false));
            }
            command.setUserInput(in.nextLine());
        }
        System.out.println(divider + "\n\t Bye. Hope to see you again soon!\n" + divider);
    }
}
