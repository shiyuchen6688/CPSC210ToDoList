package ui;

public enum ButtonNames {
    OVERDUETASK ("Print All Overdue Task"),
    ALLTASK ("Print All Task");
    // TODO figure this out
//    ON ("On"),
//    OFF ("Off");

    private final String name;

    ButtonNames(String name){
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue(){
        return name;
    }

}
