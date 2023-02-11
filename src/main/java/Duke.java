import java.util.Scanner;
import java.util.Arrays;
import duke.Task;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    private static String filePath = "data/duke.txt";
    private static ArrayList<Task> textList = new ArrayList<Task>(); //store in array of Tasks to track both description and mark status
    public static void printLine() { //print line
        System.out.println("______________________________________________________________");
    }
    private static void checkEmpty(String lowerCaseLine) throws DukeException { //check if description is empty
        if (lowerCaseLine.equals("todo") || lowerCaseLine.equals("deadline") || lowerCaseLine.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of a " + lowerCaseLine + " cannot be empty.");
        }
    }
    private static void checkCommand(String lowerCaseLine) throws DukeException { //check if command is valid
        if (!lowerCaseLine.equals("list") && !lowerCaseLine.equals("bye") && !lowerCaseLine.contains("mark") && !lowerCaseLine.contains("unmark") && !lowerCaseLine.contains("todo") && !lowerCaseLine.contains("deadline") && !lowerCaseLine.contains("event")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static void main(String[] args) throws DukeException, IOException, FileNotFoundException {
        String line;
        String lowerCaseLine;
        Scanner in = new Scanner(System.in);
        Save save = new Save(filePath);
        save.readFile(textList);
        int textListCount = textList.size();

        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        while(true) {
            line = in.nextLine();
            lowerCaseLine = line.toLowerCase();
            Task task = new Task(line);
            printLine();
            if (lowerCaseLine.equals("bye")) {
                save.saveFile(textList);
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                return;
            } else if (lowerCaseLine.equals("list")) { //if input is list
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < textListCount; ++i) {
                    String listLine = i+1 + ". " + textList.get(i).toString();
                    System.out.println(listLine);
                }
                printLine();
            } else if (lowerCaseLine.contains("mark") && !lowerCaseLine.contains("unmark")) { //mark is in unmark so need to check like this to avoid unmark being detected as mark
                int indexOfTask = Integer.parseInt(lowerCaseLine.substring(5)) - 1; //find index of the task number starting from 0
                textList.get(indexOfTask).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(" " + textList.get(indexOfTask).toString());
                printLine();
            } else if (lowerCaseLine.contains("unmark")) {
                int indexOfTask = Integer.parseInt(lowerCaseLine.substring(7)) - 1;
                textList.get(indexOfTask).unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(" " + textList.get(indexOfTask).toString());
                printLine();
            } else if (lowerCaseLine.contains("delete")) {
                int indexOfTask = Integer.parseInt(lowerCaseLine.substring(7)) - 1;
                System.out.println("Noted. I've removed this task: ");
                System.out.println(" " + textList.get(indexOfTask).toString());
                textList.remove(indexOfTask);
                textListCount--;
                System.out.println("Now you have " + textListCount + " tasks in the list.");
                printLine();
            }
            else { //if input is any other text
                if (lowerCaseLine.contains("todo")) {
                    try {
                        checkEmpty(lowerCaseLine);
                        task = new Todo(line.substring(5));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        printLine();
                        continue;
                    }
                } else if (lowerCaseLine.contains("deadline")) {
                    try {
                        checkEmpty(lowerCaseLine);
                        int indexOfBy = line.indexOf("/by");
                        task = new Deadline(line.substring(9, indexOfBy), line.substring(indexOfBy + 4));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        printLine();
                        continue;
                    }
                } else if (lowerCaseLine.contains("event")) {
                    try {
                        checkEmpty(lowerCaseLine);
                        int indexOfFrom = line.indexOf("/from");
                        int indexOfTo = line.indexOf("/to");
                        task = new Event(line.substring(6, indexOfFrom), line.substring(indexOfFrom + 6, indexOfTo), line.substring(indexOfTo + 4));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        printLine();
                        continue;
                    }
                }
                try {
                    checkCommand(lowerCaseLine);
                    textList.add(task); //add all input except for list, bye, mark and unmark to array of tasks
                    textListCount++; //counter to help input tasks into correct index in tasks array
                    save.saveFile(textList);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(" " + task.toString());
                    System.out.println("Now you have " + textListCount + " tasks in the list.");
                    printLine();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    printLine();
                    continue;
                }
            }
        }
    }
}
