class IncorrectCommandException extends Exception {
    IncorrectCommandException(String descriptor) {
        super(descriptor);
    }
}