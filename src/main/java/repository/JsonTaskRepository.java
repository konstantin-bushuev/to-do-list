package repository;

import exceptions.RepositoryException;
import model.Task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonTaskRepository implements TaskRepository{

    private final Path pathToStorage;

    public JsonTaskRepository(Path pathToStorage) {
        this.pathToStorage = pathToStorage;
    }

    @Override
    public void save(List<Task> tasks) throws RepositoryException {
        Gson gson = new Gson();
        String json = gson.toJson(tasks);

        try {
            Files.writeString(pathToStorage, json);
        } catch (IOException e) {
            throw new RepositoryException("Ошибка при записи в файл", e);
        }
    }

    @Override
    public List<Task> load() throws RepositoryException {
        if(Files.exists(pathToStorage)) {
            try {
                Gson gson = new Gson();
                String json = Files.readString(pathToStorage);
                List<Task> list = gson.fromJson(json, new TypeToken<List<Task>>(){}.getType());
                return list != null ? list : new ArrayList<>();
            } catch (IOException e) {
                throw new RepositoryException("Ошибка при чтении файла", e);
            } catch (com.google.gson.JsonParseException e) {
                throw new RepositoryException("Ошибка в формате данных", e);
            }
        }
        return new ArrayList<>();
    }
}
