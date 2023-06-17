import java.io.*;
import java.util.*;

/*
 * 그리디 문제인데
 * 조합으로 풀려고 해서 실패 (11점, 1시간)
 * 
 * 솔루션 봤음
 */
public class BOJ_21758 {
    static int N;
    static int[] bees = new int[2];
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int[] map;

    static void getMax(int home) {
        int honey = 0;

        // 벌1의 위치가 집보다 작을 때 (오른쪽으로 이동할 떄)
        if (bees[0] < home) {
            for (int i = bees[0] + 1; i <= home; i++) {
                if (i == bees[1])
                    continue;
                honey += map[i];
            }
        } else {
            // 왼쪽으로 이동할 때
            for (int i = bees[0] - 1; i >= home; i--) {
                if (i == bees[1])
                    continue;
                honey += map[i];
            }
        }

        // 벌2의 위치가 집보다 작을 때 (오른쪽으로 이동할떄)
        if (bees[1] < home) {
            for (int i = bees[1] + 1; i <= home; i++) {
                if (i == bees[0])
                    continue;
                honey += map[i];
            }
        } else {
            // 왼쪽으로 이동할 때
            for (int i = bees[1] - 1; i >= home; i--) {
                if (i == bees[0])
                    continue;
                honey += map[i];
            }
        }

        max = Math.max(max, honey);
    }

    static void getCombination(int idx, int start, int home) {
        if (idx == 2) {
            getMax(home);
            return;
        }

        for (int i = start; i < N; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            bees[idx] = i;
            getCombination(idx + 1, i, home);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        map = new int[N];

        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(tmp[i]);
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            getCombination(0, 0, i);
            visited[i] = false;
        }

        System.out.println(max);

    }
}
