import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        String desc = scan.nextLine();
        String output = new String();
        while (!desc.equals("bye")) {
            if (desc.equals("list")) {
                int a = 1;
                output="Here are the tasks in the list:"+System.lineSeparator()+Task.printTasksList(tasks);
                System.out.println("Here are the tasks in the list:");
                for (Task i : tasks) {
                    System.out.println(a + ". " + i.toString());
                    a++;
                }
            } else if (desc.matches("mark [0-9]{1,2}")) {
                String[] marks = desc.split(" ");
                tasks.get(Integer.parseInt(marks[1]) - 1).markAsDone();
                output = "Nice! I've marked this task as done:" + System.lineSeparator()
                        + tasks.get(Integer.parseInt(marks[1]) - 1).toString();
            } else if (desc.matches("unmark [0-9]{1,2}")) {
                String[] marks = desc.split(" ");
                tasks.get(Integer.parseInt(marks[1]) - 1).markAsNotDone();
                output = "OK, I've marked this task as not done yet:" + System.lineSeparator()
                        + tasks.get(Integer.parseInt(marks[1]) - 1).toString();
            } else if (desc.startsWith("todo", 0)) {
                String toDoDesc = desc.split("todo")[1].trim();
                Task toDo = new Todo(toDoDesc);
                tasks.add(toDo);
                output = "Got it. I've added this task:" + System.lineSeparator()
                        + toDo.toString() + System.lineSeparator() + "Now you have " + Task.numberOfTasks
                        + " in the list.";
            } else if (desc.startsWith("deadline", 0)) {
                String deadlineDesc = desc.split("/")[0].split("deadline")[1].trim();
                String deadlineDay = desc.split("/")[1].trim();
                Task deadline = new Deadline(deadlineDesc, deadlineDay);
                tasks.add(deadline);
                output = "Got it. I've added this task:" + System.lineSeparator()
                        + deadline.toString() + System.lineSeparator() + "Now you have " + Task.numberOfTasks
                        + " in the list.";
            } else if (desc.startsWith("event", 0)) {
                String eventDesc = desc.split("/")[0].split("event")[1].trim();
                String start = desc.split("/")[1].trim();
                String end = desc.split("/")[2].trim();
                Task event = new Event(eventDesc, start, end);
                tasks.add(event);
                output = "Got it. I've added this task:" + System.lineSeparator()
                        + event.toString() + System.lineSeparator() + "Now you have " + Task.numberOfTasks
                        + " in the list.";
            } else {
                Task task = new Task(desc);
                tasks.add(task);
                output = "Got it. I've added this task:" + System.lineSeparator()
                        + task.toString() + System.lineSeparator() + "Now you have " + Task.numberOfTasks
                        + " in the list.";
            }
            System.out.println("____________________________________________________");
            System.out.println(output);
            System.out.println("____________________________________________________");
            desc = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

}
