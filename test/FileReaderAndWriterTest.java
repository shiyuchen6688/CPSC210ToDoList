//import model.RegularTask;
//import model.Task;
//import model.ToDoList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ui.FileReaderAndWriter;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class FileReaderAndWriterTest {
//    private final String NAME_TEST1 = "test1";
//    private final String NAME_TEST2 = "test2";
//    private final String DUEDATE_TEST1 = "1111-11-11";
//    private final String DUEDATE_TEST2 = "2222-22-22";
//
//    private FileReaderAndWriter testFileReaderAndWriter;
//    private ToDoList testToDoList;
//    private Task task1;
//    private Task task2;
//
//    @BeforeEach
//    public void setup() throws ParseException, IOException {
//        testToDoList = new ToDoList();
//        testFileReaderAndWriter = new FileReaderAndWriter(testToDoList, "inputfile.txt");
//        task1 = new RegularTask(NAME_TEST1);
//        task1.setDueDate(DUEDATE_TEST1);
//        task2 = new RegularTask(NAME_TEST2);
//        task2.setDueDate(DUEDATE_TEST2);
//    }
//
//    @Test
//    public void testLoad() throws IOException, ParseException {
//        List<String> allTaskName = getAllTasksNames(testToDoList.getTasks());
//        assertFalse(allTaskName.contains(NAME_TEST1));
//        testFileReaderAndWriter.getInputFileWriter().write(NAME_TEST1+ "  " + DUEDATE_TEST1);
//
//        testFileReaderAndWriter.load();
//        allTaskName = getAllTasksNames(testToDoList.getTasks());
//        assertFalse(allTaskName.contains(NAME_TEST1));
//    }
//
//    @Test
//    public void testSaveAllHistoryToInput() throws IOException, ParseException {
//        List<String> lines = Files.readAllLines(Paths.get("inputfile.txt"));
//        assertFalse(lines.contains("test1  " + DUEDATE_TEST1));
//
//        testToDoList.addTask(task1);
//        testFileReaderAndWriter.saveAllHistoryToInput();
//        lines = Files.readAllLines(Paths.get("inputfile.txt"));
//
//        assertEquals("test1  " + DUEDATE_TEST1 , lines.get(lines.size()-1));
//    }
//
//    @Test
//    public void testCopyInputToOutput() throws IOException, ParseException {
//        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
//        assertFalse(lines.contains("String to testCopyInputToOutput\n"));
//
//        testFileReaderAndWriter.copyInputToOutput();
//        lines = Files.readAllLines(Paths.get("outputfile.txt"));
//
//        assertEquals("test1  " + DUEDATE_TEST1, lines.get(lines.size() - 2));
//        assertEquals("Goodbye, your tasks have been saved", lines.get(lines.size() - 1));
//    }
//
//
//    public List<String> getAllTasksNames(List<Task> tasks) {
//        List<String> result = new ArrayList<>();
//        for (Task t: tasks) {
//            result.add(t.getTaskName());
//        }
//        return result;
//    }
//
//}
