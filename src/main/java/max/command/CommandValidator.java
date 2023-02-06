package max.command;

import java.util.HashMap;

public class CommandValidator {
    public CommandValidator() {

    }

    // Takes in a command type to check the commandPayload map against
    // Checks for 2 things:
    // 1. All required commands are present
    // 2. Only the required commands are present
    public void validateCommandPayloadMap(Command command, HashMap commandPayload) throws InvalidCommandException {
        String[] requiredSubcommands = command.getSubcommandNames();
        int expectedSize = command.getArgumentLength();
        if (commandPayload.size() != expectedSize) {
            String errorMessage = "Invalid number of arguments. Expected: " + expectedSize + ", Got: " + commandPayload.size();
            throw new InvalidCommandException(errorMessage);
        }
        // Ensure that all required subcommands exist
        for (String subcommand : requiredSubcommands) {
            if (!commandPayload.containsKey(subcommand)) {
                throw new InvalidCommandException("Missing argument: " + subcommand);
            }
        }
    }
}
