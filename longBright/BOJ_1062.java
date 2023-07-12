package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 갓생 알고리즘 스터디 1주차
 * 1730 시작
 * 1062번 가르침
 * 완전탐색 유형 (백트래킹)
 */
public class BOJ_1062 {
    static int N, K, max = 0;
    static boolean[] used;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        process(br);
        print();
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new String[N];
    }

    private static void process(BufferedReader br) throws IOException {
        // 모든 단어는 "anta" 로 시작하고 "tica" 로 시작하므로, 5개의 알파벳 고정
        // K 가 5보다 작으면 어느 글자도 읽을 수 없음
        if (K < 5) {
            max = 0;
        } else if (K == 26) {
            max = N;
        } else {
            // 단어 입력
            for (int i = 0; i < words.length; i++) {
                words[i] = br.readLine();
            }
            K -= 5; // 고정된 알파벳 개수만큼 감소
            used = new boolean[26];
            used['a' - 'a'] = true;
            used['n' - 'a'] = true;
            used['t' - 'a'] = true;
            used['i' - 'a'] = true;
            used['c' - 'a'] = true;
            // 글자 조합 생성
            comb(0, 0);
        }
    }

    private static void comb(int depth, int now) {
        if (depth == K) {
            int cnt = 0;
            for (String word : words) {
                if (isReadable(word)) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            return;
        }
        for (int i = now; i < 26; i++) {
            if (used[i]) continue;
            used[i] = true;
            comb(depth + 1, i);
            used[i] = false;
        }
    }

    private static boolean isReadable(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!used[word.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }

    private static void print() {
        System.out.println(max);
    }
}
