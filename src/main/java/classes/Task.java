package classes;

public class Task {
    private int id;
    private String description;
    private boolean isDone;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return String.format("%d. %s %s", id, description, isDone ? "- выполнено" : "");
    }
}
