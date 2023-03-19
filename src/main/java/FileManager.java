import tools.Options;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {

    private String path;
    private File file;
    public static final boolean INCLUDE_DIRECTORY = true;
    public static final boolean WITHOUT_DIRECTORY = false;


    public FileManager(String path){
        this.path = path;
        this.file = new File(this.path);
    }

    // recursive function that counts how many files and directories are inside a given path
    public static Integer countFilesInPath(String path, boolean includeDirectories){
        File src = new File(path);
        Integer counter = 0;

        for(File f : src.listFiles()){
            if(f.isDirectory()){
                counter += countFilesInPath(f.getPath(), includeDirectories);
                if(!includeDirectories){
                    continue;
                }
            }
            counter++;
        }

        return counter;
    }

    public static void printFilesByHierarchy(String path){
        File files = new File(path);

        for(File f: files.listFiles()){
            if(f.isDirectory()){
                System.out.println("--" + f.getName());
                printFilesByHierarchy((f.getPath()));
                continue;
            }
            System.out.println("---" + f.getName());
        }
    }

    public static void deleteDirs(String ...names) throws IOException {
        for (int i = 0; i < names.length; i++) {
            Files
                    .walk(Paths.get(names[i]))
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try{
                            System.out.println("deleting "+ path);
                            Files.delete(path);
                        }
                        catch(IOException e){
                            System.out.println(e);
                        }
                    });
        }
    }

    public String getLargerDirectory(){
        String largerDirectory = "";
        int count = 0;

        for(File f: this.file.listFiles()){
            if(f.isDirectory()){
                if(f.listFiles().length > count){
                    count = f.listFiles().length;
                    largerDirectory = f.getName();
                }
            }
        }

        return largerDirectory;
    }

    public String getCurrentSpace(){
        return "you have left " + this.file.getFreeSpace() + " bytes";
    }

    public void getRootDiskName(){
        List<File> files = Arrays.asList(File.listRoots());

        for (File f : files) {
            String s1 = FileSystemView.getFileSystemView().getSystemDisplayName(f);
            System.out.println("getSystemDisplayName : " + s1);
        }
    }

    public static void printEmptyDirectories(String path){
        File files = new File(path);

        for(File f: files.listFiles()){
            if(f.isDirectory() && f.listFiles().length == 0){
                System.out.println(f.getName());
            }
            else{
                if(f.isDirectory()){
                    printEmptyDirectories(f.getPath());
                }
            }
        }
    }

    public ArrayList<String> getDirectoryNames(){

        ArrayList<String> result = new ArrayList<>();

        for(File f : this.file.listFiles()){
            if(f.isDirectory()){
                result.add(f.getName());
            }
        }

        return result;
    }

    public String getDirectoryNamesAsText(){

        StringBuilder result = new StringBuilder();

        for(String s: getDirectoryNames()){
            result.append(s+", ");
        }

        return result.toString();
    }

    public Integer countFilesByExtension(String extension){
        int count = 0;

        for(File f: this.file.listFiles()){
            if(!f.isDirectory()){
                if(f.getName().matches(".*\\."+extension)){
                    count++;
                }
            }
        }
        return count;
    }

    public String getAbsolutePathOf(String fileName){
        ArrayList<String> absolutePath = new ArrayList<>();

        for(File f: this.file.listFiles()){
            if(f.isFile()){
                absolutePath.add(f.getAbsolutePath());
            }
        }

        // just does complex stuff lol
        return absolutePath.stream().filter(s -> s.trim().contains(fileName)).collect(Collectors.joining());
                // runs an arrow function for each filename and prove if it contains the filename on it, filter them and change to string
    }

    public boolean createFiles(Options options, String ...names) throws IOException {

        boolean result = false;

        for (int i = 0; i < names.length; i++) {
            String source = this.path + "/"+ options.dir +"/" + names[i] + options.extension;
            File file = new File(source);
            System.out.println(source);
            System.out.println(this.path + "/" + names[i]);
            boolean isCraeted = file.createNewFile();

            if(isCraeted){
                result = true;
            }
            else{
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean createDirs(String ...names) throws IOException {

        boolean result = false;

        for (int i = 0; i < names.length; i++) {
            String source = this.path + "/" + names[i];
            File file = new File(source);
            System.out.println(source);

            boolean isCraeted = file.mkdirs();

            if(isCraeted){
                result = true;
            }
            else{
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean renameFileTo(String old, String name){
        File file = new File(this.path + "/" + name);
        File fileToRename = new File(this.path + "/" + old);

        if(file.exists()){
            return false;
        }
        else{
            return fileToRename.renameTo(file);
        }
    }

    public boolean removeFile(String name){
        File file = new File(this.path + "/" + name);

        if(file.exists()){
            return file.delete();
        }
        else{
            return false;
        }
    }

    public void removeFiles(){
        Scanner input = new Scanner(System.in);

        String userInput = "";

        do{
           System.out.println("which files you would like to remove in the current directory? ");

           for(File f: this.file.listFiles()){
               if(f.isFile()){
                   System.out.println("-- " + f.getName());
               }
           }


           System.out.println("digit the name of the file to delete or stop to finish deletion");
           userInput = input.nextLine();

           if(!userInput.equalsIgnoreCase("stop")){
               if(removeFile(userInput)){
                   System.out.println(userInput + " deleted!");
               }
               else{
                   System.out.println(userInput + " does not exist in the current directory");
               }
           }

       }while(!userInput.equalsIgnoreCase("stop"));
    }

    public String getPath(){
        return this.path;
    }

}
