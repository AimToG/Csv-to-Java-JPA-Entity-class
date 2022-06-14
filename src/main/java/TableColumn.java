import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableColumn {
    public String field;
    public String input;
    public String keyType;
    public String required;
    public String desc;
    public String fkTable;

    public TableColumn(Map<String, String> tableRow) {
        field = tableRow.get("Field");
        input = tableRow.get("Input");
        keyType = tableRow.get("Key Type");
        required = tableRow.get("Required");
        desc = tableRow.get("Desc");
        fkTable = tableRow.get("FK Table");
    }

    public String makeJpaStatement() {
        String result = "";
        result += makeAnnotation();
        result += makeColumnField();

        return result;
    }

    private String makeAnnotation() {
        if (isPrimaryKey()) {
            return makePrimaryKeyAnnotation();
        }

        String result = "";
        if (isForeignKey()) {
            result += makeForeignKeyAnnotation();
        }

        if (isRequiredColumnAnnotation()) {
            result += makeColumnAnnotation();
        }

        return result;
    }

    private String makePrimaryKeyAnnotation() {
        return "\t@Id @GeneratedValue(strategy = GenerationType.IDENTITY)\n";
    }

    private String makeForeignKeyAnnotation() {
        return "\t@ManyToOne\n\t@JoinColumn(name = \"" + field + "\")\n";
    }

    private String makeColumnAnnotation() {
        List<String> columnParams = new ArrayList<>();
        if (input.contains("varchar")) {
            String length = input.substring(input.indexOf('(') + 1, input.indexOf(')'));
            columnParams.add("length = " + length);
        }
        if (input.equals("text") || input.equals("timestamp") || input.equals("date")) {
            columnParams.add("columnDefinition = \"" + input + "\"");
        }
        if (required.equals("NN")) {
            columnParams.add("nullable = false");
        }

        return "\t@Column(" + String.join(", ", columnParams) + ")\n";
    }

    private boolean isPrimaryKey() {
        if (keyType.equals("PK")) {
            return true;
        }

        return false;
    }

    private boolean isForeignKey() {
        if (keyType.equals("FK")) {
            return true;
        }

        return false;
    }

    private boolean isRequiredColumnAnnotation() {
        if (input.contains("varchar") || input.equals("text") || input.equals("timestamp") || input.equals("date") || required.equals("NN")) {
            return true;
        }

        return false;
    }

    private String makeColumnField() {
        String result = "\tprivate ";

        return result + getJpaType() + " " + getFieldName() + ";\n\n";
    }

    private String getJpaType() {
        if (keyType.equals("FK")) {
            return Utils.convertSnakeToUpperCamel(fkTable);
        }

        if (input.contains("varchar") || input.contains("text")) {
            return "String";
        }

        if (input.equals("int") && keyType.equals("PK")) {
            return "Long";
        }

        if (input.equals("timestamp") || input.contains("date")) {
            return "Date";
        }

        return input;
    }

    private String getFieldName() {
        if (keyType.equals("FK")) {
            return Utils.convertSnakeToLowerCamel(fkTable);
        }

        return Utils.convertSnakeToLowerCamel(field);
    }

    @Override
    public String toString() {
        String result = "";
        result += "field: " + field + "\n";
        result += "input: " + input + "\n";
        result += "key type: " + keyType + "\n";
        result += "required: " + required + "\n\n";

        return result;
    }
}
