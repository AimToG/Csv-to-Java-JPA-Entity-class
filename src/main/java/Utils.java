public class Utils {
    private static final String SNAKE = "_";

    public static String convertSnakeToUpperCamel(String input) {
        String[] arr = input.split(SNAKE);
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            char[] temp = arr[i].toCharArray();
            temp[0] = Character.toUpperCase(temp[0]);
            result += new String(temp);
        }

        return result;
    }

    public static String convertSnakeToLowerCamel(String input) {
        String[] arr = input.split(SNAKE);
        String result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            char[] temp = arr[i].toCharArray();
            temp[0] = Character.toUpperCase(temp[0]);
            result += new String(temp);
        }

        return result;
    }
}
