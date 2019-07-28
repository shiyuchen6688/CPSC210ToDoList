import model.GeneralTask;
import model.RegularTask;
import model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ui.ToDoListUsage;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RegularTaskTest extends GeneralTaskTest {


    @BeforeEach
    public void setup() {
        testTask = new RegularTask("testTask");
    }


    @Test
    public void testOnlyNameConstructor() {
        assertEquals("testTask", testTask.getName());
    }


    @Test
    public void testTwoParaConstructor() {
        try {
            Task task = new RegularTask("a", "2019-08-08");
            assertEquals("a", task.getName());
            assertEquals(ToDoListUsage.sdf.parse("2019-08-08"),task.getDueDate());
        } catch (ParseException e) {
            System.out.println("caught ParseException");
        }

    }




}
