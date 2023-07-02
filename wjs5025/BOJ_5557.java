import java.io.*;
import java.util.*;

// 점화식 떠올리기 힘들었음.

// dp[i][prev+arr[i]] += dp[i-1][prev]
// dp[i][prev-arr[i]] += dp[i-1][prev]

public class BOJ_5557 {
    static int n;
    static int[] arr;
    static long[][] dp; // 숫자는 0 ~ 21
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        arr = new int[n];
        dp = new long[n][21];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        dp[0][arr[0]] = 1;
         
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] != 0) {
                    int p = j + arr[i];
                    int m = j - arr[i];
                    if (p >= 0 && p <= 20) {
                        dp[i][p] += dp[i - 1][j];
                    }
                    if (m >= 0 && m <= 20) {
                        dp[i][m] += dp[i - 1][j];
                    }
                }
            }
        }
        System.out.println(dp[n - 2][arr[n-1]]);
    }
    
}
