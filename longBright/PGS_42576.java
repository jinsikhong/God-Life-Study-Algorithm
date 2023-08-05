package week1;

import java.util.HashMap;

/**
 *  10분
 */
public class PGS_42576_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = checkParticipant(participant);

        checkCompletion(completion, map);

        return findAnswer(answer, map);
    }

    private static HashMap<String, Integer> checkParticipant(String[] participant) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        return map;
    }

    private static void checkCompletion(String[] completion, HashMap<String, Integer> map) {
        for (String name : completion) {
            map.put(name, map.get(name) - 1);
        }
    }

    private static String findAnswer(String answer, HashMap<String, Integer> map) {
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                answer = key;
            }
        }
        return answer;
    }
}
