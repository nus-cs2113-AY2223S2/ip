package DukeManager.Ui;

import DukeManager.common.Messages;
import DukeManager.data.Tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static DukeManager.common.Messages.FAREWELL;
import static DukeManager.common.Messages.GREET;
import static DukeManager.common.Messages.LINE_PARTITION;


public class TextUi {
	private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

	private final Scanner in;
	private final PrintStream out;

	public TextUi() {
		this(System.in, System.out);
	}

	public TextUi(InputStream in, PrintStream out) {
		this.in = new Scanner(in);
		this.out = out;
	}

	/**
	 * Returns true if the user input line should be ignored.
	 * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
	 *
	 * @param rawInputLine full raw user input line.
	 * @return true if the entire user input line should be ignored.
	 */
	private boolean shouldIgnore(String rawInputLine) {
		return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
	}

	/**
	 * Returns true if the user input line is a comment line.
	 *
	 * @param rawInputLine full raw user input line.
	 * @return true if input line is a comment.
	 */
	private boolean isCommentLine(String rawInputLine) {
		return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
	}
	/**
	 * Prompts for the command and reads the text entered by the user.
	 * Ignores empty, pure whitespace, and comment lines.
	 * Echos the command back to the user.
	 * @return command (full line) entered by the user
	 */
	public String readCmd() {
		out.print("\t  Enter command: ");
		String fullInputLine = in.nextLine();

		// silently consume all ignored lines
		while (shouldIgnore(fullInputLine)) {
			fullInputLine = in.nextLine();
		}

		showToUser("Command entered:" + fullInputLine);
		return fullInputLine;
	}

	public void showWelcomeMessage() {
		showToUser(GREET);
	}

	public void showFarewellMessage() {
		showToUser(FAREWELL);
	}

	/** Shows message(s) to the user */
	public void showToUser(String... message) {
		out.print(LINE_PARTITION);
		for (String m : message) {
			out.println("\t  " + m);
		}
		out.print(LINE_PARTITION);
	}

	public void showNoSaveFileError() {
		showToUser("Welcome, new user. How may I help you?");
	}
	public void showUserReturn() {
		showToUser("Saved list loaded. Welcome back!");
	}

}
