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
        int userTextCount = 0;
        while(!isExit){

//            if(userCommand.split(" ")[0].equals("mark")||userCommand.split(" ")[0].equals("unmark")){
//                try {
//                    taskIndex = Integer.parseInt(userCommand.split(" ")[1]);
//                    userCommand = userCommand.split(" ")[0];
//                }
//                catch (Exception e){
//                    // User command is an actual task to be added
//                }
//            }

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
                storedUserTasks[userTextCount] = new Todo(userCommand.substring(5));
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
                storedUserTasks[userTextCount] = new Deadline(userCommand.substring(9,indexOfBy-1),userCommand.substring(indexOfBy+4));
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
                storedUserTasks[userTextCount] = new Event(userCommand.substring(6,indexOfFrom-1),userCommand.substring(indexOfFrom+6,indexOfTo-1),userCommand.substring(indexOfTo+4));
                System.out.println(divider);
                System.out.println(spacer+"Got it. I've added this task:");
                System.out.println(spacer+storedUserTasks[userTextCount].toString());
                userTextCount++;
                System.out.println(spacer+"Now you have " + userTextCount + " tasks in the list.");
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            default:
//                System.out.println(divider);
//                System.out.println("added: " + userCommand);
//                System.out.println(divider);
//                storedUserTasks[userTextCount] = new Task(userCommand);
//                userCommand = scanner.nextLine();
//                userTextCount++;

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
