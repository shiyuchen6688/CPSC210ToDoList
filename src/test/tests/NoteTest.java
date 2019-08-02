package tests;

import model.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {
    private Note testNote;

    @BeforeEach
    public void setUp() {
        testNote = new Note("test note");
    }

    @Test
    public void testConstructor() {
        assertEquals("test note", testNote.getContent());
    }

    @Test
    public void testDisplay() {
        testNote.display("     ");
    }
}
