package DukeManager.Commands;

public class HelpCmd extends Cmd {

	public static final String MSG_USAGE = "help : Shows program usage instructions.\n"
			+ "Example: help";

	@Override
	public CmdResult execute() {
		return new CmdResult(
				AddCmd.MSG_USAGE
						+ "\n" + DeleteCmd.MSG_USAGE
						+ "\n" + ListCmd.MESSAGE_USAGE
						+ "\n" + HelpCmd.MSG_USAGE
						+ "\n" + ExitCmd.MSG_USAGE
		);
	}
}
