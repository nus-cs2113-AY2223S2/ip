package io.github.haoyangw.rica.exception;

/**
 * Represents and categorises Rica-specific runtime <code>Exceptions</code> for
 *   more specific exception handling in Rica's internal code
 */
public class RicaException extends RuntimeException {
    public RicaException() {
        super();
    }

    public RicaException(String msg) {
        super(msg);
    }
}
