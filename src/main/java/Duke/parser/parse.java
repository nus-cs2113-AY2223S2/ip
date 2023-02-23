package Duke.parser;

/**
 * Parser: deals with making sense of the user command
 */
public class parse {
	public static String[] parseTask (String input) {

		return input.split (" ", 2);
	}

	public static String[] parseDeadline (String input) {

		return input.split ("/by");
	}

	public static String[] parseEventFrom (String input) {

		return input.split ("/from");
	}

	public static String[] parseEventTo (String input) {

		return input.split ("/to");
	}

}
