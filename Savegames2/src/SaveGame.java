import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SaveGame {

    public static void main(String[] args) {
        // Создание трех экземпляров класса GameProgress
        GameProgress progress1 = new GameProgress(100, 5, 1, 0);
        GameProgress progress2 = new GameProgress(80, 3, 2, 500);
        GameProgress progress3 = new GameProgress(50, 1, 3, 1000);

        // Путь до папки savegames
        String folderPath = "/users/rdk/Games/savegames/";

        // Создание файлов сохранений
        save(progress1, folderPath + "progress1.ser");
        save(progress2, folderPath + "progress2.ser");
        save(progress3, folderPath + "progress3.ser");

        // Запаковка файлов сохранений в архив
        zipFolder(folderPath, folderPath + "saves.zip");

        // Удаление файлов сохранений, лежащих вне архива
        deleteFiles(folderPath);
    }

    private static void save(GameProgress progress, String path) {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(progress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipFolder(String sourceFolder, String zipFilePath) {
        try (
                FileOutputStream fos = new FileOutputStream(zipFilePath);
                ZipOutputStream zos = new ZipOutputStream(fos)
        ) {
            File fileToZip = new File(sourceFolder);
            addFilesToZip(zos, fileToZip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addFilesToZip(ZipOutputStream zos, File fileToZip) {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                addFilesToZip(zos, childFile);
            }
            return;
        }

        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            String zipFilePath = fileToZip.getName();
            ZipEntry zipEntry = new ZipEntry(zipFilePath);
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFiles(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (!file.getName().equals("saves.zip")) {
                file.delete();
            }
        }
    }
}