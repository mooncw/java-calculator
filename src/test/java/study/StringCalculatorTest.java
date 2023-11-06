package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    public boolean isNumberic(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
    public int calculatoradd(String input) {
        String delimiter = ",|:";

        if (input.contains("//") && input.contains("\n")) {
            delimiter += "|" + input.substring(2,3);
            input = input.substring(4);
        } else if (input.equals("")) {
            input = "0";
        }

        String[] output = input.split(delimiter);

        int sum = 0;

        for (String num : output) {
            if (isNumberic(num)) {
                int n = Integer.parseInt(num);
                if (n >= 0) {
                    sum += n;
                } else {
                    throw new RuntimeException("음수인 값은 계산할 수 없습니다.");
                }
            } else {
                throw new RuntimeException("숫자형이 아닌 문자열은 계산할 수 없습니다.");
            }
        }
        return sum;
    }

    @ParameterizedTest
    @CsvSource(value = {"'', 0", "'1,2', 3", "'1,2,3', 6", "'1,2:3', 6", "'//;\n1;2;3', 6"})
    @DisplayName("calculatoradd 메서드에 정상적인 값이 들어갈 때 제대로 덧셈을 하는지 확인하는 테스트")
    void calculatoraddTest(String input, int expected) {
        int actual = calculatoradd(input);
        System.out.println(actual);
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"s", "-1", "1,-2", "//;\n1;2;-3"})
    @DisplayName("calculatoradd 메서드에 비정상적인 값이 들어갈 때 RuntimeException을 발생시키는지 확인하는 테스트")
    void calculatoraddRuntimeExceptionTest(String input) {
        assertThrows(RuntimeException.class, () -> {
            calculatoradd(input);
        });
    }

}
