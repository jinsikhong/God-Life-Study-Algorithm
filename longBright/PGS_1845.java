package week1;

import java.util.*;

/**
 * 5분
 */
public class PGS_1845_폰켓몬 {

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return Math.min(n / 2, map.keySet().size());

    }
}
