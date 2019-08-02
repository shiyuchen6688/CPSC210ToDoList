package ui;


import model.GeneralTask;

public class ReturnTask {
    public static String formatOneNormalTaskWithDuedate(String name,  String date)  {
        return GeneralTask.INDENTATION + "Task: " + name
                + " Due Date:" + date;
    }

    public static String formatOneNormalTaskWithNoDuedate(String name)  {
        return GeneralTask.INDENTATION + "Task: " + name
                + " with no due date";
    }

    public static String formatOneOverdueTaskWithDuedate(String name,  String date)  {
        return GeneralTask.INDENTATION + "Overdue Task: " + name
                + " Due Date: " + date;
    }

}
