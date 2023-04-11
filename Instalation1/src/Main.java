import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws RuntimeException, IOException {
        // Создаем список директорий
        List<String> directories = new ArrayList<>();
        directories.add("/Users/rdk/Games/src");
        directories.add("/Users/rdk/Games/res");
        directories.add("/Users/rdk/Games/savegames");
        directories.add("/Users/rdk/Games/temp");
        directories.add("/Users/rdk/Games/src/main");
        directories.add("/Users/rdk/Games/src/test");
        directories.add("/Users/rdk/Games/res/drawables");
        directories.add("/Users/rdk/Games/res/vectors");
        directories.add("/Users/rdk/Games/res/icons");

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
        dir.mkdir();
        StringBuilder log = new StringBuilder();
        log.append("Directory was created successfully");
    }

    static File createFile(String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        file.createNewFile();
        StringBuilder log = new StringBuilder();
        log.append("File was created successfully");
        return file;
    }
}