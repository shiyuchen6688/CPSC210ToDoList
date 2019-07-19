package model;

import sun.java2d.loops.FillRect;
import ui.FileReaderAndWriter;
import ui.ToDoListUsage;
import ui.Tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class RegularTask extends GeneralTask {


    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dueDate to null, set overdue to false
    public RegularTask(String taskName) {

        this.taskName = taskName;
        this.dueDate = null;
        this.status = false;
    }

    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dueDate to gicen dueDate, set overdue to false
    public RegularTask(String taskName, String dueDate) throws ParseException {
        this.taskName = taskName;
        if (dueDate == null) {
            this.dueDate = null;
        } else {
            this.dueDate = ToDoListUsage.sdf.parse(dueDate);
        }
        this.status = false;
    }

    @Override
    public boolean closeToDue() {
        LocalDate currentDate = LocalDate.now();
        if (!(this.dueDate == null) && this.dueDate.before(java.sql.Date.valueOf(currentDate))) {
            return false;
        }
        Period period = Period.between(currentDate, this.dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        int days = period.getDays();
        if (days < 3) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) throws ParseException {
//        RegularTask t1 = new RegularTask("t1","2019-07-17");
//        RegularTask t2 = new RegularTask("t1","2019-07-25");
//        System.out.println(t1.closeToDue());
//        System.out.println(t2.closeToDue());
//    }

}
