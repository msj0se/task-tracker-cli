package org.ejose;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Task implements Status, DateTask {

    private String description;
    private static int currentID = 0;
    JSONArray taskArray;
    File taskFile = new File("tasks.txt");

    public JSONArray getTaskArray() {
        return taskArray;
    }

    public Task() throws IOException {
        if (taskFile.exists()) {
            String content = new String(Files.readAllBytes(Paths.get("tasks.txt")));
            taskArray = new JSONArray(content);
        } else {
            taskArray = new JSONArray();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toDo() {
        return "To-do";
    }

    @Override
    public String inProgress() {
        return "In progress";
    }

    @Override
    public String done() {
        return "Done";
    }

    @Override
    public LocalDate createdAt() {
        return LocalDate.now();
    }

    @Override
    public LocalDate updatedAt() {
        return LocalDate.now();
    }

    public int generateID() {
        return ++currentID;
    }

    public void saveTasks() throws IOException {
        try (FileWriter taskFileWriter = new FileWriter(taskFile)) {
            taskFileWriter.write(taskArray.toString(2));
        }
    }

    public void addTask(String description) throws IOException {
        JSONObject taskObject = new JSONObject();
        setDescription(description);

        taskObject.put("ID", generateID());
        taskObject.put("Description", getDescription());
        taskObject.put("Status", toDo());
        taskObject.put("Creation date", createdAt());

        taskArray.put(taskObject);
        saveTasks();
    }

    public void updateTask(int id, String newValue) throws IOException {
        boolean found = false;

        for (int i = 0; i < taskArray.length(); i++) {
            JSONObject task = taskArray.getJSONObject(i);
            if (task.getInt("ID") == id) {
                task.put("Description", newValue);
                task.put("Update date", updatedAt());
                saveTasks();
                found = true;
                break;
            }
        }

        if (!found) {
            System.err.println("ID not found to be updated");
        }
    }

    public void deleteTask(int id) throws IOException {
        boolean found = false;

        for (int i = 0; i < taskArray.length(); i++) {
            JSONObject task = taskArray.getJSONObject(i);
            if (task.getInt("ID") == id) {
                taskArray.remove(i);
                saveTasks();
                found = true;
                break;
            }
        }

        if (!found) {
            System.err.println("ID not found to be deleted");
        }
    }

    public void listTaskDone() {
        listTasksByStatus("done");
    }

    public void listTaskInProgress() {
        listTasksByStatus("in progress");
    }

    public void listTaskToDo() {
        listTasksByStatus("to-do");
    }

    private void listTasksByStatus(String status) {
        boolean found = false;

        for (int i = 0; i < taskArray.length(); i++) {
            JSONObject task = taskArray.getJSONObject(i);
            if (task.getString("Status").equalsIgnoreCase(status)) {
                System.out.println(task.toString(2));
                System.out.println("------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found with status '" + status + "'");
        }
    }

    public void listAllTask() {
        if (taskArray.length() == 0) {
            System.out.println("No tasks found");
        } else {
            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject task = taskArray.getJSONObject(i);
                System.out.println(task.toString(2));
                System.out.println("------------");
            }
        }
    }

    public void markTask(int id, String status) throws IOException {
        boolean found = false;

        for (int i = 0; i < taskArray.length(); i++) {
            JSONObject task = taskArray.getJSONObject(i);
            if (task.getInt("ID") == id) {
                switch (status) {
                    case "to-do":
                        task.put("Status", toDo());
                        task.put("Update date", updatedAt());
                        break;
                    case "in-progress":
                        task.put("Status", inProgress());
                        task.put("Update date", updatedAt());
                        break;
                    case "done":
                        task.put("Status", done());
                        task.put("Update date", updatedAt());
                        break;
                    default:
                        System.err.println("Status not registered");
                        break;
                }
                saveTasks();
                found = true;
                break;
            }
        }

        if (!found) {
            System.err.println("Task not found to be marked");
        }
    }
}
