import java.util.Scanner;


public class Duke {
    public static void main(String arguments[]) {
        int counter = 0;
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today?");
        Scanner command = new Scanner(System.in); // reading in inputs
        String input_Command = command.nextLine();
        Task task_Array[];
        task_Array = new Task[110];


        while (!"bye".equals(input_Command)) { // means if bye as input, immediately go to I look forward to seeing you again
            if ("list".equals(input_Command)) { // if list is an input
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i = i + 1) {
                    System.out.println((i + 1) + ". " + task_Array[i]);
                }
                command = new Scanner(System.in);
                input_Command = command.nextLine();
            } else if (input_Command.contains("mark")) {
                String[] tokens = input_Command.split(" ");
                int index = Integer.parseInt(tokens[1]) - 1;
                if (tokens[0].equals("mark")) {
                    task_Array[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task_Array[index]);
                } else {
                    task_Array[index].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task_Array[index]);
                }
                command = new Scanner(System.in); // re-read inputs again
                input_Command = command.nextLine();
            } else {
                task_Array[counter] = new Task(input_Command);
                System.out.println("added: " + input_Command); // added: read book
                command = new Scanner(System.in); // re-read inputs again
                input_Command = command.nextLine(); // return book
                counter = counter + 1;
            }
        }
        System.out.println("I look forward to seeing you again! Goodbye!");
    }
}