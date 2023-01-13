import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Duke.Duke;
public class TestDuke {
    /**
     * Test if the output message of Duke matches the expected default greet and exit message
     * @throws IOException
     */
    @Test
    public void givenDuke_whenRun_thenOutputMessage() throws IOException{
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/givenDuke_whenRun_thenOutputMessage.TXT"));
            // BufferedReader reader = new BufferedReader(new FileReader("text-ui-test/EXPECTED.TXT"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            reader.close();

            String content = stringBuilder.toString();
            System.out.println(content);

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            Duke.main(null);
            String lines = outContent.toString();
            
            assertEquals(content,lines.substring(0, lines.length() - 1));
        } catch (IOException e) {
            throw e;
        }
    }
}
