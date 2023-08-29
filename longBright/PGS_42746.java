package week2;

import java.util.Arrays;

/**
 * 1시간
 * 솔루션
 */
public class PGS_42746_가장큰수 {

    public String solution(int[] numbers) {
        int length = numbers.length;
        String[] strings = new String[length];

        for (int i = 0; i < length; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        if (strings[0].equals("0")) {
            return "0";
        }

        StringBuilder answer = new StringBuilder();
        for (String string : strings) {
            answer.append(string);
        }

        return answer.toString();
    }
}
