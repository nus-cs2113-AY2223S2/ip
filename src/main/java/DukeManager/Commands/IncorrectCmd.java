package DukeManager.Commands;

public class IncorrectCmd extends Cmd {
	public final String feedbackToUser;
	public IncorrectCmd(String feedbackToUser) {
		this.feedbackToUser = feedbackToUser;
	}

	@Override
	public CmdResult execute() {
		return new CmdResult((feedbackToUser));
	}
}
