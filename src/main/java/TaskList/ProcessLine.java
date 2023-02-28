package TaskList;

import Storage.Storage;
import duke.Deadline;
import duke.Todo;
import duke.Event;
import duke.DukeException;
import duke.Task;
import java.util.ArrayList;
import Ui.Print;

public class ProcessLine {
    protected String line;
    protected ArrayList<Task> tasks;

    /**
     * Breaks line down to segment into different types of tasks.
     *
     * @param line is the user's input currently being analysed
     * @param tasks is the arraylist of tasks
     */

    public ProcessLine(String line, ArrayList<Task> tasks) {
        this.line = line;
        this.tasks = tasks;
    }

    /**
     * Finds the keyword among the task list and prints the tasks that match.
     *
     * @param i is the number of tasks in the task list currently
     * @param word is the keyword
     */
    public void find (int i,String word) {
        Print ui;
        ui=new Print();
        ArrayList<Task> foundTasks = new ArrayList<>();
        int count=0;


        for(int m=0;m<i;m+=1) {
            String taskName=tasks.get(m).taskDescription();
            if(taskName.toLowerCase().contains(word.toLowerCase())) {
                foundTasks.add(tasks.get(m));
                count+=1;
            }
        }
        ui.printFind(foundTasks,count);
    }

    /**
     * Edits the task in the task list depending on command given.
     *
     * @param index_for_edit the index that is given in the command on which task to edit
     * @param tasks is the arraylist of tasks
     * @param i is the number of tasks in the task list currently
     * @return the updated number of tasks in the task list currently
     */
    public int editTask( int index_for_edit, ArrayList<Task> tasks, int i) {

        Print ui;
        ui = new Print();

        if (line.toLowerCase().contains("unmark")) {
            tasks.get(index_for_edit - 1).markAsUnDone();

            ui.printUnMark(tasks, index_for_edit);

        } else if (line.toLowerCase().contains("mark")) {
            tasks.get(index_for_edit - 1).markAsDone();
            ui.printMark(tasks, index_for_edit);

        } else if (line.toLowerCase().contains("delete")) {
            ui.printDelete(tasks, index_for_edit, i);
            i -= 1;
            tasks.remove(tasks.get(index_for_edit - 1));
        }

        Storage Storage;
        Storage = new Storage();
        Storage.saveChanges(tasks, i);
        return i;

    }

    /**
     * Creates the task in the task list depending on command given and
     * adds the new task to the file.
     *
     * @param i is the number of tasks in the task list currently
     * @param line is the current command to follow when adding tasks
     * @return the updated number of tasks in the task list currently
     *
     */
    public int createTask(int i, String line) {
        Print ui;
        ui = new Print();

        boolean isEmpty;
        isEmpty = false;

        if (line.toLowerCase().contains("todo")) {
            String[] ToSplitTodo = line.split(" ");

            // Checks if the argument is empty
            try {
                String TodoTask = line.substring(4);
                tasks.add(new Todo(TodoTask, ToSplitTodo.length));

            } catch (DukeException ex) {
                isEmpty = true;
                ui.printTodoEmpty();
            }

            //sample : deadline return book /by Sunday
            //error if any field is missing: deadline __missing__ /by __missing__

        } else if (line.toLowerCase().contains("deadline")) {
            String[] ToSplitDeadline = line.split("/");
            String[] checkDescription=ToSplitDeadline[0].split(" ");
            String[] checkDate=ToSplitDeadline[1].split(" ");

            try {
                String DeadlineTask = ToSplitDeadline[0].substring(8);
                tasks.add(new Deadline(DeadlineTask, ToSplitDeadline[1].substring(2),
                        checkDescription.length,checkDate.length));

            } catch (DukeException ex) {
                isEmpty = true;
                ui.printDeadlineEmpty();
            }

            //sample:event project meeting /from Mon 2pm /to 4pm
            //error if any field is missing: event __missing__ /from __missing__ /to __missing__

        } else if (line.toLowerCase().contains("event")) {
            String[] ToSplitEvent = line.split("/");
            String[] checkDescription=ToSplitEvent[0].split(" ");
            String[] checkFromDate=ToSplitEvent[1].split(" ");
            String[] checkToDate=ToSplitEvent[2].split(" ");

            try {
                String EventTask = ToSplitEvent[0].substring(5);
                tasks.add(new Event(EventTask, ToSplitEvent[1].substring(4),
                        ToSplitEvent[2].substring(2),checkDescription.length,
                        checkFromDate.length,checkToDate.length));

            } catch (DukeException ex) {
                isEmpty = true;
                ui.printEventEmpty();
            }
        }
        if (!isEmpty) {
            ui.printTaskAdded(tasks, i);
            i += 1;

            //Updates changes onto the file
            Storage Storage;
            Storage = new Storage();
            Storage.addToFile(tasks, i);

        }
        return i;
    }
}


