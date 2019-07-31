package tests;

import model.Birthday;
import model.GeneralTask;
import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BirthdayTest extends GeneralTaskTest {
    private Birthday testBirthday;


    @BeforeEach
    public void setup() {
        try {
            testBirthday = new Birthday("shiyu", "2019-10-08");
            testTask = new Birthday("shiyu", "2019-10-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testConstructor() {
        assertEquals("Birthday of shiyu", testBirthday.getName());
        try {
            assertEquals(GeneralTask.sdf.parse("2019-10-08"), testBirthday.getDueDate());
        } catch (ParseException e) {
            System.out.println("GOOD, Just as expected");
        }
        assertFalse(testBirthday.getStatus());
        assertTrue(testBirthday.getGreeting().equals("Happy Birthday shiyu !"));
        assertTrue(testBirthday.getGift().equals("Birthday card for shiyu"));
    }

    @Test
    @Override
    public void testGetDueDate() {
        assertEquals("Birthday of shiyu", testBirthday.getName());
    }


    @Test
    public void testSetGreeting() {
        // check initial and setup
        assertTrue(testBirthday.getGreeting().equals("Happy Birthday shiyu !"));
        // invoke
        String newGreeting = "Greeting changed";
        testBirthday.setGreeting(newGreeting);
        // check outcome
        assertTrue(testBirthday.getGreeting().equals(newGreeting));
    }


    @Test
    public void testSetGift() {
        // check initial and setup
        assertTrue(testBirthday.getGift().equals("Birthday card for shiyu"));
        // invoke
        String newGift = "Gift changed";
        testBirthday.setGift(newGift);
        // check outcome
        assertTrue(testBirthday.getGift().equals(newGift));
    }

    @Test
    public void testGetGreeting() {
        // check initial and setup
        assertTrue(testBirthday.getGreeting().equals("Happy Birthday shiyu !"));
        // invoke
        String newGreeting = "Greeting changed";
        testBirthday.setGreeting(newGreeting);
        // check outcome
        assertTrue(testBirthday.getGreeting().equals(newGreeting));
    }

    @Test
    public void testGetGift() {
        // check initial and setup
        assertEquals("Birthday card for shiyu", testBirthday.getGift());
        // invoke
        String newGift = "Gift changed";
        testBirthday.setGift(newGift);
        // check outcome
        assertTrue(testBirthday.getGift().equals(newGift));
    }

    @Test
    public void testCloseToDueBothOkFalse()  {
        try {
            Birthday b = new Birthday("abby", "2020-12-08");
            assertFalse(b.closeToDue());
        } catch (ParseException e) {
            fail();
        } catch (OverDueException e)  {
            fail();
        } catch  (NoDueDateException e) {
            fail();
        }

    }

    @Test
    public void testCloseToDueBothOkTrue()  {
        try {
            long dayInMs = 1000 * 60 * 60 * 24;
            LocalDate currentDate = LocalDate.now();
            Date now = java.sql.Date.valueOf(currentDate);
            Date birthDay = new Date(now.getTime() + 2 * dayInMs);
            Birthday b = new Birthday("abby", GeneralTask.sdf.format(birthDay));
            assertTrue(b.closeToDue());
        } catch (ParseException e) {
            fail();
        } catch (OverDueException e)  {
            fail();
        } catch  (NoDueDateException e) {
            fail();
        }

    }
    @Test
    public void testCloseToDueNoDueDate() {
        Birthday b = new Birthday("abby");
        try {
            b.closeToDue();
            fail();
        } catch (OverDueException e)  {
            fail();
        } catch  (NoDueDateException e) {
            System.out.println("expected NoDueDateException");
        }
    }


    @Test
    public void testCloseToDueOverDue() {
        Birthday b = null;
        try {
            b = new Birthday("abby", "2016-08-08");
        } catch (ParseException e) {
            fail();
        }
        try {
            b.closeToDue();
            fail();
        } catch (OverDueException e)  {
            System.out.println("expected NoDueDateException");
        } catch  (NoDueDateException e) {
            fail();
        }
    }



    @Test
    @Override
    public void testSetName() {
        assertTrue("Birthday of shiyu".equals(testBirthday.getName()));
        testTask.setName("changed");
        Assert.assertEquals("changed", testTask.getName());
    }


    @Override
    @Test
    public void testGetName() {
        assertTrue("Birthday of shiyu".equals(testBirthday.getName()));
    }
}
