package clanky.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate from, to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (from: " + from.format(outputFormat) + " to: " + to.format(outputFormat) + ")";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + from + " | " + to;
    }
}
