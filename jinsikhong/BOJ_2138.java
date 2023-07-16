import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// 솔루션 참고 : 1번 스위치를 2가지 경우로 나눠서 하는 것

public class BOJ_2138 {
    static int[] arrOne;
    static int[] arrTwo;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arrOne = new int[N];
        arrTwo = new int[N];
        int[] arrAns = new int[N];
        int cnt1 = 0;
        int cnt2 = 1;
        int ans = Integer.MAX_VALUE;
        char[] tempArr1 = br.readLine().toCharArray();
        char[] tempArr2 = br.readLine().toCharArray();



        for(int i = 0; i < N; i++){
            arrOne[i] = tempArr1[i] - '0';
            arrTwo[i] = tempArr1[i] - '0';
            arrAns[i] = tempArr2[i] - '0';
        }
        arrTwo[0] = 1 - arrTwo[0];
        arrTwo[1] = 1 - arrTwo[1];

        for(int i = 1; i < N; i++){
            if(arrOne[i-1] != arrAns[i-1]){
                push(i, true);
                cnt1++;
            }
            if(arrTwo[i-1] != arrAns[i-1]){
                push(i, false);
                cnt2++;
            }
        }

        if(arrOne[N-1] != arrAns[N-1]){
            cnt1 = Integer.MAX_VALUE;
        }
        if(arrTwo[N-1] != arrAns[N-1]){
            cnt2 = Integer.MAX_VALUE;
        }
        ans = Math.min(cnt1, cnt2);
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }

    }
    static void push(int idx, boolean flag){
        if(flag){
            arrOne[idx-1] = 1-arrOne[idx-1];
            arrOne[idx] = 1-arrOne[idx];
            if(idx != N-1){
                arrOne[idx+1] = 1-arrOne[idx+1];
            }
        }else{
            arrTwo[idx-1] = 1-arrTwo[idx-1];
            arrTwo[idx] = 1-arrTwo[idx];
            if(idx != N-1){
                arrTwo[idx+1] = 1-arrTwo[idx+1];
            }

        }
    }
}
