import java.io.*;
import java.util.*;

// 가르침
public class BOJ_1062 {
    static int N, K;
    static String[] pureWords;
    static ArrayList<String> learned = new ArrayList<>();
    static Set<String> candidate = new HashSet<>();
    static ArrayList<String> list;
    static int maxCnt = Integer.MIN_VALUE;
    static String[] combination;
    static boolean[] visited;

    // 현재 조합에서 몇개의 pureword를 읽을 수 있나 검사
    static int getCnt() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String word = pureWords[i];
            Set<String> set = new HashSet<>();
            String[] tmp = word.split("");

            for (int j = 0; j < word.length(); j++) {
                set.add(tmp[j]);
            }

            if (K - 5 >= set.size()) {
                for (int j = 0; j < K - 5; j++) {
                    word = word.replaceAll(combination[j], "");
                }

                if (word.length() == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    // 조합
    static void getCombination(int idx, int start) {
        if (idx == K - 5) {
            maxCnt = Math.max(maxCnt, getCnt());
            return;
        }

        for (int i = start; i < list.size(); i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            combination[idx] = list.get(i);
            getCombination(idx + 1, i);
            visited[i] = false;
        }
    }

    public static String getPureWord(String word) {
        word = word.replace("anta", "");
        word = new StringBuilder(word).reverse().toString().replace("acit", "");

        for (int i = 0; i < 5; i++) {
            word = word.replace(learned.get(i), "");
        }

        String[] tmp = word.split("");
        for (int i = 0; i < tmp.length; i++) {
            candidate.add(tmp[i]);
        }
        return new StringBuilder(word).reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 및 초기화
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        pureWords = new String[N];

        // 기본 단어 학습 (a,n,t,i,c)
        for (int i = 0; i < 5; i++) {
            learned.add("antic".substring(i, i + 1));
        }

        for (int i = 0; i < N; i++) {
            pureWords[i] = getPureWord(br.readLine());
        }

        if (K < 5) {
            System.out.println(0);
        } else {
            combination = new String[K - 5];
            visited = new boolean[candidate.size()];
            list = new ArrayList<>(candidate);
            getCombination(0, 0);
            System.out.println(maxCnt);
        }

    }
}
