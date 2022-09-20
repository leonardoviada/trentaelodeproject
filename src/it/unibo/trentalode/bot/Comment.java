package it.unibo.trentalode.bot;

import java.util.Date;

public class Comment {
    private String owner;
    private String text;
    private Date dateTime;

    public Comment(String owner, String text, Date dateTime) {
        this.owner = owner;
        this.text = text;
        this.dateTime = dateTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
