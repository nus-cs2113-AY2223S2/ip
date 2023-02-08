public class UnknownCommandException extends Exception {
    public String unknownCommand;
    public UnknownCommandException(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }
}

