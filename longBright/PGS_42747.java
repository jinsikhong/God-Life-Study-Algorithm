package week2;

import java.util.Arrays;

/**
 * 1시간
 * 솔루션
 */
public class PGS_42747_HIndex {
    public static void main(String[] args) {
        int[] citations = {0, 5, 6, 7, 8};
        System.out.println(solution(citations));
    }

    public static int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        int length = citations.length;
        for (int i = 0; i < length; i++) {
            int h = length - i;

            if (citations[i] >= h) {
                answer = h;
                return answer;
            }
        }
        return answer;
    }
}
