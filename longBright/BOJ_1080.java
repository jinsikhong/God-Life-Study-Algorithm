package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 행렬
 */
public class BOJ_1080 {
    static int N, M;
    static int[][] A, B;

    public static void main(String[] args) throws IOException {
        init();
        int cnt = getCnt();
        printCnt(cnt);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        B = new int[N][M];

        initArray(br, A);
        initArray(br, B);
    }

    private static void initArray(BufferedReader br, int[][] arr) throws IOException {
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
    }

    private static int getCnt() {
        int cnt = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (A[i][j] != B[i][j]) {
                    flipArray(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void flipArray(int row, int col) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                A[i][j] = Math.abs(A[i][j] - 1);
            }
        }
    }

    private static boolean checkArray() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printCnt(int cnt) {
        if (checkArray()) {
            System.out.println(cnt);
        } else {
            System.out.println(-1);
        }
    }
}
