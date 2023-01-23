import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke " + System.lineSeparator() + "What can I do for you?");
        ArrayList<Task> todoList = new ArrayList<Task>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("Here's the current list of tasks:");
                for (Task item: todoList) {
                    item.printTask();
                }
            } else {
                String[] words = line.split(" ");
                if (words[0].equals("mark")) {
                    int indexToMark = Integer.parseInt(words[1]) - 1;
                    todoList.get(indexToMark).markAsDone();
                    System.out.println("Great! I have marked this task as done:");
                    todoList.get(indexToMark).printTask();
                } else if (words[0].equals("unmark")) {
                    int indexToMark = Integer.parseInt(words[1]) - 1;
                    todoList.get(indexToMark).markAsUndone();
                    System.out.println("Hmmm! I will mark this task as not done:");
                    todoList.get(indexToMark).printTask();
                } else {
                    Task newTask = new Task(line);
                    todoList.add(newTask);
                    System.out.println("Added: " + line);
                }
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
