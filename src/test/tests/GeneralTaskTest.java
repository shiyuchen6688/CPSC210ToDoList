package tests;

import model.RegularTask;
import model.TaskNotFoundException;
import model.GeneralTask;
import model.ToDoList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class GeneralTaskTest {
    protected GeneralTask testTask;




    @Test
    public void testGetName() {
        assertEquals("testTask", testTask.getName());
    }

    @Test
    public void testGetDueDate() {
        assertEquals(null, testTask.getDueDate());
    }

    @Test
    public void testGetDueDateAlreadySet(){

        String newDate = "2019-07-07";
        try {
            testTask.setDueDate(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(testTask.getDueDate().toString().equals("Sun Jul 07 00:00:00 PDT 2019"));

    }

    @Test
    public void testGetStatus() {
        assertFalse(testTask.getStatus());
    }

    @Test
    public void testGetListBelonged() {
        assertEquals(null, testTask.getListBelonged());
    }

    @Test
    public void testSetName() {
        Assert.assertEquals("testTask", testTask.getName());
        testTask.setName("changed");
        Assert.assertEquals("changed", testTask.getName());
    }

    @Test
    public void testSetDueDate() {
        // invoke method
        String newDate = "2019-07-07";
        try {
            testTask.setDueDate(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // check outcome
        assertTrue(testTask.getDueDate().toString().equals("Sun Jul 07 00:00:00 PDT 2019"));
    }

    @Test
    public void testSetStatus() {
        assertFalse(testTask.getStatus());
        testTask.setStatus(true);
        assertTrue(testTask.getStatus());
    }

    @Test
    public void testSetListBelonged() {
        ToDoList list = new ToDoList("tests list");
        testTask.setListBelonged(list);

        assertEquals(list, testTask.getListBelonged());
        assertTrue(list.contains(testTask.getName()));
    }


    @Test
    public void testIsOverdueWhenNot() {
        try {
            testTask.setDueDate("3000-10-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertFalse(testTask.isOverdue());
    }


    @Test
    public void testOverdueWhenOverdue() {
        // invoke behavior
        try {
            testTask.setDueDate("1999-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // check outcome
        assertTrue(testTask.isOverdue());
    }

    @Test
    public void testOverdueWhenNull() {
        GeneralTask t = new RegularTask("t");
        assertFalse(t.isOverdue());
    }

    @Test
    public void testGetDayUntilDue() {
        LocalDate currentDate = LocalDate.now();
        Date now = java.sql.Date.valueOf(currentDate);
        try {
            GeneralTask t  = new RegularTask("t", "2020-10-02");
            long diff = t.getDueDate().getTime() - now.getTime();
            int day =  (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            assertEquals(day, t.getDayUntilDue());

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testEquals() {
        GeneralTask t1 = new RegularTask("t");
        Date d2 = new Date();
        t1.equals(d2);
    }

    @Test
    public void testHashCode() {
        GeneralTask t1 = new RegularTask("t");
        GeneralTask t2 = new RegularTask("t");
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void testRemoveListBelonged() {
        ToDoList list = new ToDoList("testList");
        testTask.setListBelonged(list);
        assertEquals(list, testTask.getListBelonged());
        try {
            testTask.removeListBelonged(list);
        } catch (TaskNotFoundException e) {
            fail("Caught TaskNotFound when should not have");
        }

        assertEquals(null, testTask.getListBelonged());

    }
}
