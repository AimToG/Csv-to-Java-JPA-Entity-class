import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private String tableName;
    private List<TableColumn> tableColumns;
    private List<String> tableDescription;
    private Map<Integer, String> tableDescriptionIndex;

    public Table(List<List<String>> tableData) {
        tableName = tableData.get(0).get(0);
        tableDescription = tableData.get(1);
        tableDescriptionIndex = makeTableDescriptionIndex();
        tableColumns = new ArrayList<>();
        for (int i = 2; i < tableData.size(); i++) {
            tableColumns.add(makeTableColumn(tableData.get(i)));
        }
    }

    private Map<Integer, String> makeTableDescriptionIndex() {
        Map<Integer, String> tableDescriptionIndex = new HashMap<>();
        for (int index = 0; index < tableDescription.size(); index++) {
            tableDescriptionIndex.put(index, tableDescription.get(index));
        }

        return tableDescriptionIndex;
    }

    private TableColumn makeTableColumn(List<String> tableRowData) {
        Map<String, String> tableRow = new HashMap<>();
        for (int i = 0; i < tableRowData.size(); i++) {
            tableRow.put(tableDescriptionIndex.get(i), tableRowData.get(i));
        }

        return new TableColumn(tableRow);
    }

    public String makeJpaEntity() {
        String importStatement = "import lombok.Getter;\nimport lombok.NoArgsConstructor;\nimport lombok.Setter;\n\nimport javax.persistence.*;\nimport java.util.Date;\n";
        String annotationStatement = "@Entity\n@Getter\n@Setter\n@NoArgsConstructor\n";

        String result = "";
        result += importStatement;
        result += "\n";
        result += annotationStatement;
        result += "public class " + getJavaClassName() + "{";
        result += "\n";

        for (int i = 0; i < tableColumns.size(); i++) {
            result += tableColumns.get(i).makeJpaStatement();
        }

        result += "}";

        return result;
    }

    public void makeJpaEntityFile() throws IOException {
        makeDistDirectory();
        JavaFileWriter writer = new JavaFileWriter("dist/" + getJavaFileName());
        writer.write(makeJpaEntity());
    }

    private void makeDistDirectory() {
        File file = new File("dist");
        file.mkdir();
    }

    private String getJavaClassName() {
        return Utils.convertSnakeToUpperCamel(tableName);
    }

    private String getJavaFileName() {
        return getJavaClassName() + ".java";
    }

    @Override
    public String toString() {
        String result = "";
        result += tableName;
        result += "\n";
        result += tableColumns;

        return result;
    }
}
