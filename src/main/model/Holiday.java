package model;

public interface Holiday {

    // MODIFIES: this
    // EFFECTS: set greeting
    void setGreeting(String greeting);

    // MODIFIES: this
    // EFFECTS: set gift
    void setGift(String gift);

    // EFFECTS: return greeting
    String getGreeting();

    // EFFECTS: return gift
    String getGift();
}
