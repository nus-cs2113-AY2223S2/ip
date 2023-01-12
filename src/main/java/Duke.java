import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Greetings.introduction();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            switch (input) {
                case "":
                    break;
                case "list":
                    System.out.println("\t------------------------------------------------------------");
                    if (tasks.size() == 0) { 
                        System.out.println("\tNo tasks added yet!"); 
                    }
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("\t%d. %s\n", i+1, tasks.get(i).getTaskName());
                    }
                    System.out.println("\t------------------------------------------------------------\n");
                    break;
                default:
                    tasks.add(new Task(input));
                    System.out.println("\t------------------------------------------------------------");
                    System.out.printf("\tadded: %s\n", input);
                    System.out.println("\t------------------------------------------------------------\n");
            }
            input = sc.nextLine();
        };

        Greetings.goodbye();
    }
}
