package com.example.notes_final;

// Note.java
public class Note {
    private String title;
    private String content;

    public Note() {
        // Default constructor required for Firebase
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
