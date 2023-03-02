package duke.command;

/**
 * Parses the command from a <code>String</code> into a <code>Command</code> type
 * that can be understood by <code>Duke</code>.
 */
public class Parser {
    
    /**
     * Main function parsing the command from <code>String</code> into <code>Command</code> type.
     * @param line Command line in <code>String</code> type.
     * @return A <code>Command</code> type object constructed from input <code>line</code>.
     */
    public static Command parse(String line) {
        Command cmd = new Command();
        String command;
        String cmdContent;
        int cmdIdx;

        cmd.setLine(line);

        cmdIdx = line.indexOf(" ");
        if(cmdIdx == -1) {
            cmdIdx = line.length();
        }
        cmd.setCmdIdx(cmdIdx);

        command = line.substring(0, cmdIdx);
        cmd.setCommand(command);

        if(cmdIdx == line.length()) {
            cmdContent = "";
        } else {
            cmdContent = line.substring(cmdIdx + 1);
        }
        cmd.setCmdContent(cmdContent);

        return cmd;
    }
}
