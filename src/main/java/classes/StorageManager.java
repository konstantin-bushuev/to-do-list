package classes;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {

    private Path pathToStorage;

    public StorageManager() {
        this.pathToStorage = Path.of("storage.json");
    }

    public void saveToFile(List<Task> tasks) {
        Gson gson = new Gson();
        String json = gson.toJson(tasks);

        try {
            Files.writeString(pathToStorage, json);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл", e);
        }
    }

    public List<Task> loadFromFile() {
        if(Files.exists(pathToStorage)) {
            try {
                Gson gson = new Gson();
                String json = Files.readString(pathToStorage);
                return gson.fromJson(json, new TypeToken<List<Task>>(){}.getType());
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при чтении файла", e);
            }
        }
        return new ArrayList<>();
    }
}
