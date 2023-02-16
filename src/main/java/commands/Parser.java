package commands;

public class Parser {
    /*
        Extract the Command of the input of user
        return the command if existed
     */
    public String parseCommand(String ins) {
        return ins.contains(" ") ? ins.split(" ")[0] : ins;
    }

    public static String parseTaskType(String task) {
        return task.substring(5, 6);
    }
}
