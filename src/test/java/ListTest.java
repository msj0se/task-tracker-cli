import org.ejose.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ListTest {
    Task task = new Task();

    public ListTest() throws IOException {
    }

    @Test
    void listAllTasksTest() throws IOException {
        String description = "Read a book";
        task.addTask(description);
        JSONArray jsonTask = task.getTaskArray();
        boolean taskFound = false;

        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getString("Description").equals(description)) {
                taskFound = true;
                Assertions.assertNotNull(eachTask.getInt("ID"), "ID should not be null");
                Assertions.assertEquals(task.toDo(), eachTask.getString("Status"), "Status should be 'To-do'");
                break;
            }
        }

        Assertions.assertTrue(taskFound, "The task with the description '" + description + "' should be in the array");
    }

    @Test
    void listTaskDoneTest() throws IOException {
        task.addTask("Study Java");
        task.markTask(1, "done");

        JSONArray jsonTask = task.getTaskArray();
        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getInt("ID") == 1) {
                Assertions.assertEquals(task.done(), eachTask.getString("Status"));
            }
        }
    }

    @Test
    void listTaskInProgressTest() throws IOException {
        task.addTask("Study POO");
        task.markTask(1, "in-progress");

        JSONArray jsonTask = task.getTaskArray();
        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getInt("ID") == 1) {
                Assertions.assertEquals(task.inProgress(), eachTask.getString("Status"));
            }
        }
    }

    @Test
    void listTaskToDoTest() throws IOException {
        task.addTask("Clean my room");

        JSONArray jsonTask = task.getTaskArray();
        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            Assertions.assertEquals(task.toDo(), eachTask.getString("Status"));
        }
    }
}
