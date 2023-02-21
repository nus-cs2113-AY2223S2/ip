public class Parser {
    public enum taskType{
        TODO,DEADLINE,EVENT,INVALID
    }
    /*
     * Finds out what the task type of a command is
     *
     * @param command Command string from the user or save file
     */
    public static taskType getTaskType(String command){
        String[] commands = command.split(" ");
        switch (commands[0]) {
        case "deadline":
            return taskType.DEADLINE;
        case "event":
            return taskType.EVENT;
        case "todo":
            return taskType.TODO;
        default:
            return taskType.INVALID;
        }
    }
}
