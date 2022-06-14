import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

public class MakeDdl {
    public static void main(String[] args) throws IOException {
        File dir = new File("input");
        dir.mkdir();
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".csv");
            }
        };
        File[] list = dir.listFiles(filter);

        for (int i = 0; i < list.length; i++) {
            List<List<String>> tableData = CsvReader.read(list[i].getPath());
            Table table = new Table(tableData);
            table.makeJpaEntityFile();
        }
    }
}