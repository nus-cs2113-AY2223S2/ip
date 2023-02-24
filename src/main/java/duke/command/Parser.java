package duke.command;

public class Parser {
    
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
