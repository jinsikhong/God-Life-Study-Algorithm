package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 갓생 알고리즘 스터디 1주차
 * 2100 시작
 * 14658번 가르침
 * 완전탐색 유형 (백트래킹)
 */
public class BOJ_14658 {
    static int N, M, L, K;
    static ArrayList<Pos> list = new ArrayList<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        process();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Pos(r, c));
        }
    }

    private static void process() {
        // 두개의 지점을 선택하고, 각각의 x 좌표와 y 좌표를 시작점으로 잡아서 리스트 탐색
        for (Pos p1 : list) {
            for (Pos p2 : list) {
                int cnt = 0;
                for (Pos pos : list) {
                    if (p1.r <= pos.r && pos.r <= p1.r + L && p2.c <= pos.c && pos.c <= p2.c + L) cnt++;
                }
                max = Math.max(cnt, max);
            }
        }
    }

    private static void print() {
        System.out.println(K - max);
    }

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
