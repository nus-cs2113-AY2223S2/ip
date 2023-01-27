import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;

public class TestTask {
    @Test
    public void givenDescription_whenGetDescription_thenCorrectDescription() {
        Task task = new Task(null);
        task.setDescription("Some random string");
        assertEquals("Some random string", task.getDescription());
    }

    @Test
    public void givenTask_whenMark_thenPrintX() {
        Task task = new Task(null);
        task.setMark(true);
        assertEquals('X', task.getStatusIcon());
    }
}