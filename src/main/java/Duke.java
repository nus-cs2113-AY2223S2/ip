import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = ("\u2500".repeat(50));
        Task[] List = new Task[100];

        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        Scanner myObj = new Scanner(System.in);
        int index = 0;

        while (true) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + List[i].getTaskDescription());
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                List[index] = new Task(userInput);
                List[index].setTaskDescription(userInput);
                index++;
                System.out.println("added: " + userInput);
                System.out.println(line);
            }
        }
    }
}
