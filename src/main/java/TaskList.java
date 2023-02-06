import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    private static final Display UI = new Display();

    /**
     * This method instantiate a Todo object and add the Todo object to the TaskList
     *
     * @param taskName The name of the TODO task to be added
     **/
    public void addTask(String taskName) {
        try {
            if (taskName == null) {
                throw new MissingParameterException();
            } else {
                Todo t = new Todo(taskName);
                list.add(t);
            }
            UI.addedTask(taskName, list.size());
        } catch (MissingParameterException e) {
            System.out.println(e.missingParamDesc());
        }
    }

    /**
     * This method instantiate a Deadline object and add the Deadline object to the TaskList
     *
     * @param taskName a string that represents the name of the Deadline task to add.
     * @param byWhen   a string that represents the deadline of the Deadline task.
     **/
    public void addTask(String taskName, String byWhen) {
        try {
            if (taskName == null || byWhen == null) {
                throw new MissingParameterException();
            } else {
                Deadline d = new Deadline(taskName, byWhen);
                list.add(d);
            }
            UI.addedTask(taskName, byWhen, list.size());
        } catch (MissingParameterException e) {
            if (taskName == null) {
                System.out.println(e.missingParamDesc());
            } else {
                System.out.println(e.missingParamBy());
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
    public void addTask(String taskName, String startWhen, String endWhen) {
        try {
            if (taskName == null || startWhen == null || endWhen == null) {
                throw new MissingParameterException();
            } else {
                Event e = new Event(taskName, startWhen, endWhen);
                list.add(e);
            }
            UI.addedTask(taskName, startWhen, endWhen, list.size());
        } catch (MissingParameterException e) {
            if (taskName == null) {
                System.out.println(e.missingParamDesc());
            } else if (startWhen == null) {
                System.out.println(e.missingParamStart());
            } else {
                System.out.println(e.missingParamEnd());
            }
        }
    }

    /**
     * Unmarks a completed task.
     *
     * @param taskDescription string that represents the index of the task to unmark (1-Indexed).
     */
    public void unmarkTask(String taskDescription) {
        try {
            int taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber <= 0 || taskNumber > list.size()) {
                throw new InvalidTaskException();
            } else if (!list.get(taskNumber - 1).isCompleted()) {
                throw new IllegalTaskOperation();
            } else {
                list.get(taskNumber - 1).setCompleted(false);
                UI.validUnmark(list, taskNumber);
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.errorUnmark());
        } catch (IllegalTaskOperation e) {
            System.out.println(e.errorAlreadyUnmarked());
        } catch (NumberFormatException e) {
            System.out.println("No index is provided!");
        }

    }

    /**
     * Marks a completed task.
     *
     * @param taskDescription a string that represents the index of the task to mark (1-Indexed).
     */
    public void markTask(String taskDescription) {
        try {
            int taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber <= 0 || taskNumber > list.size()) {
                throw new InvalidTaskException();
            } else if (list.get(taskNumber - 1).isCompleted()) {
                throw new IllegalTaskOperation();
            } else {
                list.get(taskNumber - 1).setCompleted(true);
                UI.validMark(list, taskNumber);
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.errorMark());
        } catch (IllegalTaskOperation e) {
            System.out.println(e.errorAlreadyMarked());
        } catch (NumberFormatException e) {
            System.out.println("No index is provided!");
        }
    }

    public void listTask() {
        System.out.println(UI.printLine());
        try {
            if (list.size() > 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(i + 1);
                    System.out.println(list.get(i).toString());
                }
            } else {
                throw new EmptyListException();
            }
        } catch (EmptyListException e) {
            System.out.println(e.errorMsg());
        }
        System.out.println(UI.printLine());
    }

    public int getTaskCount() {
        return list.size();
    }
}

