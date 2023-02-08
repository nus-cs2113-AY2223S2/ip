class MissingFieldException extends Exception {
    MissingFieldException(String descriptor) {
        super(descriptor);
    }
}