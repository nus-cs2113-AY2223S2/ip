package DukeMain;

import ErrorHandling.ErrorHandler;
import FileIO.DukeFile;
import TaskItems.Deadline;
import TaskItems.Event;
import TaskItems.Todos;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String parseEventString(String input) {
        int startIndex = input.indexOf("from") + 5;
        int endStartIndex = input.indexOf("to");
        int DeadlineIndex = input.indexOf("/to") + 4;
        String start = input.substring(startIndex, endStartIndex - 1).trim();
        String end = input.substring(DeadlineIndex).trim();
        input = input.substring(input.indexOf(" ") + 1, input.indexOf('/') - 1);
        input += ("  (" + "from: " + start + " to: " + end + ")");
        return input;
    }


    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        boolean justStarted = true;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what can i do for you");
        String input = myObj.nextLine();


        //File management segment
        File file = new File("Data.txt");
        ArrayList<Todos> todoItems;
        if (file.exists()) {
            todoItems = DukeFile.loadListFromFile("Data.txt");
        } else {
            todoItems = new ArrayList<>();
        }
        //end of file management segment

        int new_tasks = 0;
        while (!input.equalsIgnoreCase("bye")) {
            if (!ErrorHandler.isInputValid(input)) {
                input = myObj.nextLine();
                continue;
            }
            if (input.equalsIgnoreCase("list")) {
                System.out.println("-------TODO-LIST------");
                System.out.println("----------------------");
                for (int i = 0; i < todoItems.size(); i++) {
                    char x = ' ';
                    if (todoItems.get(i).isMarked) {
                        x = 'X';
                    }
                    System.out.println(i + 1 + ". " + "[" + todoItems.get(i).type + "]" + "[" + x + "] " + todoItems.get(i).item);
                }
                System.out.println("----------------------");
            } else if (input.startsWith("mark")) {
                int i = Integer.parseInt(input.substring(5));
                todoItems.get(i - 1).setMark();
                System.out.println("Item has been marked");
                System.out.println(i + ". " + "[" + todoItems.get(i - 1).type + "]" + "[X] " + todoItems.get(i-1).item);
                DukeFile.WriteToFile(todoItems);
            } else if (input.startsWith("unmark")) {
                int i = Integer.parseInt(input.substring(7));
                todoItems.get(i - 1).unMark();
                System.out.println("Item has been unmarked");
                System.out.println(i + ". " + "[" + todoItems.get(i - 1).type + "]" + "[ ] " + todoItems.get(i - 1).item);
                DukeFile.WriteToFile(todoItems);
            } else if (input.startsWith("event")) {
                int count = new_tasks + 1;
                input = parseEventString(input);
                System.out.println("Got it, ive done the Following");
                System.out.println("Added: " + input);
                System.out.println("now you have: " + count + " tasks in this list.");
                Event x = new Event(input, false, "E");
                todoItems.add(x);
                DukeFile.WriteToFile(todoItems);
                new_tasks++;
            } else if (input.startsWith("deadline")) {
                int count = new_tasks + 1;
                String date = input.substring(input.indexOf("/by") + 4);
                input = input.substring(input.indexOf(" ") + 1, input.indexOf('/') - 1) + " (by: " + date + ")";
                System.out.println("Got it, ive done the Following");
                System.out.println("Added: " + input);
                System.out.println("now you have: " + count + " tasks in this list.");
                Deadline x = new Deadline(input, false, "D");
                todoItems.add(x);
                DukeFile.WriteToFile(todoItems);
                new_tasks++;
            } else if (input.startsWith("todo")) {
                Todos x = new Todos(input.substring(input.indexOf((" "))), false, "T");
                todoItems.add(x);
                System.out.println("Got it, Ive done the Following!");
                System.out.println("Added: " + "[" + todoItems.get(new_tasks).type + "]" + "[ ]" + input.substring(input.indexOf((" "))));
                int count = new_tasks + 1;
                System.out.println("now you have: " + count + " tasks in this list.");
                DukeFile.WriteToFile(todoItems);
                new_tasks++;
            } else if(input.startsWith("delete")) {
                System.out.println("i have deleted the task:" + todoItems.get(Integer.parseInt(input.substring(7))-1).item);
                todoItems.remove(Integer.parseInt(input.substring(7))-1);
                DukeFile.WriteToFile(todoItems);
            }else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            input = myObj.nextLine();

        }
        System.out.println("Bye! see you soon!");

    }
}
