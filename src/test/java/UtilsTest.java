import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void convertSnakeToUpperCamel1() {
        String input = "test";
        String output = Utils.convertSnakeToUpperCamel(input);

        assertEquals("Test", output);
    }

    @Test
    void convertSnakeToUpperCamel2() {
        String input = "test_is_test";
        String output = Utils.convertSnakeToUpperCamel(input);

        assertEquals("TestIsTest", output);
    }

    @Test
    void convertSnakeToLowerCamel1() {
        String input = "test";
        String output = Utils.convertSnakeToLowerCamel(input);

        assertEquals("test", output);
    }

    @Test
    void convertSnakeToLowerCamel2() {
        String input = "test_is_test";
        String output = Utils.convertSnakeToLowerCamel(input);

        assertEquals("testIsTest", output);
    }
}