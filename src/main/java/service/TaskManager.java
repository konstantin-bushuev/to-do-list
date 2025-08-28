package service;

import exceptions.RepositoryException;
import model.Task;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int nextId;
    private TaskRepository repository;

    public TaskManager(TaskRepository repository) {
        this.repository = repository;

        try {
            this.tasks = repository.load();
        } catch (RepositoryException e) {
            System.out.printf("Не удалось загрузить данные из файла: %s" +
                    "%nПриложение запущено с пустым списком задач%n%n", e.getMessage());
            this.tasks = new ArrayList<>();
        }

        if(tasks.isEmpty()) {
            this.nextId = 1;
        } else {
            this.nextId = tasks.getLast().getId() + 1;
        }
    }

    public boolean add(String description) throws RepositoryException {
        Task task = new Task(nextId, description);
        tasks.add(task);
        nextId++;
        repository.save(tasks);
        return true;
    }

    public List<Task> list() {
        return new ArrayList<>(tasks);
    }

    public boolean done(int id) throws RepositoryException {
        Task task = findById(id);
        if (task != null) {
            task.markDone();
            repository.save(tasks);
            return true;
        }
        return false;
    }

    public boolean remove(int id) throws RepositoryException {
        Task task = findById(id);
        if (task != null) {
            tasks.remove(task);
            repository.save(tasks);
            return true;
        }
        return false;
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
