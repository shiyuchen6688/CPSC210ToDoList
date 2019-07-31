package tests;

import model.*;
import model.exceptions.TaskAlreadyExistException;
import model.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ToDoAppUsage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {
    private ToDoList testToDoList;

    @BeforeEach
    public void setup() {
        testToDoList = new ToDoList("general");
    }


    // tests Constructor
    @Test
    public void testConstructorOneParam() {
        checkToDoEmptyDoesntContain(testToDoList);
        assertEquals("general", testToDoList.getName());
    }

    @Test
    public void testConstructorTwoParam() {
        ToDoList list = new ToDoList("cpsc-210", "school");
        checkToDoEmptyDoesntContain(list);
        assertEquals("cpsc-210", list.getName());
        assertEquals("school", list.getType());
    }

    @Test
    public void testGetMap() {
        ToDoMap map = new ToDoMap();
        testToDoList.setMapBelonged(map);
        assertEquals(map, testToDoList.getMap());
    }


    @Test
    public void testGetTasks() {
        checkToDoEmptyDoesntContain(testToDoList);
        Task t = new RegularTask("tests");
        testToDoList.addTask(t);
        assertEquals(1, testToDoList.getTasks().size());
        assertEquals(t, testToDoList.getTasks().get(0));
    }

    @Test
    public void testSetName() {
        testToDoList.setName("changed");
        assertEquals("changed", testToDoList.getName());
    }

    @Test
    public void testSetTasks() {
        Task t1 = new RegularTask("a");
        Task t2 = new RegularTask("b");
        List<Task> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        testToDoList.setTasks(list);
        assertTrue(testToDoList.contains("a"));
        assertTrue(testToDoList.contains("b"));
    }

    @Test
    public void testSetTypee() {
        testToDoList.setType("changed");
        assertEquals("changed", testToDoList.getType());
    }

    @Test
    public void testAddTaskWithTaskName() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask("tests task");

        checkToDoContainOnce();
    }

    @Test
    public void testAddTwoTaskWithTaskName() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask("tests task");
        testToDoList.addTask("tests task 2");


        assertTrue(testToDoList.contains("tests task"));
        assertTrue(testToDoList.contains("tests task 2"));
        assertEquals(testToDoList.size(), 2);
    }

    @Test
    public void testTwoParamAddTaskWithNewTask() {
        checkToDoEmptyDoesntContain(testToDoList);
        try {
            testToDoList.addTask(new RegularTask("tests task", "2019-08-08"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkToDoContainOnce();
    }

    @Test
    public void testTwoParamAddTaskWithTaskName() {
        checkToDoEmptyDoesntContain(testToDoList);
        try {
            testToDoList.addTask("tests task", "2019-08-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        checkToDoContainOnce();
    }

    @Test
    public void testTwoParamAddTwoTaskWithTaskName() {
        checkToDoEmptyDoesntContain(testToDoList);
        try {
            testToDoList.addTask("tests task", "2019-08-08");
            testToDoList.addTask("tests task 2", "2019-08-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }



        assertTrue(testToDoList.contains("tests task"));
        assertTrue(testToDoList.contains("tests task 2"));
        assertEquals(testToDoList.size(), 2);
    }

    @Test
    public void testAddTaskWithNewTask() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask(new RegularTask("tests task"));
        checkToDoContainOnce();
    }


    @Test
    public void testDeleteTaskExist() throws TaskNotFoundException {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask("tests task");

        checkToDoContainOnce();
        try {
            boolean b = testToDoList.deleteTask("tests task");
            assertTrue(b);
            checkToDoEmptyDoesntContain(testToDoList);
        } catch (TaskNotFoundException e) {
            fail("Caught TaskNotFound when shouldn't have");
        }

    }


    @Test
    public void testDeleteTaskDoesntExist() throws TaskNotFoundException {
        checkToDoEmptyDoesntContain(testToDoList);
        boolean b = testToDoList.deleteTask("tests task");
        assertFalse(b);


    }


    @Test
    public void testFindRegularTaskExist() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask("tests task");

        checkToDoContainOnce();
        try {
            Task t = testToDoList.findTask("tests task");
            assertEquals(t.getName(), "tests task");
        } catch (TaskNotFoundException e) {
            fail("caught TaskNotFound exception when shouldn't have");
        }

        checkToDoContainOnce();
    }

    @Test
    public void testFindUrgentTaskExist() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask(new UrgentTask("tests task"));

        checkToDoContainOnce();
        try {
            Task t = testToDoList.findTask("tests task");
            assertEquals(t.getName(), "tests task");
        } catch (TaskNotFoundException e) {
            fail("caught TaskNotFound exception when shouldn't have");
        }

        checkToDoContainOnce();
    }


    @Test
    public void testFindTaskDoesntExist() {
        checkToDoEmptyDoesntContain(testToDoList);
        try {
            Task t = testToDoList.findTask("tests task");
            fail();
        } catch (TaskNotFoundException e) {
            // expected
        }
    }

    @Test
    public void testFindUrgentTasDoesntkExist() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask(new UrgentTask("tests task"));

        try {
            Task t = testToDoList.findTask("a task do not exist");
            fail();
        } catch (TaskNotFoundException e) {
            // expected
        }

    }

    @Test
    public void testReturnAllListTasks() {
        testToDoList.addTask("tests task");
        try {
            testToDoList.addTask("tests task 2", "2019-08-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> result = testToDoList.returnAllListTasks();
        assertTrue(result.contains("Here is all of your task in list: "
                + testToDoList.getName()));
        assertTrue(result.contains(ToDoAppUsage.INDENTATION + "Task: " + "test task"
                + " with no due date"));
        assertTrue(result.contains(ToDoAppUsage.INDENTATION + "Task: " + "test task 2"
                + " Due Date:" + "2019-08-08"));
    }

    @Test
    public void testContainsExist() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask("tests task");

        assertTrue(testToDoList.contains("tests task"));
    }

    @Test
    public void testContainsDoesntExist() {
        checkToDoEmptyDoesntContain(testToDoList);
        assertFalse(testToDoList.contains("tests task"));
    }

    @Test
    public void testSize() {
        assertEquals(0, testToDoList.size());
    }

    @Test
    public void testSizeContainOne() {
        testToDoList.addTask("tests task");
        assertEquals(1, testToDoList.size());
    }

    @Test
    public void testGetAllTaskStringEmpty() {
        checkToDoEmptyDoesntContain(testToDoList);
        assertEquals("", testToDoList.getAllTaskAsString());
    }


    @Test
    public void testGetAllTwoTaskString() {
        checkToDoEmptyDoesntContain(testToDoList);
        testToDoList.addTask("task1");
        testToDoList.addTask("task2");
        assertEquals("task1\ntask2", testToDoList.getAllTaskAsString());
    }


    // tests for model.exceptions
    @Test
    public void testExpectTaskNotFoundException() {
        Assertions.assertEquals(0, testToDoList.size());

        try {
            testToDoList.findTask("a task");
            fail("No exception thrown");
        } catch (TaskNotFoundException e) {
            // Do nothing
        } catch (Exception e) {
            fail("Wrong exception was thrown");
        }

    }


    @Test
    public void testExpectNoException() {
        testToDoList.addTask("t1");
        Assertions.assertTrue(testToDoList.contains("t1"));

        try {
            testToDoList.findTask("t1");
        } catch (TaskNotFoundException t) {
            fail("Thrown TaskNotFound exception when should not");
        } catch (Exception e) {
            fail("Thrown unexpected exception when should not");
        }
    }

    private void checkToDoContainOnce() {
        assertEquals(testToDoList.size(), 1);
        assertTrue(testToDoList.contains("tests task"));
    }

    private void checkToDoEmptyDoesntContain(ToDoList list) {
        assertEquals(list.size(), 0);
        assertFalse(list.contains("tests task"));
    }

}
