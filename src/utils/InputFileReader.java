package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputFileReader {
    private ArrayList<String> lines = new ArrayList<>();

    public InputFileReader(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) lines.add(line);
        fileReader.close();
    }

    public ArrayList<String> getLines() {
        return lines;
    }
}
