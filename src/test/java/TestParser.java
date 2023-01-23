import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Parser.EmptyCommandException;
import Parser.Parser;

public class TestParser {
    Parser parser;
    ByteArrayInputStream inContent;
    
    @DisplayName("givenNull_whenGetNextMessage_thenEmptyCommandException successful")
    @Test
    public void givenNull_whenGetNextMessage_thenEmptyCommandException() throws EmptyCommandException {
        String message = "";
        ByteArrayInputStream inContent = new ByteArrayInputStream(message.getBytes());
        System.setIn(inContent);
        parser = new Parser(new Scanner(System.in));
        assertThrows(EmptyCommandException.class, () -> {
            parser.getNextMessage();
       });
    }

    @DisplayName("givenBlank_whenGetNextMessage_thenEmptyCommandException successful")
    @Test
    public void givenBlank_whenGetNextMessage_thenEmptyCommandException() throws EmptyCommandException {
        String message = "   ";
        ByteArrayInputStream inContent = new ByteArrayInputStream(message.getBytes());
        System.setIn(inContent);
        parser = new Parser(new Scanner(System.in));
        assertThrows(EmptyCommandException.class, () -> {
            parser.getNextMessage();
       });
    }

    @DisplayName("givenMessage_whenGetMessage_thenEmptyCommandException successful")
    @Test
    public void givenNull_whenGetMessage_thenEmptyCommandException() throws EmptyCommandException {
        String message = "Some random message";
        ByteArrayInputStream inContent = new ByteArrayInputStream(message.getBytes());
        System.setIn(inContent);
        parser = new Parser(new Scanner(System.in));
        parser.getNextMessage();
        assertEquals(message, parser.getMessage());

    }
}