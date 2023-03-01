package util;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class OutputUI {

    private static void printLine() {
        System.out.println("  ____________________________________________________________");
    }

    public void markTaskMessage(Task task) {
        System.out.println("Pikapi has marked the task as done\n");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println("Pikapi has unmarked the task\n");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
    }

    public void addToListMessage(Todo todo, int numTasks) {
        printLine();
        System.out.println("Pikapi add this task: " + "\n" + "  [T][ ]" + todo.description);
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    public void addToListMessage(Deadline deadline, int numTasks) {
        printLine();
        System.out.println("Pikapi add this task: " + "\n" + "  [D][ ]"
                + deadline.description + "(by :" + deadline.by + ")");
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    public void addToListMessage(Event event, int numTasks) {
        printLine();
        System.out.println("Pikapi add this task: " + "\n" + "  [E][ ] "
                + event.description + "(from: " + event.startDate + " to: " + event.endDate + ")");
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    public void printList(ArrayList<Task> tasks, int numTasks) {

        if (numTasks == 0) {
            System.out.println("Pikapi's list is completely empty, please add some tasks!");
        } else {
            System.out.println("Here is your list:");
            for (int i = 0; i < numTasks; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public void printDeleteTaskMessage(Task task, int numTasks) {
        printLine();
        System.out.println("Pikapi has deleted the task: " + "\n" + "  [T][" + task.getStatusIcon() + "]" + task.description);
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }


    public void printByeByeMessage() {
        System.out.println("Pikapi is surprised to see you go, see you soon friend\n");
    }

    public void printTaskListWithKeyword(ArrayList<Task> output){
        System.out.println("Pikapi has found  list of things pertaining to your keyword");
        printList(output, output.size());
    }

    public void printNoTaskWithKeyword(){
        System.out.println("No tasks with descriptions containing the keyword is found, Pikapi tried his best");
    }

}
