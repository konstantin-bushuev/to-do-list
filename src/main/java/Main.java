import classes.TaskManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final String menuTxt = "Команды:\n" +
                "add TXT - Добавить задачу (TXT - описание задачи. Например: add Покормить кота)\n" +
                "list - Вывести полный список задач\n" +
                "done N - Отметить задачу как выполненную (N - номер задачи. Например: done 1)\n" +
                "remove N - Удалить задачу (N - номер задачи. Например: remove 1)\n" +
                "quit - выйти\n";

        TaskManager tasks = new TaskManager();

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String command = "";
        System.out.println(menuTxt);

        while (true) {
            System.out.println("\nВведите команду");
            userInput = scanner.nextLine().trim();
            command = userInput.toLowerCase();

            if (command.startsWith("add")) {
                if (userInput.length() == 3) {
                    System.out.println("Отсутствует описание задачи");
                    continue;
                }
                tasks.add(userInput.substring(3).trim());
            } else if (command.equals("list")) {
                tasks.list();
            } else if (command.startsWith("done")) {
                if (userInput.length() == 4) {
                    System.out.println("Отсутствует номер задачи");
                    continue;
                }
                String number = userInput.substring(4).trim();
                int taskNumber = 0;
                try {
                    taskNumber = Integer.parseInt(number);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка в номере задачи");
                    continue;
                }
                tasks.done(taskNumber);
            } else if (command.startsWith("remove")) {
                if (userInput.length() == 6) {
                    System.out.println("Отсутствует номер задачи");
                    continue;
                }
                String number = userInput.substring(6).trim();
                int taskNumber = 0;
                try {
                    taskNumber = Integer.parseInt(number);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка в номере задачи");
                    continue;
                }
                tasks.remove(taskNumber);
            } else if (command.equals("quit")) {
                break;
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }
}
