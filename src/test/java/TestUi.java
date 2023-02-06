import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
<<<<<<< HEAD
=======
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
>>>>>>> 559ea2ebccf6c19e8425c687df08266420e5784e
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import ui.Ui;

public class TestUi {
    @Test
    public void givenErrMessage_whenPrintErr_thenOutputErrorInRed() throws IOException {
        Ui ui = new Ui();
        // PrintStream out = new PrintStream(new FileOutputStream("src/test/resources/uiExpected.txt"));
        // System.setOut(out);
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/uiExpected.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayInputStream inContent = new ByteArrayInputStream("bye\n".getBytes());
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
        ui.printSystemErrorMessage("You made some error here!!");

        String lines = outContent.toString();

        assertEquals(stringBuilder.toString(),lines);
    }
}