public class DukeExceptionTest {
    public static void checkMissingTodoName (String input) throws MissingTaskNameException{
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            throw new MissingTaskNameException("☹ OOPS!!! Please input the name of the todo task.");
        }
    }
}
