import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        boolean isFinished = false;
        List toDoList = new List();

        String userInput;
        Scanner scan = new Scanner(System.in);

        while (!isFinished) {
            userInput = scan.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isFinished = true;
            } else if (userInput.equals("list")) {
                toDoList.printList();
            } else {
                toDoList.addTask(userInput);
                System.out.println("added: " + userInput);
            }
        }
    }
}
