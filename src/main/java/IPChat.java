import java.util.Scanner;

public class IPChat {
    public static void main(String[] args) {

        // Level 3: Mark as done
        System.out.println("Hello I'm IPChat, What can I do for you");
        System.out.println("------------------------------------------");

        Scanner input =  new Scanner(System.in);
        String statement = input.nextLine();
        Task[] tasks = new Task [100];
        int tasks_count = 0;

        while(!statement.equals("bye")) {
            System.out.println("------------------------------------------");
            if (statement.equals("list")) {
                System.out.println("Here is the list of tasks for the day! All the best :) \n");
                for (int i = 0; i < tasks_count; i += 1) {
                    System.out.println((i + 1) + "." +"[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("------------------------------------------");
            } else if (statement.contains("mark")) {
                System.out.println("I have marked the task as done");
                int index = Integer.parseInt(statement.substring(statement.length()-1)) - 1;
                tasks[index].markAsDone();
                System.out.println("------------------------------------------");
            } else {
                tasks[tasks_count] = new Task(statement);
                tasks_count += 1;
                System.out.println("Adding new tasks to the list");
                System.out.println("added: " + statement);
                System.out.println("------------------------------------------");
            }
            statement = input.nextLine();

        }
        System.out.println("------------------------------------------");
        System.out.println("Bye, Hope to see you soon");
        System.out.println("------------------------------------------");
    }
}
