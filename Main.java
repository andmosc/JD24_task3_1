package netology.task3;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * В папке Games создайте несколько директорий: src, res, savegames, temp.
 * В каталоге src создайте две директории: main, test.
 * В подкаталоге main создайте два файла: Main.java, Utils.java.
 * В каталог res создайте три директории: drawables, vectors, icons.
 * В директории temp создайте файл temp.txt.
 * Файл temp.txt будет использован для записиси в него информации об успешноном или неуспешном создании файлов и директорий.
 */
public class Main {
    public static void main(String[] args) {
        FileSystemView f = FileSystemView.getFileSystemView();
        String homePathDirectory = String.valueOf(f.getHomeDirectory());

        File rootFolder = new File(homePathDirectory + "\\Games");
        File savegamesFolder = new File(rootFolder + "\\savegames");
        File srcFolder = new File(rootFolder + "\\srs");
        File srcNameFolder = new File(srcFolder + "\\name");
        File srcTestFolder = new File(srcFolder + "\\test");
        File srcMainFile = new File(srcNameFolder, "Main.java");
        File srcUtilsFile = new File(srcNameFolder, "Utils.java");

        File resFolder = new File(rootFolder + "\\res");
        File drawablesFolder = new File(resFolder + "\\drawables");
        File vectorsFolder = new File(resFolder + "\\vectors");
        File iconsFolder = new File(resFolder + "\\icons");


        File tempFolder = new File(rootFolder + "\\temp");
        File tempFile = new File(tempFolder, "temp.txt");

        List<File> folderList = Arrays.asList(rootFolder, srcFolder, srcNameFolder, srcTestFolder,
                resFolder, drawablesFolder, vectorsFolder, iconsFolder, tempFolder,savegamesFolder);

        List<File> fileList = Arrays.asList(srcMainFile, srcUtilsFile, tempFile);

        createDirectory(folderList, fileList, tempFile);

    }


    private static boolean createDirectory(List<File> folderList, List<File> fileList, File logFile) {
        StringBuilder log = new StringBuilder();
        boolean create = false;

        for (File folder : folderList) {
            if (!folder.exists()) {
                create = folder.mkdir();
                log.append("create folder: ")
                        .append(folder.getAbsoluteFile())
                        .append("\n");
            }
        }

        for (File file : fileList) {
            if (!file.exists()) {
                try {
                    create = file.createNewFile();
                    log.append("create file: ")
                            .append(file.getAbsoluteFile())
                            .append("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (create) {
            try (FileWriter fileLog = new FileWriter(logFile)) {
                fileLog.write(log.toString());
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return create;
    }
}
