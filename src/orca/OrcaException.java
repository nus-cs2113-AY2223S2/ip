package orca;

public class OrcaException extends Exception {
    public OrcaException(String message) {
        super("    --------------------------------------------------\n    " + message
                + "\n    --------------------------------------------------\n");
    }
}
