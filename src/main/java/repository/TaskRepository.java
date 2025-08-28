package repository;

import exceptions.RepositoryException;
import model.Task;

import java.util.List;

public interface TaskRepository {

    void save(List<Task> tasks) throws RepositoryException;

    List<Task> load() throws RepositoryException;
}
