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

public class Parser {
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
