package Duke;

import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;
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
        System.out.println("Hello! I'm Duke.Duke");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void byeDuke() {  // Print goodbye message
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void listTasks(ArrayList<Task> tasks) {  // List out all the tasks
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getType() + tasks.get(i).toString());
        }
        drawLine();
    }

    public static void markStatus(String input, ArrayList<Task> tasks) {  // Mark tasks as done/undone
        String[] arrOfInput = input.split(" ", 2);
        int index = Integer.parseInt(arrOfInput[1]);
        index = index - 1;

        if(input.startsWith("mark")){
            tasks.get(index).isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        else {
            tasks.get(index).isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println("   " + tasks.get(index).getType() + tasks.get(index).toString());
        drawLine();
    }

    public static int updateTasks(String input, ArrayList<Task> tasks, int curPos) {  // Include remarks
        String[] arrOfInput = input.split(" ", 2);
        System.out.println("Got it. I've added this task:");

        if (arrOfInput[0].equals("todo")) {
            tasks.add(new Task(arrOfInput[1]));
        }
        else if (arrOfInput[0].equals("deadline")) {
            String[] arrOfTask = arrOfInput[1].split("/by");
            tasks.add(new Deadline(arrOfTask[0], arrOfTask[1]));
        }
        else {
            String[] arrOfTask = arrOfInput[1].split("/from");
            String[] arrOfEvent = arrOfTask[1].split("/to");
            tasks.add(new Event(arrOfTask[0], arrOfEvent[0], arrOfEvent[1]));
        }

        System.out.println("   " + tasks.get(curPos).getType() + tasks.get(curPos).toString());
        curPos++;
        System.out.println("Now you have " + curPos + (curPos > 1 ? " tasks " : " task ") + "in the list.");
        drawLine();
        return curPos;
    }

    // @@[yuanners] [aaronxujiachen]-reused
    // With minor modification
    public static int checkInput(String input, ArrayList<Task> tasks, int curPos) throws DukeException {

        if(input.length () > 4) {
            if(input.startsWith("mark") || input.startsWith("unmark")) {
                markStatus(input, tasks);
            }
            else if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                curPos = updateTasks(input, tasks, curPos);
            }
            else if(input.startsWith("delete")) {
                curPos = deleteTask (input, tasks, curPos);
            }
            else if(!((input.startsWith("mark")) || (input.startsWith("unmark")) || (input.startsWith("todo")) || (input.startsWith("deadline")) || (input.startsWith("event")) || (input.startsWith("delete")))) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            else{
                throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }
        }
        else {
            if(input.equals("list")) {
                listTasks(tasks);
            }
            else if(input.equals("mark") || input.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        return curPos;
    }
    // @@[yuanners]

    public static int deleteTask (String input, ArrayList<Task> tasks, int curPos) {
        String[] arrOfInput = input.split (" ", 2);
        int index = Integer.parseInt(arrOfInput[1]);
        index = index - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("	" + tasks.get(index).getType() + tasks.get(index).toString());
        tasks.remove(index);
        curPos--;
        System.out.println("Now you have " + curPos + " tasks in the list.");
        drawLine();
        return curPos;
    }

    public static void main(String[] args) {
        hiDuke();  //Print Logo

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Task> tasks = new ArrayList<Task>();
        int curPos = 0;

        while(!(input.isEmpty())) {
            drawLine();
            if(input.equals("bye")) {
                break;
            }
            else {
                try {
                    curPos = checkInput(input, tasks, curPos);
                }
                catch(DukeException duEx) {
                    System.out.println(duEx.getMessage());
                    drawLine();
                    continue;
                }
                finally {
                    input = scan.nextLine();
                }
            }
        }

        byeDuke();
    }
}
