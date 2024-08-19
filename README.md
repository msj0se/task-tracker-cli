# Task Tracker CLI

## Overview

Task Tracker CLI is a simple command-line interface application for managing and tracking tasks. This project allows you to add, update, delete, and manage tasks using various commands. Tasks are stored in a JSON file, which is created if it does not exist. The application provides functionality to list tasks by their status and manage task states.

The project is built following the guidelines from the Task Tracker development roadmap available at [roadmap.sh](https://roadmap.sh/projects/task-tracker).

## Requirements

- Programming language: Java.
- Build tool: Maven.
- Testing framework: JUnit.
- Data storage: JSON file.

## Task properties

Each task has the following properties:

- id: A unique identifier for the task.
- description: A short description of the task.
- status: The status of the task (to-do, in-progress, done).
- createdAt: The date when the task was created.
- updatedAt: The date when the task was last updated.
