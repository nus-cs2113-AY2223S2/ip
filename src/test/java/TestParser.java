import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import parser.Command;
import parser.EmptyCommandException;
import parser.Parser;

public class TestParser {
    Parser parser;
    ByteArrayInputStream inContent;
    
    @Test
    public void givenNull_whenGetCommand_thenEmptyCommandException() {
        String message = "";
        ByteArrayInputStream inContent = new ByteArrayInputStream(message.getBytes());
        System.setIn(inContent);
        parser = new Parser(new Scanner(System.in));
        assertThrows(EmptyCommandException.class, () -> {
            parser.getCommand();
       });
    }

    @Test
    public void givenToDo_whenGetCommand_thenEmptyCommandException() throws EmptyCommandException {
        String message = "todo eat rice";
        ByteArrayInputStream inContent = new ByteArrayInputStream(message.getBytes());
        System.setIn(inContent);
        parser = new Parser(new Scanner(System.in));
        assertEquals(Command.TASK, parser.getCommand());
    }
}