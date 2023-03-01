package Arsdorint.command;

public class Command {
    public String commandType;
    protected Command(String commandType) {
        this.commandType = commandType;
    }
    public CommandRes execute() {
        throw new UnsupportedOperationException("Child classes' method");
    }

    public boolean contains(final int[] arr, final int key) {
        for (final int i : arr) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }
}
