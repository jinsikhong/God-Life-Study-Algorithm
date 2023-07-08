import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    첫번째 풀이 방법 : dp를 1차원으로 두고 계속 갱신하는 방법으로 시도했지만 실패!
 */


public class BOJ_5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        long[][] dp = new long[N][21];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][num[0]] = 1;
        for(int i = 1; i < N-1; i++){
            for(int j = 0; j<=20; j++){
                if(j - num[i] >= 0){
                    dp[i][j] += dp[i-1][j-num[i]];
                }
                if(j + num[i] <= 20){
                    dp[i][j] += dp[i-1][j+num[i]];
                }
            }
        }
        System.out.println(dp[N-2][num[N-1]]);
    }
}
