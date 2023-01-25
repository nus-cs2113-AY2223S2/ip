import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo =
                " __   __\n"
                        + "|  | |  |   ____     _  _   _    _\n"
                        + "|  |_|  |  / _  \\   | |/_\\ | |  | |\n"
                        + "|   _   | | |_|  \\  |  /   | \\_/  |\n"
                        + "|__| |__|  \\___/\\_\\ |_|     \\__/|_|\n";

        String divider = "____________________________________________________________";

        System.out.println(divider);
        System.out.println(logo);
        System.out.println("Hello! I'm Haru");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        String userCommand = scanner.nextLine();

        boolean isExit = false;
        Task[] storedUserTasks = new Task[100];
        int taskIndex = -1;
        int userTextCount = 0;
        while(!isExit){
            if(userCommand.split(" ")[0].equals("mark")||userCommand.split(" ")[0].equals("unmark")){
                try {
                    taskIndex = Integer.parseInt(userCommand.split(" ")[1]);
                    userCommand = userCommand.split(" ")[0];
                }
                catch (Exception e){
                    // User command is an actual task to be added
                }
            }
            switch (userCommand){
            case "list":
                System.out.println(divider);
                System.out.println("Here are the tasks in your lists:");
                for(int i=0;i<userTextCount;i++){
                    System.out.println((i+1)+"."+"["+storedUserTasks[i].getStatusIcon()+"] "+storedUserTasks[i].description);
                }
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            case "bye":
                isExit = true;
                break;
            case "mark":
                System.out.println(divider);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] "+storedUserTasks[taskIndex-1].description);
                storedUserTasks[taskIndex-1].isDone = true;
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            case "unmark":
                System.out.println(divider);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] "+storedUserTasks[taskIndex-1].description);
                storedUserTasks[taskIndex-1].isDone = false;
                System.out.println(divider);
                userCommand = scanner.nextLine();
                break;
            default:
                System.out.println(divider);
                System.out.println("added: " + userCommand);
                System.out.println(divider);
                storedUserTasks[userTextCount] = new Task(userCommand);
                userCommand = scanner.nextLine();
                userTextCount++;
                break;
            }
        }

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
