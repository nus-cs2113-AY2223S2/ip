public class ArgumentBlankException extends Exception {
    public String argumentType;
    public ArgumentBlankException(String argumentType) {
        this.argumentType = argumentType;
    }
}
