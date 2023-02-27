import java.util.Scanner;  // Import the Scanner class
import java.util.Arrays;
public class Duke {

    public static void drawLine() {  // Draw horizontal lines
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void hiDuke() {  // Print logo and hello message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void byeDuke() {  // Print goodbye message
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void listTasks(Task[] tasks, int curPos) {  // List out all the tasks
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < curPos; i++) {
            System.out.println((i+1) + ". " + tasks[i].getType() + tasks[i].toString());
        }
        drawLine();
    }

    public static void markStatus(String input, Task[] tasks) {  // Mark tasks as done/undone
        String[] arrOfInput = input.split(" ", 2);
        int index = Integer.parseInt(arrOfInput[1]) - 1;  //index = curPos + 1

        if(input.startsWith("mark")){
            tasks[index].isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        else {
            tasks[index].isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println("   " + tasks[index].getType() + tasks[index].getStatusIcon() + tasks[index].description);
        drawLine();
    }

    public static int updateTasks(String input, Task[] tasks, int curPos) {  // Include remarks
        String[] arrOfInput = input.split(" ", 2);
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";

        System.out.println("Got it. I've added this task:");

        if (arrOfInput[0].equals(todo)) {
            tasks[curPos] = new Task(arrOfInput[1]);
        }
        else if (arrOfInput[0].equals(deadline)) {
            String[] arrOfTask = arrOfInput[1].split("/by");
            tasks[curPos] = new Deadline(arrOfTask[0], arrOfTask[1]);
        }
        else {
            String[] arrOfTask = arrOfInput[1].split("/from");
            String[] arrOfEvent = arrOfTask[1].split("/to");
            tasks[curPos] = new Event(arrOfTask[0], arrOfEvent[0], arrOfEvent[1]);
        }

        System.out.println("   " + tasks[curPos].getType() + tasks[curPos].toString());
        curPos++;

        if(curPos == 1) {
            System.out.println("Now you have " + curPos + " task in the list.");
        }
        else {
            System.out.println("Now you have " + curPos + " tasks in the list.");
        }

        drawLine();
        return curPos;
    }

    public static void main(String[] args) {
        hiDuke();  //Print Logo

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Task tasks[] = new Task[100];
        int curPos = 0;

        while(!(input.isEmpty())) {
            drawLine();
            if(input.equals("bye")) {
                break;
            }
            else if(input.equals("list")) {
                listTasks(tasks, curPos);
            }
            else if(input.startsWith("mark") || input.startsWith("unmark")){
                markStatus(input, tasks);
            }
            else if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
                curPos = updateTasks(input, tasks, curPos);
            }
            input = scan.nextLine();
        }

        byeDuke();

    }

}
