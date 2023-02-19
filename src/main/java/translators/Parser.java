package translators;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnknownCommand;

/**
 * Represents a parser that separates the String of command into Command Objects.
 */
public class Parser {
    /**
     * Returns a Command of a specific type depending on the content of
     * the input String.
     *
     * @param fullCommand A String inputted by the user, containing at
     *                    least a Command word that need to be parsed
     * @return A Command Object translated from <code>fullCommand</code>.
     * @throws NumberFormatException If the input is not an Integer when it is
     *                               supposed to be one.
     * @throws IndexOutOfBoundsException If the content of a Command that should not be
     *                                   empty is empty.
     */
    public static Command parse(String fullCommand)
            throws NumberFormatException, IndexOutOfBoundsException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        if (fullCommand.equals("list")) {
            return new ListCommand();
        }
        String[] words = fullCommand.split(" ");
        switch (words[0]) {
        case "mark":
            int indexToMark = Integer.parseInt(words[1]) - 1;
            return new MarkCommand(true, indexToMark);
        case "unmark":
            int indexToUnmark = Integer.parseInt(words[1]) - 1;
            return new MarkCommand(false, indexToUnmark);
        case "todo":
            // fall through
        case "deadline":
            // fall through
        case "event":
            return new AddCommand(words[0], fullCommand);
        case "help":
            return new HelpCommand();
        case "delete":
            int indexToDelete = Integer.parseInt(words[1]) - 1;
            return new DeleteCommand(indexToDelete);
        case "find":
            String toFind = fullCommand.replace(words[0],"" ).trim();
            return new FindCommand(toFind.toLowerCase());
        default:
            return new UnknownCommand();
        }
    }
}
