package clanky;

import clanky.task.Task;
import clanky.task.Todo;
import clanky.task.Deadline;
import clanky.task.Event;
import clanky.ClankyException;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> list) throws ClankyException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            for (Task task : list) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e){
            throw new ClankyException("Failed to save tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws ClankyException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return list;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(parseLine(line));
            }
            reader.close();
        } catch (IOException e) {
            throw new ClankyException("Failed to load tasks: " + e.getMessage());
        }
        return list;
    }

    private Task parseLine(String line) throws ClankyException {
        String[] fields = line.split("\\|");
        for (int i = 0; i < fields.length; i ++) {
            fields[i] = fields[i].trim();
        }
        String type = fields[0];
        boolean isDone = fields[1].equals("1");

        Task task;
        switch (type) {
            case "T":
                task = new Todo(fields[2]);
                break;
            case "D":
                task = new Deadline(fields[2], fields[3]);
                break;
            case "E":
                task = new Event(fields[2], fields[3], fields[4]);
                break;
            default:
                throw new ClankyException("Corrupted Save File: Unkown Task Type '" + type + "'");
        }
        if (isDone) {
            task.setMark();
        }
        return task;
    }
}
