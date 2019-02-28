package com.levelp.example.web;

import com.levelp.example.Room;

import javax.validation.constraints.Pattern;

public class AddMessageFormBean {

    private String subject;
    private String text;
    @Pattern(regexp = "[a-zA-Z0-9_-]+(:[a-zA-Z0-9_-]+)+", message = "Неверный формат вложенных файлов")
    private String attachedFiles;
    private String name;
    private String passportNumber;
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(String attachedFiles) {
        this.attachedFiles = attachedFiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
