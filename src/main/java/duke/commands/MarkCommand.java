package duke.commands;

import duke.exceptions.EmptyListException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import static duke.constants.Constants.LINEBREAK;

public class MarkCommand extends Command{

    @Override
    public void handleCommand(String line, TaskList taskList){
        int indexCount = Task.getIndexCount();
        int indexSelect;
        try {

            String done = line.split(" ")[1];
            if (indexCount == 0) {
                throw new EmptyListException();
            }
            indexSelect = Integer.parseInt(done) - 1;

            taskList.getTask(indexSelect).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.getTask(indexSelect));
            System.out.println(LINEBREAK);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Invalid task number. Please try again.");
            System.out.println(LINEBREAK);

        } catch (EmptyListException e) {
            System.out.println("The list is empty. Please add a task first.");
            System.out.println(LINEBREAK);

        } catch (NumberFormatException e) {
            System.out.println("Invalid command. Please try again.");
            System.out.println(LINEBREAK);

        }
    }
}
