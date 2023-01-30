import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.EmptyDescriptionException;
import task.Task;
import task.TaskMarkException;
import task.ToDo;

public class TestTask {
    @Test
    public void givenDescription_whenGetDescription_thenCorrectDescription() throws EmptyDescriptionException {
        Task task = new ToDo();
        task.setDescription("Some random string");
        assertEquals("Some random string", task.getDescription());
    }

    @Test
    public void givenTask_whenMark_thenPrintX() throws TaskMarkException {
        Task task = new ToDo();
        task.setMark(true);
        assertEquals('X', task.getStatusIcon());
    }
}