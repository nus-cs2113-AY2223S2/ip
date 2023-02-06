import java.util.HashMap;

public class CommandParser {

    public CommandParser() {

    }

    public String[] splitIntoCommands(String rawCommand) {
        // " -" splits the string into substrings
        // if the substring starts with two dashes '--' and is preceded by whitespace
        // For example: deadline my_task --by -xyz sunday-monday
        // Will output: ["deadline my_task", "by -xyz sunday-monday"]
        // For now, this does not account for accidental or adversarial '--' input at the start of words
        // but this is an unlikely edge case that can be dealt with later
        return rawCommand.split(" --");
    }

    public HashMap<String, String> getCommandPayloadMap(String[] commandList) {
        // Each command can be processed into a subcommand and its corresponding text value
        // For example: "deadline work on CS2113 --by Sunday"
        // Should map to: <"deadline", "work on CS2113">, <by, Sunday>
        HashMap<String, String> commandMap = new HashMap<>();
        for (String cmdStr : commandList) {
            String cmd = extractCommandFromSubcommand(cmdStr);
            String text = extractTextFromSubcommand(cmdStr);
            commandMap.put(cmd, text);
        }
        return commandMap;
    }

    public static String extractCommandFromSubcommand(String subcommand) {
        // Subcommands have format <subcommand> <text_to_extract>
        // Return the <subcommand>
        // For example, the deadline subcommand: "by Friday, 13th"
        // Should return "by"
        String[] words = subcommand.split(" ");
        return words[0];
    }

    public static String extractTextFromSubcommand(String subcommand) {
        // Subcommands have format <subcommand> <text_to_extract>
        // Return the <subcommand> and <text_to_extract> in a string array
        // For example, the deadline subcommand: "by Friday, 13th"
        // Should return ["by" , "Friday, 13th"]
        String[] words = subcommand.split(" ");
        String subcommandText = "";
        for (int i = 1; i < words.length; ++i) {
            subcommandText = subcommandText.concat(words[i]);
            if (i != words.length - 1) {
                subcommandText = subcommandText.concat(" ");
            }
        }
        return subcommandText;
    }

    // Takes in the Main command and its payload, if any
    public Command getCommandType(String mainCommand) {
        /* TODO:
         *  Consider possible optimisation by hashing the commands
         *   This may be premature optimisation, might not be necessary
         */
        // Extract the real command from its payload
        String commandName = extractCommandFromSubcommand(mainCommand);

        // Find out which Command commandName is
        Command[] commands = Command.values();
        for (Command command : commands) {
            if (command.getMainCommand().equals(commandName)) {
                return command;
            }
        }
        return Command.UNKNOWN_COMMAND;
    }

}
