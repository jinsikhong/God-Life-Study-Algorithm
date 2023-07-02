import java.io.*;
import java.util.*;

// 점화식 유도 실패
/* 
dp[x][y][0] += dp [x][y - 1][0] + dp[x][y - 1][2]
dp[x][y][1] += dp [x - 1][y][1] + dp[x - 1][y][2]
dp[x][y][2] += dp [x - 1][y - 1][0] + dp[x - 1][y - 1][1] + dp[x - 1][y - 1][2]
*/
public class BOJ_17070 {
     public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] home = new int[N + 1][N + 1];
        int[][][] dp = new int[N + 1][N + 1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dp[1][2][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(home[i][j]==1)
                    continue;

                dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
                dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];

                if (home[i - 1][j] == 0 && home[i][j - 1] == 0) {
                    dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }

            }
        }

        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }

}
