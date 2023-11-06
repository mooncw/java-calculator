package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class StringTest {
    @Test
    void splitTest1() {
        String input = "1,2";
        String[] output = input.split(",");

        assertThat(output).contains("1", "2");
    }

    @Test
    void splitTest2() {
        String input = "1";
        String[] output = input.split(",");

        assertThat(output).containsExactly("1");
    }

    @Test
    void subStringTest() {
        String input = "(1,2)";
        String output = input.substring(1,4);

        assertThat(output).isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt() 메소드가 특정 위치의 문자 가져오는지 확인하는 테스트")
    void charAtTest() {
        String input = "abc";
        char check = input.charAt(1);

        assertThat(check).isEqualTo('b');
    }

    @Test
    @DisplayName("charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이" +
        " 발생하는지 확인하는 테스트")
    void stringIndexOutOfBoundsExceptionTest() {
        String input = "abc";

        int index = input.length();

        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            input.charAt(index);
        });
    }

}
