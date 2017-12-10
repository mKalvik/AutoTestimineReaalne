package txtFile;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<String> cityNames = new ArrayList<>();

    public Controller(){}

    public void printToOutputFile(String stringToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true))) {
            writer.write(stringToWrite+"\n");
        } catch (IOException e) {
            System.out.println("Error writing file:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    cityNames.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> getCityNamesList() {
        readFromFile();
        return cityNames;
    }


}
