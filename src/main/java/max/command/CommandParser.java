package max.command;

import java.util.HashMap;

public class CommandParser {

    /**
     * Constructs a CommandParser.
     * <br>
     * A CommandParser is used to process user input from a defined format <p>
     * We define the user input format via console to consist of: <br>
     * 1. COMMANDS - user input substring consisting of an argument and payload <br>
     * 2. ARGUMENTS - command substring representing the action/parameters of the command <br>
     * 3. PAYLOADS - value of the action/parameters <br>
     * <br>
     * For example, a given user input: <i>"deadline work on CS2113 --by Sunday"</i>
     * <li>Has commands ["deadline work on CS2113", "by Sunday"]</li>
     * <li>Has arguments ["deadline", "by"]</li>
     * <li>Has payloads ["work on CS2113", ["Sunday"]</li>
     */
    public CommandParser() {

    }

    /**
     * Takes in a user input (string) in its entirety and partitions it into its commands in an array. <br>
     * A command is defined as either: <br>
     * 1. The first word in the user input and its payload <br>
     * 2. OR the word immediately following " --" and its payload <br>
     * For example: <i>deadline my_task --by -xyz sunday-monday</i>
     * Will render as: ["deadline my_task", "by -xyz sunday-monday"]
     * <p>
     *
     * @param rawCommand user console input in its entirety
     * @return String array of commands
     */
    public String[] splitIntoCommands(String rawCommand) {
        return rawCommand.split(" --");
    }

    /**
     * Processes a list of commands and maps each command's argument to its payload <br>
     * <p>
     * Each command consists of an argument and payload <br>
     * <p>
     * For example, for user input: "deadline work on CS2113 --by Sunday"
     * <li>    Has commands ["deadline work on CS2113", "by Sunday"]    </li>
     * <li>    Has arguments ["deadline", "by"]                         </li>
     * <li>    Has payloads ["work on CS2113", ["Sunday"]               </li>
     * Thus,  as: <"deadline", "work on CS2113">, <by, Sunday>
     *
     * @param commandList list of commands
     * @return HashMap
     */
    public HashMap<String, String> getCommandPayloadMap(String[] commandList) {
        HashMap<String, String> commandMap = new HashMap<>();
        for (String cmdStr : commandList) {
            String cmd = extractArgumentFromCommand(cmdStr);
            String text = extractPayloadFromCommand(cmdStr);
            commandMap.put(cmd, text);
        }
        return commandMap;
    }


    /**
     * Return the argument from a command <br>
     * <p>
     * For example, the command: "by Friday, 13th" <br>
     * Should return ["by"]
     *
     * @param command the command to be processed
     * @return command's payload rendered as a string
     */
    private static String extractArgumentFromCommand(String command) {
        String[] words = command.split(" ");
        return words[0];
    }

    /**
     * Return the payload from a command text <br>
     * <p>
     * For example, the command: "by Friday, 13th" <br>
     * Should return ["by" , "Friday, 13th"]
     *
     * @param command the command to be processed
     * @return command's payload rendered as a string
     */
    private static String extractPayloadFromCommand(String command) {

        String[] words = command.split(" ");
        String subcommandText = "";
        for (int i = 1; i < words.length; ++i) {
            subcommandText = subcommandText.concat(words[i]);
            if (i != words.length - 1) {
                subcommandText = subcommandText.concat(" ");
            }
        }
        return subcommandText;
    }


    /**
     * Returns what valid main command the input string represents.
     * A main command is a valid command defined under the Command enum
     * The input string should be the FIRST argument input of any given command.
     * For example, the input <i>"mark 10"</i> has first argument <i>`mark`</i>
     *
     * @param mainCommand the first argument input, i.e. the command that the user wants to execute (e.g. list)
     * @return Command type of the detected command. Returns UNKNOWN_COMMAND for unmatchable inputs.
     */
    public Command getCommandType(String mainCommand) {
        // Extract the main command from its payload
        String commandName = extractArgumentFromCommand(mainCommand);

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
