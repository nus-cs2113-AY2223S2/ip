import java.util.Scanner;
public class IPChat {
    public static void main(String[] args) {

        // Level 3: Mark as done
        System.out.println("Hello I'm IPChat, What can I do for you");
        System.out.println("------------------------------------------");

        Scanner input =  new Scanner(System.in);
        String statements = input.nextLine(); // changed statement to statements
        Task[] tasks = new Task [100];
        int tasksCount = 0; // used camelCase

        while(!statements.equals("bye")) {
            System.out.println("------------------------------------------");
            if (statements.equals("list")) {
                System.out.println("Here is the list of tasks for the day! All the best :) \n");
                for (int i = 0; i < tasksCount; i += 1) {
                    System.out.println((i + 1) + "." +"[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("------------------------------------------");
            } else if (statements.contains("mark")) {
                System.out.println("I have marked the task as done");
                int taskIndex = Integer.parseInt(statements.substring(statements.length()-1)) - 1; // changed index to taskIndex
                tasks[taskIndex].markAsDone();
                System.out.println("------------------------------------------");
            } else {
                tasks[tasksCount] = new Task(statements);
                tasksCount += 1;
                System.out.println("Adding new tasks to the list");
                System.out.println("added: " + statements);
                System.out.println("------------------------------------------");
            }
            statements = input.nextLine();

        }
        System.out.println("------------------------------------------");
        System.out.println("Bye, Hope to see you soon");
        System.out.println("------------------------------------------");
    }
}
