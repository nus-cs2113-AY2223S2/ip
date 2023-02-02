import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo =
                "\t __   __\n"
                        + "\t|  | |  |   ____     _  _   _    _\n"
                        + "\t|  |_|  |  / _  \\   | |/_\\ | |  | |\n"
                        + "\t|   _   | | |_|  \\  |  /   | \\_/  |\n"
                        + "\t|__| |__|  \\___/\\_\\ |_|     \\__/|_|\n";

        String divider = "\t____________________________________________________________";
        String spacer = "\t";
        System.out.println(divider);
        System.out.println(logo);
        System.out.println(spacer+"Hello! I'm Haru");
        System.out.println(spacer+"What can I do for you?");
        System.out.println(divider);

        String userCommand = scanner.nextLine();

        boolean isExit = false;
        Task[] storedUserTasks = new Task[100];
        int taskIndex;
        String description, by, from, to;
        int userTextCount = 0;
        while(!isExit){
            switch (userCommand.split(" ")[0]){
            case "list":
                System.out.println(divider);
                System.out.println(spacer+"Here are the tasks in your lists:");
                for(int i=0;i<userTextCount;i++){
                    System.out.println(spacer+(i+1)+"."+storedUserTasks[i].toString());
                }
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            case "bye":
                isExit = true;
                break;
            case "mark":
                taskIndex = Integer.parseInt(userCommand.split(" ")[1]);
                System.out.println(divider);
                System.out.println(spacer+"Nice! I've marked this task as done:");
                storedUserTasks[taskIndex-1].isDone = true;
                System.out.println(spacer+storedUserTasks[taskIndex-1].toString());
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            case "unmark":
                taskIndex = Integer.parseInt(userCommand.split(" ")[1]);
                System.out.println(divider);
                System.out.println(spacer+"OK, I've marked this task as not done yet:");
                storedUserTasks[taskIndex-1].isDone = false;
                System.out.println(spacer+storedUserTasks[taskIndex-1].toString());
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            case "todo":
                description = userCommand.substring(5);
                storedUserTasks[userTextCount] = new Todo(description);
                System.out.println(divider);
                System.out.println(spacer+"Got it. I've added this task:");
                System.out.println(spacer+spacer+storedUserTasks[userTextCount].toString());
                userTextCount++;
                System.out.println(spacer+"Now you have " + userTextCount + " tasks in the list.");
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            case "deadline":
                int indexOfBy = userCommand.indexOf("/by");
                description = userCommand.substring(9,indexOfBy-1);
                by = userCommand.substring(indexOfBy+4);
                storedUserTasks[userTextCount] = new Deadline(description,by);
                System.out.println(divider);
                System.out.println(spacer+"Got it. I've added this task:");
                System.out.println(spacer+spacer+storedUserTasks[userTextCount].toString());
                userTextCount++;
                System.out.println(spacer+"Now you have " + userTextCount + " tasks in the list.");
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            case "event":
                int indexOfFrom = userCommand.indexOf("/from");
                int indexOfTo = userCommand.indexOf("/to");
                description = userCommand.substring(6,indexOfFrom-1);
                from = userCommand.substring(indexOfFrom+6,indexOfTo-1);
                to = userCommand.substring(indexOfTo+4);
                storedUserTasks[userTextCount] = new Event(description,from,to);
                System.out.println(divider);
                System.out.println(spacer+"Got it. I've added this task:");
                System.out.println(spacer+storedUserTasks[userTextCount].toString());
                userTextCount++;
                System.out.println(spacer+"Now you have " + userTextCount + " tasks in the list.");
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            default:
                System.out.println(divider);
                System.out.println(spacer+"Please enter a valid input.");
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            }
        }

        System.out.println(divider);
        System.out.println(spacer+"Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
