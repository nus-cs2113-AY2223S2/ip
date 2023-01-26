import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        System.out.println("<(0v0)/ Hey! Liri here!" + System.lineSeparator() + "<(-v-)> How can I help you?");
        ArrayList<Task> todoList = new ArrayList<Task>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("|(*O*)| Here's the tasks I remembered:");
                for (Task item: todoList) {
                    item.printTask();
                }
            } else {
                String[] words = line.split(" ");
                if (words[0].equals("mark")) {
                    int indexToMark = Integer.parseInt(words[1]) - 1;
                    todoList.get(indexToMark).markAsDone();
                    System.out.println("*^(^v^)^* Great! I have marked this task as done:");
                    todoList.get(indexToMark).printTask();
                } else if (words[0].equals("unmark")) {
                    int indexToMark = Integer.parseInt(words[1]) - 1;
                    todoList.get(indexToMark).markAsUndone();
                    System.out.println("_(@_@)_ Hmmm. I shall mark this task as not done:");
                    todoList.get(indexToMark).printTask();
                } else {
                    Task newTask = new Task(line);
                    todoList.add(newTask);
                    System.out.println("_(ovo)-| Added: " + newTask.getItemName());
                }
            }
            line = in.nextLine();
        }
        System.out.println("/(0A0)/ Bye. See you next time!");
    }
}
