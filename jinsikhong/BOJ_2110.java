import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] home = new int[N+1];

        for(int i = 1; i <= N; i++){
            home[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(home); // 정렬

        int res = Integer.MIN_VALUE; // 정답
        int left = 1;
        int right = home[N] - home[1]; // 최대 거리
        int distance = 0;

        while(left <= right){ // 이분탐색
            int mid = (left+right) / 2;
            int start = home[1];
            int cnt = 1;
            for(int i = 1; i<= N; i++){
                distance = home[i] - start; // 거리 확인
                if(distance >= mid){
                    cnt++;
                    start = home[i];
                }
            }

            if(cnt >= C){
                res = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(res);



    }
}
