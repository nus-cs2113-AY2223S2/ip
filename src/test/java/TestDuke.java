import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Duke.Duke;
public class TestDuke {
    @Test
    public void givenDuke_whenRun_thenOutputMessage(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("text-ui-test/EXPECTED.TXT"));
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
            
            assertEquals(content,lines);
        } catch (Exception e) {
            // do nothing
        }
    }
}
