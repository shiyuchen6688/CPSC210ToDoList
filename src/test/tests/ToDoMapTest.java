package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoMapTest {
    private ToDoMap map;
    private ToDoList l1;
    private ToDoList l2;
    Task t1 = null;
    Task t2 = null;
    Task t3 = null;

    @BeforeEach
    public void setUp() {
        map = new ToDoMap();
        l1 = new ToDoList("l1");
        l2 = new ToDoList("l2");
    }


    @Test
    public void testAddToDoListAsStringDoesntExist() {
        String listName = "list 1";
        map.addToDoList(listName);

        assertTrue(map.contains(listName));
        assertEquals(new ToDoList(listName), map.getList(listName));
    }

    @Test
    public void testAddToDoListAsStringExist() {
        String listName = "list 1";
        map.addToDoList(listName);
        assertEquals(2, map.size());

        assertTrue(map.contains(listName));
        assertEquals(new ToDoList(listName), map.getList(listName));

        map.addToDoList(listName);
        assertEquals(2, map.size());
    }

    @Test
    public void testAddToDoListAsListDoesntExist() {

        map.addToDoList(l1);

        assertTrue(map.contains(l1.getName()));
        assertEquals(new ToDoList("l1"), map.getList(l1.getName()));
    }

    @Test
    public void testAddToDoListAsListExist() {

        map.addToDoList(l1);
        assertEquals(2, map.size());
        assertTrue(map.contains(l1.getName()));
        assertEquals(new ToDoList("l1"), map.getList(l1.getName()));

        map.addToDoList(l1);
        assertEquals(2, map.size());
    }

    @Test
    public void testReturnAllMapTasks() {
        t1 = new RegularTask("t1");
        t2 = new RegularTask("t2");
        t3 = new RegularTask("t3");

        addTasksToL1AndL2(t1, t2, t3);

        addL1L2ToMap();

        List<String> result = map.returnAllMapTasks();
        assertTrue(result.contains("-----Here is all of your task in list: " + l1.getName() + "-----"));
        assertTrue(result.contains(GeneralTask.INDENTATION + "Task: " + t1.getName()
                + " with no due date"));
        assertTrue(result.contains(GeneralTask.INDENTATION + "Task: " + t2.getName()
                + " with no due date"));
        assertTrue(result.contains("\n-----list: " + l1.getName() + " is done-----"));
        assertTrue(result.contains("-----Here is all of your task in list: " + l2.getName() + "-----"));
        assertTrue(result.contains(GeneralTask.INDENTATION + "Task: " + t3.getName()
                + " with no due date"));
        assertTrue(result.contains("\n-----list: " + l2.getName() + " is done-----"));
    }

    @Test
    public void testReturnAllMapOverdueTasks() {
        try {
            t1 = new RegularTask("t1");
            t2 = new RegularTask("t2", "2018-08-08");
            t3 = new RegularTask("t3", "2018-08-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        addTasksToL1AndL2(t1, t2, t3);

        addL1L2ToMap();

        List<String> result = map.returnMapAllOverdueTasks();
        assertTrue(result.contains("-----Here is all of your overdue task in list: " + l1.getName() + "-----"));
        assertTrue(result.contains(GeneralTask.INDENTATION + "Overdue Task: " + t2.getName()
                + " Due Date: " + GeneralTask.sdf.format(t2.getDueDate())));
        assertTrue(result.contains("\n-----list: " + l1.getName() + " is done-----"));
        assertTrue(result.contains("-----Here is all of your overdue task in list: " + l2.getName() + "-----"));
        assertTrue(result.contains(GeneralTask.INDENTATION + "Overdue Task: " + t3.getName()
                + " Due Date: " + GeneralTask.sdf.format(t2.getDueDate())));
        assertTrue(result.contains("\n-----list: " + l2.getName() + " is done-----"));
    }

    @Test
    public void testContainsFalse() {
        addL1L2ToMap();
        assertFalse(map.contains("c"));
    }

    @Test
    public void testContainsTrue() {
        String listName = "list 1";
        map.addToDoList(listName);

        assertTrue(map.contains(listName));
    }

    @Test
    public void testSize() {
        assertEquals(1, map.size());
        addL1L2ToMap();
        assertEquals(3, map.size());
    }

    @Test
    public void tetGetMap() {
        assertEquals(1,map.getMap().size());
    }

    private void addL1L2ToMap() {
        map.addToDoList(l1);
        map.addToDoList(l2);
    }

    private void addTasksToL1AndL2(Task t1, Task t2, Task t3) {
        l1.addTask(t1);
        l1.addTask(t2);
        l2.addTask(t3);
    }


}

