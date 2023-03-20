package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeEmptyArgumentException;
import duke.exceptions.DukeIllegalCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.List;

public class AddCommand extends Command {
    private final String description;
    private final String type;
    private String date;

    public AddCommand(String command) throws DukeEmptyArgumentException, DukeIllegalCommandException {
        String parsedResponse;
        try {
            this.type = command.split(" ", 2)[0];
            parsedResponse = command.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("@.@ I'm confused! Please check your argument");
        }
        if (command.startsWith("todo")) {
            // example : todo finish tp
            this.description = parsedResponse;
        } else if (command.startsWith("deadline")) {
            // example : deadline finish tp /by sunday
            try {
                String description = parsedResponse.split(" /by ")[0];
                String date = parsedResponse.split(" /by ")[1];
                this.description = description;
                this.date = date;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeEmptyArgumentException("@.@ I'm confused! Please check your argument");
            }
        } else if (command.startsWith("event")) {
            // example : event tp meeting /at 2-4pm
            try {
                String description = parsedResponse.split("/at",2)[0];
                String date = parsedResponse.split("/at",2)[1];
                this.description = description;
                this.date = date;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeEmptyArgumentException("@.@ I'm confused! Please check your argument");
            }
        } else {
            throw new DukeIllegalCommandException("@.@ Oops, I don't understand your command");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the task specified based on the task command (example:todo,deadline,event) to the TaskList.
     *
     * @param tasks   the current state of TaskList.
     * @param storage the current state of Storage.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        List<Task> list = tasks.getList();
        int size;
        if (type.equalsIgnoreCase("todo")) {
            list.add(new Todo(description));
        } else if (type.equalsIgnoreCase("deadline")) {
            list.add(new Deadline(description, date));
        } else if (type.equalsIgnoreCase("event")) {
            list.add(new Event(description, date));
        } else {
            return;
        }
        size = list.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(size - 1).getTask());
        System.out.println("Now you have " + size + " in the list.");
        tasks.setList(list);
    }
}