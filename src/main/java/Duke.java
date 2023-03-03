import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.DukeException;
import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        DukeStorage storage = new DukeStorage("data/duke.txt");
        Task.numberOfTasks = 0;
        ArrayList<Task> tasks = storage.loadTaskList();
        try (Scanner scan = new Scanner(System.in)) {
            String input = scan.nextLine();
            String output = new String();
            while (!input.equals("bye")) {
                try {
                    if (input.equals("list")) {
                        output = "Here are the tasks in the list:" + System.lineSeparator()
                                + Task.printTasksList(tasks);

                    } else if (input.startsWith("mark")) {
                        String[] marks = input.split(" ");
                        tasks.get(Integer.parseInt(marks[1]) - 1).markAsDone();
                        output = "Nice! I've marked this task as done:" + System.lineSeparator()
                                + tasks.get(Integer.parseInt(marks[1]) - 1).toString();
                    } else if (input.startsWith("unmark")) {
                        String[] marks = input.split(" ");
                        tasks.get(Integer.parseInt(marks[1]) - 1).markAsNotDone();
                        output = "OK, I've marked this task as not done yet:" + System.lineSeparator()
                                + tasks.get(Integer.parseInt(marks[1]) - 1).toString();
                    } else if (input.startsWith("todo", 0)) {
                        String toDoDesc = input.split("todo")[1].trim();
                        Task toDo = new Todo(toDoDesc);
                        tasks.add(toDo);
                        output = "Got it. I've added this task:" + System.lineSeparator()
                                + toDo.toString() + System.lineSeparator() + "Now you have " + Task.numberOfTasks
                                + " in the list.";
                    } else if (input.startsWith("deadline", 0)) {
                        String deadlineDesc = input.split("/")[0].split("deadline")[1].trim();
                        String deadlineDay = input.split("/")[1].trim();
                        Task deadline = new Deadline(deadlineDesc, deadlineDay);
                        tasks.add(deadline);
                        output = "Got it. I've added this task:" + System.lineSeparator()
                                + deadline.toString() + System.lineSeparator() + "Now you have " + Task.numberOfTasks
                                + " in the list.";
                    } else if (input.startsWith("event", 0)) {
                        String eventDesc = input.split("/")[0].split("event")[1].trim();
                        String start = input.split("/")[1].trim();
                        String end = input.split("/")[2].trim();
                        Task event = new Event(eventDesc, start, end);
                        tasks.add(event);
                        output = "Got it. I've added this task:" + System.lineSeparator()
                                + event.toString() + System.lineSeparator() + "Now you have " + Task.numberOfTasks
                                + " in the list.";
                    } else if (input.startsWith("delete")) {
                        int toDelete = Integer.parseInt(input.split(" ")[1]);
                        output = "Noted. I've removed this task:" + System.lineSeparator()
                                + tasks.get(toDelete - 1).toString();
                        tasks.remove(toDelete - 1);
                        Task.decrementNumberOfTasks();
                        output += System.lineSeparator() + "Now you have " + Task.numberOfTasks + " tasks in the list.";
                    } else {
                        output = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    output = "Please provide the necessary information for the task.";
                }

                System.out.println("___________________________________________________");
                System.out.println(output);
                System.out.println("___________________________________________________");
                input = scan.nextLine();
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Bye. Hope to see you again soon!");
        storage.saveTaskList(tasks);

    }

}
