package duke;

import duke.exception.EmptyTaskException;
import duke.exception.IllegalCommandException;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;



    public static void main(String[] args) {
        initTasks();
        Output.printWelcomeMessage();
        while (true) {
            String userCommand = in.nextLine();
            final String[] commandAndParam = Processor.command(userCommand);
            String command = commandAndParam[0];
            String param = commandAndParam[1];
            try {
                executeCommand(command, param);
                Storage.updateDuke();
            } catch (IllegalCommandException e) {
                Output.printInvalidCommand();
            } catch (EmptyTaskException e) {
                Output.printEmptyTask();
            } catch (IOException e) {
                Output.printErrorForIO();
            } catch (NumberFormatException e) {
                Output.printErrorForIdx();
            }
        }
    }





    }


}
