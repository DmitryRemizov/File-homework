
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws RuntimeException, IOException {
        // Создаем список директорий
        List<String> directories = List.of("/Users/rdk/Games/src", "/Users/rdk/Games/res",
                "/Users/rdk/Games/savegames", "/Users/rdk/Games/temp", "/Users/rdk/Games/src/main",
                "/Users/rdk/Games/src/test", "/Users/rdk/Games/res/drawables",
                "/Users/rdk/Games/res/vectors","/Users/rdk/Games/res/icons");

        // Создаем директории
        for (String directory : directories) {
            createDirectory(directory);
        }

        // Создаем файлы
        File mainFile = createFile("/Users/rdk/Games/src/main", "Main.java");
        File utilsFile = createFile("/Users/rdk/Games/src/main", "Utils.java");
        File tempFile = createFile("/Users/rdk/Games/temp", "temp.txt");

        // Записываем результаты создания файлов в лог
        StringBuilder log = new StringBuilder();
        try {
            log.append("Files were created successfully");
        } catch (Exception e) {
            log.append("Error while creating files: " + e.getMessage());
        }

        // Записываем лог в файл temp.txt
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Error while writing log to file: " + e.getMessage());
        }
    }

    static void createDirectory(String path) throws IOException {
        File dir = new File(path);
        StringBuilder log = new StringBuilder();
        if (dir.mkdir()) {
            log.append("Directory was created successfully");
        } else log.append("Directory was not created");
    }

    static File createFile(String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        StringBuilder log = new StringBuilder();
        if (file.createNewFile()) {
            log.append("File was created successfully");
        } else log.append("File was not created");
        return file;
    }
}
