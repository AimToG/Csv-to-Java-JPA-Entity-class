import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JavaFileWriter {
    private String filePath;

    public JavaFileWriter(String filePath) {
        this.filePath = filePath;
    }

    public void write(String text) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        writer.write(text);

        writer.flush();
        writer.close();
    }
}
