package commands;

public class Parser {

    /**
     * processes the user's input
     * @return the user's command
     */
    public String parseCommand(String ins) {
        return ins.contains(" ") ? ins.split(" ")[0] : ins;
    }

}
