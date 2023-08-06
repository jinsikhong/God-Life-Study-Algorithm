package week1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 19:40 시작
 * 1시간 / 솔루션
 */
public class PGS_42579_베스트앨범 {

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> genreMap = new HashMap<>(); // 장르별 총 재생횟수
        Map<String, HashMap<Integer, Integer>> playsMap = new HashMap<>(); // 장르에 속하는 노래 및 재생횟수

        // 장르별 재생 횟수와 장르에 속하는 노래 및 재생횟수 초기화
        for (int i = 0; i < plays.length; i++) {
            int play = plays[i];
            String genre = genres[i];

            genreMap.put(genre, genreMap.getOrDefault(genre, 0) + play);

            playsMap.putIfAbsent(genre, new HashMap<>());
            playsMap.get(genre).put(i, play);
        }

        // genreMap 의 key 를 장르별 총 재생 횟수로 정렬
        List<String> keySet = new ArrayList<>(genreMap.keySet());
        keySet.sort(Comparator.comparingInt(genreMap::get).reversed());

        for (String key : keySet) {
            // 장르별 노래 및 재생횟수를 가져와서 노래를 재생 횟수 기준으로 정렬
            Map<Integer, Integer> map = playsMap.get(key);
            List<Integer> genreKey = new ArrayList<>(map.keySet());
            genreKey.sort(Comparator.comparingInt(map::get).reversed());

            // 가장 많이 재생된 노래 두 개를 answer 리스트에 추가
            genreKey.stream().map(answer::add).limit(2).collect(Collectors.toList());
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
