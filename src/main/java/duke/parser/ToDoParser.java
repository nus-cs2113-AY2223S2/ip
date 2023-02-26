package duke.parser;

import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.TaskEnum;
import duke.tasks.ToDo;

public class ToDoParser implements TaskParser {
    public ToDo parseInput(String input) throws InvalidTaskFormatException {
        TaskParser.checkValidInput(input, TaskEnum.TODO);
        return new ToDo(input);
    }
}
