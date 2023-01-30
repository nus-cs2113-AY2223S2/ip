package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import static duke.constants.Constants.LINEBREAK;

public class UnmarkCommand extends Command{

    @Override
    public void handleCommand(String line, TaskList taskList) {
        try {
            int indexCount = Task.getIndexCount();
            int indexSelect;

            String undone = line.split(" ")[1];
            if (indexCount == 0) {
                throw new EmptyListException();
            }

            indexSelect = Integer.parseInt(undone) - 1;

            taskList.getTask(indexSelect).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
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
