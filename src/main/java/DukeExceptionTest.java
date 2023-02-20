/**
 * Tests for a missing task name when the user is trying to add a todo task
 */
public class DukeExceptionTest {
    public static void checkMissingTodoName (String input) throws MissingTaskNameException{
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            throw new MissingTaskNameException("☹ OOPS!!! Please input the name of the todo task.");
        }
    }
}
