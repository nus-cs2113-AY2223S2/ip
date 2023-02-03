import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import parser.Command;
import parser.EmptyCommandException;
import parser.InvalidCommandException;
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

    @Test
    public void givenCommand_whenGetTaskIndex_thenReturnIndex() throws InvalidCommandException, EmptyCommandException {
        String message = "Mark 1";
        ByteArrayInputStream inContent = new ByteArrayInputStream(message.getBytes());
        System.setIn(inContent);
        parser = new Parser(new Scanner(System.in));
        parser.getCommand();
        assertEquals(1, parser.getTaskIndex());
    }

    @Test
    public void givenInvalidIndex_whenGetTaskIndex_thenInvalidCommandException() throws InvalidCommandException, EmptyCommandException {
        String message = "Mark ";
        ByteArrayInputStream inContent = new ByteArrayInputStream(message.getBytes());
        System.setIn(inContent);
        parser = new Parser(new Scanner(System.in));
        parser.getCommand();
        assertThrows(InvalidCommandException.class, () -> {
            parser.getTaskIndex();
       });
    }
}