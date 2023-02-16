package exceptions;

public class InvalidSyntaxException extends Exception {

    private final String expectedSyntax;

    public InvalidSyntaxException(String expectedSyntax) {
        this.expectedSyntax = expectedSyntax;
    }

    public String getExpectedSyntax() {
        return expectedSyntax;
    }

}
