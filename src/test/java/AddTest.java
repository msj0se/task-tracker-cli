import org.ejose.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AddTest {
    Task task = new Task();
    JSONArray jsonTask = task.getTaskArray();
    String addKeyword = "add";
    String command = "add Buy groceries";
    String description = command.substring(addKeyword.length()).trim();
    int id = 1;
    boolean idFound = false;
    boolean taskAdded = false;

    public AddTest() throws IOException {
    }

    @Test
    void addTaskTest() throws IOException {
        if (!description.isEmpty()) {
            task.addTask(description);
            taskAdded = true;
        }

        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getInt("ID") == id) {
                idFound = true;
                break;
            }
        }

        Assertions.assertTrue(taskAdded, "Task was not added in the JSON array");
        Assertions.assertTrue(idFound,"ID was not found in the JSON array");
    }
}
