package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 갓생 알고리즘 스터디 3일차
 * 17070 파이프 옮기기
 */
public class BOJ_17070 {
    static int n;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 1, 0);
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /**
     * @param r         파이프 앞부분의 현재 행
     * @param c         파이프 앞부분의 현재 열
     * @param direction 가로 = 0, 세로 = 1, 대각선 = 2
     */
    private static void dfs(int r, int c, int direction) {
        if (isOutOfRange(r, c) || isWall(r, c)) {
            return;
        }

        if (direction == 0) {
            dfs(r, c + 1, direction);
            dfs(r + 1, c + 1, 2);
        } else if (direction == 1) {
            dfs(r + 1, c, direction);
            dfs(r + 1, c + 1, 2);
        } else {
            if (isWall(r - 1, c) || isWall(r, c - 1)) {
                return;
            }
            dfs(r, c + 1, 0);
            dfs(r + 1, c, 1);
            dfs(r + 1, c + 1, direction);
        }
        dp[r][c]++;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r == n || c == n;
    }

    private static boolean isWall(int r, int c) {
        return map[r][c] == 1;
    }

    private static void print() {
        System.out.println(dp[n - 1][n - 1]);
    }
}
