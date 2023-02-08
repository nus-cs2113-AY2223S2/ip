import java.util.Arrays;

public class ToDo extends Task {
    public ToDo(String[] descriptionArray) {
        super(descriptionArray);
    }

    public static String[] parseCommand (String command) throws InvalidCommandException{
        String[] commandArray = command.split(" ");
        if (commandArray.length < 2){
            throw new InvalidCommandException("Description of todo cannot be empty!");
        }
        return Arrays.copyOfRange(commandArray, 1, commandArray.length);
    }
    @Override
    public String toString() {
        String todoPrefix = "[T]";
        String taskString = super.toString();
        return todoPrefix + taskString;
    }
}
