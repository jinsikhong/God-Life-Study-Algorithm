package day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 여행가자
 */
public class BOJ_1976 {
    static int N, M;
    static int[] parent, plan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);

        initParent();

        updateParent(br);

        StringTokenizer st = new StringTokenizer(br.readLine());
        initPlan(br, st);

        print();

    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        parent = new int[N+1];
        plan = new int[M];
    }

    private static void print() {
        if (isPossible()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void initPlan(BufferedReader br, StringTokenizer st) throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static boolean isPossible() {
        for (int i = 1; i < M; i++) {
            if (find(plan[i]) != find(plan[i-1])) {
                return false;
            }
        }
        return true;
    }

    private static void updateParent(BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int city = Integer.parseInt(st.nextToken());
                if (city == 1) {
                    union(i, j);
                }
            }
        }
    }

    private static void initParent() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
