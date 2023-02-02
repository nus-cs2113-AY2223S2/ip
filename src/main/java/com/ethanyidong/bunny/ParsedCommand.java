package com.ethanyidong.bunny;

import com.ethanyidong.bunny.arg.CommandValidator;

import java.util.HashMap;

public class ParsedCommand {
    private CommandType command;
    private String positionalArgument;
    private HashMap<String, String> flagArguments;
    public ParsedCommand(String command) {
        String[] commandAndArguments = command.split(" ", 2);
        this.command = extractCommandType(commandAndArguments[0]);

        String arguments;
        if(commandAndArguments.length == 2) {
            arguments = commandAndArguments[1];
        } else {
            return;
        }

        String[] positionalAndFlagArguments = arguments.split("/");
        this.positionalArgument = extractPositionalArgument(positionalAndFlagArguments);
        this.flagArguments = extractFlagArguments(positionalAndFlagArguments);

    }

    private static CommandType extractCommandType(String command) {
        return CommandType.fromString(command);
    }

    private static String extractPositionalArgument(String[] positionalAndFlagArgument) {
        return positionalAndFlagArgument[0].trim();
    }

    private static HashMap<String, String> extractFlagArguments(String[] positionalAndFlagArguments) {
        HashMap<String, String> ret = new HashMap<>();

        for(int i = 1; i < positionalAndFlagArguments.length; i++) {
            String[] flagAndFlagArgument = positionalAndFlagArguments[i].split(" ", 2);
            if (flagAndFlagArgument.length == 2) {
                ret.put(flagAndFlagArgument[0], flagAndFlagArgument[1]);
            }
        }

        return ret;
    }

    public CommandType getCommand() {
        return command;
    }

    public String getPositionalArgument() {
        return positionalArgument;
    }

    public String getFlagArgument(String flag) {
        return this.flagArguments.get(flag);
    }

    public boolean isValidCommand(BunnySession bunny) {
        for(CommandValidator validator : this.command.validators()) {
            if(!validator.isValidCommand(bunny, this)) {
                return false;
            }
        }
        return true;
    }
}
