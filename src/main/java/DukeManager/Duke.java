package DukeManager;// packages import
import DukeManager.Commands.Cmd;
import DukeManager.Parser.Parser;
import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.DukeErrors.BlankListException;
import DukeManager.data.TaskList;

import java.io.FileNotFoundException;

// java library import


public class Duke {
	private final Storage storage;
	private TaskList tasks;
	private TextUi ui;
	private Parser parser;

	public Duke(String filePath) {
		ui = new TextUi();
		storage = new Storage(filePath);
		try {
			tasks = new TaskList(storage.load());
			ui.showUserReturn();
		} catch (FileNotFoundException | TaskList.DuplicateTaskException e) {
			ui.showNoSaveFileError();
			tasks = new TaskList();
		}
	}

	public void run() {
		ui.showWelcomeMessage();
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = ui.readCmd();
				Cmd c = Parser.parse(fullCommand);
				c.execute(tasks, ui);
				Storage.save(tasks);
			} catch (BlankListException e) {
				ui.showToUser(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new Duke("tasks.txt").run();
	}
}