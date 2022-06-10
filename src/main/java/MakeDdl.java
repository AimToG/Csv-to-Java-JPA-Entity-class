import java.io.IOException;
import java.util.List;

public class MakeDdl {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            return;
        }

        for (int i = 0; i < args.length; i++) {
            List<List<String>> tableData = CsvReader.read(args[i]);
            Table table = new Table(tableData);
            table.makeJpaEntityFile();
        }
    }
}