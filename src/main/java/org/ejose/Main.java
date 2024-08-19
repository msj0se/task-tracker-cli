package org.ejose;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Task task = new Task();
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            String addKeyword = "add";
            String deleteKeyword = "delete";
            String updateKeyword = "update";
            String listAllKeyword = "list";
            String listDoneKeyword = "list done";
            String listToDoKeyword = "list todo";
            String listInProgressKeyword = "list in-progress";
            String markInProgressKeyword = "mark in-progress";
            String markDoneKeyword = "mark done";
            String exitKeyword = "exit";

            String command = scanner.nextLine();

            if (command.startsWith(addKeyword)) {
                String description = command.substring(addKeyword.length()).trim();
                if (!description.isEmpty()) {
                    task.addTask(description);
                } else {
                    System.out.println("Description cannot be empty");
                }
            } else if (command.startsWith(deleteKeyword)) {
                try {
                    String idStr = command.substring(deleteKeyword.length()).trim();
                    int id = Integer.parseInt(idStr);

                    task.deleteTask(id);

                    System.out.println("Task with ID " + id + " has been deleted");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format, please enter a valid number");
                }
            } else if (command.startsWith(updateKeyword)) {
                try {
                    String[] parts = command.substring(updateKeyword.length()).trim().split(" ", 2);
                    int id = Integer.parseInt(parts[0]);
                    String newDescription = parts[1];

                    task.updateTask(id, newDescription);
                    System.out.println("Task with ID " + id + " has been updated");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format, please enter a valid number");
                }
            } else if (command.equals(listAllKeyword)) {
                task.listAllTask();
            } else if (command.equals(listDoneKeyword)) {
                task.listTaskDone();
            } else if (command.equals(listToDoKeyword)) {
                task.listTaskToDo();
            } else if (command.equals(listInProgressKeyword)) {
                task.listTaskInProgress();
            } else if (command.startsWith(markInProgressKeyword)) {
                try {
                    String idStr = command.substring(markInProgressKeyword.length()).trim();
                    int id = Integer.parseInt(idStr);

                    task.markTask(id, "in-progress");
                    System.out.println("Task with ID " + id + " marked as in progress");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format, please enter a valid number");
                }
            } else if (command.startsWith(markDoneKeyword)) {
                try {
                    String idStr = command.substring(markDoneKeyword.length()).trim();
                    int id = Integer.parseInt(idStr);

                    task.markTask(id, "done");
                    System.out.println("Task with ID " + id + " marked as done");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format, please enter a valid number");
                }
            } else if (command.equals(exitKeyword)) {
                valid = true;
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}