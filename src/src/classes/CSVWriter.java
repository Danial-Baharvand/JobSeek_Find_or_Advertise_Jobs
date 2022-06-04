package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public CSVWriter() {

    }

    /**
     * appends single line of String-type data to file
     * @param fileName the path of the file
     * @param data the String to write to the file
     */
    public void newLine(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
