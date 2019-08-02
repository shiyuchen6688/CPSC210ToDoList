package model;


public class Note extends Element {
    String content;

    public Note(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public void display(String indentLevel) {
        System.out.println(indentLevel + content);
    }
}
