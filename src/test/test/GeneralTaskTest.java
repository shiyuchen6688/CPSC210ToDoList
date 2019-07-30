package test;

import model.exceptions.TaskNotFoundException;
import model.GeneralTask;
import model.ToDoList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

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
    public void testGetDueDateAlreadySet() throws ParseException {

        String newDate = "2019-07-07";
        testTask.setDueDate(newDate);

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
    public void testSetDueDate() throws ParseException {
        // invoke method
        String newDate = "2019-07-07";
        testTask.setDueDate(newDate);
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
        ToDoList list = new ToDoList("test list");
        testTask.setListBelonged(list);

        assertEquals(list, testTask.getListBelonged());
        assertTrue(list.contains(testTask.getName()));
    }


    @Test
    public void testIsOverdueWhenNot() throws ParseException {
        testTask.setDueDate("3000-10-10");
        assertFalse(testTask.isOverdue());
    }

    @Test
    public void testOverdueWhenOverdue() throws ParseException {
        // invoke behavior
        testTask.setDueDate("1999-01-01");
        // check outcome
        assertTrue(testTask.isOverdue());
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
