import java.util.Scanner;
import java.util.ArrayList;

public class
Duke {
    public static boolean containsNumbers(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isDigit(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________\n";

        String greeting = (line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line);

        String goodBye = (line + "Bye. Hope to see you again soon!\n" + line);

        System.out.println(greeting);
        Scanner myObj = new Scanner(System.in);
        String userInput;
        String userInputParts[];
        String dummy;
        userInput = myObj.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>(100);

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (Task item : taskList) {
                    System.out.print((taskList.indexOf(item) + 1) + ".");
                    System.out.println(item.toString());
                }
                System.out.println(line);
            }

            //For marking and unmarking items in the list
            else if(userInput.contains("mark")) {
                int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                if (userInput.contains("unmark")) {
                    taskList.get(itemNumber).markAsUnDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "    "
                            + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription());
                    System.out.println(line);
                } else {
                    taskList.get(itemNumber).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "    "
                            + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription());
                    System.out.println(line);
                }
            } else if (userInput.contains("event")){
                userInput = userInput.replace("event","");
                userInputParts = userInput.split("/");
                Event event = new Event(userInputParts[0], userInputParts[1], userInputParts[2]);
                taskList.add(event);
                System.out.println("Got it. I've added this task: ");
                System.out.println("[E][ ] " + event.description + "(" + event.from + event.to + ")");
                System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
                System.out.println(line);
            } else if (userInput.contains("deadline")) {
                userInput = userInput.replace("deadline","");
                userInputParts = userInput.split("/");
                Deadline deadline = new Deadline(userInputParts[0], userInputParts[1]);
                taskList.add(deadline);
                System.out.println("[D][ ] " + deadline.description + "(" + userInputParts[1] + ")");
                System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
                System.out.println(line);
            } else if (userInput.contains("todo")){
                userInput = userInput.replace("todo","");
                Task task = new Task(userInput);
                taskList.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println("[T][ ] " + task.description);
                System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
                System.out.println(line);
            } else {
                System.out.println("Please enter a valid command!");
                System.out.println(line);
            }

            userInput = myObj.nextLine();
        }
        System.out.println(goodBye);
    }
}