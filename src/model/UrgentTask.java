package model;

import ui.ToDoListUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class UrgentTask extends GeneralTask {
    public UrgentTask(String taskName) {
        this.taskName = taskName;
    }

    public UrgentTask(String taskName, String dueDate) throws ParseException {
        this.taskName = taskName;
        this.dueDate = ToDoListUsage.sdf.parse(dueDate);
    }

    // well produce true if below 10 day out
    @Override
    public boolean closeToDue() {
        LocalDate currentDate = LocalDate.now();
        if (!(this.dueDate == null) && this.dueDate.before(java.sql.Date.valueOf(currentDate))) {
            return false;
        }
        Period period = Period.between(currentDate,this.dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        int days = period.getDays();
        if (days < 10) {
            return true;
        }
        return false;
    }
}
