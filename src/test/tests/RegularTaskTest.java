package tests;

import model.Birthday;
import model.RegularTask;
import model.Task;
import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ui.ToDoAppUsage;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
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
            assertEquals(ToDoAppUsage.sdf.parse("2019-08-08"), task.getDueDate());
        } catch (ParseException e) {
            System.out.println("caught ParseException");
        }

    }

    @Test
    public void testCloseToDueBothOk() {
        try {
            RegularTask t = new RegularTask("abby", "2020-12-08");
            assertFalse(t.closeToDue());
        } catch (ParseException e) {
            fail();
        } catch (OverDueException e) {
            fail();
        } catch (NoDueDateException e) {
            fail();
        }

    }

    @Test
    public void testCloseToDueNoDueDate() {
        RegularTask t = new RegularTask("abby");
        try {
            t.closeToDue();
            fail();
        } catch (OverDueException e) {
            fail();
        } catch (NoDueDateException e) {
            System.out.println("expected NoDueDateException");
        }
    }


    @Test
    public void testCloseToDueOverDue() {
        RegularTask t = null;
        try {
            t = new RegularTask("abby", "2016-08-08");
        } catch (ParseException e) {
            fail();
        }
        try {
            t.closeToDue();
            fail();
        } catch (OverDueException e) {
            System.out.println("expected NoDueDateException");
        } catch (NoDueDateException e) {
            fail();
        }
    }


}
