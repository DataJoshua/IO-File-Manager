import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ReadOperations {
    public static void main(String[] args) throws IOException {
        // a.)
        String path = "src/basedir/data/dataset_1.txt";

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String text = fileReader.readLine();
        int a = Integer.parseInt(text.split(" ")[0]);
        int b = Integer.parseInt(text.split(" ")[1]);

        System.out.println(a + b);

        // b.)
        String file1 = "src/basedir/data/dataset_2.txt";

        //readAllLines(file1);

        System.out.println("--------------------");
        System.out.println("Even numbers: ");

        for(String line: getAllLines(file1)){
            int num = Integer.parseInt(line);

            if(num % 2 == 0){
                System.out.println(num);
            }
        }

        System.out.println("----------------------");

        // c.)

        String pathc = "src/basedir/data/dataset_3.txt";

        BufferedReader fileReaderc = new BufferedReader(new InputStreamReader(new FileInputStream(pathc)));
        String textc = fileReaderc.readLine();
        File filec = new File("src/basedir/data/res_3.txt");
        if(filec.createNewFile()){
            System.out.println("success!");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filec));

        for(String number: textc.split(" ")){
            int res = Integer.parseInt(number);
            if(res < 9999){
                writer.write(res + "\n");
            }
        }


        // d.)

        String pathd = "src/basedir/data/dataset_4.txt";

        BufferedReader readerd = new BufferedReader(new InputStreamReader(new FileInputStream(pathd)));

        String numebersd = readerd.readLine();

        int max = Integer.MIN_VALUE;

        for(String number: numebersd.split(" ")){
            int num = Integer.parseInt(number);
            if(num > max){
                max = num;
            }
        }

        writer.write(max + "\n");
        writer.close();

        // e.)

        ArrayList<String> linese = getAllLines("src/basedir/data/dataset_5.txt");

        for(String line: linese){
            System.out.println(line.toUpperCase());
        }
    }

    // use to print all the lines in a file
    public static void readAllLines(String path) throws IOException {
        BufferedReader fileReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

        String line;
        while((line  = fileReader2.readLine()) != null){
            System.out.println(line);
        }
    }

    public static ArrayList<String> getAllLines(String path) throws IOException {
        BufferedReader fileReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

        ArrayList<String> results = new ArrayList<>();

        String line;
        while((line  = fileReader2.readLine()) != null){
            results.add(line);
        }

        return results;
    }
}
