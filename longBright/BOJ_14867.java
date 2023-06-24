package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_14867 {
    static int a, b;
    static Set<String> usedSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int targetA = Integer.parseInt(st.nextToken());
        int targetB = Integer.parseInt(st.nextToken());
        usedSet = new HashSet<String>();

        System.out.println(bfs(targetA, targetB));
    }

    static int bfs(int targetA, int targetB) {
        Queue<Status> q = new LinkedList<Status>();
        q.offer(new Status(0, 0));

        while (!q.isEmpty()) {
            Status now = q.poll();
            int x = now.x;
            int y = now.y;
            int cnt = now.count;
            
            if (x == targetA && y == targetB) {
                return cnt;
            }

            for (int i = 1; i <= 6; i++) {
                // 다음 x, y 초기화 (이 부분 놓치고 틀렸음)
                int nx = x;
                int ny = y;

                if (!isValid(i, nx, ny)) continue;

                switch (i) {
                    case 1:
                        nx = a;
                        break;
                    case 2:
                        ny = b;
                        break;
                    case 3:
                        nx = 0;
                        break;
                    case 4:
                        ny = 0;
                        break;
                    case 5:
                        if (b - ny < nx) {
                            nx = nx - (b - ny);
                            ny = b;
                        } else {
                            ny += nx;
                            nx = 0;
                        }
                        break;
                    case 6:
                        if (a - nx < ny) {
                            ny = ny - (a - nx);
                            nx = a;

                        } else {
                            nx += ny;
                            ny = 0;
                        }
                        break;
                }

                if (isUsed(nx, ny)) {
                    continue;
                }
                q.offer(new Status(nx, ny, cnt + 1));
            }
        }

        return -1;
    }

    private static boolean isValid(int i, int nx, int ny) {
        // 차있는 경우 채우기 수행하지 않음
        if (nx == a && i == 1) {
            return false;
        }
        if (ny == b && i == 2) {
            return false;
        }

        // 비어있는 경우 이동이나 비우기 수행하지 않음
        if (nx == 0) {
            if (i == 3 || i == 5) {
                return false;
            }
        }
        if (ny == 0) {
            if (i == 4 || i == 6) {
                return false;
            }
        }
        return true;
    }

    static boolean isUsed(int x, int y) {
        String point = x + "_" + y;
        if (usedSet.contains(point)) {
            return true;
        }

        usedSet.add(point);
        return false;
    }

    static class Status {
        int x;
        int y;
        int count = 0;

        Status(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Status(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}