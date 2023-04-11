import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        GameProgress game1 = new GameProgress(100, 10, 5, 0.5);
        GameProgress game2 = new GameProgress(50, 5, 3, 0.2);
        GameProgress game3 = new GameProgress(75, 8, 4, 0.3);
        FileOutputStream fos1 = new FileOutputStream("/users/rdk/Games/savegames/game1.bin");
        FileOutputStream fos2 = new FileOutputStream("/users/rdk/Games/savegames/game2.bin");
        FileOutputStream fos3 = new FileOutputStream("/users/rdk/Games/savegames/game3.bin");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
        oos1.writeObject(game1);
        oos2.writeObject(game2);
        oos3.writeObject(game3);
        oos1.close();
        oos2.close();
        oos3.close();
        FileOutputStream fos = new FileOutputStream("/users/rdk/Games/savegames.zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        addToZipFile(zos, "/users/rdk/Games/savegames/game1.bin");
        addToZipFile(zos, "/users/rdk/Games/savegames/game2.bin");
        addToZipFile(zos, "/users/rdk/Games/savegames/game3.bin");
        zos.close();
        File file1 = new File("/users/rdk/Games/savegames/game1.bin");
        File file2 = new File("/users/rdk/Games/savegames/game2.bin");
        File file3 = new File("/users/rdk/Games/savegames/game3.bin");
        file1.delete();
        file2.delete();
        file3.delete();
    }

    private static void addToZipFile(ZipOutputStream zos, String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }
        zos.closeEntry();
        fis.close();
    }
}