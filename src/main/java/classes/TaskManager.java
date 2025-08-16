package classes;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    int nextId;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.nextId = 1;
    }

    public void add(String description) {
        Task task = new Task(nextId, description);
        tasks.add(task);
        System.out.println("Задача добавлена");
        nextId++;
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
    }

    public void remove(int id) {
        Task task = findById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Задача удалена");
        } else {
            System.out.println("Задача с таким номером не найдена");
        }
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
