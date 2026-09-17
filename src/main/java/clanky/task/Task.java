package clanky.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setMark() {
        this.isDone = true;
    }

    public void setUnmark() {
        this.isDone = false;
    }

    // All Task Types will Override
    public String getTaskType() {
        return " ";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + description;
    }

    // Deadline & Event will Override
    public String toSaveFormat() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

}
