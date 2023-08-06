package week1;

import java.util.*;

/**
 * 30분
 * 솔루션
 */
public class PGS_42578_의상 {

    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();
        for (String[] strings :clothes){
            map.put(strings[1], map.getOrDefault(strings[1], 0) + 1);
        }

        for (String key : map.keySet()) {
            answer *= map.get(key) + 1;
        }
        answer--;

        return answer;
    }

}