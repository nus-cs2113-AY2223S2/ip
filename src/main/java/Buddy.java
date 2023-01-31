import java.util.Scanner;
import java.util.Arrays;

public class Buddy {
    public static void main(String[] args) {
        String greeting = "Hello there! I'm Buddy\n"
                + "How may I assist you?";
        String exitMessage = "Hope I was of help to you! Have a great day and see you again, Buddy :)";
        String divider = "________________________________________________________________________________";

        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);

        Task[] listOfThings = new Task[100];  // why cannot private static?
        int currentPosition = 0;            // why cannot private static?
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        while (! command.equals("bye")){
            int index = 1;
            if (command.equals("list")){
                for (int i = 0; i < currentPosition; i++){ // while not null
                    System.out.println(index + ". [" + listOfThings[index - 1].getStatusIcon() + "] " + listOfThings[index-1].description);
                    index++;
                }
            }

            else if (command.startsWith("mark")){  // .startsWith(" ")
                int taskNumber = Integer.parseInt(command.substring(5));
                // have to parse
                Task currentTask = listOfThings[taskNumber - 1];
                currentTask .setDone(true);
                System.out.println(divider);
                System.out.println("Great work on completing this task! Marked as done! :)");
                System.out.println("[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println(divider);
            }

            else if (command.startsWith("unmark")){
                int taskNumber = Integer.parseInt(command.substring(7));
                Task currentTask = listOfThings[taskNumber - 1];
                currentTask.setDone(false);
                System.out.println(divider);
                System.out.println("Remember to come back to this task! Marked as undone!");
                System.out.println("[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println(divider);

            }

            else { // adding tasks
                listOfThings[currentPosition] = new Task(command); // have to write in
                //Task t = new Task(command);
                System.out.println(divider);
                System.out.println("added: " + command);
                System.out.println(divider);
                currentPosition++;
            }
            command = in.nextLine();

        }

        System.out.println(divider);
        System.out.println(exitMessage);
        System.out.println(divider);

    }
}
