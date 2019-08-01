package tests;

import model.GeneralTask;
import model.ReturnTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnTaskTest {
    private static final String name1 = "name1";
    private static final String date1 = "2019-08-08";

    @Test
    public void testFormatOneNormalTaskWithDuedate() {
        String s = ReturnTask.formatOneNormalTaskWithDuedate(name1, date1);
        assertEquals(s, GeneralTask.INDENTATION + "Task: " + name1
                + " Due Date:" + date1);
    }

    @Test
    public void testFormatOneNormalTaskWithNoDuedate() {
        String s = ReturnTask.formatOneNormalTaskWithNoDuedate(name1);
        assertEquals(s, GeneralTask.INDENTATION + "Task: " + name1
                + " with no due date");
    }

    @Test
    public void testFormatOneOverdueTaskWithDuedate() {
        String s = ReturnTask.formatOneOverdueTaskWithDuedate(name1, date1);
        assertEquals(s, GeneralTask.INDENTATION + "Overdue Task: " + name1
                + " Due Date: " + date1);
    }
}
