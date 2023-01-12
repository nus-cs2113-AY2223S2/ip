import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Greetings.introduction();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println("\t------------------------------------------------------------");
                if (tasks.size() == 0) { 
                    System.out.println("\tNo tasks added yet!"); 
                }
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t%d. ", i+1); TaskPrinter.printTask(tasks.get(i));
                }
                System.out.println("\t------------------------------------------------------------\n");
            } else if (input.contains("unmark")) {
                try {
                    int idx = Integer.parseInt(input.split(" ", 0)[1]) - 1;
                    tasks.get(idx).setDone(false);

                    System.out.println("\t------------------------------------------------------------");
                    System.out.println("\t OK, I've marked this task as not done yet:");
                    System.out.printf("\t\t"); TaskPrinter.printTask(tasks.get(idx));
                    System.out.println("\t------------------------------------------------------------\n");
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("An error occurred. Please use the syntax 'unmark x' to unmark task of index x\n");
                }
            } else if (input.contains("mark")) {
                try {
                    int idx = Integer.parseInt(input.split(" ", 0)[1]) - 1;
                    tasks.get(idx).setDone(true);

                    System.out.println("\t------------------------------------------------------------");
                    System.out.println("\t Nice! I've marked this task as done:");
                    System.out.printf("\t\t"); TaskPrinter.printTask(tasks.get(idx));
                    System.out.println("\t------------------------------------------------------------\n");
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("An error occurred. Please use the syntax 'mark x' to mark task of index x\n");
                }
            } else if (input.length() > 0) {
                boolean alreadyExists = false;
                for (Task t: tasks) {
                    if (t.getTaskName().equals(input)) {
                        alreadyExists = true;
                        break;
                    }
                }
                System.out.println("\t------------------------------------------------------------");
                if (alreadyExists) {
                    System.out.printf("\t%s already exists in tasks!\n", input);
                } else {
                    tasks.add(new Task(input));
                    System.out.printf("\tadded: %s\n", input);
                }
                System.out.println("\t------------------------------------------------------------\n");
            }

            input = sc.nextLine();
        };

        Greetings.goodbye();
    }
}
