import org.ejose.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MarkTest {
    Task task = new Task();
    JSONArray jsonTask = task.getTaskArray();
    String markInProgressKeyword = "mark in-progress";
    String markDoneKeyword = "mark done";
    String commandInProgress = "mark in-progress 1";
    String commandDone = "mark done 1";
    String idInProgressStr = commandInProgress.substring(markInProgressKeyword.length()).trim();
    int idInProgress = Integer.parseInt(idInProgressStr);
    String idDoneStr =  commandDone.substring(markDoneKeyword.length()).trim();
    int idDone = Integer.parseInt(idDoneStr);
    boolean taskMarked = false;

    public MarkTest() throws IOException {
    }

    @Test
    void markTaskInProgressTest() throws IOException {
        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getInt("ID") == idInProgress) {
                task.markTask(idInProgress, "in-progress");
                taskMarked = true;
                break;
            }
        }

        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            Assertions.assertEquals(task.inProgress(), eachTask.getString("Status"));
        }

        Assertions.assertTrue(taskMarked, "Task was not marked in the JSON array");
    }

    @Test
    void markTaskDoneTest() throws IOException {
        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            if (eachTask.getInt("ID") == idDone) {
                task.markTask(idDone, "done");
                taskMarked = true;
                break;
            }
        }

        for (int i = 0; i < jsonTask.length(); i++) {
            JSONObject eachTask = jsonTask.getJSONObject(i);
            Assertions.assertEquals(task.done(), eachTask.getString("Status"));
        }

        Assertions.assertTrue(taskMarked, "Task was not marked in the JSON array");
    }
}
