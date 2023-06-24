import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1062_sol {
    static int N, K;
    static int maxCnt = Integer.MIN_VALUE;
    static boolean[] visited;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        words = new String[N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            word = word.replace("anta", "");
            word = word.replace("tica", "");
            words[i] = word;
        }

        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }
        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;

        backTracking(0, 0);
        System.out.println(maxCnt);
    }

    static void backTracking(int alpha, int len) {
        if (len == K - 5) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                boolean read = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if (!visited[words[i].charAt(j) - 'a']) {
                        read = false;
                        break;
                    }
                }
                if (read) {
                    cnt++;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
        for (int i = alpha; i < 26; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                backTracking(i, len + 1);
                visited[i] = false;
            }
        }
    }
}
