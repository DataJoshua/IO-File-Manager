import tools.Options;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // the number of files and directories in the basedir/dir0
        int len = FileManager.countFilesInPath("src/basedir/dir0", FileManager.WITHOUT_DIRECTORY);

        FileManager file = new FileManager("src/basedir");

        System.out.println(file.getDirectoryNames());

        System.out.println(len);

        // number of files with X extension

        System.out.println(".txt files in "+ file.getPath());
        System.out.println(file.countFilesByExtension("txt"));

        // absolute path of file2-5

        System.out.println(file.getAbsolutePathOf("file2-5"));

        FileManager.printFilesByHierarchy("src/basedir/data");

        // print empty directories

        System.out.println("empty directories");
        FileManager.printEmptyDirectories("src/basedir/data");

        System.out.println("large directory");
        System.out.println(file.getLargerDirectory());

        // free space

        System.out.println(file.getCurrentSpace());

        // system roots
        file.getRootDiskName();

        // create files

        System.out.println(file.createDirs("Picture", "Texts/History", "Texts/Horror/First"));
        System.out.println(file.createFiles(new Options("Picture", ".txt"), "1", "2", "3", "4", "5", "6"));

        FileManager file2 = new FileManager("src/basedir/Picture");

        System.out.println("File 2 ----------------");

        System.out.println(file2.renameFileTo("5.txt", "50000.txt"));
        file2.removeFile("6.txt");

        //file2.removeFiles();

        FileManager.deleteDirs("src/basedir/Texts/Horror", "src/basedir/Picture");
    }
}
