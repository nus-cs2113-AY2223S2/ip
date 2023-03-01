package duke.utils;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todos;

import java.util.ArrayList;

import static duke.utils.Ui.*;

public class TaskList {
    Ui ui = new Ui();
    public static ArrayList<Task> list = new ArrayList<>();
    public static int currentTaskNum = 0;
    public void printList(int currentTaskNum) {
        int currentPrintedTask = 0;
        int placeHolder = currentTaskNum;
        System.out.println(LINE);
        if (placeHolder == 0) {
            System.out.println("No Task! 〲⚆ﻌ⚆〉");
        } else {
            while (placeHolder > 0) {
                System.out.println(currentPrintedTask + 1 + ". " + list.get(currentPrintedTask).toString());
                currentPrintedTask++;
                placeHolder--;
            }
        }
        System.out.println(LINE);
    }
    public void addEvent(String content) {
        String[] phrases;
        phrases = content.split("/");
        if (phrases.length < 3) {
            ui.printErrorMessage(EVENT_TIME_ERROR_MESSAGE);
        } else {
            list.add(new Event(phrases[0], phrases[1], phrases[2]));
            //processAddTaskRequest();
        }
    }

    public void addDeadline(String content) {
        String[] phrases;
        phrases = content.split("/by ");
        if (phrases.length < 2) {
            ui.printErrorMessage(DEADLINE_TIME_ERROR_MESSAGE);
        } else {
            list.add(new Deadline(phrases[0], phrases[1]));
            //processAddTaskRequest();
        }
    }

    public void addTodo(String content) {
        list.add(new Todos(content));
        //processAddTaskRequest();
    }


//    public void processAddTaskRequest() {
//        currentTaskNum++;
//        printTotalTasks(currentTaskNum);
//        System.out.println(LINE);
//    }
//
    public void deleteTask(String content) {
        list.remove(Integer.parseInt(content) - 1);
        currentTaskNum--;
    }

    public void toggleMark(String content, boolean shouldMarkAsDone) throws DukeException {
        int posOfMark = Integer.parseInt(content) - 1;
        if (!(posOfMark >= 0 && posOfMark <= currentTaskNum)) {
            ui.printErrorMessage(INVALID_NUM_ERROR_MESSAGE);
            throw new DukeException();
        } else {
            if (shouldMarkAsDone) {
                list.get(posOfMark).markAsDone();
            } else {
                list.get(posOfMark).markAsUndone();
            }
        }
    }

    public void printAddTaskMessage() {
        System.out.println(LINE + "Got it. I've added this task:\n"
                + "  "
                + TaskList.list.get(TaskList.currentTaskNum).toString()
                + System.lineSeparator());
        currentTaskNum++;
        printTotalTasks(currentTaskNum);
    }

    public void printDeleteTaskMessage(int taskNum) {
        System.out.println(LINE + "Got it. I've deleted this task:\n"
                + "  "
                + TaskList.list.get(taskNum).toString()
                + System.lineSeparator());
    }

    public void printTotalTasks(int currentTaskNum) {
        if (currentTaskNum == 1) {
            System.out.println("Now you have " + currentTaskNum + " task in the list.");
        } else {
            System.out.println("Now you have " + currentTaskNum + " tasks in the list.");
        }
    }

}
