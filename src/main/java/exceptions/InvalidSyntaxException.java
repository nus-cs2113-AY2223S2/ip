package exceptions;

/**
 * Represents an exception caused by user input that does not match the syntax for a given command
 *
 * @see ui.Syntax
 */
public class InvalidSyntaxException extends Exception {

    private final String expectedSyntax;

    public InvalidSyntaxException(String expectedSyntax) {
        this.expectedSyntax = expectedSyntax;
    }

    public String getExpectedSyntax() {
        return expectedSyntax;
    }

}
