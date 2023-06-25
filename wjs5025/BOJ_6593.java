import java.io.*;
import java.util.*;

public class BOJ_6593 {
    static int L, R, C;
    static ArrayList<String[][]> building;
    static ArrayList<boolean[][]> visited;
    static Pos start;
    static int time;
    static int[] dl = {0,0,0,0,1,-1}; // 동 서 남 북 하 상
    static int[] dr = {0,0,1,-1,0,0}; // 동 서 남 북 하 상
    static int[] dc = {1,-1,0,0,0,0}; // 동 서 남 북 하 상

    // 위치를 나타내기 위한 클래스
    static class Pos {
        int l;
        int r;
        int c;
        int time;

        Pos(int l, int r, int c, int time) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format("l : %s, r :  %s, c : %s", l,r,c);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        while (true) {
            time = 0;
            input = br.readLine().split(" ");
            L = Integer.parseInt(input[0]);
            R = Integer.parseInt(input[1]);
            C = Integer.parseInt(input[2]);

            // 입력 종료 조건
            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            building = new ArrayList<>();
            visited = new ArrayList<>();

            // 빌딩 입력받기
            for (int i = 0; i < L; i++) {
                String[][] floor = new String[R][C];
                boolean[][] tmp = new boolean[R][C];

                for (int j = 0; j < R; j++) {
                    input = br.readLine().split("");
                    for (int k = 0; k < C; k++) {
                        floor[j][k] = input[k];
                        if (input[k].equals("S"))
                            start = new Pos(i,j,k,0);
                    }
                }
                building.add(floor);
                visited.add(tmp);
                br.readLine();
            }

            tryEscape();
        }
    }
    
    // 탐색수행(bfs)에 따른 결과 출력
    static void tryEscape() {
        boolean result = bfs(start);

        if (result) {
            System.out.println(String.format("Escaped in %d minute(s).", time));
        } else {
            System.out.println("Trapped!");
        }
    }

    // 탐색 수행
    static boolean bfs(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited.get(start.l)[start.r][start.c] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (building.get(now.l)[now.r][now.c].equals("E")) {
                time = now.time;
                return true;
            }

            for (int i = 0; i < 6; i++) {
                int nl = now.l + dl[i];
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C)
                    continue;
                if (visited.get(nl)[nr][nc])
                    continue;
                if (!building.get(nl)[nr][nc].equals("#")) {
                    q.add(new Pos(nl, nr, nc, now.time + 1));
                    visited.get(nl)[nr][nc] = true;
                }
            }
        }

        return false;
    }
}
