package max.command;

import java.util.HashMap;

/**
 * CommandValidator helps check for the correctness the arguments that have been processed
 */
public class CommandValidator {
    private static final String MESSAGE_INVALID_ARG_NUM_EXPECTED = "Invalid number of arguments. Expected: ";
    private static final String MESSAGE_INVALID_ARG_NUM_GOT = ", Got: ";
    private static final String MESSAGE_MISSING_ARG = "Missing argument: ";

    public CommandValidator() {

    }

    /**
     * Takes in a command type to check the commandPayload map against <br>
     * <p>
     * Checks for 2 things:
     * 1. All required commands are present <br>
     * 2. Only the required commands are present <br>
     *
     * @param command         command type to check the argumentPayload map against
     * @param argumentPayload the map containing argument-payload pairs
     * @throws InvalidCommandException if there are missing or too many arguments
     */
    public void validateCommandPayloadMap(Command command, HashMap<String, String> argumentPayload) throws InvalidCommandException {
        String[] requiredSubcommands = command.getSubcommandNames();
        int expectedSize = command.getArgumentLength();
        if (argumentPayload.size() != expectedSize) {
            String errorMessage = MESSAGE_INVALID_ARG_NUM_EXPECTED + expectedSize;
            errorMessage += MESSAGE_INVALID_ARG_NUM_GOT + argumentPayload.size();
            throw new InvalidCommandException(errorMessage);
        }
        // Ensure that all required subcommands exist
        for (String subcommand : requiredSubcommands) {
            if (!argumentPayload.containsKey(subcommand)) {
                throw new InvalidCommandException(MESSAGE_MISSING_ARG + subcommand);
            }
        }
    }
}
