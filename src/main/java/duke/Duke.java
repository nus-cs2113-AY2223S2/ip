package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Good day. YodaBot, I am.");
        System.out.println("Assistance, you need?");
        Scanner scanner = new Scanner(System.in);
        Task[] taskArray = new Task[100];
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
                        if (taskArray[j] instanceof ToDo) {
                            System.out.println(j + ". " + ((ToDo) taskArray[j]).getToDo() + " " + taskArray[j].getDoneStatus() + " " + taskArray[j].getDescription());
                        }
                        if (taskArray[j] instanceof Deadline) {
                            System.out.println(j + ". " + ((Deadline) taskArray[j]).getDeadline() + " " + taskArray[j].getDoneStatus() + " " + taskArray[j].getDescription() + " (" + ((Deadline) taskArray[j]).getDate() + ")");
                        }
                        if (taskArray[j] instanceof Event) {
                            System.out.println(j + ". " + ((Event) taskArray[j]).getEvent() + " " + taskArray[j].getDoneStatus() + " " + taskArray[j].getDescription() + " (" + ((Event) taskArray[j]).getStartAndEnd() + ")");
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
                    taskArray[indexToMark].isDone = true;
                    System.out.println("Marked it, I have:");
                    if (taskArray[indexToMark] instanceof ToDo) {
                        System.out.println(indexToMark + ". " + ((ToDo) taskArray[indexToMark]).getToDo() + " " + taskArray[indexToMark].getDoneStatus() + " " + taskArray[indexToMark].getDescription());
                    }
                    if (taskArray[indexToMark] instanceof Deadline) {
                        System.out.println(indexToMark + ". " + ((Deadline) taskArray[indexToMark]).getDeadline() + " " + taskArray[indexToMark].getDoneStatus() + " " + taskArray[indexToMark].getDescription() + "(" + ((Deadline) taskArray[indexToMark]).getDate() + ")");
                    }
                    if (taskArray[indexToMark] instanceof Event) {
                        System.out.println(indexToMark + ". " + ((Event) taskArray[indexToMark]).getEvent() + " " + taskArray[indexToMark].getDoneStatus() + " " + taskArray[indexToMark].getDescription() + " (" + ((Event) taskArray[indexToMark]).getStartAndEnd() + ")");
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
                    taskArray[indexToUnmark].isDone = false;
                    System.out.println("Unmarked it, I have:");
                    if (taskArray[indexToUnmark] instanceof ToDo) {
                        System.out.println(indexToUnmark + ". " + ((ToDo) taskArray[indexToUnmark]).getToDo() + " " + taskArray[indexToUnmark].getDoneStatus() + " " + taskArray[indexToUnmark].getDescription());
                    }
                    if (taskArray[indexToUnmark] instanceof Deadline) {
                        System.out.println(indexToUnmark + ". " + ((Deadline) taskArray[indexToUnmark]).getDeadline() + " " + taskArray[indexToUnmark].getDoneStatus() + " " + taskArray[indexToUnmark].getDescription() + " (" + ((Deadline) taskArray[indexToUnmark]).getDate() + ")");
                    }
                    if (taskArray[indexToUnmark] instanceof Event) {
                        System.out.println(indexToUnmark + ". " + ((Event) taskArray[indexToUnmark]).getEvent() + " " + taskArray[indexToUnmark].getDoneStatus() + " " + taskArray[indexToUnmark].getDescription() + " (" + ((Event) taskArray[indexToUnmark]).getStartAndEnd() + ")");
                    }
                }
                break;
            //if command is todo, prompt for task and add into list
            case "Todo":
                try {
                    if (remainingWords == null) {
                        throw new IllegalCommandException();
                    }
                    taskArray[taskIndex] = new ToDo(remainingWords, false, "[T]");
                    System.out.println(taskIndex + ". " + ((ToDo) taskArray[taskIndex]).getToDo() + " " + taskArray[taskIndex].getDoneStatus() + " " + taskArray[taskIndex].getDescription() + " - Added, I have");
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
                taskArray[taskIndex] = new Deadline(work, false, "[D]", date);
                System.out.println(taskIndex + ". " + ((Deadline) taskArray[taskIndex]).getDeadline() + " " + taskArray[taskIndex].getDoneStatus() + " " + taskArray[taskIndex].getDescription() + " " + ((Deadline) taskArray[taskIndex]).getDate() + " - Added, I have");
                ++taskIndex;
                break;
            //if command is Event, prompt for event, then prompt for start and end date
            case "Event":
                demarcator = remainingWords.indexOf("/");
                work = remainingWords.substring(0, demarcator - 1);
                date = remainingWords.substring(demarcator + 1, remainingWords.length());
                taskArray[taskIndex] = new Event(work, false, "[E]", date);
                System.out.println(taskIndex + ". " + ((Event) taskArray[taskIndex]).getEvent() + " " + taskArray[taskIndex].getDoneStatus() + " " + taskArray[taskIndex].getDescription() + " " + ((Event) taskArray[taskIndex]).getStartAndEnd() + " - Added, I have");
                ++taskIndex;
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
