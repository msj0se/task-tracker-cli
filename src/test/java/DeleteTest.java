import org.ejose.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DeleteTest {
    Task task = new Task();
    JSONArray jsonTask = task.getTaskArray();
    String deleteKeyword = "delete";
    String command = "delete 1";
    String idStr = command.substring(deleteKeyword.length()).trim();
    int id = Integer.parseInt(idStr);
    boolean idFound = false;

    public DeleteTest() throws IOException {
    }

    @Test
    void deleteTaskTest() throws IOException {
        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getInt("ID") == id) {
                task.deleteTask(id);
                idFound = true;
                break;
            }
        }

        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            Assertions.assertNotEquals(id, eachTask.get("ID"));
        }

        Assertions.assertTrue(idFound, "Task was not deleted in the JSON array");
    }
}
