//package ui;
//
//import model.*;
//
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.*;
//
//public class FileReaderAndWriter {
//
//    public static final String MESSAGE_END_OUTPUT = "Goodbye, your tasks have been saved";
//
//
//    private static FileWriter outputFileWriter;
//    private static FileWriter inputFileWriter;
//    private static FileWriter fileNamesWriter;
//    private static String chosenFile;
//
//
//    public FileReaderAndWriter(String inputFileName, String outputFileName) throws IOException {
//        outputFileWriter = new FileWriter(outputFileName, false);
//        inputFileWriter = new FileWriter(inputFileName, true);
//        fileNamesWriter = new FileWriter("filenames.txt", true);
//        this.chosenFile = inputFileName;
//    }
//
//    public FileWriter getOutPutFileWriter() {
//        return outputFileWriter;
//    }
//
//    public FileWriter getInPutFileWriter() {
//        return inputFileWriter;
//    }
//
//    public String getChosenFile() {
//        return chosenFile;
//    }
//
//
//    public List<String> addHistryIntoMapAndReturnLoad(ToDoMap map)
//            throws IOException, ParseException {
//        List<String> msgList = setUpMsgListAddInitialSentence();
//        List<String> lines = Files.readAllLines((Paths.get(chosenFile)));
//
//        for (String line : lines) {
//            ArrayList<String> partsOfLine = splitOnTwoSpace(line);
//            String listName = partsOfLine.get(0);
//            String taskName = partsOfLine.get(1);
//            String dueDateAsString;
//            if (partsOfLine.get(2).equals("None")) {
//                dueDateAsString = "no due date";
//            } else {
//                dueDateAsString = formatDueDateAsString(partsOfLine);
//            }
//            msgList.add("List: " + listName + " " + "| Task: " + taskName + " "
//                    + "| Duedate(yyyy-MM-dd): " + dueDateAsString);
//            // get the the list from the map
//            ToDoList curList = setUpCurList(map, listName);
//
//            addTaskNoDuplicate(taskName, dueDateAsString, curList);
//
//
//        }
//        return addFinishedStringToMsgList(msgList);
//    }
//
//    private List<String> addFinishedStringToMsgList(List<String> msgList) {
//        msgList.add("All history above has already been saved");
//        return msgList;
//    }
//
//    private ToDoList setUpCurList(ToDoMap map, String listName) {
//        ToDoList curList = map.getList(listName);
//
//        // if this list is not already in, create and add it
//        curList = ifCurLIstNullCreateNew(map, listName, curList);
//        return curList;
//    }
//
//    private List<String> setUpMsgListAddInitialSentence() {
//        List<String> msgList = new ArrayList<>();
//        msgList.add("\n---------- Here is all the task you added Before ----------");
//        return msgList;
//    }
//
//    private String formatDueDateAsString(ArrayList<String> partsOfLine) {
//        String dueDateAsString;
//        List<String> dueDateStrings = splitOnLine(partsOfLine.get(2));
//        String dueYearString = dueDateStrings.get(0);
//        String dueMonthString = dueDateStrings.get(1);
//        String dueDayString = dueDateStrings.get(2);
//
//
//        String dueYear = splitOnColonSpace(dueYearString).get(1);
//        String dueMonth = splitOnColonSpace(dueMonthString).get(1);
//        String dueDay = splitOnColonSpace(dueDayString).get(1);
//
//        dueDateAsString = dueYear + "-" + dueMonth + "-" + dueDay;
//        return dueDateAsString;
//    }
//
//
//    private void addTaskNoDuplicate(String taskName, String dueDate, ToDoList curList)
//            throws ParseException {
//        List<Task> allOriginalTask = curList.getTasks();
//        Task newTask;
//        if (!dueDate.equals("no due date")) {
//            newTask = new RegularTask(taskName, dueDate);
//        } else {
//            newTask = new RegularTask(taskName);
//        }
//
//        // add this task into this list
//        if (!allOriginalTask.contains(newTask)) {
//            curList.addTask(newTask);
//        }
//    }
//
//    private ToDoList ifCurLIstNullCreateNew(ToDoMap map, String listName, ToDoList curList) {
//        if (curList == null) {
//            curList = new ToDoList(listName);
//            map.addToDoList(curList);
//        }
//        return curList;
//    }
//
//    // save history of a map of list
//    public static void saveAllHistoryInMapToInput(ToDoMap toDoMap) throws IOException {
//        cleanInputFile();
//        Map<String, ToDoList> map = toDoMap.getMap();
//        for (String name : map.keySet()) {
//            saveAllHistoryInListToInput(name, map.get(name));
//        }
//
//        inputFileWriter.close();
//    }
//
//
//    // save history of a single list
//    // MODIFIES: inputfile.txt
//    // EFFECTS: save all tasks in s single list to inputfile.txt
//    public static void saveAllHistoryInListToInput(String listName, ToDoList toDoList) throws IOException {
//
//
//        List<Task> tasks = toDoList.getTasks();
//        for (int i = 0; i < tasks.size(); i++) {
//            Task t = tasks.get(i);
//            String name = t.getName();
//
//            String date = "";
//            if (t.getDueDate() != null) {
//                // date = GeneralTask.sdf.format(t.getDueDate());
//                LocalDate localDate = t.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                int year = localDate.getYear();
//                int month = localDate.getMonthValue();
//                int day = localDate.getDayOfMonth();
//                date = "year: " + year + "-" + "month: " + month + "-" + "day: " + day;
//            } else {
//                date = "None";
//            }
//
//            // stringToAdd is the actual line that get added
//            String stringToAdd = listName + "  " + name + "  " + date + "\n";
//            inputFileWriter.write(stringToAdd);
//        }
//
//    }
//
//    private static void cleanInputFile() throws FileNotFoundException {
//        PrintWriter writer = new PrintWriter(chosenFile);
//        writer.print("");
//        writer.close();
//    }
//
//    // MODIFIES: outputfile.txt
//    // EFFECTS: add things in inputfile.txt to outputfile.txt
//    public static void copyInputToOutput() throws IOException {
//        List<String> linesOutput = Files.readAllLines(Paths.get(chosenFile));
//
//        linesOutput.add(MESSAGE_END_OUTPUT);
//        for (int i = 0; i < linesOutput.size(); i++) {
//            String line = linesOutput.get(i);
//            if (line.equals(MESSAGE_END_OUTPUT)) {
//                System.out.println(MESSAGE_END_OUTPUT);
//                outputFileWriter.write(line + "\n");
//            } else {
//                ArrayList<String> partsOfLine = splitOnTwoSpace(line);
//
//                // first two is print, the third one is line
////                System.out.print("List: " + partsOfLine.get(0) + " ");
////                System.out.print("Task: " + partsOfLine.get(1) + " ");
////                System.out.println("DueDate: " + partsOfLine.get(2));
//                outputFileWriter.write(line + "\n");
//            }
//        }
//        outputFileWriter.close();
//    }
//
//    public static void addNewFileNameToFileNames(String fileName) {
//        try {
//            fileNamesWriter.write("\n" + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            fileNamesWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // helper to split words in add HistryIntoMapAndReturnLoad and save. File download from CPSC-210 EDX.
//    public static ArrayList<String> splitOnTwoSpace(String line) {
//        String[] splits = line.split("  ");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//    public static ArrayList<String> splitOnLine(String line) {
//        String[] splits = line.split("-");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//    public static ArrayList<String> splitOnColonSpace(String line) {
//        String[] splits = line.split(": ");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//
//}
