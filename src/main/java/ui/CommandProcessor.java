package ui;

import exceptions.RepositoryException;
import model.Task;
import service.TaskManager;

import java.util.List;

public class CommandProcessor {

    private final TaskManager taskManager;

    public CommandProcessor(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public boolean handle(String userInput) {
        String[] parts = userInput.trim().split("\\s+", 2);
        String commandStr = parts[0].toUpperCase();
        String argument = parts.length > 1 ? parts[1] : "";

        try {
            Commands command = Commands.valueOf(commandStr);
            switch (command) {
                case ADD -> { return processAdd(argument); }
                case DONE -> { return processDone(argument); }
                case REMOVE -> { return processRemove(argument); }
                case LIST -> { return processList(); }
                case QUIT -> { return false; }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неизвестная команда");
        }
        return true;
    }


    private boolean processAdd(String taskDescription) {
        if (taskDescription.isBlank()) {
            System.out.println("Отсутствует описание");
            return true;
        } else {
            try {
                taskManager.add(taskDescription);
                System.out.println("Задача добавлена");
                return true;
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    private boolean processDone(String taskId) {
        if(taskId.isBlank()) {
            System.out.println("Не указан номер задачи");
            return true;
        } else {
            try {
                int id = Integer.parseInt(taskId);
                boolean action = taskManager.done(id);
                System.out.println(action ? "Задача выполнена" : "Задача не найдена");
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка в номере задачи");
                return true;
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    private boolean processRemove(String taskId) {
        if(taskId.isBlank()) {
            System.out.println("Не указан номер задачи");
            return true;
        } else {
            try {
                int id = Integer.parseInt(taskId);
                boolean action = taskManager.remove(id);
                System.out.println(action ? "Задача удалена" : "Задача не найдена");
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка в номере задачи");
                return true;
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    private boolean processList() {
        List<Task> tasks = taskManager.list();
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
        return true;
    }
}
