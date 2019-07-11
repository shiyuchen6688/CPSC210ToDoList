import model.Task;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class TaskTest {
    private Task testTask1;

    @Before
    public void setup() {
        testTask1 = new Task("testTask1");
    }

    @Test
    public void testConstructor() {
        assertEquals("testTask1", testTask1.getTaskName());
        assertEquals(0, testTask1.getDayUntilDue());
    }
}
