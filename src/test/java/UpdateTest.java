import org.ejose.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class UpdateTest {
    Task task = new Task();
    JSONArray jsonTask = task.getTaskArray();
    String updateKeyword = "update";
    String command = "update 1 Buy milk";
    String [] commandArray = command.substring(updateKeyword.length()).trim().split(" ", 2);
    int id = Integer.parseInt(commandArray[0]);
    String newDescription = commandArray[1];
    boolean taskUpdated = false;

    public UpdateTest() throws IOException {
    }

    @Test
    void updateTaskTest() throws IOException {
        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getInt("ID") == id) {
                task.updateTask(id, newDescription);
                taskUpdated = true;
                break;
            }
        }

        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            Assertions.assertEquals(eachTask.getString("Description"), newDescription);
        }

        Assertions.assertTrue(taskUpdated, "Task was not updated in the JSON array");
    }
}
