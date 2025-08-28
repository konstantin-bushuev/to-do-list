package repository;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements TaskRepository {

    private List<Task> storage = new ArrayList<>();

    @Override
    public void save(List<Task> tasks) {
        this.storage = new ArrayList<>(tasks);

    }

    @Override
    public List<Task> load() {
        return new ArrayList<>(storage);
    }
}
