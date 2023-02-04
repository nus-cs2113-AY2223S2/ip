package controller;

import constants.Message;
import database.Database;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.Todo;
import view.TaskView;

/**
 * TaskController is the middleman to separate out the model from
 * the view. It will mainly contain the business logic and ensures
 * that the view and model do not mix.
 */
public class TaskController {

    protected TaskView view = new TaskView();
    protected Database db;
    static int counter = 0;

    // We connect our controller to the instance of the database
    public TaskController(Database db) {
        this.db = db;
    }

    /**
     * List out all the tasks in the database.
     *
     * @throws Exception An exception if there is an issue with
     *                   fetching the data from the database.
     */
    public void listTasks() throws Exception {
        System.out.println(Message.LIST_TASKS.message);
        int numberOfEntries = counter;
        for (int i = 0; i < numberOfEntries; i += 1) {
            Task model = db.read(i);
            System.out.printf("%d. ", (i + 1));
            view.printTaskDescriptionText(model);
        }
    }

    /**
     * Adds a todo task into the database.
     *
     * @param taskDescription The description of the todo task.
     * @throws Exception An exception if there is an issue with
     *                   adding the data from the database.
     */
    public void addTodoTask(String taskDescription) throws Exception {
        Todo model = new Todo(taskDescription);
        counter += 1;
        db.create(model);
        System.out.println("Got it. I've added this task:");
        view.printTaskDescriptionText(model);
        System.out.printf("Now you have %d tasks in the list.\n", counter);
    }

    /**
     * Updates the task model in the database with the updated value.
     * Following which, we will read the model and print the corresponding value.
     *
     * @param isMark The value to update the task with
     * @param index  The index of the task to be updated
     * @throws Exception An exception if the task is invalid
     */
    public void toggleMark(boolean isMark, int index) throws Exception {
        db.update(index, isMark);
        Task model = db.read(index);
        System.out.printf(
                "%s\n",
                isMark ? Message.MARKED.message : Message.UNMARKED.message
        );
        view.printTaskDescriptionText(model);
    }

    /**
     * Adds a deadline task into the database
     *
     * @param taskDescription The task description
     * @throws Exception An exception if the task is invalid
     */
    public void addDeadlineTask(String taskDescription) throws Exception {
        int index = taskDescription.indexOf("/by");
        String description = taskDescription.substring(0, index);
        String endDuration = taskDescription.substring(index + "/by ".length());
        Deadline model = new Deadline(description, endDuration);
        db.create(model);
        System.out.printf(Message.TASK_ADDED.message, model.getDescriptionText());
    }

    /**
     * Adds an event task into the tasks array and prints
     * out the corresponding message
     *
     * @param taskDescription A string after removing the
     *                        command
     */
    public void addEventTask(String taskDescription) throws Exception {
        int indexOfFrom = taskDescription.indexOf("/from");
        int indexOfTo = taskDescription.indexOf("/to");
        String description = taskDescription.substring(0, indexOfFrom);
        String from = taskDescription.substring(
                indexOfFrom + "/from ".length(),
                indexOfTo
        );
        String to = taskDescription.substring(indexOfTo + "/to ".length());
        Event model = new Event(description, from, to);
        db.create(model);
        System.out.printf(Message.TASK_ADDED.message);
        view.printTaskDescriptionText(model);
    }
}
