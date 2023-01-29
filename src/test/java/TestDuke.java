import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import duke.Duke;

public class TestDuke {
    /**
     * Test if the output message of Duke matches the expected default greet and exit message
     * @throws IOException
     */
    @Test
    public void givenBye_whenDukeMain_thenExit() throws IOException{
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/givenBye_whenDukeMain_thenExit.TXT"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            // stringBuilder.deleteCharAt(stringBuilder.length()-1);
            reader.close();

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            ByteArrayInputStream inContent = new ByteArrayInputStream("bye\n".getBytes());
            System.setIn(inContent);
            System.setOut(new PrintStream(outContent));
            Duke.main(null);

            String lines = outContent.toString();

            assertEquals(stringBuilder.toString(),lines.substring(0, lines.length() - 1));
        } catch (IOException e) {
            throw e;
        }
    }
}