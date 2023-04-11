import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws RuntimeException {
        // Создаем папку Games
        File gamesDir = new File("/Users/rdk//Games");
        gamesDir.mkdir();

        // Создаем директории в папке Games
        File srcDir = new File(gamesDir, "src");
        srcDir.mkdir();
        File resDir = new File(gamesDir, "res");
        resDir.mkdir();
        File savegamesDir = new File(gamesDir, "savegames");
        savegamesDir.mkdir();
        File tempDir = new File(gamesDir, "temp");
        tempDir.mkdir();

        // Создаем директории в папке srs
        File mainDir = new File(srcDir, "main");
        mainDir.mkdir();
        File testDir = new File(srcDir, "test");
        testDir.mkdir();

        // Создаем директории в папке res
        File drawablesDir = new File(resDir, "drawables");
        drawablesDir.mkdir();
        File vectorsDir = new File(resDir, "vectors");
        vectorsDir.mkdir();
        File iconsDir = new File(resDir, "icons");
        iconsDir.mkdir();

        //Создаем файлы в папке main
        File mainFile = new File(mainDir, "Main.java");
        File utilsFile = new File(mainDir, "Utils.java");

        // Создаем файл в папке temp
        File tempFile = new File(tempDir, "temp.txt");

        // Записываем результаты создания файлов в лог
        StringBuilder log = new StringBuilder();
        try {
            mainFile.createNewFile();
            utilsFile.createNewFile();
            tempFile.createNewFile();
            log.append("Files were created successfully");
        } catch (IOException e) {
            log.append("Error while creating files: " + e.getMessage());
        }

        // Записываем лог в файл temp.txt
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            } catch(IOException e){
                System.out.println("Error while writing log to file: " + e.getMessage());
            }
        }
    }
