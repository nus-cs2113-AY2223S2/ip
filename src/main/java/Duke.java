import java.util.Scanner;



public class Duke {
    private static int userInputCount = 0;
    public static void main(String[] args) {
        Task[] userInputList = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        String userInput;
        Scanner scan = new Scanner(System.in);
        while (true) {
            userInput = scan.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < userInputCount; i++) {
                    int index = i;
                    index++;
                    System.out.println(index + ".[" + userInputList[i].getStatusIcon() + "] " + userInputList[i].description);
                }
            }
            else {
                String[] userInputSplit = userInput.split(" ");
                if (userInputSplit.length > 1) {
                    if (userInputSplit[0].equals("mark")){
                        int taskIndex = Integer.parseInt(userInputSplit[1]);
                        taskIndex--;
                        userInputList[taskIndex].markAsDone();
                        System.out.print("The following task has been marked done: ");
                        System.out.println(userInputList[taskIndex].description);
                        continue;
                    }  else if (userInputSplit[0].equals("unmark")) {
                        int taskIndex = Integer.parseInt(userInputSplit[1]);
                        taskIndex--;
                        userInputList[taskIndex].markAsUndone();
                        System.out.print("The following task has been marked done: ");
                        System.out.println(userInputList[taskIndex].description);
                        continue;
                    }
                }
                Task t = new Task(userInput);
                System.out.println(userInput);
                userInputList[userInputCount] = t;
                userInputCount++;
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
