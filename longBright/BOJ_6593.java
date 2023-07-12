package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 갓생 알고리즘 스터디 2일차
 * 2000 시작 2040 종료
 * 6593 상범 빌딩
 */
public class BOJ_6593 {
    static final int[] dl = {0, 0, 0, 0, -1, 1};
    static final int[] dr = {-1, 1, 0, 0, 0, 0};
    static final int[] dc = {0, 0, -1, 1, 0, 0};
    static int L, R, C;
    static Pos start, end;
    static char[][][] map;
    static boolean[][][] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        while (true) {
            input(br);
            if (isEndOfInput()) break;
            init(br);
            getResult(result);
        }
        printResult(result);
    }

    private static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    private static boolean isEndOfInput() {
        return L == 0 && R == 0 && C == 0;
    }

    private static void init(BufferedReader br) throws IOException {
        map = new char[L][R][C];
        used = new boolean[L][R][C];

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                String input = br.readLine();
                if (input.equals("")) {
                    input = br.readLine();
                }
                for (int k = 0; k < C; k++) {
                    map[i][j][k] = input.charAt(k);
                    if (map[i][j][k] == 'S') {
                        start = new Pos(i, j, k, 0);
                    } else if (map[i][j][k] == 'E') {
                        end = new Pos(i, j, k, 0);
                    }
                }
            }
        }
    }

    private static void getResult(StringBuilder result) {
        int min = bfs();
        if (min == -1) {
            result.append("Trapped!\n");
        } else {
            result.append("Escaped in ").append(min).append(" minute(s).\n");
        }
    }

    private static int bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.add(start);
        used[start.l][start.r][start.c] = true;
        while (!q.isEmpty()) {
            Pos now = q.poll();
            int l = now.l;
            int r = now.r;
            int c = now.c;
            int cnt = now.cnt;
            if (isEnd(l, r, c)) {
                return cnt;
            }

            for (int i = 0; i < 6; i++) {
                int nl = l + dl[i];
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isOutOfRange(nl, nr, nc)) {
                    continue;
                }
                if (isNotValid(nl, nr, nc)) {
                    continue;
                }
                used[nl][nr][nc] = true;
                q.add(new Pos(nl, nr, nc, cnt + 1));
            }
        }
        return -1;
    }

    private static boolean isEnd(int l, int r, int c) {
        return l == end.l && r == end.r && c == end.c;
    }

    private static boolean isOutOfRange(int nl, int nr, int nc) {
        return nl < 0 || nr < 0 || nc < 0 || nl >= L || nr >= R || nc >= C;
    }

    private static boolean isNotValid(int nl, int nr, int nc) {
        return map[nl][nr][nc] == '#' || used[nl][nr][nc];
    }

    private static void printResult(StringBuilder result) {
        System.out.println(result);
    }

    static class Pos {

        int l;
        int r;
        int c;
        int cnt;

        public Pos(int l, int r, int c, int cnt) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

}
