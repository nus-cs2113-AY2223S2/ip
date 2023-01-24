import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = ("─".repeat(50));
        Task[] List = new Task[100];

        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        Scanner myObj = new Scanner(System.in);
        int index = 0;

        while (true) {
            String userInput = myObj.next();
            switch (userInput) {
            case "bye":
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            case "list":
                System.out.println(line);
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + "[" + List[i].getStatusIcon() + "] " + List[i].getTaskDescription());
                }
                System.out.println(line);
                break;
            case "mark":
            case "unmark":
                System.out.println(line);
                if (myObj.hasNextInt()) {
                    int taskNumber = myObj.nextInt();
                    if (userInput.equals("mark")) {
                        List[taskNumber - 1].setDone(true);
                        System.out.println("Nice! I've marked this task as done:\n");
                    } else {
                        List[taskNumber - 1].setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:\n");
                    }
                    System.out.println("[" + List[taskNumber - 1].getStatusIcon() + "] " + List[taskNumber - 1].getTaskDescription());
                    System.out.println(line);
                } else {
                    String taskDescription = userInput + " " + myObj.next();
                    List[index] = new Task(taskDescription);
                    List[index].setTaskDescription(taskDescription);
                    index++;
                    System.out.println("added: " + taskDescription);
                    System.out.println(line);
                }
                break;
            default:
                System.out.println(line);
                String taskDescription = userInput + myObj.nextLine();
                List[index] = new Task(taskDescription);
                List[index].setTaskDescription(taskDescription);
                index++;
                System.out.println("added: " + taskDescription);
                System.out.println(line);
            }
        }
    }
}
