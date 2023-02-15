package sage.utility;

import java.util.ArrayList;

import sage.exceptions.IllegalOperationException;
import sage.exceptions.MissingParameterException;
import sage.exceptions.EmptyListException;
import sage.exceptions.OutOfBoundException;
import sage.tasktypes.Deadline;
import sage.tasktypes.Event;
import sage.tasktypes.Task;
import sage.tasktypes.Todo;
import sage.utility.FileMgmt;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    private static final Display UI = new Display();
    private static final FileMgmt fm = new FileMgmt();

    public void update(FileMgmt fm) {
        fm.updateFile(list);
    }

    /**
     * This method instantiate a Todo object and add the Todo object to the TaskList
     *
     * @param taskName The name of the TODO task to be added
     **/
    public void addTask(String taskName, boolean isCompleted, boolean silentmode) {
        try {
            if (taskName == null) {
                throw new MissingParameterException();
            } else {
                Todo t = new Todo(taskName);
                list.add(t);
                t.setCompleted(isCompleted);
                if (!silentmode) {
                    UI.printAddedTask(t, list.size());
                    fm.updateFile(list);
                }
            }
        } catch (MissingParameterException e) {
            e.missingParamDesc();
        }
    }

    /**
     * This method instantiate a Deadline object and add the Deadline object to the TaskList
     *
     * @param taskName a string that represents the name of the Deadline task to add.
     * @param byWhen   a string that represents the deadline of the Deadline task.
     **/
    public void addTask(String taskName, String byWhen, boolean isCompleted, boolean silentMode) {
        try {
            if (taskName == null || byWhen == null) {
                throw new MissingParameterException();
            } else {
                Deadline d = new Deadline(taskName, byWhen);
                list.add(d);
                d.setCompleted(isCompleted);
                if (!silentMode) {
                    fm.updateFile(list);
                    UI.printAddedTask(d, list.size());
                }
            }
        } catch (MissingParameterException e) {
            if (taskName == null) {
                e.missingParamDesc();
            } else {
                e.missingParamBy();
            }
        }
    }

    /**
     * This method instantiate an Event object and add the Event object to the TaskList
     *
     * @param taskName  a string that represents the name of the Event task to add.
     * @param startWhen a string that represents the start date and time of the Event task.
     * @param endWhen   a string that represents the end date and time of the Event task.
     */
    public void addTask(String taskName, String startWhen, String endWhen, boolean isCompleted, boolean silentmode) {
        try {
            if (taskName == null || startWhen == null || endWhen == null) {
                throw new MissingParameterException();
            } else {
                Event e = new Event(taskName, startWhen, endWhen);
                list.add(e);
                e.setCompleted(isCompleted);
                if (!silentmode) {
                    fm.updateFile(list);
                    UI.printAddedTask(e, list.size());
                }
            }
        } catch (MissingParameterException e) {
            if (taskName == null) {
                e.missingParamDesc();
            } else if (startWhen == null) {
                e.missingParamStart();
            } else {
                e.missingParamEnd();
            }
        }
    }

    /**
     * Deletes a task.
     *
     * @param taskIndex string that represents the index of the task to unmark (1-Indexed).
     */
    public void deleteTask(String taskIndex) {
        try {
            if (taskIndex == null) {
                throw new MissingParameterException();
            }
            int taskNumber = Integer.parseInt(taskIndex);
            if (taskNumber <= 0 || taskNumber > list.size()) {
                throw new OutOfBoundException();
            } else {
                UI.printDeletedTask(list, taskNumber);
                fm.updateFile(list);
                list.remove(taskNumber - 1);
            }
        } catch (OutOfBoundException e) {
            e.errorDelete();
        } catch (MissingParameterException e) {
            e.missingParamTaskIndex();
        }
    }

    /**
     * Unmarks a completed task.
     *
     * @param taskIndex string that represents the index of the task to unmark (1-Indexed).
     */
    public void markingTask(String taskIndex, Boolean isMark) {

        try {
            if (taskIndex == null) {
                throw new MissingParameterException();
            }
            int taskNumber = Integer.parseInt(taskIndex);
            if (taskNumber <= 0 || taskNumber > list.size()) {
                throw new OutOfBoundException();
            } else if ((!isMark && !list.get(taskNumber - 1).isCompleted()) ||
                    (isMark && list.get(taskNumber - 1).isCompleted())) {
                throw new IllegalOperationException();
            } else {
                list.get(taskNumber - 1).setCompleted(isMark);
                fm.updateFile(list);
                UI.printMarking(list, taskNumber, isMark);
            }
        } catch (OutOfBoundException e) {
            e.errorUnmark();
        } catch (IllegalOperationException e) {
            if (isMark) {
                e.errorAlreadyMarked();
            } else {
                e.errorAlreadyUnmarked();
            }
        } catch (MissingParameterException e) {
            e.missingParamTaskIndex();
        }

    }

    public void listTask() {
        System.out.println(UI.printLine());
        try {
            if (list.size() > 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(i + 1 + ".");
                    System.out.println(list.get(i).toString());
                }
            } else {
                throw new EmptyListException();
            }
        } catch (EmptyListException e) {
            e.errorMsg();
        }
        System.out.println(UI.printLine());
    }

    public int getTaskCount() {
        return list.size();
    }
}

