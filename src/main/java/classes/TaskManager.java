package classes;

import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int nextId;
    private StorageManager storageManager;

    public TaskManager() {
        storageManager = new StorageManager();
        this.tasks = storageManager.loadFromFile();

        if(tasks.isEmpty()) {
            this.nextId = 1;
        } else {
            this.nextId = tasks.getLast().getId() + 1;
        }
    }

    public void add(String description) {
        Task task = new Task(nextId, description);
        tasks.add(task);
        System.out.println("Задача добавлена");
        nextId++;

        storageManager.saveToFile(tasks);
    }

    public void list() {
        if (tasks.isEmpty()) {
            System.out.println("Список дел пуст");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void done(int id) {
        Task task = findById(id);
        if (task != null) {
            task.setDone(true);
            System.out.println("Задача отмечена как выполненная");
        } else {
            System.out.println("Задача с таким номером не найдена");
        }

        storageManager.saveToFile(tasks);
    }

    public void remove(int id) {
        Task task = findById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Задача удалена");
        } else {
            System.out.println("Задача с таким номером не найдена");
        }

        storageManager.saveToFile(tasks);
    }

    private Task findById(int id) {
        for (Task task : tasks) {
            if (id == task.getId()) {
                return task;
            }
        }
        return null;
    }
}
