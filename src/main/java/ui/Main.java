package ui;

import exceptions.RepositoryException;
import model.Task;
import repository.InMemoryRepository;
import repository.JsonTaskRepository;
import repository.TaskRepository;
import service.TaskManager;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        final String menuTxt = "Команды:\n" +
                "add TXT - Добавить задачу (TXT - описание задачи. Например: add Покормить кота)\n" +
                "list - Вывести полный список задач\n" +
                "done N - Отметить задачу как выполненную (N - номер задачи. Например: done 1)\n" +
                "remove N - Удалить задачу (N - номер задачи. Например: remove 1)\n" +
                "quit - выйти\n";

        Path path = Path.of("storage.json");
        TaskRepository storage = new JsonTaskRepository(path);
        //InMemoryRepository storage = new InMemoryRepository();

        TaskManager tasks = new TaskManager(storage);
        CommandProcessor processor = new CommandProcessor(tasks);

        Scanner scanner = new Scanner(System.in);
        System.out.println(menuTxt);

        while (true) {
            System.out.println("\nВведите команду");
            String userInput = scanner.nextLine();

            if (!processor.handle(userInput)) {
                break;
            }
        }

        System.out.println("Работа завершена");
    }
}
