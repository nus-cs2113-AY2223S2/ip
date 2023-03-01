package com.ethanyidong.bunny.cmd;

import java.util.HashMap;

/**
 * Represents an input String split into specific command tokens
 * Provides the command name, the positional argument (if any), and the flag arguments (if any)
 */
public class TokenizedCommand {
    private final String command;
    private String positionalArgument;
    private HashMap<String, String> flagArguments;

    /**
     * Parses a String command into its tokens (name, positional argument, flag arguments)
     * @param command the String representation of the command to parse
     */
    public TokenizedCommand(String command) {
        String[] commandAndArguments = command.split(" ", 2);
        this.command = extractCommand(commandAndArguments[0]);

        String arguments;
        if (commandAndArguments.length == 2) {
            arguments = commandAndArguments[1];
        } else {
            return;
        }

        String[] positionalAndFlagArguments = arguments.split("/");
        this.positionalArgument = extractPositionalArgument(positionalAndFlagArguments);
        this.flagArguments = extractFlagArguments(positionalAndFlagArguments);

    }

    private static String extractCommand(String command) {
        return command;
    }

    private static String extractPositionalArgument(String[] positionalAndFlagArgument) {
        return positionalAndFlagArgument[0].trim();
    }

    private static HashMap<String, String> extractFlagArguments(String[] positionalAndFlagArguments) {
        HashMap<String, String> ret = new HashMap<>();

        for (int i = 1; i < positionalAndFlagArguments.length; i++) {
            String[] flagAndFlagArgument = positionalAndFlagArguments[i].split(" ", 2);
            if (flagAndFlagArgument.length == 2) {
                ret.put(flagAndFlagArgument[0], flagAndFlagArgument[1].trim());
            } else {
                ret.put(flagAndFlagArgument[0], "");
            }
        }

        return ret;
    }

    /**
     * @return the command name of the parsed command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return the positional argument of the parsed command
     */
    public String getPositionalArgument() {
        return positionalArgument;
    }

    /**
     * @param flag the name of the flag to return the corresponding value for
     * @return the flag argument value of the passed flag
     */
    public String getFlagArgument(String flag) {
        return this.flagArguments.get(flag);
    }
}
