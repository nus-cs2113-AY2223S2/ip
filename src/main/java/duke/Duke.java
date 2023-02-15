package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Good day. YodaBot, I am.");
        System.out.println("Assistance, you need?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskArray = new ArrayList<Task>();
        int taskIndex = 0;
        while (true) {
            String command = scanner.nextLine(); //reads in input
            //if command is bye, end program
            if (command.equals("bye")) {
                System.out.println("See you soon, I hope. Goodbye.");
                break;
            }
            String firstWord;
            String remainingWords;
            if (command.contains(" ")) {
                int firstSpaceIndex = command.indexOf(" ");
                firstWord = command.substring(0, firstSpaceIndex);
                remainingWords = command.substring(firstSpaceIndex + 1, command.length());
            } else {
                firstWord = command;
                remainingWords = null;
            }
            switch (firstWord) {
            //if command is list, either display empty or display list
            case "List":
                if (taskIndex == 0) {
                    System.out.println("Empty, list is.");
                } else {
                    System.out.println("As shown, list is:");
                    for (int j = 0; j < taskIndex; ++j) {
                        if (taskArray.get(j) instanceof ToDo) {
                            System.out.println(j + ". " + ((ToDo) taskArray.get(j)).getToDo() + " " + taskArray.get(j).getDoneStatus() + " " + taskArray.get(j).getDescription());
                        }
                        if (taskArray.get(j) instanceof Deadline) {
                            System.out.println(j + ". " + ((Deadline) taskArray.get(j)).getDeadline() + " " + taskArray.get(j).getDoneStatus() + " " + taskArray.get(j).getDescription() + " (" + ((Deadline) taskArray.get(j)).getDate() + ")");
                        }
                        if (taskArray.get(j) instanceof Event) {
                            System.out.println(j + ". " + ((Event) taskArray.get(j)).getEvent() + " " + taskArray.get(j).getDoneStatus() + " " + taskArray.get(j).getDescription() + " (" + ((Event) taskArray.get(j)).getStartAndEnd() + ")");
                        }
                    }
                }
                break;
            //if command is mark, mark the appropriate entry in the list
            case "Mark":
                int indexToMark = Integer.parseInt(remainingWords);
                if (taskIndex == 0 || indexToMark > taskIndex) {
                    System.out.println("In list, it is not.");
                } else {
                    taskArray.get(indexToMark).isDone = true;
                    System.out.println("Marked it, I have:");
                    if (taskArray.get(indexToMark) instanceof ToDo) {
                        System.out.println(indexToMark + ". " + ((ToDo) taskArray.get(indexToMark)).getToDo() + " " + taskArray.get(indexToMark).getDoneStatus() + " " + taskArray.get(indexToMark).getDescription());
                    }
                    if (taskArray.get(indexToMark) instanceof Deadline) {
                        System.out.println(indexToMark + ". " + ((Deadline) taskArray.get(indexToMark)).getDeadline() + " " + taskArray.get(indexToMark).getDoneStatus() + " " + taskArray.get(indexToMark).getDescription() + "(" + ((Deadline) taskArray.get(indexToMark)).getDate() + ")");
                    }
                    if (taskArray.get(indexToMark) instanceof Event) {
                        System.out.println(indexToMark + ". " + ((Event) taskArray.get(indexToMark)).getEvent() + " " + taskArray.get(indexToMark).getDoneStatus() + " " + taskArray.get(indexToMark).getDescription() + " (" + ((Event) taskArray.get(indexToMark)).getStartAndEnd() + ")");
                    }
                }
                break;
            //if command is unmark, unmark the appropriate entry in the list
            case "Unmark":
                int indexToUnmark = Integer.parseInt(remainingWords);
                if (taskIndex == 0 || indexToUnmark > taskIndex) {
                    System.out.println("In list, it is not.");
                    break;
                } else {
                    taskArray.get(indexToUnmark).isDone = false;
                    System.out.println("Unmarked it, I have:");
                    if (taskArray.get(indexToUnmark) instanceof ToDo) {
                        System.out.println(indexToUnmark + ". " + ((ToDo) taskArray.get(indexToUnmark)).getToDo() + " " + taskArray.get(indexToUnmark).getDoneStatus() + " " + taskArray.get(indexToUnmark).getDescription());
                    }
                    if (taskArray.get(indexToUnmark) instanceof Deadline) {
                        System.out.println(indexToUnmark + ". " + ((Deadline) taskArray.get(indexToUnmark)).getDeadline() + " " + taskArray.get(indexToUnmark).getDoneStatus() + " " + taskArray.get(indexToUnmark).getDescription() + " (" + ((Deadline) taskArray.get(indexToUnmark)).getDate() + ")");
                    }
                    if (taskArray.get(indexToUnmark) instanceof Event) {
                        System.out.println(indexToUnmark + ". " + ((Event) taskArray.get(indexToUnmark)).getEvent() + " " + taskArray.get(indexToUnmark).getDoneStatus() + " " + taskArray.get(indexToUnmark).getDescription() + " (" + ((Event) taskArray.get(indexToUnmark)).getStartAndEnd() + ")");
                    }
                }
                break;
            //if command is todo, prompt for task and add into list
            case "Todo":
                try {
                    if (remainingWords == null) {
                        throw new IllegalCommandException();
                    }
                    Task newTask = new ToDo(remainingWords, false, "[T]");
                    taskArray.add(newTask);
                    System.out.println(taskIndex + ". " + ((ToDo) taskArray.get(taskIndex)).getToDo() + " " + taskArray.get(taskIndex).getDoneStatus() + " " + taskArray.get(taskIndex).getDescription() + " - Added, I have");
                    ++taskIndex;
                } catch (IllegalCommandException e) {
                    System.out.println("Error: To do what, I ask?");
                    break;
                }
                break;
            //if command is Deadline, prompt for task then deadline and add into list
            case "Deadline":
                int demarcator = remainingWords.indexOf("/");
                String work = remainingWords.substring(0, demarcator - 1);
                String date = remainingWords.substring(demarcator + 1, remainingWords.length());
                Task newTask = new Deadline(work, false, "[D]", date);
                taskArray.add(newTask);
                System.out.println(taskIndex + ". " + ((Deadline) taskArray.get(taskIndex)).getDeadline() + " " + taskArray.get(taskIndex).getDoneStatus() + " " + taskArray.get(taskIndex).getDescription() + " " + ((Deadline) taskArray.get(taskIndex)).getDate() + " - Added, I have");
                ++taskIndex;
                break;
            //if command is Event, prompt for event, then prompt for start and end date
            case "Event":
                demarcator = remainingWords.indexOf("/");
                work = remainingWords.substring(0, demarcator - 1);
                date = remainingWords.substring(demarcator + 1, remainingWords.length());
                Task newTask1 = new Event(work, false, "[E]", date);
                taskArray.add(newTask1);
                System.out.println(taskIndex + ". " + ((Event) taskArray.get(taskIndex)).getEvent() + " " + taskArray.get(taskIndex).getDoneStatus() + " " + taskArray.get(taskIndex).getDescription() + " " + ((Event) taskArray.get(taskIndex)).getStartAndEnd() + " - Added, I have");
                ++taskIndex;
                break;
            case "Delete":
                int indexToDelete = Integer.parseInt(remainingWords);
                if (taskIndex == 0 || indexToDelete > taskIndex) {
                    System.out.println("In list, it is not.");
                } else {
                    taskArray.remove(indexToDelete);
                    taskIndex -= 1;
                    System.out.println("Deleted, I have");
                }
                break;
            //throws IllegalCommandException if the input is not one of the above cases
            default:
                try {
                    throw new IllegalCommandException();
                } catch (IllegalCommandException e1) {
                    System.out.println("Error: Understand, I do not.");
                }
            }
        }
    }
}
