import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        Task[] allTasks = new Task[100];
        int numberOfTasks = 0;
        // Create a Scanner object
        Scanner myObj = new Scanner(System.in);
        greeting();
        boolean isBye = false;
        while(!isBye) {
            String userInput = myObj.nextLine();
            String[] words = userInput.split(" ");
            if(words[0].toLowerCase().equals("list")) {
                for(int i = 1; i <= numberOfTasks; i++) {
                    System.out.print(i);
                    System.out.println(". " + "[" + allTasks[i-1].getStatusIcon() + "]" + " " + allTasks[i-1].getDescription());
                }
                printLine();

            }
            else if(words[0].toLowerCase().equals("bye")) {
                exit();
                isBye = true;
                break;
            }
            else if(words[0].toLowerCase().equals("mark")) {
                printLine();
                System.out.println("Good job! I have marked this task as completed:");
                int number = Integer.parseInt(words[1]);
                allTasks[number - 1].markAsDone();
                System.out.println("[" + allTasks[number - 1].getStatusIcon() + "]" + " " + allTasks[number - 1].getDescription());
                printLine();

            }
            else if(words[0].toLowerCase().equals("unmark")) {
                printLine();
                System.out.println("Got it! I have marked this task as not yet completed:");
                int number = Integer.parseInt(words[1]);
                allTasks[number - 1].unmark();
                System.out.println("[" + allTasks[number - 1].getStatusIcon() + "]" + " " + allTasks[number - 1].getDescription());
                printLine();


            }
            else {
                Task t = new Task(userInput);
                allTasks[numberOfTasks] = t;
                numberOfTasks += 1;
                printLine();
                echoResponse(userInput);
            }


        }


    }
    public static void echoResponse(String userInput) {
        System.out.println("added: " + userInput);
        printLine();
    }
    public static void printLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }
    public static void greeting() {
        printLine();
        String greet = "Hello! I'm Alex\nWhat can I do for you today?";
        //String question = "What can I do for you today?";
        System.out.println(greet);
        //System.out.println(question);
        printLine();
    }
    public static void exit() {
        String exit = "Bye. Hope to chat with you again soon!";
        System.out.println(exit);
        printLine();
    }
}
