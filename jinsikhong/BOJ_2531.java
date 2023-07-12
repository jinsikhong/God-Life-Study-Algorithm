import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 문제 : 회전 초밥
 * 문제 링크 : https://www.acmicpc.net/problem/2531
 * 풀이시간 : 30m
 * 풀이 방법 : 슬라이딩 윈도우
 */



public class BOJ_2531 {
    static int N;
    static int d;
    static int k;
    static int c;
    static HashSet<Integer> set;
    static int res = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int[] susi = new int[N]; // 회전 초밥
        for(int i = 0; i < N; i ++){
            susi[i] = Integer.parseInt(br.readLine());
        }

        int idx1 = 0;


        while(true){
            if(idx1 == N){ // 종료 조건
                 break;
            }
            set = new HashSet<>();
            set.add(c);
            for(int i = idx1; i < idx1 + k; i++){
                int temp = i;
                if(temp >= N){
                    temp -= N;
                }
                set.add(susi[temp]);
            }
            res = Math.max(res, set.size());
            idx1++;
        }
        System.out.println(res);


    }
}
