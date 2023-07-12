package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 갓생 알고리즘 스터디 3일차
 * 5557번 1학년
 * 솔루션
 */
public class BOJ_5557 {
    static int n;
    static int[] arr;
    static long[][] dp;
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new long[n][21];  // dp[i][j] : i 번째 수까지 이용했을 때 j 번째 수를 만들 수 있는 경우의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void process() {
        dp[0][arr[0]] = 1;
        // 1번 인덱스부터 확인, n-2 번째에 있는 수가 정답
        // 0 부터 20까지의 수 확인
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] != 0) {
                    // i 번째 수와 j의 합이 20 이하인 경우
                    if (j + arr[i] <= 20) {
                        dp[i][j + arr[i]] += dp[i - 1][j];
                    }
                    // i 번째 수와 j의 차가 0 이상인 경우
                    if (j - arr[i] >= 0) {
                        dp[i][j - arr[i]] += dp[i - 1][j];
                    }
                }
            }
        }
    }

    private static void print() {
        System.out.println(dp[n-2][arr[n-1]]);
    }

    public static void main(String[] args) throws IOException {
        init();
        process();
        print();
    }
}
