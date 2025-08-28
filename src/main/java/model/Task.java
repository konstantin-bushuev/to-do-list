package model;

import java.time.LocalDateTime;

public class Task {
    private final int id;
    private final String description;
    private boolean isDone;
    private String doneAt;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isDone = false;
        this.doneAt = "";
    }

    public int getId() {
        return id;
    }

    public void markDone() {
        if(!isDone) {
            this.isDone = true;
            this.doneAt = LocalDateTime.now().toString();
        }
    }

    @Override
    public String toString() {
        return String.format("%d. %s %s", id, description, isDone ? "- выполнено" : "");
    }
}
